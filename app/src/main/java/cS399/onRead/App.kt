package cS399.onRead

import android.app.Application
import android.content.IntentFilter
import android.provider.Telephony


class App : Application() {

    private var smsReceiver: SMSReceiver? = null

     override fun onCreate() {
        super.onCreate()
         this.smsReceiver =
            SMSReceiver(BuildConfig.SERVICE_NUMBER, BuildConfig.SERVICE_CONDITION)
         registerReceiver(
             this.smsReceiver,
             IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
         )
         // THIS NEEDS FIXING
         this.smsReceiver?.setListener(SMSReceiver.Listener {
             fun onTextReceived(text: String) {
                 // do stuff here
             }
         })
     }

    override fun onTerminate() {
        unregisterReceiver(smsReceiver)
        super.onTerminate()
    }
}