package com.squareup.shipfaster.common;

import com.squareup.otto.Bus;
import java.util.Set;
import javax.inject.Inject;

public class BusRegistrar {

  @Inject Bus bus;
  @Inject @RegisterOnResume Set<Object> subscribers;

  public void registerSubscribers() {
    for(Object subscriber : subscribers) {
      bus.register(subscriber);
    }
  }

  public void unregisterSubscribers() {
    for(Object subscriber : subscribers) {
      bus.unregister(subscriber);
    }
  }
}
