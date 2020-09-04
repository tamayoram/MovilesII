package com.example.contactosv2.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.contactosv2.database.SQLiteHelper;
import com.example.contactosv2.models.UsuarioModel;

public class UsuarioAdapter {

    private final String NOMBRE_DB="contactosv2.db";
    private final int VERSION=1;
    public static final String NOMBRE_TABLA="usuario";

    public static final String SCRIPT_NUEVA_TABLA="CREATE TABLE "+ NOMBRE_TABLA +
            "(" +
            "id integer primary key autoincrement, " +
            "nombre text, " +
            "usuario text, " +
            "contrasena text" +
            ");";

    private static SQLiteDatabase database;
    private static SQLiteHelper helper;
    private final Context context;
    private UsuarioModel model;

    public UsuarioAdapter(Context context) {
        this.context = context;
        helper=new SQLiteHelper(context,NOMBRE_DB,null,VERSION);
    }

    public void openRead(){
        database=helper.getReadableDatabase();
    }

    public void openWrite(){
        database=helper.getWritableDatabase();
    }

    public UsuarioModel login(String usuario,String contrasena){

    }
}
