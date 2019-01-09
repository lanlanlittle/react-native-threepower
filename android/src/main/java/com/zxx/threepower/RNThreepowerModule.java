
package com.zxx.threepower;

import android.Manifest;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.mdad.sdk.mduisdk.AdManager;
import com.zxx.threepower.util.PermissionUtil;

import java.util.HashMap;

public class RNThreepowerModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private String power_appId = "";
  private String power_appSecret = "";

  public RNThreepowerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;

    ApplicationInfo appInfo = null;
    try {
      appInfo = reactContext.getPackageManager().getApplicationInfo(reactContext.getPackageName(), PackageManager.GET_META_DATA);
    } catch (PackageManager.NameNotFoundException e) {
      throw new Error(e);
    }
    if (!appInfo.metaData.containsKey("power_appId")){
      throw new Error("meta-data power_appId not found in AndroidManifest.xml");
    }
    this.power_appId = String.valueOf(appInfo.metaData.get("power_appId"));

    if (!appInfo.metaData.containsKey("power_appSecret")){
      throw new Error("meta-data power_appSecret not found in AndroidManifest.xml");
    }
    this.power_appSecret = String.valueOf(appInfo.metaData.get("power_appSecret"));
  }

  @Override
  public String getName() {
    return "RNThreepower";
  }

  @Override
  public void initialize() {
    super.initialize();
  }

  @Override
  public void onCatalystInstanceDestroy() {
    super.onCatalystInstanceDestroy();
    if(AdManager.getInstance(reactContext).isInitialized()) {
      AdManager.getInstance(reactContext).onAppExit();
    }
  }

  @ReactMethod
  public void open(ReadableMap map){
    if(map == null) return;
    // 没有权限
    if(!PermissionUtil.hasPermission(reactContext, Manifest.permission.READ_PHONE_STATE) ||
            !PermissionUtil.hasPermission(reactContext, Manifest.permission.ACCESS_WIFI_STATE) ||
            !PermissionUtil.hasPermission(reactContext, Manifest.permission.CHANGE_WIFI_STATE)){
      return;
    }

    HashMap<String, Object> json = map.toHashMap();
    String userid = "0";
    if(json.get("userid") != null){
      userid = String.valueOf(json.get("userid"));
    }

    if(!AdManager.getInstance(reactContext).isInitialized()){
      AdManager.getInstance(reactContext).setProviderName(getReactApplicationContext().getPackageName() + ".com.zxx.threepower.fileprovider");
      AdManager.getInstance(reactContext).init(reactContext.getCurrentActivity(), this.power_appId, userid, this.power_appSecret);
    }
    tasks();
  }

  public void sign() {
    AdManager.getInstance(reactContext).openSignList(reactContext.getCurrentActivity());
  }

  public void common() {
    AdManager.getInstance(reactContext).openWallList(reactContext.getCurrentActivity());
  }

  public void wechat() {
    AdManager.getInstance(reactContext).openWeChatTaskList(reactContext.getCurrentActivity());
  }

  public void wechatSet() {
    AdManager.getInstance(reactContext).openWeChatTaskSetList(reactContext.getCurrentActivity());
  }

  public void task() {
    AdManager.getInstance(reactContext).openCommonTaskList(reactContext.getCurrentActivity());
  }

  public void tasks() {
//        AdManager.getInstance(this).init(this, appId, "ab", appKey);
    AdManager.getInstance(reactContext).openCommonTaskList(reactContext.getCurrentActivity(), true);
  }
}