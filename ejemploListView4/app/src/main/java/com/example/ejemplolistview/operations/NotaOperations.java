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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ejemplolistview.R;
import com.example.ejemplolistview.database.SQLHelper;
import com.example.ejemplolistview.models.NotaModel;

import java.util.ArrayList;

public class NotaOperations extends BaseAdapter {
    private String DBNAME="appnotasmyapp.db";
    private  int VERSION=1;
    public final Context context;
    private SQLiteDatabase database;
    private SQLHelper helper;
    private NotaModel model;
    private ArrayList<NotaModel> list;

    /*Constructor con conexión a la base de datos una vez se instancia un objeto*/
    public NotaOperations(Context context) {
        this.context = context;
        helper= new SQLHelper(context,DBNAME,null,VERSION);

    }

    /*Constructor con el contexto y la lista, recordar que el ArrayList se debe ajustar con NotaModel*/
    public NotaOperations(Context context, ArrayList<NotaModel> list) {
        this.context = context;
        this.list = list;
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
    public ArrayList<NotaModel> list(){
        list=new ArrayList<>();
        openRead();
        Cursor cursor=database.query("nota",null,null,null,null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            String titulo,contenido,consolidado;
            int id;
            do{
                model=new NotaModel();
                model.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
               // id=cursor.getInt(cursor.getColumnIndex("id"));
                model.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
                //titulo=cursor.getString(cursor.getColumnIndex("titulo"));
                model.setContenido(cursor.getString(cursor.getColumnIndex("contenido")));
                //contenido=cursor.getString(cursor.getColumnIndex("contenido"));

                list.add(model);

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

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewNota=convertView;
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewNota=inflater.inflate(R.layout.item_book,parent,false);
        }
            model= (NotaModel) getItem(position);

            TextView tv_titulo, tv_contenido;
            tv_titulo=viewNota.findViewById(R.id.tv_titulo);
            tv_contenido=viewNota.findViewById(R.id.tv_contenido);

            tv_titulo.setText(model.getTitulo());
            tv_contenido.setText(model.getContenido());
            return viewNota;

    }
}
