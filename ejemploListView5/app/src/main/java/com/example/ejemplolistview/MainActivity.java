package com.example.ejemplolistview;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejemplolistview.models.NotaModel;
import com.example.ejemplolistview.operations.NotaOperations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NotaModel model; //se llama el modelo para utilizarlo
    private NotaOperations operations; //se llama la clase NotaOperations para utilizarla
    private TextView tv_ejemplolista,tv_ejemploid,tv_ejemplotitulo,tv_ejemplocontenido; //se llama al textview que esta en la parte visual
    private ArrayList<String> list; // se llama el objeto arraylist
    private EditText edt_id,edt_titulo,edt_contenido; // se llaman los editText
    private Button btn_insertar,btn_listar,btn_modificar,btn_eliminar; // se llaman los botones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init(); //función definida en la parte de abajo para inicializar la conexión entre las vistas XML y el lenguaje java.

    operations=new NotaOperations(getApplicationContext()); //se genera un objeto para utilizar las funciones de NotaOperations

        btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titulo=edt_titulo.getText().toString(); // se obtiene el valor de título desde el formulario.
                String contenido=edt_contenido.getText().toString(); // se obtiene el valor del contenido desde el formulario.
                model=new NotaModel(titulo,contenido); //se inicializa el modelo para interactuar con la base de datos.

                int validarinsert= operations.insert(model); // se define la variable para verificar si se insertan los datos
                if(validarinsert>0){
                    Toast.makeText(MainActivity.this,"Guardado correctamente",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity.this,"No se ha guardado la información",Toast.LENGTH_LONG).show();
                }

            }
        });

        //Rutinas correspondientes al botón modificar

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id=Integer.parseInt(edt_id.getText().toString());
                String titulo=edt_titulo.getText().toString();
                String contenido=edt_contenido.getText().toString();

                model=new NotaModel(id,titulo,contenido); //se inicializa un objeto correspondiente a la clase NotaModel

                int validarupdate= operations.update(model); // se define la variable para verificar si se insertan los datos
                if(validarupdate>0){
                    Toast.makeText(MainActivity.this,"Modificado correctamente",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity.this,"No se ha modificado la información",Toast.LENGTH_LONG).show();
                }

            }
        });

        //Rutina para pasar de este activity al activity donde se van a listar la información
        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listar=new Intent(MainActivity.this,ListarActivity.class);
                startActivity(listar);
            }
        });

        //Rutinas para eliminar.
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(edt_id.getText().toString());
                int validardelete= operations.delete(id);
                if(validardelete>0){
                    Toast.makeText(MainActivity.this,"Eliminado correctamente",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity.this,"No se ha eliminado la información",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    //Función para conectar las vistar en XML del formularion y el lenguaje java.

    public void init(){
        edt_id=findViewById(R.id.edt_id);
        edt_titulo=findViewById(R.id.edt_titulo);
        edt_contenido=findViewById(R.id.edt_contenido);

        btn_insertar=findViewById(R.id.btn_insertar);
        btn_listar=findViewById(R.id.btn_listar);
        btn_modificar=findViewById(R.id.btn_modificar);
        btn_eliminar=findViewById(R.id.btn_eliminar);


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