package cn.org.bjca.footstone.usercenter.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/16
 * @since 1.0
 */
public class SnowFlake {

  /*
   * bits allocations for timeStamp, datacenterId, workerId and sequence
   */
  private static SnowFlake sf;

  static {
    String ip = HostAddress.getIp();
    int last = Integer.parseInt(ip.substring(ip.lastIndexOf('.') + 1, ip.length()));
    sf = new SnowFlake(last);
  }


  private final long unusedBits = 1L;
  /**
   * 'time stamp' here is defined as the number of millisecond that have elapsed since the {@link
   * #epoch} given by users on instance initialization
   */
  private final long timestampBits = 41L;
  private final long workerIdBits = 10L;
  private final long sequenceBits = 12L;

  /*
   * max values of timeStamp, workerId, datacenterId and sequence
   */
  private final long maxWorkerId = ~(-1L << workerIdBits); // 2^5-1
  private final long maxSequence = ~(-1L << sequenceBits); // 2^12-1

  /**
   * left shift bits of timeStamp, workerId and datacenterId
   */
  private final long timestampShift = sequenceBits + workerIdBits;
  private final long datacenterIdShift = sequenceBits + workerIdBits;
  private final long workerIdShift = sequenceBits;

  /*
   * object status variables
   */

  /**
   * reference material of 'time stamp' is '2018-01-01'. its value can't be modified after
   * initialization.
   */
  private final long epoch = 1514736060000L;


  /**
   * machine or process number, its value can't be modified after initialization. <p> max: 2^5-1
   * range: [0,31]
   */
  private final long workerId;

  /**
   * the unique and incrementing sequence number scoped in only one period/unit (here is ONE
   * millisecond). its value will be increased by 1 in the same specified period and then reset to 0
   * for next period. <p> max: 2^12-1 range: [0,4095]
   */
  private long sequence = 0L;

  /**
   * the time stamp last snowflake ID generated
   */
  private long lastTimestamp = -1L;

  /**
   * generate an unique and incrementing id
   *
   * @return id
   */
  public synchronized long nextId() {
    long currTimestamp = timestampGen();

    if (currTimestamp < lastTimestamp) {
      throw new IllegalStateException(
          String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
              lastTimestamp - currTimestamp));
    }

    if (currTimestamp == lastTimestamp) {
      sequence = (sequence + 1) & maxSequence;
      if (sequence == 0) { // overflow: greater than max sequence
        currTimestamp = waitNextMillis(currTimestamp);
      }

    } else { // reset to 0 for next period/millisecond
      sequence = 0L;
    }

    // track and memo the time stamp last snowflake ID generated
    lastTimestamp = currTimestamp;

    return ((currTimestamp - epoch) << timestampShift) |
        (workerId << workerIdShift) | // new line for nice looking
        sequence;
  }

  /**
   * @param workerId machine or process number, value range: [0,31]
   */
  public SnowFlake(long workerId) {
    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException(
          String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
    }

    this.workerId = workerId;
  }

  /**
   * track the amount of calling {@link #waitNextMillis(long)} method
   */
  private final AtomicLong waitCount = new AtomicLong(0);

  /**
   * running loop blocking until next millisecond
   *
   * @param currTimestamp current time stamp
   * @return current time stamp in millisecond
   */
  protected long waitNextMillis(long currTimestamp) {
    waitCount.incrementAndGet();
    while (currTimestamp <= lastTimestamp) {
      currTimestamp = timestampGen();
    }
    return currTimestamp;
  }

  /**
   * get current time stamp
   *
   * @return current time stamp in millisecond
   */
  protected long timestampGen() {
    return System.currentTimeMillis();
  }

  public static long next() {
    return sf.nextId();
  }

}
