package com.example.speeddialer;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        // if a contact is not saved yet, open SelectContact activity
        //TODO change true to actual condition
        if(true){
            intent = new Intent(this,SelectContact.class);
        }
        else {
            intent = new Intent(this,SavedContact.class);
        }
        startActivity(intent);
        finish();
    }

}
