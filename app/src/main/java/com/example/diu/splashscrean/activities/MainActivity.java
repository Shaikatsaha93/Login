package com.example.diu.splashscrean.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diu.splashscrean.R;
import com.example.diu.splashscrean.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    EditText login_email;
    EditText login_password;
    Button login_button;
    Button sign_up_button;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Log In");

        databaseHelper = new DatabaseHelper(this);
        login_email = (EditText)findViewById(R.id.enter_email);
        login_password = (EditText)findViewById(R.id.enter_pass);
        login_button = (Button)findViewById(R.id.log_in);
        sign_up_button = (Button)findViewById(R.id.sign_up);
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Sing_up.class);
                startActivity(i);
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = login_email.getText().toString().trim();
                String pwd = login_password.getText().toString().trim();
                Boolean res = databaseHelper.checkUser(user, pwd);

                if (res == true){
                    Intent go_to_home = new Intent(MainActivity.this,Home.class);
                   // Toast.makeText(MainActivity.this,"Successfully Logged In",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
