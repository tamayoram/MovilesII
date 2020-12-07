package com.example.fbooks;

import android.os.Bundle;

import com.example.fbooks.models.BookModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class DetailActivity extends BaseActivity {

       FloatingActionButton fb_detail_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        model= (BookModel) getIntent().getSerializableExtra("model");
        if(model !=null){

            //makeSimpleAlertDialog("Success","Model: "+ model.getCode());
            Bundle bundle=new Bundle();
            bundle.putSerializable("model",model);
            InformationDetailFragment.receivedInformation(bundle);

        }else{
            makeSimpleAlertDialog("Error","Empty model");
        }

        fb_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });

    }


    protected void init(){
        fb_detail_back=findViewById(R.id.fb_detail_back);
    }
}