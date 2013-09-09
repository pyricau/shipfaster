package com.squareup.shipfaster.log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.shipfaster.SwipeEvent;
import javax.inject.Inject;

public class EventLogger {

  private final FileLogger fileLogger;

  @Inject EventLogger(FileLogger fileLogger, Bus bus) {
    this.fileLogger = fileLogger;
    bus.register(this);
  }

  @Subscribe public void logSwipe(SwipeEvent event) {
    fileLogger.add(new SwipeLog(event.successfulSwipe));
  }
}