package com.example.segproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import java.util.Iterator;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class AdminManageAccounts extends AppCompatActivity {

    Button goBackButton;

    ListView accountList;

    static AccountDBHandler db;

    Button goBackToHomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_accounts);
        goBackButton = findViewById(R.id.goBackButton);
        accountList = findViewById(R.id.accountList);
        goBackToHomepage = findViewById(R.id.goToHomepageAdmin);
        db = new AccountDBHandler(this);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        Cursor c = db.getData();
        ArrayList<String> arr= new ArrayList<>();

        if(c.getCount() == 0){
            Toast.makeText(AdminManageAccounts.this, "No entries", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while(c.moveToNext()){
                arr.add(c.getString(0));
            }
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr); accountList.setAdapter(adapter);
        accountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    String account = (String) parent.getItemAtPosition(position);
                    try {
                        db.deleteUserData(account);
                        adapter.remove(account);
                        Toast.makeText(AdminManageAccounts.this, "User Deleted", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AdminManageAccounts.this, "Error Deleting User", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        goBackToHomepage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminLoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}