package igl.vel.isr.appdef1_0.Store_Fragments;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import igl.vel.isr.appdef1_0.Adaptadores.AdapterShop1;
import igl.vel.isr.appdef1_0.MainActivity;
import igl.vel.isr.appdef1_0.R;
import igl.vel.isr.appdef1_0.var_per.itemshop;


/**
 * A simple {@link Fragment} subclass.
 */
public class Integrados extends Fragment {

    private Toolbar toolbar;
    private View vista;
    private MainActivity mainActivity;
    private ActionBarDrawerToggle toggle;
    private TabLayout tabLayout;
    //private FloatingActionButton fab;
    private RecyclerView recycler;

    private ArrayList<itemshop> listItem;
    private FirebaseFirestore database;
    private CollectionReference collectionTorneadas;
    private CollectionReference TTL;

    public static final String NOMBRE_ART = "Nombre";
    public static final String DESCRIPCION_ART = "Descripcion";
    public static final String PRECIO_ART = "Precio";

    public Integrados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.fragment_mostrador, container, false);

        database = FirebaseFirestore.getInstance();
        collectionTorneadas = database.collection("componentes").document("BasesCI").collection("BasesTorneadas");
        TTL = database.collection("componentes").document("CI").collection("TTL");
        recycler = vista.findViewById(R.id.recyclerI);

       // recycler.setLayoutManager(new LinearLayoutManager(vista.getContext(),LinearLayoutManager.VERTICAL,false));

        recycler.setLayoutManager(new GridLayoutManager(vista.getContext(),1));


        llenarBases();
        toolbarset();
        tabLayoutset();
        return vista;
    }

    private void tabLayoutset() {
        tabLayout = vista.findViewById(R.id.tabI);
        crearTabs();
    }

    private void crearTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Bases para C.I"));
        tabLayout.addTab(tabLayout.newTab().setText("C.I TTL"));
        tabLayout.addTab(tabLayout.newTab().setText("CMOS 74HC"));

        tabLayout.addTab(tabLayout.newTab().setText("CMOS CD"));
        tabLayout.addTab(tabLayout.newTab().setText("CMOS HEF"));
        tabLayout.addTab(tabLayout.newTab().setText("Optoacopladores"));

        tabLayout.addTab(tabLayout.newTab().setText("Drivers ULN"));
        tabLayout.addTab(tabLayout.newTab().setText("Puentes H"));
        tabLayout.addTab(tabLayout.newTab().setText("C.I MAX"));

        tabLayout.addTab(tabLayout.newTab().setText("C.I NE"));
        tabLayout.addTab(tabLayout.newTab().setText("C.I LM"));
        tabLayout.addTab(tabLayout.newTab().setText("OP AMP"));

        tabLayout.addTab(tabLayout.newTab().setText("Reguladores de Voltaje"));
        tabLayout.addTab(tabLayout.newTab().setText("GAL'S"));
        tabLayout.addTab(tabLayout.newTab().setText("DAC y ADC"));
        escuchar();
    }

    private void escuchar() {
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString())
                {
                    case "Bases para C.I":
                    {
                        llenarBases();
                    }break;

                    case "C.I TTL":
                    {

                        llenarTtl();
                    }break;

                    case "CMOS 74HC":
                    {


                    }break;

                    case "CMOS CD":
                    {

                    }break;

                    case "CMOS HEF":
                    {


                    }break;

                    case "Optoacopladores":
                    {

                    }break;

                    case "Drivers ULN":
                    {


                    }break;

                    case "Puentes H":
                    {


                    }break;

                    case "C.I MAX":
                    {


                    }break;

                    case "C.I NE":
                    {


                    }break;

                    case "C.I LM":
                    {


                    }break;

                    case "OP AMP":
                    {


                    }break;

                    case "Reguladores de Voltaje":
                    {


                    }break;

                    case "GAL'S":
                    {


                    }break;

                    case "DAC y ADC":
                    {


                    }break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void llenarTtl() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        TTL.orderBy(NOMBRE_ART, Query.Direction.ASCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        listItem = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult())
                        {
                            String nombre = document.getString(NOMBRE_ART);
                            String descripcion = document.getString(DESCRIPCION_ART);
                            Double precio = document.getDouble(PRECIO_ART);
                            listItem.add(new itemshop(nombre,descripcion,precio));
                        }
                        progressDialog.dismiss();
                        AdapterShop1 adapter1 = new AdapterShop1(getContext(),listItem);
                        recycler.setAdapter(adapter1);
                    }
                });
    }


    private void llenarBases() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        collectionTorneadas.orderBy(PRECIO_ART, Query.Direction.ASCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        listItem = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult())
                        {
                            String nombre = document.getString(NOMBRE_ART);
                            String descripcion = document.getString(DESCRIPCION_ART);
                            Double precio = document.getDouble(PRECIO_ART);
                            listItem.add(new itemshop(nombre,descripcion,precio));
                        }
                        progressDialog.dismiss();
                        AdapterShop1 adapter1 = new AdapterShop1(getContext(),listItem);
                        recycler.setAdapter(adapter1);
                    }
                });



    }

    private void toolbarset() {
        //fab = vista.findViewById(R.id.actionb);
        Toolbar toolbar = vista.findViewById(R.id.toolMain);
        toolbar.setBackgroundColor(Color.parseColor("#43a047"));
        toolbar.setTitle("Circuitos Integrados");
        mainActivity = (MainActivity) getActivity();
        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                igl.vel.isr.appdef1_0.mainFragment mainFragment = new igl.vel.isr.appdef1_0.mainFragment();
                fragmentTransaction.replace(R.id.frame,mainFragment,null);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolfragments,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.cart:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                assert fragmentManager != null;
                ShopCart cart = new ShopCart();
                fragmentTransaction.replace(R.id.frame,cart,null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
