package com.example.fbooks;

import android.os.Bundle;

import com.example.fbooks.adapters.BookAdapter;
import com.example.fbooks.models.BookModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {
    private FloatingActionButton fab_list_create;
    private ListView lv_list_books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();


        fab_list_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNew();
            }
        });


    }

    protected void init(){
        fab_list_create=findViewById(R.id.fab_list_create);
        lv_list_books=findViewById(R.id.lv_list_books);
    }

    protected void getBooks(){
        if(collectionReference !=null){

            collectionReference.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                if(task.getResult()!=null){

                                    modelArrayList=new ArrayList<>();
                                    for(QueryDocumentSnapshot snapshot : task.getResult()){
                                        model=snapshot.toObject(BookModel.class);
                                        modelArrayList.add(model);
                                    }

                                    if(modelArrayList.size()>0){
                                        paintBooks(modelArrayList);
                                    }else{
                                        makeSimpleAlertDialog("Alert","The book was not found");
                                    }

                                }else{
                                    makeSimpleAlertDialog("Warning","The book was not found");
                                }
                            }else{
                                makeSimpleAlertDialog("Error",task.getException().getMessage());
                            }
                        }
                    });

        }else{
            makeSimpleToast("Database error",1);
        }
    }

    protected void paintBooks(ArrayList<BookModel> modelArrayList){
        adapter=new BookAdapter(this,modelArrayList);
        lv_list_books.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getBooks();

    }
}