package com.squareup.shipfaster.common;

import com.squareup.shipfaster.settings.DevSettings;
import com.squareup.shipfaster.settings.Settings;
import dagger.Module;
import dagger.Provides;

@Module(includes = ProdAppModule.class, overrides = true)
public class DevAppModule {

  @Provides Settings provideSettings(DevSettings settings) {
    return settings;
  }
}
