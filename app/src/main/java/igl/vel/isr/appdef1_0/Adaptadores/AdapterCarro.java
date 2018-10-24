package igl.vel.isr.appdef1_0.Adaptadores;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import igl.vel.isr.appdef1_0.Entidades.ConexionSQLiteHelper;
import igl.vel.isr.appdef1_0.R;
import igl.vel.isr.appdef1_0.Store_Fragments.ShopCart;
import igl.vel.isr.appdef1_0.Utilidades.Utilidades;
import igl.vel.isr.appdef1_0.var_per.itemCart;

public class AdapterCarro extends RecyclerView.Adapter<AdapterCarro.ViewHolderDatos>{

    ArrayList<itemCart> list;
    Context context;
    ConexionSQLiteHelper conn;
    View vista;


    public AdapterCarro(Context c, ArrayList<itemCart> lista, View vista)
    {
        this.context = c;
        this.list = lista;
        this.vista = vista;
    }



    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_cart1,null,false);

        return new ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderDatos viewHolderDatos, final int i) {
        viewHolderDatos.nombre.setText(list.get(i).getNombre());
        viewHolderDatos.precio.setText(list.get(i).getPrecio().toString());
        viewHolderDatos.cantidad.setText(String.valueOf(list.get(i).getCantidad()));
        Double precioTotal = (list.get(i).getPrecio()) * (list.get(i).getCantidad());
        setSubs(viewHolderDatos, i, precioTotal);
        viewHolderDatos.cantidad.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {

                    actualizar(viewHolderDatos,i);
                    return true;
                }
                return false;
            }
        });
        viewHolderDatos.cantidad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    actualizar(viewHolderDatos, i);
                }
            }
        });

        viewHolderDatos.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.add1:
                    {
                        int inicial = Integer.parseInt(viewHolderDatos.cantidad.getText().toString());
                        int fin = inicial+1;
                        viewHolderDatos.cantidad.setText(String.valueOf(fin));
                        actualizar(viewHolderDatos,i);
                    }break;
                }
            }
        });

        viewHolderDatos.rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.rest1:
                    {
                        int inicial = Integer.parseInt(viewHolderDatos.cantidad.getText().toString());
                        int fina = inicial-1;
                        if(inicial <= 1)
                        {
                            fina = 1;
                        }
                        if(fina <= 1)
                        {
                            fina = 1;
                        }
                        viewHolderDatos.cantidad.setText(String.valueOf(fina));
                        actualizar(viewHolderDatos,i);
                    }break;
                }
            }
        });

        viewHolderDatos.dell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.dellT:
                    {
                        list.remove(i);
                        remove(viewHolderDatos);
                    }break;
                }
            }
        });
        ShopCart act = new ShopCart();

        act.subTotales(context, vista);

    }

    private void setSubs(ViewHolderDatos viewHolderDatos, int i, Double subTotal) {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context,"bd_carro",null,1);
        String[] parametros = {viewHolderDatos.nombre.getText().toString()};
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(subTotal <= 0.0 )
        {
            subTotal = 0.0;
        }

        values.put(Utilidades.CAMPO_TOTAL,subTotal);
        db.update(Utilidades.TABLA_CARRO,values,Utilidades.CAMPO_NOMBRE + "=?",parametros);
        db.close();
        list.get(i).setSubTotal(subTotal);
        viewHolderDatos.precioTot.setText(subTotal.toString());
        ShopCart act = new ShopCart();
        act.subTotales(context, vista);


    }

    private void remove(ViewHolderDatos viewHolderDatos) {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context,"bd_carro",null,1);
        String[] parametros = {viewHolderDatos.nombre.getText().toString()};
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete(Utilidades.TABLA_CARRO,Utilidades.CAMPO_NOMBRE+"=?",parametros);
        Toast.makeText(context, "Borrando...", Toast.LENGTH_SHORT).show();
        db.close();
        notifyDataSetChanged();

        ShopCart act = new ShopCart();
        act.subTotales(context, vista);

    }

    private void actualizar(ViewHolderDatos viewHolderDatos, int i) {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context,"bd_carro",null,1);
        String[] parametros = {viewHolderDatos.nombre.getText().toString()};
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        int cantidad = Integer.parseInt(viewHolderDatos.cantidad.getText().toString());
        if(cantidad <= 0)
        {
            cantidad = 1;
            viewHolderDatos.cantidad.setText(String.valueOf(cantidad));
        }
        values.put(Utilidades.CAMPO_CANTIDAD,cantidad);
        db.update(Utilidades.TABLA_CARRO,values,Utilidades.CAMPO_NOMBRE + "=?",parametros);
        db.close();

        list.get(i).setCantidad(cantidad);
        Double precioTotal = (list.get(i).getPrecio()) * (list.get(i).getCantidad());
        setSubs(viewHolderDatos,i,precioTotal);

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView precio;
        TextView precioTot;
        EditText cantidad;
        ImageButton add;
        ImageButton rest;
        ImageButton dell;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre  = itemView.findViewById(R.id.NombreCart);
            precio = itemView.findViewById(R.id.PrecioCart);
            precioTot = itemView.findViewById(R.id.PrecioTCart);
            cantidad = itemView.findViewById(R.id.CantCart);
            add = itemView.findViewById(R.id.add1);
            rest = itemView.findViewById(R.id.rest1);
            dell = itemView.findViewById(R.id.dellT);

        }
    }

}
