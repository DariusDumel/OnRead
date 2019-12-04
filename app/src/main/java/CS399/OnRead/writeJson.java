package CS399.OnRead;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class writeJson {


    //values from input page, id values are random and need to be changed later based on actual id values in input file

    EditText contactName;

    EditText contactNumber;

    EditText timeThreshold;

    EditText message;

    Button writeJsonFile;


    public void writeJson(View view) {

        contactName = ( EditText ) findViewById(R.id.contactName);
        contactNumber = (EditText) findViewById( R.id.contactNumber)
        timeThreshold = (EditText) findViewById( R.id.timeThreshold);
        message = (EditText) findViewById( R.id.message );
        writeJsonFile = (Button) findViewById(R.id.writeJsonFile);


        writeJsonFile.setOnClickListener(new View.OnClickListener())
        {
            @Override
            public void onClick(View view)
            {

                save(main.Assets.bots, contactName, contactNumber, timeTreshold, message);
            }
        }

    }

    public void save(String inputFile, String name , String phone, String time, String message)
    {
        try
        {
            FileOutputStream file = openFileOutput("bots.json", Context.MODE_PRIVATE);
            file.write(name + phone +  time + message);
            file.close();

        }
        catch( Exception exc)
        {
            Toast.makeText(writeJson.this, "Error.", Toast.LENGTH_LONG).show();
        }
    }
}
