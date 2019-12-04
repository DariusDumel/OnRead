package CS399.OnRead

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class writeJson(view: View?) : AppCompatActivity() {
    //values from input page, id values are random and need to be changed later based on actual id values in input file
    var contactName: EditText
    var contactNumber: EditText
    var timeThreshold: EditText
    var message: EditText
    var writeJsonFile: Button
    fun save(
        inputFile: String?,
        name: String?,
        phone: String?,
        time: String?,
        message: String?
    ) {
        try {
            val file =
                openFileOutput("bots.json", Context.MODE_PRIVATE)
            file.write(contactName + contactNumber + timeTreshold + textMessage)
            file.close()
        } catch (exc: Exception) {
            Toast.makeText(this@writeJson, "Error.", Toast.LENGTH_LONG).show()
        }
    }

    init {
        contactName = findViewById<View>(R.id.contactName) as EditText
        contactNumber = findViewById<View>(R.id.contactNumber) as EditText
        timeThreshold = findViewById<View>(R.id.timeThreshold) as EditText
        message = findViewById<View>(R.id.textMessage) as EditText
        writeJsonFile = findViewById<View>(R.id.writeJsonFile) as Button
        writeJsonFile.setOnClickListener {
            save(
                "bots.json",
                contactName.text.toString(),
                contactNumber.text.toString(),
                timeTreshold,
                textMessage
            )
        }
    }
}