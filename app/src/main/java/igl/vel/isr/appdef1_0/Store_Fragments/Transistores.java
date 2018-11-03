package igl.vel.isr.appdef1_0.Store_Fragments;


import android.graphics.Color;
import android.os.Bundle;
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
public class Transistores extends Fragment {
    View vista;
    MainActivity mainActivity;
    TabLayout tabLayout;

    public Transistores() {
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
        tabLayout.setBackgroundColor(Color.parseColor("#76d275"));
        crearTabs();
    }

    private void crearTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("DISIPADORES DE CALOR"));
        tabLayout.addTab(tabLayout.newTab().setText("TIP"));
        tabLayout.addTab(tabLayout.newTab().setText("BC"));

        tabLayout.addTab(tabLayout.newTab().setText("BD"));
        tabLayout.addTab(tabLayout.newTab().setText("BT"));
        tabLayout.addTab(tabLayout.newTab().setText("F-FET"));

        tabLayout.addTab(tabLayout.newTab().setText("IRF"));
        tabLayout.addTab(tabLayout.newTab().setText("2N"));
        tabLayout.addTab(tabLayout.newTab().setText("MJE"));

        tabLayout.addTab(tabLayout.newTab().setText("MPS"));
        tabLayout.addTab(tabLayout.newTab().setText("BUH"));
        tabLayout.addTab(tabLayout.newTab().setText("2SA,2CB,2SC,2SD,2SK,3DD"));
    }

    private void toolbarset() {
        Toolbar toolbar = vista.findViewById(R.id.toolMain);
        toolbar.setBackgroundColor(Color.parseColor("#43a047"));
        toolbar.setTitle("Transistores");
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
