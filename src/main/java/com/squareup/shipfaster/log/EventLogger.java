package com.squareup.shipfaster.log;

import com.squareup.otto.Subscribe;
import com.squareup.shipfaster.swipe.SwipeEvent;
import javax.inject.Inject;

public class EventLogger {

  @Inject FileLogger fileLogger;

  @Subscribe public void logSwipe(SwipeEvent event) {
    fileLogger.add(new SwipeLog(event.successfulSwipe));
  }
}