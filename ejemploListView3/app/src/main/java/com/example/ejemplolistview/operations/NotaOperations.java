package com.example.ejemplolistview.operations;

// En esta clase se definen las operaciones que se van a realizar con la base de datos (insertar, listar, modificar y eliminar).
//Importante: siempre se debe definir el contexto, abrir la base de datos y cerrar la base de datos.
// Para consultar se abre con Read, para actualizar, insertar o eliminar se abre con Write.

/* Pasos a seguir:
-Nombre de la base de datos.
-Definir la versión.
- Definir el contexto: public para que pueda ser utilizado en otras clases. Final porque una vez
definido no va a cambiar.
- Activar la base de datos con SQLiteDatabase.
- Activar el helper que definimos previamente en la carpeta database.
- Activar el modelo.
- Generar el constructor del contexto. Generate/constructor. Recordar importar la clase android.content.Context
- Incluir en el constructor el helper para conectarse con la base de datos.
- Crear las funciones openRead y openWrite.
- Crear la función close.
- Crear las funciones CRUD.
- El MainActivity y otras clases de Activity van a llamar las funciones CRUD de acuerdo a la necesidad.
- Recordar importar la librería para que funcione el cursor.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telecom.CallRedirectionService;

import com.example.ejemplolistview.database.SQLHelper;
import com.example.ejemplolistview.models.NotaModel;

import java.util.ArrayList;

public class NotaOperations {
    private String DBNAME="appnotasmyapp.db";
    private  int VERSION=1;
    public final Context context;
    private SQLiteDatabase database;
    private SQLHelper helper;
    private NotaModel model;
    private ArrayList<String> list;

    /*Constructor con conexión a la base de datos una vez se instancia un objeto*/
    public NotaOperations(Context context) {
        this.context = context;
        helper= new SQLHelper(context,DBNAME,null,VERSION);

    }

    /*Función para abrir la base datos*/
    /* Las estamos dejando protected porque las estamos llamando acá mismo en la clase desde
    cada función del CRUD. Si no las queremos dejar acá y llamarlas libremente desde los Activity
    se deben dejar como públicas y no incluirlas en las funciones CRUD.
     */
    protected void openRead(){
        database=helper.getReadableDatabase();
    }

    /*Función para escribir en la base de datos*/
    protected void openWrite(){
        database=helper.getWritableDatabase();
    }

    /*Función para cerrar la base de datos*/
    protected void close(){
        database.close();
    }

    /*Funciones CRUD*/
    /* le estoy pasando un objeto correspondiente al modelo que habíamos creado previamente para
    que lon inserte en la base de datos*/
    /* Se devuelve un int porque se verifica que guardo solo con el id. Si es mayor que cero es porque guardo*/
    /* ContentValues permite convertir el modelo a rutinas que entiende el SQLite*/
    /* En el return se utiliza el int para convertir la variable long en un int*/

    public int insert(NotaModel model){
        /*Se crea el objeto contentvalues*/
        ContentValues content=new ContentValues();
        content.put("titulo",model.getTitulo());
        content.put("contenido",model.getContenido());

        openWrite();
        long id=database.insert("nota",null,content);
        close();
        return (int) id;
    }
    public ArrayList<String> list(){
        list=new ArrayList<>();
        openRead();
        Cursor cursor=database.query("nota",null,null,null,null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            String titulo,contenido,consolidado;
            int id;
            do{
                id=cursor.getInt(cursor.getColumnIndex("id"));
                titulo=cursor.getString(cursor.getColumnIndex("titulo"));
                contenido=cursor.getString(cursor.getColumnIndex("contenido"));

                consolidado=String.valueOf(id)+" -- "+titulo+" "+ "/ Contenido: "+ contenido;
                list.add(consolidado);
            }while(cursor.moveToNext());
        }
        close();
        return list;
    }

    public int update(NotaModel model){
        String where="id=?";
        String idtext=String.valueOf(model.get_id());

        ContentValues content=new ContentValues();
        content.put("titulo",model.getTitulo());
        content.put("contenido",model.getContenido());

        openWrite();
        long id= database.update("nota",content,where,new String[]{idtext});
        close();
        return (int) id;

    }

    public int delete(int id){
        String where="id=?";
        String idtext=String.valueOf(id);
        openWrite();
        long del=database.delete("nota",where,new String[]{idtext});
        close();
        return(int) del;
    }
}
