
package com.zxx.threepower;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNThreepowerModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNThreepowerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNThreepower";
  }
}