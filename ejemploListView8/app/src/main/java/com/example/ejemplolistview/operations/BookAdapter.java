package com.example.ejemplolistview.operations;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejemplolistview.R;
import com.example.ejemplolistview.models.NotaModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

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
        TextView tv_book_date= convertView.findViewById(R.id.tv_book_date);
        ImageView img_book= convertView.findViewById(R.id.img_book);

        tv_book_titulo.setText(getItem(position).getTitulo());
        tv_book_descripcion.setText(getItem(position).getContenido());
        tv_book_date.setText("25/10/2020");


        // Colores
        String[] colores = {"#ff33b5e5", "#ffff8800", "#ff99cc00", "#ffff4444", "#ff0099cc", "#ff669900", "#ffaa66cc", "#ffffbb33"};
        Random r =new Random();
        int randomNumber = r.nextInt(colores.length);
        convertView.setBackgroundColor(Color.parseColor(colores[randomNumber]));

        // Icono
        String[] iconos = {"ic_library_books_black_18dp", "ic_bookmark_border_24px","ic_bookmarks_24px","ic_description_24px","ic_reorder_24px"};
        randomNumber = r.nextInt(iconos.length);
        int icono = convertView.getResources().getIdentifier("com.example.ejemplolistview:drawable/" + iconos[randomNumber], null, null);
        img_book.setImageResource(icono);

        return convertView;
    }
}
