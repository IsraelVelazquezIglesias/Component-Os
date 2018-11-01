package igl.vel.isr.appdef1_0.University_Menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import igl.vel.isr.appdef1_0.Adaptadores.AdapterRecycler;
import igl.vel.isr.appdef1_0.MainActivity;
import igl.vel.isr.appdef1_0.R;
import igl.vel.isr.appdef1_0.var_per.stringblob;

public class UniversityActivity extends AppCompatActivity {

    private RecyclerView seleccion;
    private ArrayList<stringblob> listEscuelas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);

        set_toolbar();
        set_recycler();
    }

    private void set_toolbar() {

        Toolbar toolbar = findViewById(R.id.toolUni);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Universidades");
    }

    private void set_recycler() {
        seleccion = (RecyclerView) findViewById(R.id.SeleccionUniversidad);
        seleccion.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listEscuelas = new ArrayList<>();
        llenarEscuelas();
        AdapterRecycler adapterRecycler = new AdapterRecycler(listEscuelas, 2);
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
        listEscuelas.add(new stringblob("Escuela Superior de Ingeniería Química e Industrias Extractivas", R.drawable.esiqielogo01));
        listEscuelas.add(new stringblob("Escuela Superior de Física y Matematicas", R.drawable.esfmlogo));
    }

    private void clickOnCard(View v) {

        String descripcion = listEscuelas.get(seleccion.getChildAdapterPosition(v)).getDesc();
        switch (descripcion) {

            case "Escuela Superior de Ingeniería Mecánica y Eléctrica": {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            break;

            case "Escuela Superior de Ingeniería Química e Industrias Extractivas": {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            break;

            case "Escuela Superior de Física y Matematicas": {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            break;

            default: {

            }
            break;

        }

    }

}