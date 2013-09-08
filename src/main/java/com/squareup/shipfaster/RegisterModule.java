package com.squareup.shipfaster;

import dagger.Module;
import dagger.Provides;

@Module(injects = RegisterActivity.class)
public class RegisterModule {

  @Provides Settings provideSettings(FileBackedSettings settings) {
    return settings;
  }
}
