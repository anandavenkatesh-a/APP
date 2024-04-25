package don.wick.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText email;
    EditText password;
    Button signup;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        signup = findViewById(R.id.signup_button);
        login = findViewById(R.id.loginRedirectText);

        databaseHelper = new DatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();

                if(email.equals("")||password.equals(""))
                    Toast.makeText(getApplicationContext(), "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{

                        Boolean checkUserEmail = databaseHelper.checkEmail(mail);
                        if(!checkUserEmail){
                            Boolean insert = databaseHelper.insertData(mail, pass);
                            if(insert){
                                Toast.makeText(getApplicationContext(), "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.putExtra("user", mail);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }

                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
