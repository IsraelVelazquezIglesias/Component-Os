package igl.vel.isr.appdef1_0.Store_Fragments;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import igl.vel.isr.appdef1_0.Adaptadores.AdapterCarro;
import igl.vel.isr.appdef1_0.Entidades.ConexionSQLiteHelper;
import igl.vel.isr.appdef1_0.MainActivity;
import igl.vel.isr.appdef1_0.R;
import igl.vel.isr.appdef1_0.Utilidades.Utilidades;
import igl.vel.isr.appdef1_0.var_per.itemCart;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopCart extends Fragment implements View.OnClickListener {

MainActivity mainActivity;
Button pagar;
RecyclerView recyclerView;

TextView subTotal;
TextView envioT;
TextView descuentoT;
TextView totalFT;


ArrayList<itemCart> listaCompras;
ConexionSQLiteHelper conn;
Double total;


    public ShopCart() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        total = 0.0;

        subTotal = vista.findViewById(R.id.subTotal);
        envioT = vista.findViewById(R.id.costoEnvio);
        descuentoT = vista.findViewById(R.id.promociones);
        totalFT = vista.findViewById(R.id.totalT);

        pagar = vista.findViewById(R.id.PagarC);

        recyclerView = vista.findViewById(R.id.RecyclerCarro);
        Toolbar toolbar = vista.findViewById(R.id.toolCarro);
        mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( getFragmentManager() != null) {

                    getFragmentManager().popBackStack();
                }
            }
        });
        pagar.setOnClickListener(this);
        conn = new ConexionSQLiteHelper(getContext(),"bd_carro",null,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(vista.getContext(),LinearLayoutManager.VERTICAL,false));
        conultarBaseDatos(vista);
        AdapterCarro adapter1 = new AdapterCarro(getContext(),listaCompras,vista);
        recyclerView.setAdapter(adapter1);
        return vista;
    }



    public void subTotales(Context context, View vista)
    {

        TextView su;
        TextView e;
        TextView d;
        TextView tFT;

        Double sT =0.0;
        Integer index = 0;
        Integer envio = 0;
        Double desc = 0.0;
        Double tot = 0.0;

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(context,"bd_carro",null,1);
        SQLiteDatabase database =  conexion.getReadableDatabase();
        ArrayList<Double> listaSubTotales;
        listaSubTotales = new ArrayList<>();
        Cursor cur =  database.rawQuery(" SELECT * FROM "+Utilidades.TABLA_CARRO,null);
        while(cur.moveToNext())
        {
            Double s = cur.getDouble(3);
            listaSubTotales.add(s);
        }

        Iterator iterator = listaSubTotales.iterator();

        if(listaSubTotales.size()<=0)
        {
            sT = 0.0;

        }
        if(listaSubTotales.size() == 1)
        {
            sT = listaSubTotales.get(0);
        }
        else {
            for (index = 0; index < listaSubTotales.size(); index++) {
                sT = sT + listaSubTotales.get(index);
            }
        }
        //subTotal.setText(String.valueOf(sT));

        su = vista.findViewById(R.id.subTotal);
        e = vista.findViewById(R.id.costoEnvio);
        d = vista.findViewById(R.id.promociones);
        tFT = vista.findViewById(R.id.totalT);


        if(listaSubTotales.size()<=0)
        {
            envio = 0;
        }
        else if(sT <= 150)
        {
            envio = 50;
        }
        else
        {
            envio = 0;
        }
        e.setText(String.valueOf(envio));
        d.setText(String.valueOf(desc));
        su.setText(String.valueOf(sT));
        tot = (sT + envio) - desc;
        tFT.setText(String.valueOf(tot));

    }


    private void conultarBaseDatos(View vista) {
        SQLiteDatabase db = conn.getReadableDatabase();
        itemCart item = null;
        listaCompras = new ArrayList<itemCart>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_CARRO,null);

        while(cursor.moveToNext())
        {
            item = new itemCart();
            item.setNombre(cursor.getString(0));
            item.setPrecio(cursor.getDouble(1));
            item.setCantidad(cursor.getInt(2));
            item.setSubTotal(cursor.getDouble(3));

            listaCompras.add(item);
        }

        subTotales(getContext(),vista);

        db.close();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.PagarC:
            {

            }break;
        }

    }
}
