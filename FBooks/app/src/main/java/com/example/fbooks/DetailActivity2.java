package com.example.fbooks;

import android.os.Bundle;

import com.example.fbooks.models.BookModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

import java.util.Map;

public class DetailActivity2 extends BaseActivity {

    FloatingActionButton fb_detail2_update,fb_detail2_back;
    EditText et_detail2_code,et_detail2_title,et_detail2_description;
    private static String code,description,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        model= (BookModel) getIntent().getSerializableExtra("model");
        if(model !=null){

            //makeSimpleAlertDialog("Success","Model: "+ model.getCode());
            Bundle bundle=new Bundle();
            bundle.putSerializable("model",model);
            DetailActivity2.receivedInformation(bundle);

        }else{
            makeSimpleAlertDialog("Error","Empty model");
        }

        et_detail2_code.setText(code);
        et_detail2_title.setText(title);
        et_detail2_description.setText(description);


        fb_detail2_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code,title,description,id;
                boolean available;

                DocumentReference bookDocument = collectionReference.document();
                id=bookDocument.getId();

                code=et_detail2_code.getText().toString();
                title=et_detail2_title.getText().toString();
                description=et_detail2_description.getText().toString();

                if (code.isEmpty()||title.isEmpty()||description.isEmpty()){
                    makeSimpleAlertDialog("Information","Please check for empty fields");
                }else{

                    model = new BookModel();
                    model.setAvailable(true);
                    model.setId(id);
                    model.setCode(code);
                    model.setTitle(title);
                    model.setDescription(description);

                    update(model);
                }
            }
        });


        fb_detail2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });


    }

    protected void init(){
        fb_detail2_update=findViewById(R.id.fb_detail2_update);
        fb_detail2_back=findViewById(R.id.fb_detail2_back);

        et_detail2_code=findViewById(R.id.et_detail2_code);
        et_detail2_title=findViewById(R.id.et_detail2_title);
        et_detail2_description=findViewById(R.id.et_detail2_description);
    }

    static void receivedInformation(Bundle bundle){
        BookModel model= (BookModel) bundle.getSerializable("model");

        if(model!=null){
            code=model.getCode();
            title=model.getTitle();
            description=model.getDescription();
        }
    }

    private void update(BookModel model){
        if(collectionReference!=null){
            collectionReference.document(model.getId()).set(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    makeSimpleAlertDialog("Success","The book was updated");
                }
            });

        }else{

            makeSimpleAlertDialog("Error","Not database connection");
        }

    }
}