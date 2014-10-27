package com.edh;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

/**
 * Created by soloman.weng on 23/10/14.
 */
public class StormTopology {

  public static void main(String[] args) throws Exception {
    TopologyBuilder builder = new TopologyBuilder();
    builder.setSpout("event_spout", new RandomEventSpout(), 5);
    builder.setBolt("event_count_bolt", new EventCountBolt(), 3).shuffleGrouping("event_spout");

    Config conf = new Config();
    conf.setDebug(true);

    conf.setMaxTaskParallelism(3);

    if (args != null && args.length > 0) {
      conf.setNumWorkers(3);
      StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
    } else{
      LocalCluster cluster=new LocalCluster();
      cluster.submitTopology("event_count",conf,builder.createTopology());

      Thread.sleep(10000);
      cluster.shutdown();
    }
  }

}
