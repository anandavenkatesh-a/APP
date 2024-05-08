package com.example.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etName, etAddress;
    ToggleButton btnDose1, btnDose2, btnDose3;
    Button btnRegister, btnFetch;
    Spinner spinner;
    ImageView imageView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etn);
        etAddress = findViewById(R.id.eta);
        btnDose1 = findViewById(R.id.btn1);
        btnDose2 = findViewById(R.id.btn2);
        btnDose3 = findViewById(R.id.btn3);
        btnRegister = findViewById(R.id.reg);
        btnFetch = findViewById(R.id.fet);
        spinner = findViewById(R.id.spinner);
        imageView = findViewById(R.id.imageView);
        databaseHelper = new DatabaseHelper(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_values, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pic = position + 1;
                switch (pic) {
                    case 1:
                        imageView.setImageResource(R.drawable.img1);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.img2);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.img3);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String dose1 = btnDose1.isChecked() ? "Yes" : "No";
        String dose2 = btnDose2.isChecked() ? "Yes" : "No";
        String dose3 = btnDose3.isChecked() ? "Yes" : "No";
        int pic = spinner.getSelectedItemPosition() + 1;

        Log.d("Test", name);
        Log.d("Test", address);
        Log.d("Test", dose1);
        Log.d("Test", dose2);
        Log.d("Test", dose3);
        Log.d("test", Integer.toString(pic));

        boolean success = databaseHelper.insertData(name, address, dose1, dose2, dose3, pic);
        if (success) {
            Toast.makeText(MainActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Failed to register user", Toast.LENGTH_SHORT).show();
        }
    }
}

