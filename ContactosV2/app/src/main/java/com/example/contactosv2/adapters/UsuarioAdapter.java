package com.example.contactosv2.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.contactosv2.database.SQLiteHelper;
import com.example.contactosv2.models.UsuarioModel;

public class UsuarioAdapter {

    /*Creación de la base de datos y la tabla usuario*/
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

    /*Apertura de la base de datos para leerla*/
    public void openRead(){
        database=helper.getReadableDatabase();
    }

    /*Función para escribir en la base de datos*/
    public void openWrite(){
        database=helper.getWritableDatabase();
    }

    /*Función para cerrar la base de datos*/
    public void close(){
        database.close();
    }

    /*Consulta a la base de datos*/
    public UsuarioModel login(String usuario,String contrasena){

        String where="usuario=? AND contrasena=?";
        Cursor cursor=database.query(NOMBRE_TABLA,null,where,new String[]{usuario,contrasena},null,null,null);
        if (cursor.getCount()<1){
            return null;
        } else{
            cursor.moveToFirst();
            model=new UsuarioModel();
            model.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            model.set_nombre(cursor.getString(cursor.getColumnIndex("nombre")));
            model.set_usuario(cursor.getString(cursor.getColumnIndex("usuario")));
            model.set_contrasena(cursor.getString(cursor.getColumnIndex("contrasena")));
            return model;
        }
    }

    /*Función para insertar en la base de datos*/
    public long  insert(UsuarioModel model){
        ContentValues values=new ContentValues();
        values.put("nombre",model.get_nombre());
        values.put("usuario",model.get_usuario());
        values.put("contrasena",model.get_contrasena());
        return database.insert(NOMBRE_TABLA,null,values);
    }
}
