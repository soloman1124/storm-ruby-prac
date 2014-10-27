package com.edh;

import backtype.storm.task.ShellBolt;
import backtype.storm.topology.*;
import backtype.storm.tuple.Fields;

import java.util.Map;

/**
 * Created by soloman.weng on 23/10/14.
 */
public class EventCountBolt extends ShellBolt implements IRichBolt {

  public EventCountBolt() {
    super("ruby", "event_count.rb");
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("event", "count"));
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}

