package igl.vel.isr.appdef1_0.University_Menus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import igl.vel.isr.appdef1_0.Adaptadores.AdapterRecycler;
import igl.vel.isr.appdef1_0.R;
import igl.vel.isr.appdef1_0.var_per.stringblob;

public class UniversityActivity extends AppCompatActivity {

    private RecyclerView seleccion;
    private ArrayList<stringblob> listEscuelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);

        seleccion = (RecyclerView) findViewById(R.id.SeleccionUniversidad);

        seleccion.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listEscuelas = new ArrayList<>();
        llenarEscuelas();
        AdapterRecycler adapterRecycler = new AdapterRecycler(listEscuelas);
        adapterRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnCard(v);
            }
        });
        seleccion.setAdapter(adapterRecycler);

    }

    private void llenarEscuelas() {

        listEscuelas.add(new stringblob("Escuela Superior de Ingeniería Mecánica y Eléctrica", R.drawable.esimelogo));
        listEscuelas.add(new stringblob("Escuela Superior de Ingeniería Química e Industrias Extractivas", R.drawable.esiqielogo));
    }

    private void clickOnCard(View v) {
    }

}