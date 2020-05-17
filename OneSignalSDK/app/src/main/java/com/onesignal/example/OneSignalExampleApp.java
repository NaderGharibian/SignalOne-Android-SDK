/**
 * Modified MIT License
 *
 * Copyright 2016 OneSignal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * 1. The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * 2. All copies of substantial portions of the Software may only be used in connection
 * with services provided by OneSignal.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.onesignal.example;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

public class OneSignalExampleApp extends Application {

   private static final String SHARED_PREFS_OS_APP_ID = "SHARED_PREFS_OS_APP_ID";

   @Override
   public void onCreate() {
      super.onCreate();

      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
              .detectAll()
              .penaltyLog()
              .build());

      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
              .detectAll()
              .penaltyLog()
              .build());

      OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

      String currentAppId = getOneSignalAppId(this);
      if (currentAppId == null)
         setOneSignalAppId(this, "0ba9731b-33bd-43f4-8b59-61172e27447d");

      OneSignal.init(
         this,
         "1234567", // This is ignored, dashboard value will be used.
         getOneSignalAppId(this),
         new ExampleNotificationOpenedHandler(),
         new ExampleNotificationReceivedHandler()
      );
   }

   public static void setOneSignalAppId(@NonNull Context context, @NonNull String id) {
      SharedPreferences sharedPref = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPref.edit();
      editor.putString(SHARED_PREFS_OS_APP_ID, id);
      editor.commit();

      OneSignal.init(context, null, id);
   }

   @Nullable
   public static String getOneSignalAppId(@NonNull Context context) {
      SharedPreferences sharedPref = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE);
      return sharedPref.getString(SHARED_PREFS_OS_APP_ID, null);
   }

   private class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
      /**
       * Callback to implement in your app to handle when a notification is received while your app running
       *  in the foreground or background.
       *
       *  Use a NotificationExtenderService instead to receive an event even when your app is closed (not 'forced stopped')
       *     or to override notification properties.
       *
       * @param notification Contains information about the notification received.
       */
      @Override
      public void notificationReceived(OSNotification notification) {
         Log.w("OneSignalExample", "notificationReceived!!!!!!");
         DebuggingHelper.printObject(notification);
         DebuggingHelper.printObject(notification.payload);
      }
   }

   private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
      /**
       * Callback to implement in your app to handle when a notification is opened from the Android status bar or in app alert
       *
       * @param openedResult Contains information about the notification opened and the action taken on it.
       */
      @Override
      public void notificationOpened(OSNotificationOpenResult openedResult) {
         Log.w("OneSignalExample", "notificationOpened!!!!!!");
         DebuggingHelper.printObject(openedResult.action);
         DebuggingHelper.printObject(openedResult.notification);
      }
   }
}