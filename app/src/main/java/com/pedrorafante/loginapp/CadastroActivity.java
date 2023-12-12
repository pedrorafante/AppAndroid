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
                if (checkAllFields() && checkPassword() && checkUsername()) {
                    databaseHelper.insertUser(usuario.getText().toString(), senha.getText().toString());
                    Toast.makeText(CadastroActivity.this, "Usuário Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    private boolean checkAllFields() {
        if (usuario.getText().toString().isEmpty()) {
            usuario.setError("Infome o usuário");
            usuario.requestFocus();
            return false;
        } else if (senha.getText().toString().isEmpty()) {
            senha.setError("Informe a senha");
            senha.requestFocus();
            return false;
        } else if (confirmacaoSenha.getText().toString().isEmpty()) {
            confirmacaoSenha.setError("Informe a confirmação de senha");
            confirmacaoSenha.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean checkPassword(){
        if (!senha.getText().toString().equals(confirmacaoSenha.getText().toString())){
            senha.setError("A senha e a confirmação de senha devem ser iguais!");
            senha.requestFocus();
           return false;
        }
        return true;
    }

    private boolean checkUsername(){
        if (databaseHelper.checkUserExists(usuario.getText().toString())) {
            usuario.setError("Usuário já existe no banco de dados!");
            usuario.requestFocus();
            return false;
        }
        return true;
    }
}