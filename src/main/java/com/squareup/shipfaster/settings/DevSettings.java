package com.squareup.shipfaster.settings;

import javax.inject.Inject;

public class DevSettings implements Settings {

  @Inject DevSettings() {
  }

  @Override public boolean acceptsCreditCards() {
    return false;
  }
}
