package com.example.ejemplolistview;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejemplolistview.models.NotaModel;
import com.example.ejemplolistview.operations.NotaOperations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetalleActivity<model> extends AppCompatActivity {
    private TextView tv_detalleejemplolista,tv_detalleejemploid,tv_detalleejemplotitulo,tv_detalleejemplocontenido;
    private EditText edt_detalleid,edt_detalletitulo,edt_detallecontenido;
    private Button btn_detallelistar,btn_detallemodificar,btn_detalleeliminar;
    private NotaModel model;
    private NotaOperations operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        //Captura de la información que esta en el list2
        model= (NotaModel) getIntent().getSerializableExtra("model");
        if(model !=null){

            edt_detalleid.setText(model.get_id());
            edt_detalletitulo.setText(model.getTitulo());
            edt_detallecontenido.setText(model.getContenido());
        }

        btn_detallelistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listar=new Intent(DetalleActivity.this,ListActivity2.class);
                startActivity(listar);
            }
        });



    }


    public void init(){
        edt_detalleid=findViewById(R.id.edt_detalleid);
        edt_detalletitulo=findViewById(R.id.edt_detalletitulo);
        edt_detallecontenido=findViewById(R.id.edt_detallecontenido);

        btn_detallelistar=findViewById(R.id.btn_detallelistar);
        btn_detallemodificar=findViewById(R.id.btn_detallemodificar);
        btn_detalleeliminar=findViewById(R.id.btn_detalleeliminar);

        operations=new NotaOperations(getApplicationContext());
        model=new NotaModel();
    }
}