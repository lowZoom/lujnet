package luj.net.internal.receive.read;

import static com.google.common.base.Preconditions.checkState;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.StringUtil;
import luj.net.internal.receive.frame.FrameReceiveInvoker;
import luj.net.internal.receive.init.FrameReceiveState;
import luj.net.internal.receive.init.ReceiveCumulateState;

public enum ReceiveChannelReader {
  GET;

  /**
   * @see ByteToMessageDecoder#channelRead
   */
  public void read(ChannelHandlerContext ctx, ByteBuf inBuf, FrameReceiveState state,
      Object bindParam) throws Exception {
    ReceiveCumulateState cumulating = state.getCumulateState();
    ByteToMessageDecoder.Cumulator cumulator = cumulating.getCumulator();
    int discardAfterReads = cumulating.getDiscardAfterReads();

    ByteBuf cumulation = cumulating.getCumulation();
    int numReads = cumulating.getNumReads();

    boolean first = cumulation == null;
    try {
      cumulation = cumulator.cumulate(ctx.alloc(),
          first ? Unpooled.EMPTY_BUFFER : cumulation, inBuf);
      callDecode(ctx, cumulation, state, bindParam);
//    } catch (DecoderException e) {
//      throw e;
//    } catch (Exception e) {
//      throw new DecoderException(e);
    } finally {
      if (cumulation != null && !cumulation.isReadable()) {
        numReads = 0;
        cumulation.release();
        cumulation = null;
      } else if (++numReads >= discardAfterReads) {
        // We did enough reads already try to discard some bytes so we not risk to see a OOME.
        // See https://github.com/netty/netty/issues/4275
        numReads = 0;
        discardSomeReadBytes(cumulation, first);
      }

      cumulating.setNumReads(numReads);
      cumulating.setCumulation(cumulation);
    }
  }

  private void callDecode(ChannelHandlerContext ctx, ByteBuf in, FrameReceiveState state,
      Object bindParam) throws Exception {
//    LOG.debug("总收到{}：{}", state.getNextReceiver(), in.readableBytes());

    while (in.isReadable()) {
      int oldInputLength = in.readableBytes();
      checkState(oldInputLength > 0);
      boolean handled = decode(in, state, bindParam);

      // Check if this handler was removed before continuing the loop.
      // If it was removed, it is not safe to continue to operate on the buffer.
      //
      // See https://github.com/netty/netty/issues/1664
      if (ctx.isRemoved()) {
        break;
      }

      if (!handled) {
        if (oldInputLength == in.readableBytes()) {
          break;
        }
        continue;
      }

      if (oldInputLength == in.readableBytes()) {
        throw new DecoderException(StringUtil.simpleClassName(getClass()) +
            ".decode() did not read anything but decoded a message.");
      }
    }
  }

  private void discardSomeReadBytes(ByteBuf cumulation, boolean first) {
    if (cumulation != null && !first && cumulation.refCnt() == 1) {
      // discard some bytes if possible to make more room in the
      // buffer but only if the refCnt == 1  as otherwise the user may have
      // used slice().retain() or duplicate().retain().
      //
      // See:
      // - https://github.com/netty/netty/issues/2327
      // - https://github.com/netty/netty/issues/1764
      cumulation.discardSomeReadBytes();
    }
  }

  /**
   * @see io.netty.handler.codec.LengthFieldBasedFrameDecoder#decode(ChannelHandlerContext, ByteBuf)
   */
  private boolean decode(ByteBuf in, FrameReceiveState state, Object bindParam) throws Exception {
    int readableBytes = in.readableBytes();
    int bytesToWait = state.getByteCountToWait();
    if (readableBytes < bytesToWait) {
      return false;
    }

    int readerIndex = in.readerIndex();
    int actualFrameLength = (bytesToWait <= 0) ? readableBytes : bytesToWait;

    ByteBuf frame = extractFrame(in, readerIndex, actualFrameLength);
    in.readerIndex(readerIndex + actualFrameLength);

    try {
//      LOG.debug("一帧{}：{}", state.getNextReceiver(), actualFrameLength);
      FrameReceiveInvoker.GET.invoke(frame, state, bindParam);

    } finally {
      ReferenceCountUtil.release(frame);
    }

    return true;
  }

  private ByteBuf extractFrame(ByteBuf buffer, int index, int length) {
    return buffer.retainedSlice(index, length);
  }

//  private static final Logger LOG = LoggerFactory.getLogger(ReceiveChannelReader.class);
}
