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

             contactInfo;

    EditText timeThreshold;

    EditText message;

    Button writeJsonFile;


    public void writeJson(View view) {

        contactInfo = ( ) findViewById(R.id.contactInfo);
        timeThreshold = (EditText) findViewById( R.id.timeThreshold);
        message = (EditText) findViewById( R.id.message );
        writeJsonFile = (Button) findViewById(writeJsonFile);


        writeJsonFile.setOnClickListener(new View.OnClickListener())
        {
            @Override
            public void onClick(View view)
            {
                //This might change if the contact image, name, and number are all seperate.
                save(main.Assets.bots, contactInfo.Image, contactInfo.name, contactInfo.phone, timeTreshold, message);
            }
        }

    }

    public void save(String inputFile, Image contactImage, String name , String phone, String time, String message)
    {
        try
        {
            FileOutputStream file = openFileOutput(inputFile, Context.MODE_PRIVATE);
            file.write(contactImage +  name + phone +  time + message);
            file.close();

        }
        catch( Exception exc)
        {
            Toast.makeText(writeJson.this, "Error.", Toast.LENGTH_LONG).show();
        }
    }
}
