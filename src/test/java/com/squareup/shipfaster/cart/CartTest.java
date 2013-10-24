package com.squareup.shipfaster.cart;

import android.app.Activity;
import android.content.Intent;
import com.squareup.shipfaster.payment.PaymentActivity;
import com.squareup.shipfaster.settings.Settings;
import com.squareup.shipfaster.swipe.Card;
import com.squareup.shipfaster.swipe.SwipeEvent;
import javax.inject.Provider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class CartTest {

  Cart cart;
  Settings settings;
  Activity activity;

  Provider<Activity> activityProvider = new Provider<Activity>() {
    @Override public Activity get() {
      return activity;
    }
  };

  @Before public void setUp() {
    activity = new Activity();
    settings = mock(Settings.class);
    cart = new Cart(settings, activityProvider);
  }

  @Test public void can_swipe_card_when_accepts_credit_cards() {
    when(settings.acceptsCreditCards()).thenReturn(true);

    cart.addItem(Item.newFakeItem());
    assertThat(cart.canSwipeCard()).isTrue();
  }

  @Test public void pay_on_swipe_success() {
    prepareCanSwipe();

    cart.onSwipe(new SwipeEvent(true, Card.fakeCard()));

    Intent intent = Robolectric.shadowOf(activity).peekNextStartedActivity();
    String startedActivityName = intent.getComponent().getClassName();
    assertThat(startedActivityName).isEqualTo(PaymentActivity.class.getName());
  }

  @Test public void no_pay_on_swipe_failed() {
    prepareCanSwipe();

    cart.onSwipe(new SwipeEvent(false, null));

    assertThat(Robolectric.shadowOf(activity).peekNextStartedActivity()).isNull();
  }

  private void prepareCanSwipe() {
    when(settings.acceptsCreditCards()).thenReturn(true);
    cart.addItem(Item.newFakeItem());
  }
}
