package com.getindata;

import java.util.Random;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.metrics.MetricGroup;
import org.apache.flink.runtime.checkpoint.CheckpointOptions;
import org.apache.flink.runtime.checkpoint.OperatorSubtaskState;
import org.apache.flink.runtime.jobgraph.OperatorID;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.graph.StreamConfig;
import org.apache.flink.streaming.api.operators.ChainingStrategy;
import org.apache.flink.streaming.api.operators.OneInputStreamOperator;
import org.apache.flink.streaming.api.operators.OperatorSnapshotResult;
import org.apache.flink.streaming.api.operators.Output;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.runtime.streamrecord.LatencyMarker;
import org.apache.flink.streaming.runtime.streamrecord.StreamRecord;
import org.apache.flink.streaming.runtime.tasks.StreamTask;


/**
 * Skeleton for a Flink Streaming Job.
 */
public class StreamingJob {

  public static void main(String[] args) throws Exception {
    // set up the streaming execution environment
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    env.addSource(new SourceFunction<Integer>() {

      private boolean isRunning = false;

      @Override
      public void run(SourceContext<Integer> sourceContext) throws Exception {
        final Random random = new Random();
        while (isRunning) {
          sourceContext.collect(random.nextInt());
        }
      }

      @Override
      public void cancel() {
        isRunning = true;
      }
    }).transform("http", BasicTypeInfo.STRING_TYPE_INFO,
        new OneInputStreamOperator<Integer, String>() {
          @Override
          public void processElement(StreamRecord<Integer> streamRecord) throws Exception {

          }

          @Override
          public void processWatermark(Watermark watermark) throws Exception {

          }

          @Override
          public void processLatencyMarker(LatencyMarker latencyMarker) throws Exception {

          }

          @Override
          public void setup(StreamTask<?, ?> streamTask, StreamConfig streamConfig,
              Output<StreamRecord<String>> output) {

          }

          @Override
          public void open() throws Exception {

          }

          @Override
          public void close() throws Exception {

          }

          @Override
          public void dispose() throws Exception {

          }

          @Override
          public OperatorSnapshotResult snapshotState(long l, long l1,
              CheckpointOptions checkpointOptions)
              throws Exception {
            return null;
          }

          @Override
          public void initializeState(OperatorSubtaskState operatorSubtaskState) throws Exception {

          }

          @Override
          public void notifyOfCompletedCheckpoint(long l) throws Exception {

          }

          @Override
          public void setKeyContextElement1(StreamRecord<?> streamRecord) throws Exception {

          }

          @Override
          public void setKeyContextElement2(StreamRecord<?> streamRecord) throws Exception {

          }

          @Override
          public ChainingStrategy getChainingStrategy() {
            return null;
          }

          @Override
          public void setChainingStrategy(ChainingStrategy chainingStrategy) {

          }

          @Override
          public MetricGroup getMetricGroup() {
            return null;
          }

          @Override
          public OperatorID getOperatorID() {
            return null;
          }
        });

    // execute program
    env.execute("Flink Streaming Java API Skeleton");
  }
}
