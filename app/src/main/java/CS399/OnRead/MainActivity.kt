package CS399.OnRead

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    //One of the problems is if everything will go the the upper left bc no constraints
    //have not considered image views yet
    //Dont know how to get num from contacts
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addButton = findViewById<Button>(R.id.add)
        addButton.setOnClickListener{
            addCard(/* Phone Number from contacts?*/ null)
        }
        val deleteButton = findViewById<Button>(R.id.delete)
        deleteButton.setOnClickListener{
            deleteCard(/* Phone Number from contacts?*/ )
        }

    }

    //The constraintLayout cardviews are in
    val cardViewConstraint = findViewById<ConstraintLayout>(R.id.cardViewConstraint)

    fun addCard(phoneNum :Integer) {
        //Phone Number from contacts
        //Creates and sets details for a cardview
        //trying to set the id of the card created to the phone number
        //details for text of textview, it is a string of the phone number
        //adds the textview into the cardview
        //adds the cardview to the constraint layout
        val card = CardView(this)
        card.setCardElevation(30.toFloat())
        card.id = phoneNum.toInt()
        card.setRadius(25.toFloat())
        card.setCardBackgroundColor(Color.MAGENTA)
        val numberText = TextView(this)
        numberText.setText(phoneNum.toString())
        numberText.setTextSize(25.toFloat())
        card.addView(numberText)
        cardViewConstraint.addView(card)
    }
    //Pass in the phone number
    //the card with the id that is the phone number will be deleted from the constraint layout
    //dont know why cardIdis red
    fun deleteCard(phoneNum :Integer)
    {
        val cardId = phoneNum.toInt()
        val cardWithNum = findViewById<CardView>(R.id.cardId)
        cardViewConstraint.removeView(cardWithNum)
    }

}
