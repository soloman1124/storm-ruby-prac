package com.edh;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMap;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Random;

/**
 * Created by soloman.weng on 23/10/14.
 */
public class RandomEventSpout extends BaseRichSpout {

  private static final Logger LOG = Logger.getLogger(RandomEventSpout.class);

  private SpoutOutputCollector collector;

  private Random random;


  @Override
  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    outputFieldsDeclarer.declare(new Fields("event"));
  }

  @Override
  public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
    collector = spoutOutputCollector;
    random = new Random();
  }

  @Override
  public void nextTuple() {
    Utils.sleep(1000);
    try {
      collector.emit(new Values(randomEvent().toJsonString()));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      LOG.error(e.getMessage());
    }
  }

  private Event randomEvent(){
    String[] event_names = new String[]{
        "test_page_created", "test_user_signed_up", "test_page_viewed"
    };
    Event event = new Event(event_names[random.nextInt(event_names.length)]);
    event.setPayload(ImmutableMap.of("value", random.nextInt()));

    return event;
  }
}
