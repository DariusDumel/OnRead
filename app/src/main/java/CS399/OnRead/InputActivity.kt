package CS399.OnRead

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.inputlayout.*


/**
 * Created by Darius Dumel on 11/24/2019. any questions? get help @ dtd66@nau.edu
 */

class InputActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inputlayout)

        if(intent.extras != null){
            contactName_view.text = intent.getStringExtra("NAME")
            phoneNumber_view.text = intent.getStringExtra("Phone Number")
        }

        submit.setOnClickListener {
            if(messageInputText.text.isNullOrBlank() && timeInputText.text.isNullOrBlank())
            {
                Toast.makeText(this, "please fill in al fields", Toast.LENGTH_SHORT).show()
            } else {
                //run WriteJSON code and then intent to manage bots page
                Toast.makeText(this, "bot created", Toast.LENGTH_SHORT).show()
            }
        }

    }
}