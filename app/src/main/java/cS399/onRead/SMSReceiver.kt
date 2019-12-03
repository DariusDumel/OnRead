package cS399.onRead

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.telephony.SmsMessage.createFromPdu


/**
 * A broadcast receiver who listens for incoming SMS
 */

@Suppress("DEPRECATION")
class SMSReceiver(
    private val serviceProviderNumber: String,
    private val serviceProviderSmsCondition: String
) : BroadcastReceiver() {
    private var listener: Listener? = null

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            var smsSender : String? = null
            var smsBody : String? = null
            for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                smsSender = smsMessage.displayOriginatingAddress
                smsBody += smsMessage.messageBody
            }
            val smsBundle = intent.extras
            if (smsBundle != null) {
                val pdus = smsBundle.get("pdus") as Array<*>
                val messages = arrayOfNulls<SmsMessage>(pdus.size)
                for (i in messages.indices) {
                    messages[i] = createFromPdu(pdus[i] as ByteArray)
                    smsBody += messages[i]?.messageBody
                }
                smsSender = messages[0]?.displayOriginatingAddress
            }

            if (smsBody != null) {
                when {
                    smsSender == serviceProviderNumber && smsBody.startsWith(serviceProviderSmsCondition) && listener != null -> listener!!.onTextReceived(smsBody)
                }
            }
        }
    }

    internal fun setListener(listener: Listener) {
        this.listener = listener
    }

    internal interface Listener {
        fun onTextReceived(text: String)
    }

    companion object {

        private const val TAG = "SMSReceiver"
    }
}




