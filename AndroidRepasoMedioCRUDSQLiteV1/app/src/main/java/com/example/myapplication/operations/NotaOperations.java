package com.example.myapplication.operations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.SQLHelper;
import com.example.myapplication.models.NotaModel;

import java.util.ArrayList;

public class NotaOperations {
    private String DBNAME = "appnotasmyapp.db";
    private int VERSION = 3;
    public final Context context;
    private SQLiteDatabase database;
    private SQLHelper helper;
    private NotaModel model;
    private ArrayList<NotaModel> list;


    public NotaOperations(Context context) {
        this.context = context;
        helper = new SQLHelper(context, DBNAME, null, VERSION);
    }

    protected void openRead(){
        database = helper.getReadableDatabase();
    }

    protected void openWrite(){
        database = helper.getWritableDatabase();
    }

    protected void close(){
        database.close();
    }

    public int insert(NotaModel model){

        ContentValues content = new ContentValues();
        content.put("titulo", model.getTitulo());
        content.put("contenido", model.getContenido());

        openWrite();
        long id = database.insert("nota", null, content);
        close();
        return (int)id;
    }

    public ArrayList<NotaModel> list(){
        list = new ArrayList<>();
        openRead();

        Cursor cursor = database.query("nota", null, null, null, null, null, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            String titulo, contenido;
            int id;
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"));
                titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                contenido = cursor.getString(cursor.getColumnIndex("contenido"));

                model = new NotaModel(id, titulo, contenido);
                list.add(model);
            }while (cursor.moveToNext());
        }
        close();
        return list;
    }
}
