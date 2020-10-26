package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapters.NotaAdapter;
import com.example.myapplication.models.NotaModel;
import com.example.myapplication.operations.NotaOperations;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NotaOperations operations;
    private ListView lv_main_notas;
    private Button btn_main_nuevo;
    private ArrayList<NotaModel> list;
    private NotaAdapter adapter;
    private NotaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main_notas = findViewById(R.id.lv_main_notas);
        btn_main_nuevo = findViewById(R.id.btn_main_nuevo);
        operations = new NotaOperations(this);

        btn_main_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevo = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(nuevo);
            }
        });


        list = operations.list();
        adapter = new NotaAdapter(this, list);
        lv_main_notas.setAdapter(adapter);

        lv_main_notas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), String.valueOf(list.get(i).get_id()), Toast.LENGTH_LONG).show();
                model = list.get(i);
                Intent detalle = new Intent(MainActivity.this, DetalleActivity.class);
                detalle.putExtra("model", model);
                startActivity(detalle);
            }
        });


    }
}