package com.example.ejemplolistview.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//El Helper es una clase que permite definir las rutinas para conectarse a la base de datos.
//Se debe adicionar el extends para heredar del SQLite OpenHelper.
// El OpenHelper es una clase abstracta. Esta clases no permiten crear objetos, son clases predefinidas con unas
//rutinas para el determinar el comportamiento de sus clases hijas.

/*Procedimiento con clases abstractas:
- En las ayudas se debe dar implement methods: onCreate, onUpgrade.
- En las ayudas Create constructor matching super. Seleccionar de las opciones disponibles.
 */

public class SQLHelper extends SQLiteOpenHelper {


    public SQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           //Creación de la tabla con SQL y los campos de la tabla.
            db.execSQL("CREATE TABLE nota(id integer primary key autoincrement,titulo text, contenido text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Borra la tabla nota si se actualiza la versión de la tabla, adicionalmente crea una nueva tabla con la función
        //onCreate.
        db.execSQL("DROP TABLE IF EXISTS nota;");
        onCreate(db);
    }
}
