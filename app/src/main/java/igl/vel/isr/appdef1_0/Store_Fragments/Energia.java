package igl.vel.isr.appdef1_0.Store_Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import igl.vel.isr.appdef1_0.MainActivity;
import igl.vel.isr.appdef1_0.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Energia extends Fragment {
    View vista;
    MainActivity mainActivity;
    TabLayout tabLayout;
    FloatingActionButton fab;

    public Energia() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_mostrador, container, false);
        toolbarset();
        tabLayoutset();
        return vista;
    }

    private void tabLayoutset() {
        tabLayout = vista.findViewById(R.id.tabI);
        crearTabs();
    }

    private void crearTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("BATERIAS ACIDO-PLOMO"));
        tabLayout.addTab(tabLayout.newTab().setText("PILAS RECARGABLES"));
        tabLayout.addTab(tabLayout.newTab().setText("PILAS ALCALINAS Y ZINC-CARBON"));
        tabLayout.addTab(tabLayout.newTab().setText("PILAS DE BOTON"));
        tabLayout.addTab(tabLayout.newTab().setText("CARGADORES DE BATERIAS"));
        tabLayout.addTab(tabLayout.newTab().setText("ELIMINADORES VCC"));
        tabLayout.addTab(tabLayout.newTab().setText("VENTILADORES VCC Y VCA"));
        tabLayout.addTab(tabLayout.newTab().setText("PORTAPILAS"));
        tabLayout.addTab(tabLayout.newTab().setText("CABLES DE ENERGIA"));
        tabLayout.addTab(tabLayout.newTab().setText("CAIMANES"));
    }

    private void toolbarset() {
        Toolbar toolbar = vista.findViewById(R.id.toolMain);
        toolbar.setBackgroundColor(Color.parseColor("#43a047"));
        toolbar.setTitle("Energia");
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

        /*toggle = new ActionBarDrawerToggle(mainActivity,mainActivity.drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer);
        mainActivity.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();*/
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
