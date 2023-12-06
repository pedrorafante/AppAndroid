package com.pedrorafante.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    EditText usuario;
    EditText senha;
    EditText confirmacaoSenha;
    Button cadastrarButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        usuario = findViewById(R.id.cadastro_usuario);
        senha = findViewById(R.id.cadastro_senha);
        confirmacaoSenha = findViewById(R.id.cadastro_senha_confirm);

        cadastrarButton = findViewById(R.id.cadastro_btnCadastrar);

        databaseHelper = new DatabaseHelper(this);

        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.insertUser(usuario.getText().toString(), senha.getText().toString());
                Toast.makeText(CadastroActivity.this,"Usuário Cadastrado com Sucesso!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}