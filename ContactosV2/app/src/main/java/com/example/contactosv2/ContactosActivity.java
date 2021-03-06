package com.example.contactosv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.contactosv2.adapters.ContactoAdapter;
import com.example.contactosv2.models.ContactoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactosActivity extends AppCompatActivity {

    private FloatingActionButton fab_contactos_nuevo;
    private SharedPreferences preferences;
    private ContactoModel model;
    private ArrayList<ContactoModel> list;
    private ContactoAdapter adapter;
    private ListView lv_contactos_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        int usuario_id=preferences.getInt("usuario_id",0);
        String usuario_nombre=preferences.getString("usuario_nombre",null);

        if (usuario_id >0 && usuario_nombre!=null){
            adapter.openRead();
            list=adapter.selectContactosByUsuarioId(usuario_id);
            adapter.close();
            if(list!=null){
                lv_contactos_lista.setAdapter(new ContactoAdapter(this,list));
            }

        }

        fab_contactos_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro=new Intent(ContactosActivity.this,RegistroContactoActivity.class);
                startActivity(registro);
            }
        });
    }

    /*función para detectar el objeto xml y activar las preferencias*/
    public void init(){
        lv_contactos_lista=findViewById(R.id.lv_contactos_lista);
        fab_contactos_nuevo=findViewById(R.id.fab_contactos_nuevo);
        list=new ArrayList<>();
        model=new ContactoModel();
        adapter=new ContactoAdapter(getApplicationContext());
        preferences=getSharedPreferences("Preferences", MODE_PRIVATE);
    }

    /*función es para limpiar las preferencias una vez se cierra la sesión desde el menú*/
    private void cerrarSesion(){
        preferences.edit().clear().apply();
        irLogin();

    }

    private void irLogin(){
        Intent login=new Intent(ContactosActivity.this,MainActivity.class);
        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(login);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_salir) {
            finish();
            System.exit(0);
        }else if(id == R.id.action_cerrar_sesion){
            cerrarSesion();
        }

        return super.onOptionsItemSelected(item);
    }

}
