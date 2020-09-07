package com.example.contactosv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.contactosv2.adapters.ContactoAdapter;
import com.example.contactosv2.adapters.UsuarioAdapter;
import com.example.contactosv2.models.ContactoModel;
import com.example.contactosv2.models.UsuarioModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroContactoActivity extends AppCompatActivity {

    private EditText et_registroc_nombre,et_registroc_apellido,et_registroc_celular,et_registroc_fijo;
    private Button btn_registroc_ingresar;
    private ContactoAdapter adapter;
    private ContactoModel model;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_contacto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        final int id_usuario=validarSesion();
        if(id_usuario==0){
            irLogin();
        }


        /*funciones para los objetos*/
        btn_registroc_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=et_registroc_nombre.getText().toString();
                String apellido=et_registroc_apellido.getText().toString();
                String celular=et_registroc_celular.getText().toString();
                String fijo=et_registroc_fijo.getText().toString();

                /*crear un modelo nuevo*/
                    model=new ContactoModel(nombre,apellido,celular,fijo,id_usuario);
                    /*abra el adaptador para escribir*/
                    adapter.openWrite();
                    /*abra la función para insertar el modelo que tiene la información de nombre, apellido, celular y fijo. La función se crea en Contactodapter*/
                    long valorInsert= adapter.insert(model);
                    /*cierre el adaptador*/
                    adapter.close();

                    if(valorInsert>0){
                        Toast.makeText(RegistroContactoActivity.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                        Intent login=new Intent(RegistroContactoActivity.this,ContactosActivity.class);
                        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(login);
                        finish();

                    }else{
                        Toast.makeText(RegistroContactoActivity.this, "Registro no exitoso, intente de nuevo", Toast.LENGTH_LONG).show();
                    }

            }
        });


    }

    /*Función para enlazar los objetos gráficos con XML con su código en java*/
    private void init(){
        et_registroc_nombre=findViewById(R.id.et_registroc_nombre);
        et_registroc_apellido=findViewById(R.id.et_registroc_apellido);
        et_registroc_celular=findViewById(R.id.et_registroc_celular);
        et_registroc_fijo=findViewById(R.id.et_registroc_fijo);
        btn_registroc_ingresar=findViewById(R.id.btn_registroc_ingresar);
        preferences=getSharedPreferences("Preferences", MODE_PRIVATE);

        /*Se inicializa el adapter y el modelo. Estos se pueden inicializar acá o en el onCreate*/
        adapter=new ContactoAdapter(getApplicationContext());
        model=new ContactoModel();
    }

    private int validarSesion(){

        int usuario_id=preferences.getInt("usuario_id",0);
        String usuario_nombre=preferences.getString("usuario_nombre",null);

        if (usuario_id >0 && usuario_nombre!=null){
            return usuario_id;
        }else{
            return 0;
        }
    }

    private void irLogin(){
        Intent login=new Intent(RegistroContactoActivity.this,MainActivity.class);
        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(login);

    }

}