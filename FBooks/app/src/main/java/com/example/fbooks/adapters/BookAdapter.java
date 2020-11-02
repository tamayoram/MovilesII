package com.example.fbooks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fbooks.R;
import com.example.fbooks.models.BookModel;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<BookModel> modelArrayList;

    public BookAdapter(Context context, ArrayList<BookModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public BookModel getItem(int i) {
        return modelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            view=layoutInflater.inflate(R.layout.book_list_item,viewGroup,false);
        }

        TextView tv_book_list_item_code=view.findViewById(R.id.tv_book_list_item_code);
        TextView tv_book_list_item_title=view.findViewById(R.id.tv_book_list_item_title);
        EditText et_book_list_item_description= view.findViewById(R.id.et_book_list_item_description);

        tv_book_list_item_code.setText(getItem(i).getCode());
        tv_book_list_item_title.setText(getItem(i).getTitle());
        et_book_list_item_description.setText(getItem(i).getDescription());

        return view;
    }
}
