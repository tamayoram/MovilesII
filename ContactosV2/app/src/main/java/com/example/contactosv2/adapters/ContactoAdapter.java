package com.example.contactosv2.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.contactosv2.R;
import com.example.contactosv2.database.SQLiteHelper;
import com.example.contactosv2.models.ContactoModel;

import java.util.ArrayList;


public class ContactoAdapter extends BaseAdapter {
    /*Creación de la base de datos y la tabla usuario*/
    private final String NOMBRE_DB="contactosv2.db";
    private final int VERSION=2;
    public static final String NOMBRE_TABLA="contacto";

    public static final String SCRIPT_NUEVA_TABLA="CREATE TABLE "+ NOMBRE_TABLA +
            "(" +
            "id integer primary key autoincrement, " +
            "nombre text, " +
            "apellido text, " +
            "celular text, " +
            "fijo text, " +
            "usuario_id integer not null, " +
            "FOREIGN KEY (usuario_id) REFERENCES " + UsuarioAdapter.NOMBRE_TABLA + "(id)" +
            ");";

    private static SQLiteDatabase database;
    private static SQLiteHelper helper;
    private final Context context;
    private ContactoModel model;
    private ArrayList<ContactoModel> list;

    public ContactoAdapter(Context context) {
        this.context = context;
        helper=new SQLiteHelper(context,NOMBRE_DB,null,VERSION);
    }

    public ContactoAdapter(Context context, ArrayList<ContactoModel> list) {
        this.context = context;
        this.list = list;
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

    /*Función para insertar en la base de datos*/
    public long  insert(ContactoModel model){
        ContentValues values=new ContentValues();
        values.put("nombre",model.get_nombre());
        values.put("apellido",model.get_apellido());
        values.put("celular",model.get_celular());
        values.put("fijo",model.get_fijo());
        values.put("usuario_id",model.get_usuario_id());
        return database.insert(NOMBRE_TABLA,null,values);
    }

    public ContactoModel selectPorId(int id){

        String where="id=?";
        String idtext=String.valueOf(id);
        Cursor cursor=database.query(NOMBRE_TABLA,null,where,new String[]{idtext},null,null,null);
        if (cursor.getCount()<1){
            return null;
        } else{
            cursor.moveToFirst();
            model=new ContactoModel();
            model.set_id(id);
            model.set_nombre(cursor.getString(cursor.getColumnIndex("nombre")));
            model.set_celular(cursor.getString(cursor.getColumnIndex("celular")));
            model.set_fijo(cursor.getString(cursor.getColumnIndex("fijo")));
            return model;
        }
    }

    public ArrayList<ContactoModel> selectContactosByUsuarioId(int usuario_id){

        String where="usuario_id=?";
        String idtext=String.valueOf(usuario_id);
        Cursor cursor=database.query(NOMBRE_TABLA,null,where,new String[]{idtext},null,null,null);
        if (cursor.getCount()<1){
            return null;
        } else{
            list=new ArrayList<>();
            cursor.moveToFirst();
            do{
                model=new ContactoModel();
                model.set_id(usuario_id);
                model.set_nombre(cursor.getString(cursor.getColumnIndex("nombre")));
                model.set_celular(cursor.getString(cursor.getColumnIndex("celular")));
                model.set_fijo(cursor.getString(cursor.getColumnIndex("fijo")));
                list.add(model);
            }while(cursor.moveToNext());
            return list;
        }
    }

    public int  update(ContactoModel model){
        String where="id=?";
        String idtext=String.valueOf(model.get_id());

        ContentValues values=new ContentValues();
        values.put("nombre",model.get_nombre());
        values.put("apellido",model.get_apellido());
        values.put("celular",model.get_celular());
        values.put("fijo",model.get_fijo());

        return database.update(NOMBRE_TABLA,values,where,new String[]{idtext});
    }

    public int  delete(int id){
        String where="id=?";
        String idtext=String.valueOf(id);

        return database.delete(NOMBRE_TABLA,where,new String[]{idtext});
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
        View viewItemContacto=convertView;
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewItemContacto=inflater.inflate(R.layout.item_contacto,parent,false);
        }
        model= (ContactoModel) getItem(position);
        String nombre_completo=model.get_nombre()+" "+model.get_apellido();
        TextView iv_item_contacto_nombre_completo,iv_item_contacto_celular;
        iv_item_contacto_nombre_completo=viewItemContacto.findViewById(R.id.iv_item_contacto_nombre_completo);
        iv_item_contacto_celular=viewItemContacto.findViewById(R.id.iv_item_contacto_celular);

        iv_item_contacto_nombre_completo.setText(nombre_completo);
        iv_item_contacto_celular.setText(model.get_celular());
        return viewItemContacto;
    }
}
