<p align="center">
  <img src="https://signalone.app/assets/common/logo_signalone_color.png"/>
</p>

### SignalOne Android Push Notification Plugin
[![](https://jitpack.io/v/NaderGharibian/SignalOne-Android-SDK.svg)](https://jitpack.io/#NaderGharibian/SignalOne-Android-SDK)



---

[SignalOne.app](https://signalone.app/) is a free push notification service for mobile apps. This plugin makes it easy to integrate your native Android or Amazon app with SignalOne.



How to Use
-------------

#Android


 Include the SignalOne dependency in app's **build.gradle**
```gradle

android {

    defaultConfig {
        // TODO: Please update the OneSignal ID below to yours!
        manifestPlaceholders = [onesignal_app_id: '********-****-****-****-************',
                                // Project number pulled from dashboard, local value is ignored.
                                onesignal_google_project_number: 'REMOTE']
    }
}

                                
dependencies {
     // SignalOne SDK
     implementation 'com.github.NaderGharibian:SignalOne-Android-SDK:1.0.5'

     // Required for geotagging
    implementation 'com.google.android.gms:play-services-location:15.0.1'
}


allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}


```


---------------------------------------------------------------------------------------------------------------
#Rest Api C#
``` C#
            var request = WebRequest.Create("https://signalone.app/api/v1/notifications") as HttpWebRequest;

            request.KeepAlive = true;
            request.Method = "POST";
            request.ContentType = "application/json; charset=utf-8";

            request.Headers.Add("authorization", "Basic Zgg*****************************************jA5");

            byte[] byteArray = Encoding.UTF8.GetBytes("{"
                                                    + "\"app_id\": \"********-****-****-****-************\","
                                                    + "\"contents\": {\"en\": \"test rest api\"},"
                                                    + "\"included_segments\": [\"All\"]}");

            string responseContent = null;

            try
            {
                using (var writer = request.GetRequestStream())
                {
                    writer.Write(byteArray, 0, byteArray.Length);
                }

                using (var response = request.GetResponse() as HttpWebResponse)
                {
                    using (var reader = new StreamReader(response.GetResponseStream()))
                    {
                        responseContent = reader.ReadToEnd();
                    }
                }
            }
            catch (WebException ex)
            {
                System.Diagnostics.Debug.WriteLine(ex.Message);
                System.Diagnostics.Debug.WriteLine(new StreamReader(ex.Response.GetResponseStream()).ReadToEnd());
            }

            System.Diagnostics.Debug.WriteLine(responseContent);
```

