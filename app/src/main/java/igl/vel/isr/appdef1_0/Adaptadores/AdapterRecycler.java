package igl.vel.isr.appdef1_0.Adaptadores;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import igl.vel.isr.appdef1_0.R;
import igl.vel.isr.appdef1_0.var_per.stringblob;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolderDatos> implements View.OnClickListener{

    private ArrayList<stringblob> ListDatos;
    private View.OnClickListener listener;

    public AdapterRecycler(ArrayList<stringblob> listDatos) {
        this.ListDatos = listDatos;
    }
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_items1,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos viewHolderDatos, int i) {
            viewHolderDatos.dat.setText(ListDatos.get(i).getDesc());
            viewHolderDatos.img.setImageResource(ListDatos.get(i).getImg());
    }

    @Override
    public int getItemCount() {
        return ListDatos.size();
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null)
        {
            listener.onClick(v);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dat;
        ImageView img;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            dat = itemView.findViewById(R.id.Dat);
            img = itemView.findViewById(R.id.id_Img);
        }
    }





}
