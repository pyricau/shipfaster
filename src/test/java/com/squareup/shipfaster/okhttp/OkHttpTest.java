package com.squareup.shipfaster.okhttp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class OkHttpTest {

  /**
   * This test relies on the internals of RetroFit. It's not really needed, but it demonstrates
   * that RetroFit uses OkHttp when it's in the classpath.
   */
  @Test public void retrofit_uses_okhttp() throws Exception {
    Field clientProviderField = RestAdapter.class.getDeclaredField("clientProvider");
    clientProviderField.setAccessible(true);
    Method get = Client.Provider.class.getDeclaredMethod("get");

    RestAdapter adapter = new RestAdapter.Builder().setServer("https://squareup.com").build();
    Object clientProvider = clientProviderField.get(adapter);
    Object client = get.invoke(clientProvider);

    assertThat(client).isInstanceOf(OkClient.class);
  }
}
