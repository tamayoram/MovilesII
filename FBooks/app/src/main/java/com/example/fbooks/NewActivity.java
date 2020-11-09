package com.example.fbooks;

import android.os.Bundle;

import com.example.fbooks.models.BookModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class NewActivity extends BaseActivity {

    FloatingActionButton fb_new_save,fb_new_clear,fb_new_back;
    ImageView iv_new_image;
    TextView tv_new_image;
    EditText et_new_code,et_new_title,et_new_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        fb_new_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code,title,description;
                boolean available;

                code=et_new_code.getText().toString();
                title=et_new_title.getText().toString();
                description=et_new_description.getText().toString();

                if (code.isEmpty()||title.isEmpty()||description.isEmpty()){
                    makeSimpleAlertDialog("Information","Please check for empty fields");
                }else{
                    model=new BookModel();
                    model.setAvailable(true);
                    model.setCode(code);
                    model.setTitle(title);
                    model.setDescription(description);
                    
                    save(model);
                }
            }
        });

        fb_new_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });

        fb_new_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
    }

    private void save(BookModel model) {
        if(collectionReference!=null){
            collectionReference.add(model)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                                if(task.getResult() !=null){
                                    makeSimpleAlertDialog("Success","The book was saved");
                                    clear();
                                }else{
                                    makeSimpleAlertDialog("Warning","The book was not saved");
                                }
                        }else{
                            makeSimpleAlertDialog("Error",task.getException().getMessage());
                        }
                    }
                });
        }else{
            makeSimpleAlertDialog("Error","Not database connection");
        }
    }

    protected void init(){
        fb_new_save=findViewById(R.id.fb_new_save);
        fb_new_clear=findViewById(R.id.fb_new_clear);
        fb_new_back=findViewById(R.id.fb_new_back);

        iv_new_image=findViewById(R.id.iv_new_image);
        tv_new_image=findViewById(R.id.tv_new_image);

        et_new_code=findViewById(R.id.et_new_code);
        et_new_title=findViewById(R.id.et_new_title);
        et_new_description=findViewById(R.id.et_new_description);
    }

    private void clear(){
        et_new_code.setText("");
        et_new_title.setText("");
        et_new_description.setText("");

        et_new_code.requestFocus();

        iv_new_image.setImageResource(R.drawable.ic_library_books_black_18dp);
    }

}