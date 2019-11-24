package CS399.OnRead

import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.telephony.SmsManager

import java.util.ArrayList

import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.SEND_SMS

class contactActivity : AppCompatActivity() {


    private var phones: ArrayList<String>? = null

    private fun mayRequestSMS(): Boolean {

        if (checkSelfPermission(SEND_SMS) === PackageManager.PERMISSION_GRANTED) {
            return true
        }


        if (shouldShowRequestPermissionRationale(SEND_SMS)) {
            requestPermissions(arrayOf(SEND_SMS), REQUEST_SMS)
        } else {
            return false
        }

        return false
    }

    private fun mayRequestContacts(): Boolean {

        Log.w("tag", "mayRequestContacts")

        if (checkSelfPermission(READ_CONTACTS) === PackageManager.PERMISSION_GRANTED) {
            Log.w("tag", "checkSelfPermission = true")

            return true
        }
        Log.w("tag", "checkSelfPermission = false")


        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Log.w("tag", "shouldShowRequestPermissionRationale = true")

            requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS)
        } else {
            Log.w("tag", "shouldShowRequestPermissionRationale = false")

            return false
        }

        Log.w("tag", "mayRequestContacts = false")

        return false
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts()
            }
        }
    }


    fun loadContacts() {
        if (!mayRequestContacts()) {
            return
        }
        val ContentURI = ContactsContract.Contacts.CONTENT_URI
        val resolver = getContentResolver()
        val myCursor = resolver.query(ContentURI, null, null, null, null)

        val myList = findViewById(R.id.contacts) as ListView
        val names = ArrayList<String>()
        phones = ArrayList()

        if (myCursor!!.getCount() < 1) {
            return
        }

        while (myCursor!!.moveToNext()) {
            val name = myCursor!!.getString(myCursor!!.getColumnIndex("display_name"))
            val ID = myCursor!!.getString(myCursor!!.getColumnIndex("_id"))

            if (myCursor!!.getString(myCursor!!.getColumnIndex("has_phone_number")) == "0") {
                continue
            }

            val PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            val phoneCursor =
                resolver.query(PhoneCONTENT_URI, null, "contact_id = ?", arrayOf<String>(ID), null)

            phoneCursor!!.moveToNext()
            val phoneNumber = phoneCursor!!.getString(phoneCursor!!.getColumnIndex("data1"))
            phoneCursor!!.close()

            names.add(name)
            phones!!.add(phoneNumber)
            Log.w("tag", name + " added")
        }
        myCursor!!.close()
        val adapter =
            ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names)
        myList.adapter = adapter

        val listner = ListClickListner()
        myList.onItemClickListener = listner
    }

    internal inner class ListClickListner : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

//            check to see if contact is duplicated
            if (!checkDuplicateContacts()) {
                val Toast = Toast.makeText(
                    getApplicationContext(),
                    "This contact already exists. Please edit this contact on the Manage Page or select another contact.",
                    Toast.LENGTH_LONG
                )
                return
            }

            val listView = parent.findViewById(R.id.contacts) as ListView
            val nameValue = listView.getItemAtPosition(position) as String

            //Send the user to Darius's layout file
            //Do this by creating an instance of his layout and have the name, phone number, and photo as parameters

            //parameters for name, phone number, **need to research how to get the image**
            (nameValue + ": " + phones!![position],  image      )



        }

        fun checkDuplicateContacts(): Boolean{
            return true
        }
    }

    companion object {

        private val REQUEST_READ_CONTACTS = 444
        private val REQUEST_SMS = 333
    }


}
