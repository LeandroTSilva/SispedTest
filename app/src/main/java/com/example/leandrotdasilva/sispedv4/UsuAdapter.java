package com.example.leandrotdasilva.sispedv4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by leandrot.dasilva on 09/04/2018.
 */

public class UsuAdapter extends ArrayAdapter<Usuario> {

    private final Context context;
    private final List<Usuario> elementos;

    public UsuAdapter(Context context, List<Usuario> elementos){
        super(context,R.layout.linha,elementos);
        this.context = context;
        this.elementos = elementos;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.linha, parent, false);

        TextView nome = rowView.findViewById(R.id.nome_usuario);
        TextView perfil =  rowView.findViewById(R.id.perfil_usuario);
        TextView id =  rowView.findViewById(R.id.id_usuario);


        nome.setText(elementos.get(position).getUsuNome());
        perfil.setText(elementos.get(position).getUsuPerfil());
        id.setText(Integer.toString(elementos.get(position).getUsuId()));

        return rowView;

        }
}



