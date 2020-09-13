package com.example.ejemplolistview;

import android.os.Bundle;

import com.example.ejemplolistview.models.NotaModel;
import com.example.ejemplolistview.operations.NotaOperations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NotaModel model; //se llama el modelo para utilizarlo
    private NotaOperations operations; //se llama la clase NotaOperations para utilizarla
    private TextView tv_ejemplolista; //se llama al textview que esta en la parte visual
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_ejemplolista=findViewById(R.id.tv_ejemplolista); //se conecta el ejemplo gráfica con el lenguaje java
        /*Pedir datos para llevar a la base de datos*/
        /*Tarea que es habilitar la parte visual para solicitar datos del usuario*/
   String titulo="Nuevo Titulo de la nota";
   String contenido="Contenido de la nota que quiero guardar en la BD";

    operations=new NotaOperations(getApplicationContext()); //se genera un objeto para utilizar las funciones de NotaOperations
    model=new NotaModel(titulo,contenido); //se inicializa un objeto correspondiente a la clase NotaModel
    int r= operations.insert(model); // se define la variable r para verificar si se insertan los datos
        if(r>0){
            Toast.makeText(this,"Guardado correctamente",Toast.LENGTH_LONG).show();
            //tv_ejemplolista.setText(String.valueOf(r));
        }else{
            Toast.makeText(this,"No se guardo la información",Toast.LENGTH_LONG).show();
        }

        String consolidadmostrar="";
        list=operations.list();
        for(int i=0;i<list.size();i++){
            consolidadmostrar += list.get(i)+"\n --------\n\n";
        }

        tv_ejemplolista.setText(consolidadmostrar);

        /*Esta parte del código corresponde al botón flotante*/
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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