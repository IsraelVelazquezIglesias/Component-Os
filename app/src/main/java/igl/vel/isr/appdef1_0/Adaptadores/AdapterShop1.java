package igl.vel.isr.appdef1_0.Adaptadores;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import igl.vel.isr.appdef1_0.Entidades.ConexionSQLiteHelper;
import igl.vel.isr.appdef1_0.MainActivity;
import igl.vel.isr.appdef1_0.R;
import igl.vel.isr.appdef1_0.Store_Fragments.ShopCart;
import igl.vel.isr.appdef1_0.Utilidades.Utilidades;
import igl.vel.isr.appdef1_0.var_per.itemCart;
import igl.vel.isr.appdef1_0.var_per.itemshop;

public class AdapterShop1 extends RecyclerView.Adapter<AdapterShop1.ViewHolderDatos> implements View.OnClickListener{
    private ArrayList<itemshop> listItem;
    private Context context;
    //private View.OnClickListener listener;

    public AdapterShop1(Context c, ArrayList<itemshop> listItem)
    {
        this.context = c;
        this.listItem = listItem;
    }
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_shop1,null,false);
        return new ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderDatos viewHolderDatos, int i) {
        viewHolderDatos.nombre.setText(listItem.get(i).getNomb());
        viewHolderDatos.descrip.setText(listItem.get(i).getDesc());
        viewHolderDatos.prec.setText(listItem.get(i).getPrec().toString());

        viewHolderDatos.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.addI:
                    {
                        if(viewHolderDatos.cantidad.getText().toString().isEmpty() || viewHolderDatos.cantidad.getText().toString().matches(" "))
                        {
                            Toast.makeText(v.getContext(), "Empty", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            int cantidad = Integer.parseInt(viewHolderDatos.cantidad.getText().toString());
                            String nomb = viewHolderDatos.descrip.getText().toString();
                            Double prec = Double.parseDouble(viewHolderDatos.prec.getText().toString());
                            String ref = cantidad +" " + nomb;
                            addItem(nomb,prec,cantidad);
                            Toast.makeText(v.getContext(), ref, Toast.LENGTH_SHORT).show();
                            viewHolderDatos.cantidad.setText("");


                        }

                    }break;
                }
            }
        });
    }

    private void addItem(String nombre, Double precio, int cantidad) {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context,"bd_carro",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,nombre);
        values.put(Utilidades.CAMPO_PRECIO,precio);
        values.put(Utilidades.CAMPO_CANTIDAD,cantidad);
        values.put(Utilidades.CAMPO_TOTAL,0);

        Long idresultante = db.insert(Utilidades.TABLA_CARRO,Utilidades.CAMPO_NOMBRE,values);
        Toast.makeText(context, "id registro: "+idresultante, Toast.LENGTH_SHORT).show();
        db.close();

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    @Override
    public void onClick(View v) {

    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombre,descrip,prec;
        EditText cantidad;
        FloatingActionButton add;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreItem);
            descrip = itemView.findViewById(R.id.descItem);
            prec = itemView.findViewById(R.id.precItem);
            cantidad = itemView.findViewById(R.id.cantItem);
            add = itemView.findViewById(R.id.addI);
        }
    }
}
