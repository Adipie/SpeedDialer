package com.example.speeddialer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SelectContact extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contact);
        setSpinnerChoices();
    }


    private void setSpinnerChoices(){
        Spinner spinner = (Spinner) findViewById(R.id.contact_spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, getContactList());
        spinner.setAdapter(adapter);
    }

    private String[] getContactList(){
        ArrayList<String> contacts = new ArrayList<String>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext()){
            contacts.add(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
        }
        return contacts.toArray(new String[contacts.size()]);
    }

    public void saveContact(View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getString(R.string.contact_name), getChosenContactName());
        editor.putString(getString(R.string.contact_number), getChosenContactNumber());
        editor.commit();
        startService(new Intent(this,UpdateService.class));
        Intent intent = new Intent(this,SavedContact.class);
        startActivity(intent);
        finish();
    }


    private String getChosenContactName(){
        Spinner spinner = (Spinner) findViewById(R.id.contact_spinner);
        return spinner.getSelectedItem().toString();
    }

    private String getChosenContactNumber(){
        String phoneNumber = null;
        Spinner spinner = (Spinner) findViewById(R.id.contact_spinner);
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" like'%" + spinner.getSelectedItem().toString() +"%'";
        String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, selection, null, null);
        if (c.moveToFirst()) {
            phoneNumber = c.getString(0);
        }
        c.close();
        return phoneNumber;
    }



}
