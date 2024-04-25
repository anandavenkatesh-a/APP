package com.example.userauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    DBhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = (EditText)findViewById(R.id.username_login);
        password = (EditText) findViewById(R.id.password_login);
        login = (Button) findViewById(R.id.login);
        db = new DBhelper(this);
    }

    public void putToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void loginClick(View v){
        String Username = username.getText().toString();
        String Password = password.getText().toString();

        if(!db.checkusernamepassowrd(Username,Password)){
            putToast("Check credentials");
            return;
        }

        Intent gotoHome = new Intent(this,LoginActivity.class);
        putToast("Login successful");
        gotoHome.putExtra("username",Username);
        startActivity(gotoHome);
    }
};