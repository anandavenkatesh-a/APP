package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, 101);
        }

        try {
            String number = "+1 650 555-6789";
            Random rand = new Random();

            // Generate random integers in range 0 to 9999
            int rand_int1 = rand.nextInt(10000);
            String otp = Integer.toString(rand_int1);
            while(otp.length() < 4){
                otp = "0" + otp;
            }
            String msg = "Your OTP = " + otp;
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number,null, msg,null,null);
            Toast.makeText(getApplicationContext(),"Message Sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}