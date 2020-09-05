package com.example.contactosv2;

import android.content.Intent;
import android.os.Bundle;

import com.example.contactosv2.adapters.UsuarioAdapter;
import com.example.contactosv2.models.UsuarioModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroUsuarioActivity extends AppCompatActivity {

   /*definición de los objetos que se van a utilizar*/

    private EditText et_registrou_nombre,et_registrou_usuario,et_registrou_contrasena;
    private Button btn_registrou_ingresar;
    private UsuarioAdapter adapter;
    private UsuarioModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_persona);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        /*funciones para los objetos*/
        btn_registrou_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=et_registrou_nombre.getText().toString();
                String usuario=et_registrou_usuario.getText().toString();
                String contrasena=et_registrou_contrasena.getText().toString();
                boolean validarInterfaz=validarCampos(nombre,usuario,contrasena);

                if(validarInterfaz){
                    /*crear un modelo nuevo*/
                    model=new UsuarioModel(nombre,usuario,contrasena);
                    /*abra el adaptador para escribir*/
                    adapter.openWrite();
                    /*abra la función para insertar el modelo que tiene la información de nombre, usuario y contrasena. La función se crea en usuariadapter*/
                    long valorInsert= adapter.insert(model);
                    /*cierre el adaptador*/
                    adapter.close();

                    if(valorInsert>0){
                        Toast.makeText(RegistroUsuarioActivity.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                        Intent login=new Intent(RegistroUsuarioActivity.this,MainActivity.class);
                        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(login);

                    }else{
                        Toast.makeText(RegistroUsuarioActivity.this, "Registro no exitoso, intente de nuevo", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    /*Función para enlazar los objetos gráficos con XML con su código en java*/
    private void init(){
    et_registrou_nombre=findViewById(R.id.et_registrou_nombre);
    et_registrou_usuario=findViewById(R.id.et_registrou_usuario);
    et_registrou_contrasena=findViewById(R.id.et_registrou_contrasena);
    btn_registrou_ingresar=findViewById(R.id.btn_registrou_ingresar);

    /*Se inicializa el adapter y el modelo. Estos se pueden inicializar acá o en el onCreate*/
    adapter=new UsuarioAdapter(getApplicationContext());
    model=new UsuarioModel();
    }

    /*Función para validar los campos de usuario y contraseña*/
    public boolean validarCampos(String nombre, String usuario, String contrasena) {
        if (nombre.isEmpty() || usuario.isEmpty()||contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese nombre, usuario y contraseña", Toast.LENGTH_LONG).show();
            return false;
        } else if (nombre.length()<3 || usuario.length() < 3 || contrasena.length() < 8) {
            Toast.makeText(this, "Por favor ingrese nombre, usuario y contraseña validos. Mínimo 3 para nombre y usuario, y mínimo 8 para contraseña.", Toast.LENGTH_LONG).show();
            return false;
        } else{
            return true;
        }
    }

}
