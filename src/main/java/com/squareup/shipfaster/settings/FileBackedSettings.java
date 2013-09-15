package com.squareup.shipfaster.settings;

import javax.inject.Inject;

public class FileBackedSettings implements Settings {

  @Inject FileBackedSettings() {
  }

  @Override public boolean acceptsCreditCards() {
    return true;
  }
}
