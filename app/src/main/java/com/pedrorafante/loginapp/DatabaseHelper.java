package com.pedrorafante.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

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

    public void insertUser(){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", "pedro");
        contentValues.put("senha", "1234");
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

}
