package com.example.contactosv2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init(); /*se llama la función init*/

        /*Se captura el texto en usario y contraseña, se aplica la funciòn validar campos*/
        btn_main_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario=et_main_usuario.getText().toString();
                String contrasena=et_main_contrasena.getText().toString();
                boolean validacionInterfaz=validarCampos(usuario,contrasena);
                if(validacionInterfaz){
                    //Inicio de sesión con BD
                }
            }
        });

        btn_main_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent es para desplazarse por formularios
                Intent registro=new Intent(MainActivity.this, RegistroPersonaActivity.class);
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
    }

 /*Función para validar los campos de usuario y contraseña*/
    public boolean validarCampos(String usuario, String contrasena) {
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese usuario y contraseña", Toast.LENGTH_LONG).show();
            return false;
        } else if (usuario.length() < 8 || contrasena.length() < 8) {
            Toast.makeText(this, "Por favor ingrese usuario y contraseña validos. Mínimo 8 carácteres.", Toast.LENGTH_LONG).show();
            return false;
        } else{
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
