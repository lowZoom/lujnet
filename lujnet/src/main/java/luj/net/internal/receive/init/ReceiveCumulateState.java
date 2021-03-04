package luj.net.internal.receive.init;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ReceiveCumulateState {

  public ByteToMessageDecoder.Cumulator getCumulator() {
    return cumulator;
  }

  public int getDiscardAfterReads() {
    return discardAfterReads;
  }

  public ByteBuf getCumulation() {
    return cumulation;
  }

  public void setCumulation(ByteBuf cumulation) {
    this.cumulation = cumulation;
  }

  public int getNumReads() {
    return numReads;
  }

  public void setNumReads(int numReads) {
    this.numReads = numReads;
  }

  ByteBuf cumulation;
  private ByteToMessageDecoder.Cumulator cumulator = ByteToMessageDecoder.MERGE_CUMULATOR;
  private boolean first;

  private int discardAfterReads = 16;
  private int numReads;

}
