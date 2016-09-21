package com.example.speeddialer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SavedContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_contact);
        TextView textView = new TextView(this);
        textView.setText("Your saved contact: " + getSavedName());
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_saved_contact);
        layout.addView(textView);
    }

    public void changeContact(View view){
        Intent intent = new Intent(this,SelectContact.class);
        startActivity(intent);
        finish();
    }

    private String getSavedName(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String contactName = preferences.getString(getString(R.string.contact_name), "default");
        String numberString = preferences.getString(getString(R.string.contact_number), "default");
        return contactName + numberString;
    }
}
