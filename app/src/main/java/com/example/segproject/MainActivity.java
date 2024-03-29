package com.example.segproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button goBackBtn, searchType, searchName, searchClub;

    TextView searchBar;

    ClubDBHandler cdb;

    EventDBHandler edb;
    EventTypeDBHandler etdb;

    AccountDBHandler adb;


    ListView finder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goBackBtn = findViewById(R.id.goBack2);
        searchType = findViewById(R.id.eventType);
        searchName = findViewById(R.id.eventname);
        searchClub = findViewById(R.id.clubname);
        searchBar = findViewById(R.id.search);
        finder = findViewById(R.id.finder);
        cdb = new ClubDBHandler(this);
        etdb = new EventTypeDBHandler(this);
        edb = new EventDBHandler(this);
        adb = new AccountDBHandler(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // changing to search bar

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        // search by event type
        searchType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), UserEventType.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });

        // search by event name
        searchName.setOnClickListener(new View.OnClickListener() {
            String searched = String.valueOf(searchBar.getText()).toLowerCase();
            public void onClick(View view) {
                if (TextUtils.isEmpty(searched)) {
                    Toast.makeText(MainActivity.this, "Enter Something to Search By", Toast.LENGTH_SHORT).show();
                    return;
                }


                Club c = cdb.getByEventName(searched, etdb, edb, adb);
                String eventName = edb.getEvent(searched, cdb, etdb, adb).getName();

                Intent intent = new Intent(getApplicationContext(), UserRegister.class);
                intent.putExtra("username", username);
                intent.putExtra("clubOwner", c.getUsername());
                intent.putExtra("eventName", eventName);
                startActivity(intent);
            };
        });

        // search by club name
        searchClub.setOnClickListener(new View.OnClickListener() {
            String searched = String.valueOf(searchBar.getText()).toLowerCase();
            public void onClick(View view) {
                if (TextUtils.isEmpty(searched)) {
                    Toast.makeText(MainActivity.this, "Enter Something to Search By", Toast.LENGTH_SHORT).show();
                    return;
                }
                Club c = cdb.getByClubName(searched, etdb, edb, adb);

                Intent intent = new Intent(getApplicationContext(), UserEventList.class);
                intent.putExtra("username", username);
                intent.putExtra("clubOwner", c.getUsername());
                startActivity(intent);
                finish();
            }

        });

    }

}
