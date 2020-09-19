package com.example.ejemplolistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ejemplolistview.operations.NotaOperations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {

    private NotaOperations operations;
    private ListView lv_titulo;
    private FloatingActionButton flt_principal;
    private ArrayList<String> list;
    private ArrayAdapter<String> itemsAdapter;
    private Button btn_principal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        init();

        operations=new NotaOperations(getApplicationContext());

        // Se crea una lista de datos de acuerdo a la función definida en la clase NotaOperations.
        list=operations.list();

        // se utiliza el adaptador propio de android para llevar la list a la vista que se encuentra en el layout.
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv_titulo.setAdapter(itemsAdapter);

        lv_titulo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texto = list.get(position);
                Snackbar.make(view, texto, Snackbar.LENGTH_LONG).show();
            }
        });

        flt_principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent principal=new Intent(ListarActivity.this,MainActivity.class);
                startActivity(principal);
            }
        });

    }

    public void init(){
        lv_titulo=findViewById(R.id.lv_titulo);
        flt_principal=findViewById(R.id.flt_principal);

    }
}