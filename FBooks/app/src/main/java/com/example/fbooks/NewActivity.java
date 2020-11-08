package com.example.fbooks;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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