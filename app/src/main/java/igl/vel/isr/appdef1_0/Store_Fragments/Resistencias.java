package igl.vel.isr.appdef1_0.Store_Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import igl.vel.isr.appdef1_0.MainActivity;
import igl.vel.isr.appdef1_0.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Resistencias extends Fragment {
    View vista;
    MainActivity mainActivity;
    TabLayout tabLayout;
    FloatingActionButton fab;

    public Resistencias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_resistencias, container, false);
        toolbarset();
        tabLayoutset();
        return vista;
    }

    private void tabLayoutset() {
        tabLayout = vista.findViewById(R.id.tabR);
        crearTabs();
    }

    private void crearTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("CARBON 1/4 W"));
        tabLayout.addTab(tabLayout.newTab().setText("CARBON 1/2 W"));
        tabLayout.addTab(tabLayout.newTab().setText("CARBON 1 W"));
        tabLayout.addTab(tabLayout.newTab().setText("CARBON PRECISION 1/4 W"));
        tabLayout.addTab(tabLayout.newTab().setText("ALAMBRE 2 W"));
        tabLayout.addTab(tabLayout.newTab().setText("ALAMBRE 5 W"));
        tabLayout.addTab(tabLayout.newTab().setText("ALAMBRE 10 W"));
        tabLayout.addTab(tabLayout.newTab().setText("ALAMBRE 25 W"));

    }

    private void toolbarset() {
        fab = vista.findViewById(R.id.actionbR);
        Toolbar toolbar = vista.findViewById(R.id.toolRes);
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
