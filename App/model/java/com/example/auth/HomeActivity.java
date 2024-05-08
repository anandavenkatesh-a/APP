package com.example.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelper;
    CustomArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        int selectedValue = intent.getIntExtra("selectedValue", 0);

        // Fetch user data based on the selected value
        List<User> userList = databaseHelper.fetch(String.valueOf(selectedValue));

        if (!userList.isEmpty()) {
            // Create a custom adapter to display the user data with name and image
            adapter = new CustomArrayAdapter(this, userList);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No users found", Toast.LENGTH_SHORT).show();
        }
    }
}

