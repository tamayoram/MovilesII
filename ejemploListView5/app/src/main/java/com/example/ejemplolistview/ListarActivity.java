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

import com.example.ejemplolistview.models.NotaModel;
import com.example.ejemplolistview.operations.BookAdapter;
import com.example.ejemplolistview.operations.NotaOperations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {

    private NotaOperations operations;
    private ListView lv_titulo;
    private FloatingActionButton flt_principal;
    private ArrayList<NotaModel> list;
    private NotaModel model;
    private Button btn_principal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        init();
        // Se crea una lista de datos de acuerdo a la función definida en la clase NotaOperations.
        list=operations.list();

        if(list !=null){
            lv_titulo.setAdapter(new BookAdapter(this,list));
        }



        lv_titulo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detalle=new Intent(ListarActivity.this,DetalleActivity.class);
                startActivity(detalle);

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
        model=new NotaModel();
        list= new ArrayList<>();
        operations=new NotaOperations(getApplicationContext());

    }
}