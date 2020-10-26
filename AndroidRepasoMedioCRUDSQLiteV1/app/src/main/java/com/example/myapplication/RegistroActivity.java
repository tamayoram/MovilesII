package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.models.NotaModel;
import com.example.myapplication.operations.NotaOperations;


public class RegistroActivity extends AppCompatActivity {

    private EditText et_registro_titulo, et_registro_contenido;
    private Button btn_registro_guardar;
    private NotaModel model;
    private NotaOperations operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_registro_contenido = findViewById(R.id.et_registro_contenido);
        et_registro_titulo = findViewById(R.id.et_registro_titulo);
        btn_registro_guardar = findViewById(R.id.btn_registro_guardar);
        operations = new NotaOperations(this);

        btn_registro_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contenido, titulo;
                titulo = et_registro_titulo.getText().toString();
                contenido = et_registro_contenido.getText().toString();

                model = new NotaModel(titulo, contenido);
                int r = operations.insert(model);
                if(r>0){
                    Toast.makeText(getApplicationContext(), "Guardado correctamente", Toast.LENGTH_LONG).show();
                    Intent listar = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(listar);
                }else{
                    Toast.makeText(getApplicationContext(), "No se guardó correctamente", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}