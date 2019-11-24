package CS399.OnRead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        only changed it to contacts_layout to test. Should typically go to activity_main
        setContentView(R.layout.contacts_layout)

        val ContactActivity = contactActivity()
        ContactActivity.loadContacts()
    }
}
