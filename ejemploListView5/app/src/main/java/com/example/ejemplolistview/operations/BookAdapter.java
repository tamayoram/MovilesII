package com.example.ejemplolistview.operations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ejemplolistview.R;
import com.example.ejemplolistview.models.NotaModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NotaModel> modelArrayList;

    public BookAdapter(Context context, ArrayList<NotaModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public NotaModel getItem(int position) {

        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(R.layout.book_list_item,parent,false);
        }

        TextView tv_book_titulo= convertView.findViewById(R.id.tv_book_titulo);
        TextView tv_book_descripcion= convertView.findViewById(R.id.tv_book_descripcion);

        tv_book_titulo.setText(getItem(position).getTitulo());
        tv_book_descripcion.setText(getItem(position).getContenido());

        return convertView;
    }
}
