package com.example.ejemplolistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ejemplolistview.operations.NotaOperations;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {

    private NotaOperations operations;
    private ArrayList<String> list;
    private Button btn_principal;
    private TextView tv_listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        init();

        operations=new NotaOperations(getApplicationContext());

        String consolidadomostrar="";

        list=operations.list();

        for(int i=0;i<list.size();i++){
            consolidadomostrar += list.get(i)+"\n --------\n\n";
        }

        tv_listar.setText(consolidadomostrar);


        btn_principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent principal=new Intent(ListarActivity.this,MainActivity.class);
                startActivity(principal);
            }
        });

    }

    public void init(){
        btn_principal=findViewById(R.id.btn_principal);
        tv_listar=findViewById(R.id.tv_listar);
    }
}