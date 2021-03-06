package com.example.contactosv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.contactosv2.adapters.UsuarioAdapter;
import com.example.contactosv2.models.UsuarioModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_main_usuario,et_main_contrasena;
    private Button btn_main_ingresar,btn_main_registrarse;
    private UsuarioAdapter adapter;
    private UsuarioModel model;
    /*se define la variable para el SharedPreferences. Esto es para obtener de la base datos informaciòn de los registros realizados*/
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init(); /*se llama la función init*/
        validarSesion();/*se llama la función sesión. El sistema valida si hay preference y si es así se va a contactos*/

        /*Se captura el texto en usario y contraseña, se aplica la funciòn validar campos*/
        btn_main_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario=et_main_usuario.getText().toString();
                String contrasena=et_main_contrasena.getText().toString();

                boolean validacionInterfaz=validarCampos(usuario,contrasena);
                /*si la validación es favorable, se continua con el login, se saca el mensaje y se lleva al usuario  a contactos con el intent*/
                if(validacionInterfaz){
                    adapter.openRead();
                    model=adapter.login(usuario,contrasena);
                    adapter.close();

                    if(model == null){
                    Toast.makeText(MainActivity.this, "Usuario no encontrado, revise su información", Toast.LENGTH_LONG).show();

                    }else{

                    Toast.makeText(MainActivity.this, "Usuario encontrado, iniciando sesión", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor=preferences.edit();
                    /*se obtiene id y nombre de registros en la BD*/
                    editor.putInt("usuario_id",model.get_id());
                    editor.putString("usuario_nombre",model.get_nombre());
                    /*no se cierra el sharedpreference hasta que no graba*/
                    editor.commit();

                    irContactos();

                    }

                }

            }
        });

        btn_main_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent es para desplazarse por formularios
                Intent registro=new Intent(MainActivity.this, RegistroUsuarioActivity.class);
                startActivity(registro);
            }
        });

    }

    /* Función para enlazar los objetos que se encuentran en la interfaz*/
    public void init(){
        et_main_usuario=findViewById(R.id.et_main_usuario);
        et_main_contrasena=findViewById(R.id.et_main_contrasena);
        btn_main_ingresar=findViewById(R.id.btn_main_ingresar);
        btn_main_registrarse=findViewById(R.id.btn_main_registrarse);

        /*inicialización del adaptador y el modelo*/
        adapter= new UsuarioAdapter(getApplicationContext());
        model=new UsuarioModel();

        /*Se inicializa el sharepreference. Es para obtener información de registros en la base de datos*/
        preferences=getSharedPreferences("Preferences",MODE_PRIVATE);
    }

    /*Función para validar el cierre de la sesión*/

    private  void validarSesion(){
        int usuario_id=preferences.getInt("usuario_id",0);
        String usuario_nombre=preferences.getString("usuario_nombre",null);

        if (usuario_id >0 && usuario_nombre!=null){
            irContactos();
        }

    }

    private void irContactos(){
        Intent contactos=new Intent(MainActivity.this,ContactosActivity.class);
        /* el flag es para crear un flujo de trabajo diferente, tal que si el usuario se devuelve en la aplicación se sale de la misma*/
        contactos.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(contactos);
    }

 /*Función para validar los campos de usuario y contraseña*/
    public boolean validarCampos(String usuario, String contrasena) {
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Campo vacío, por favor ingrese usuario y contraseña", Toast.LENGTH_LONG).show();
            return false;
        } else if (usuario.length() < 3 || contrasena.length() < 8) {
            Toast.makeText(this, "Por favor ingrese usuario y contraseña validos. Mínimo 3 carácteres para el usuario y 8 para contraseña.", Toast.LENGTH_LONG).show();
            return false;
        } else{
            return true;
        }
    }

}
