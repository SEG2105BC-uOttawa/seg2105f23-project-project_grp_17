package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {
    public void login(String name, String pass, boolean Admin) {
        if (name.equals("username") && pass.equals("password") && !Admin) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        }
        else if (name.equals("username") && pass.equals("password")) {
            Intent intent = new Intent(getApplicationContext(), Registration.class);
        }
    }
}
