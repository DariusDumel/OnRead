package CS399.OnRead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.nio.channels.InterruptedByTimeoutException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startIntent = Intent(this, InputActivity::class.java).apply {
            putExtra("NAME", "Andrew CoolKid")
            putExtra("Phone Number", "619-420-1234");
        }

        startActivity(startIntent)

    }

}
