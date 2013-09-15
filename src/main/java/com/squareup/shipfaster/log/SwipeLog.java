package com.squareup.shipfaster.log;

public class SwipeLog implements Log {
  private boolean successfulSwipe;

  public SwipeLog(boolean successfulSwipe) {
    this.successfulSwipe = successfulSwipe;
  }

  @Override public String getMessage() {
    return "Swipe success: " + successfulSwipe;
  }

}
