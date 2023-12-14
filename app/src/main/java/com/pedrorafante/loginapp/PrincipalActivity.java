package com.pedrorafante.loginapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    DatabaseHelper databaseHelper;
    List<Usuario> itemList;

    ActivityResultLauncher<Intent> cadastroLauncher;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        databaseHelper = new DatabaseHelper(this);
        // Criar dados de exemplo
        itemList = new ArrayList<>();

        cadastroLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
            if(result.getResultCode() == RESULT_OK){
                adapter.atualizarList(databaseHelper.getAllUsuario());
            }
        });


        itemList = databaseHelper.getAllUsuario();
//        itemList.add("Item 1");
//        itemList.add("Item 2");
        // Adicione mais itens conforme necessário

        // Configurar o RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter(itemList);
        recyclerView.setAdapter(adapter);

        floatingActionButton = findViewById(R.id.btnAdicionar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                cadastroLauncher.launch(intent);
//                itemList.add("Sanfona");
//                adapter.notifyItemInserted(itemList.size() - 1);
            }
        });

        adapter.setOnItemClickListener(position -> {
            Usuario usuarioSelecionado = itemList.get(position);
            Toast.makeText(this, "VOCE CLICOU NO USUARIO de ID: " + usuarioSelecionado.getId() + " - " + usuarioSelecionado.getUser(), Toast.LENGTH_LONG ).show();
        });
    }
}