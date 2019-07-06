package com.signalone.example;

import android.support.v4.app.NotificationCompat;

import com.signalone.NotificationExtenderService;
import com.signalone.OSNotificationPayload;
import com.signalone.OSNotificationReceivedResult;

import java.math.BigInteger;

public class NotificationExtenderServiceTest extends NotificationExtenderService {

   @Override
   protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
      DebuggingHelper.printObject(notification);
      if (notification.payload.actionButtons != null) {
         for(OSNotificationPayload.ActionButton button : notification.payload.actionButtons) {
           // System.out.println("button:");
            DebuggingHelper.printObject(button);
         }
      }

      OverrideSettings overrideSettings = new OverrideSettings();

      overrideSettings.extender = new NotificationCompat.Extender() {
         @Override
         public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
            return builder.setColor(new BigInteger("FF00FF00", 16).intValue());
         }
      };

      displayNotification(overrideSettings);

      return true;
   }
}
