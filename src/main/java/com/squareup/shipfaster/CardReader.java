package com.squareup.shipfaster;

import com.squareup.otto.Bus;
import java.util.Random;
import javax.inject.Inject;

public class CardReader {

  @Inject Bus bus;

  private Thread readingThread;

  public void start() {
    // Simulating reading of cards
    readingThread = new Thread() {
      @Override public void run() {
        Random random = new Random();
        while(true) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // stop() called
            return;
          }

          boolean success = random.nextInt(2) == 0;
          if (success) {
            onSwipeSuccess(Card.fakeCard());
          } else {
            onSwipeFailed();
          }
        }
      }
    };
  }

  public void stop() {
    readingThread.interrupt();
    readingThread = null;
  }

  private void onSwipeSuccess(Card card) {
    bus.postOnMainThread(new SwipeEvent(true, card));
  }

  private void onSwipeFailed() {
    bus.postOnMainThread((new SwipeEvent(false, null)));
  }
}