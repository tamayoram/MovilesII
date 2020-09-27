package com.example.ejemplolistview;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejemplolistview.models.NotaModel;
import com.example.ejemplolistview.operations.NotaOperations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class ListActivity2 extends AppCompatActivity {
    private NotaOperations operations;
    private ArrayList<NotaModel> list;
    private NotaModel model;
    private ListView lv_contactos2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        list=operations.list();

        if(list !=null){
            lv_contactos2.setAdapter(new NotaOperations(this,list));
        }

        lv_contactos2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model= (NotaModel) parent.getItemAtPosition(position);
                Intent detalle=new Intent(ListActivity2.this,DetalleActivity.class);
                detalle.putExtra("model", (Serializable) model);
                startActivity(detalle);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void init(){
        lv_contactos2=findViewById(R.id.lv_contactos2);
        model=new NotaModel();
        list= new ArrayList<>();
        operations=new NotaOperations(getApplicationContext());
    }
}