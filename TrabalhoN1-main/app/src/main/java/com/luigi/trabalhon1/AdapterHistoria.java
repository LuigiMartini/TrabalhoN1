package com.luigi.trabalhon1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterHistoria extends BaseAdapter {



    private List<Historia> historiaList;
    private Context context;
    private LayoutInflater inflater;
    private ImageView imageLista;


    public AdapterHistoria(Context context, List<Historia> listaHistoria){
        this.historiaList = listaHistoria;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return historiaList.size();
    }

    @Override
    public Object getItem(int i) {
        return historiaList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return historiaList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
    View view = convertView;
    ItemSuporte item;

    if( convertView == null){
        convertView = inflater.inflate(R.layout.layout_lista, null);

        item = new ItemSuporte();
        item.tvNome = convertView.findViewById(R.id.tvListaNome);
        item.tvAutor = convertView.findViewById(R.id.tvListaAutor);
        item.tvAno = convertView.findViewById(R.id.tvListaAno);
        item.imageLista = (ImageView) convertView.findViewById(R.id.imageLista);

        item.layout = convertView.findViewById(R.id.llFundoLista);

        convertView.setTag( item );
    }else {
        item = (ItemSuporte) convertView.getTag();
    }

    Historia historia = historiaList.get(i);
    item.tvNome.setText(  historia.nome );
    item.tvAutor.setText(  historia.autor );
    item.tvAno.setText(  String.valueOf( historia.ano ) );

    try {
        byte[] fotoBytes = Base64.decode(historia.getFoto(), Base64.DEFAULT);
        Bitmap fotoBitmap = BitmapFactory.decodeByteArray(fotoBytes, 0, 10000);
        item.imageLista.setImageBitmap(fotoBitmap);

    }catch (Exception e){

        item.imageLista.setImageResource(R.drawable.comic);

    }




    if( i % 2 == 0 ){
        item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
    }else {
        item.layout.setBackgroundColor( Color.WHITE );
    }
    return convertView;
    }

    private class ItemSuporte{
        TextView tvNome,tvAutor, tvAno;
        ImageView imageLista;

        LinearLayout layout;
    }
}
