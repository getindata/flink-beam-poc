package com.getindata;

import java.util.Random;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;


/**
 * Skeleton for a Flink Streaming Job.
 */
public class StreamingJob {

  public static void main(String[] args) throws Exception {
    // set up the streaming execution environment
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    env.addSource(new SourceFunction<Integer>() {

      private boolean isRunning = true;

      @Override
      public void run(SourceContext<Integer> sourceContext) throws Exception {
        final Random random = new Random();
        while (isRunning) {
          sourceContext.collect(random.nextInt());
          Thread.sleep(200);
        }
      }

      @Override
      public void cancel() {
        isRunning = false;
      }
    }).transform("http", BasicTypeInfo.STRING_TYPE_INFO, new HttpOneInputStreamOperator<>())
        .print();

    // execute program
    env.execute("Flink Streaming Java API Skeleton");
  }
}
