package com.example.diu.splashscrean.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diu.splashscrean.R;
import com.example.diu.splashscrean.activities.MainActivity;
import com.example.diu.splashscrean.database.DatabaseHelper;

public class Sing_up extends AppCompatActivity {

    EditText name_e1;
    EditText email_e2;
    EditText password_e3;
    EditText confirm_password_e4;
    Button submit_btn;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        getSupportActionBar().setTitle("Sing Up");

        databaseHelper = new DatabaseHelper(this);
        name_e1 = (EditText)findViewById(R.id.name);
        email_e2 = (EditText)findViewById(R.id.email);
        password_e3 = (EditText)findViewById(R.id.pass);
        confirm_password_e4 = (EditText)findViewById(R.id.confirm_pass);
        submit_btn = (Button)findViewById(R.id.submit);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sing_up.this,MainActivity.class);
                startActivity(intent);
            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name_e1.getText().toString().trim();
                String user = email_e2.getText().toString().trim();
                String pwd = password_e3.getText().toString().trim();
                String cnf_pwd = confirm_password_e4.getText().toString().trim();

                if (pwd.equals(cnf_pwd)){
                    long val = databaseHelper.addUser(user,pwd);
                    if (val>0){
                        Toast.makeText(Sing_up.this,"You have Registered",Toast.LENGTH_SHORT).show();
                        Intent move_to_login = new Intent(Sing_up.this,MainActivity.class);
                        startActivity(move_to_login);
                    }
                    else {
                        Toast.makeText(Sing_up.this,"Registeration Error",Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(Sing_up.this,"Password is not matched",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
