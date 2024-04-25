package com.example.userauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login,signup;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = (EditText)findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signup = (Button) findViewById(R.id.signup);
        login = (Button) findViewById(R.id.gotologin);
        db = new DBhelper(this);
    }

    public void putToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void signUpClick(View v){
         String Username = username.getText().toString();
         String Password = password.getText().toString();

         if(db.checkusername(Username)) {
             putToast("User already exists");
             return;
         }

         if(db.insertData(Username,Password)){
             putToast("SignUp successful");
             return;
         }
         else{
             putToast("Internal Error");
             return;
         }
    }

    public void gotoLoginClick(View v){
        Intent toLogin = new Intent(this,LoginActivity.class);
        startActivity(toLogin);
    }
}