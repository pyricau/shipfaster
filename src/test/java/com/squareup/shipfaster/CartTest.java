package com.squareup.shipfaster;

import dagger.ObjectGraph;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CartTest {

  @Inject Cart cart;
  @Inject Settings settings;

  @Before public void setUp() {
    ObjectGraph.create(new TestModule()).inject(this);
    //settings = mock(Settings.class);
    //cart = new Cart(settings);
  }

  @Test public void can_buy_banana_with_card() {
    when(settings.acceptsCreditCards()).thenReturn(true);

    cart.addItem(Item.newBanana());
    assertThat(cart.canSwipeCard()).isTrue();
  }

}
