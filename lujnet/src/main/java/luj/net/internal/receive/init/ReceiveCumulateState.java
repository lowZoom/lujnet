package luj.net.internal.receive.init;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ReceiveCumulateState {

  public ByteToMessageDecoder.Cumulator getCumulator() {
    return ByteToMessageDecoder.MERGE_CUMULATOR;
  }

  public int getDiscardAfterReads() {
    return 16;
  }

  public ByteBuf getCumulation() {
    return _cumulation;
  }

  public void setCumulation(ByteBuf cumulation) {
    _cumulation = cumulation;
  }

  public int getNumReads() {
    return _numReads;
  }

  public void setNumReads(int numReads) {
    _numReads = numReads;
  }

  private ByteBuf _cumulation;

  private int _numReads;
}
