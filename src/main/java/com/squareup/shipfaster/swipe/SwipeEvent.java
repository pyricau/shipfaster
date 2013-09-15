package com.squareup.shipfaster.swipe;


public class SwipeEvent {

  public final boolean successfulSwipe;
  public final Card card;

  public SwipeEvent(boolean successfulSwipe, Card card) {
    this.successfulSwipe = successfulSwipe;
   this.card = card;
  }

}