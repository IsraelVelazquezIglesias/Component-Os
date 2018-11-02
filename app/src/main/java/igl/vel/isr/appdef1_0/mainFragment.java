package igl.vel.isr.appdef1_0;

import android.app.SearchManager;
import android.content.Context;
import android.drm.DrmStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import igl.vel.isr.appdef1_0.Store_Fragments.ShopCart;
import igl.vel.isr.appdef1_0.var_per.stringblob;
import java.util.ArrayList;
import igl.vel.isr.appdef1_0.Store_Fragments.Componentes;
import igl.vel.isr.appdef1_0.Store_Fragments.Energia;
import igl.vel.isr.appdef1_0.Store_Fragments.Integrados;
import igl.vel.isr.appdef1_0.Store_Fragments.Leds;
import igl.vel.isr.appdef1_0.Store_Fragments.Resistencias;
import igl.vel.isr.appdef1_0.Store_Fragments.Transistores;
import igl.vel.isr.appdef1_0.Adaptadores.AdapterRecycler;


public class mainFragment extends Fragment {

    @Nullable
    DrawerLayout draw;
    ArrayList<stringblob> ListDatos;
    RecyclerView recycler;
    ActionBarDrawerToggle toggle;
    Menu menu;
    FloatingActionButton actionButton;
    public MainActivity mainActivity;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.main_fragment,container,false);
        mainActivity = (MainActivity) getActivity();
        draw = mainActivity.drawerLayout;

        Toolbar toolbar = v.findViewById(R.id.toolMain);
        mainActivity.setSupportActionBar(toolbar);
        //getActivity().onCreateOptionsMenu()
        setHasOptionsMenu(true);
        toggle = new ActionBarDrawerToggle(mainActivity,mainActivity.drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer);
        mainActivity.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        recycler = v.findViewById(R.id.recycler1);
        recycler.setFocusable(false);
        v.findViewById(R.id.linear_recycler1).requestFocus();
        //recycler.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));

        recycler.setLayoutManager(new GridLayoutManager(v.getContext(),2));

        actionButton = v.findViewById(R.id.action);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                assert fragmentManager != null;
                ShopCart cart = new ShopCart();
                fragmentTransaction.replace(R.id.frame,cart,null);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        ListDatos = new ArrayList<>();
        llenarPantalla();
        AdapterRecycler adapter1 = new AdapterRecycler(ListDatos, 1);
        adapter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnCard(v);
            }
        });
        recycler.setAdapter(adapter1);
        return v;
    }

    private void clickOnCard(View vista) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        assert fragmentManager != null;
        String descripcion = ListDatos.get(recycler.getChildAdapterPosition(vista)).getDesc();
        switch (descripcion)
        {
            case "Integrados":
                Integrados integrados = new Integrados();
                fragmentTransaction.replace(R.id.frame,integrados,null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Transistores":
                Transistores transistores = new Transistores();
                fragmentTransaction.replace(R.id.frame,transistores,null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Resistencias":
                Resistencias resistencias = new Resistencias();
                fragmentTransaction.replace(R.id.frame,resistencias,null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Leds":
                Leds leds = new Leds();
                fragmentTransaction.replace(R.id.frame,leds,null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Componentes":
                Componentes componentes = new Componentes();
                fragmentTransaction.replace(R.id.frame,componentes,null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Energia":
                Energia energia = new Energia();
                fragmentTransaction.replace(R.id.frame,energia,null);
                fragmentTransaction.addToBackStack(null).commit();
                break;

                default:
                    break;
        }
    }

    private void llenarPantalla() {

        ListDatos.add(new stringblob("Integrados",R.drawable.integratedmin));
        ListDatos.add(new stringblob("Transistores",R.drawable.transistorsmin));
        ListDatos.add(new stringblob("Resistencias",R.drawable.resistancemin));
        ListDatos.add(new stringblob("Leds",R.drawable.ledmin));
        ListDatos.add(new stringblob("Componentes",R.drawable.componentesmin));
        ListDatos.add(new stringblob("Energia",R.drawable.energymin));
        ListDatos.add(new stringblob("Arduino",R.drawable.energymin));
        ListDatos.add(new stringblob("PIC'S",R.drawable.energymin));
        ListDatos.add(new stringblob("Optoelectronica",R.drawable.energymin));
        ListDatos.add(new stringblob("Robotica",R.drawable.energymin));
        ListDatos.add(new stringblob("Quimicos",R.drawable.energymin));
        ListDatos.add(new stringblob("Cable",R.drawable.energymin));
        ListDatos.add(new stringblob("Interruptores",R.drawable.energymin));
        ListDatos.add(new stringblob("Herramientas",R.drawable.energymin));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolmain,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
