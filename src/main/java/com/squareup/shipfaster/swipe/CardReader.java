package com.squareup.shipfaster.swipe;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
import java.util.Random;
import javax.inject.Inject;

public class CardReader {

  @Inject Bus bus;

  private Thread readingThread;
  private final Handler handler = new Handler(Looper.getMainLooper());

  public void start() {
    stop();
    // Simulating reading of cards
    readingThread = new Thread() {
      @Override public void run() {
        Random random = new Random();
        while (true) {
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            // stop() called
            return;
          }

          boolean success = random.nextInt(2) == 0;
          if (success) {
            onSwipeSuccess(new Card("0987654321098765"));
          } else {
            onSwipeFailed();
          }
        }
      }
    };
    readingThread.start();
  }

  public void stop() {
    if (readingThread != null) {
      readingThread.interrupt();
      readingThread = null;
    }
  }

  private void onSwipeSuccess(final Card card) {
    handler.post(new Runnable() {
      @Override public void run() {
        bus.post(new SwipeEvent(true, card));
      }
    });
  }

  private void onSwipeFailed() {
    handler.post(new Runnable() {
      @Override public void run() {
        bus.post(new SwipeEvent(false, null));
      }
    });
  }
}