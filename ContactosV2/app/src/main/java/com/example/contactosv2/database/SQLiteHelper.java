package com.example.contactosv2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.contactosv2.adapters.ContactoAdapter;
import com.example.contactosv2.adapters.UsuarioAdapter;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioAdapter.SCRIPT_NUEVA_TABLA);
        db.execSQL(ContactoAdapter.SCRIPT_NUEVA_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UsuarioAdapter.NOMBRE_TABLA);
        db.execSQL("DROP TABLE IF EXISTS " + ContactoAdapter.NOMBRE_TABLA);
        onCreate(db);
    }
}
