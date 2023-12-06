package com.pedrorafante.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);
//        databaseHelper.insertUser(); // Rodar so uma vez pq se nao toda vez que executar ira criar um usuário

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAllFields()) {
                    if (databaseHelper.checkUserPassword(username.getText().toString(), password.getText().toString())) {
                        Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário ou senha Incorretos!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean checkAllFields() {
        if (username.getText().toString().isEmpty()) {
            username.setError("Infome o username");
            username.requestFocus();
            return false;
        } else if (password.getText().toString().isEmpty()) {
            password.setError("Informe a senha");
            password.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}