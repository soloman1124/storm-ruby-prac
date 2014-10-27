package com.edh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * Created by soloman.weng on 23/10/14.
 */
public class Event {
  private final static ObjectMapper jsonMapper = new ObjectMapper();

  private Serializable payload;
  private String name;
  private long time;

  public Event(String name) {
    this.name = name;
    this.time = System.currentTimeMillis();
  }

  public Serializable getPayload() {
    return this.payload;
  }

  public String getName() {
    return this.name;
  }

  public long getTime() {
    return this.time;
  }

  public void setPayload(Serializable payload){
    this.payload = payload;
  }

  public String toJsonString() throws JsonProcessingException {
    return jsonMapper.writeValueAsString(this);
  }
}
