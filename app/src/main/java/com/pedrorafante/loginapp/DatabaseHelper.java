package com.pedrorafante.loginapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "loginapp.db";

    SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        sqLiteDatabase = getWritableDatabase();
        //Cria o banco de dados caso nao exista
        //método construtor da classe
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //cria tabelas
        String tableUser = "CREATE table usuario(\n" +
                "  id INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "  user varchar(255) NOT NULL,\n" +
                "  senha varchar(50) NOT NULL\n" +
                "  )";
        try {
            sqLiteDatabase.execSQL(tableUser);
        }catch (SQLException e){
            Log.e("DB_LOG", "onCreate: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop Table if exists usuario");
//        onCreate(db);
    }

    public void insertUser(String usuario, String senha){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", usuario);
        contentValues.put("senha", senha);
        MyDatabase.insert("usuario", null, contentValues);
    }

    public Boolean checkUserPassword(String username, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from usuario where user = ? and senha = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkUserExists(String username){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from usuario where user = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    @SuppressLint("Range")
    public List<Usuario> getAllUsuario(){
        List<Usuario> listUsuario = new ArrayList<Usuario>();
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from usuario", null);

        cursor.moveToFirst();
        do {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            usuario.setUser(cursor.getString(cursor.getColumnIndex("user")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));

            listUsuario.add(usuario);
        }while (cursor.moveToNext());

        cursor.close();
        MyDatabase.close();

        return listUsuario;
    }

    public void deleteUser(int id) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        try{
            MyDatabase.delete("usuario", "id = ?", new String[]{String.valueOf(id)});

        } catch (SQLException e){
            Log.e("DELETE", "Erro ao excluir" + e.getMessage());
        } finally {
            MyDatabase.close();
        }
    }

    @SuppressLint("Range")
    public Usuario getUsuario(int id){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Usuario usuario = new Usuario();
        Cursor cursor = MyDatabase.rawQuery("Select * from usuario where id = ?", new String[]{Integer.toString(id)});
        if(cursor.moveToFirst()){
            usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            usuario.setUser(cursor.getString(cursor.getColumnIndex("user")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        }
        return usuario;
    }

    public void atualizarRegistro(int id, String username, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", username);
        contentValues.put("senha", password);
        MyDatabase.update("usuario", contentValues,"id = ? ", new String[]{String.valueOf(id)} );
    }
}
