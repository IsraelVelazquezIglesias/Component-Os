package igl.vel.isr.appdef1_0;

import android.app.SearchManager;
import android.content.Context;
import android.drm.DrmStore;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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

import igl.vel.isr.appdef1_0.Adaptadores.ImageAdapter;
import igl.vel.isr.appdef1_0.Store_Fragments.Arduino;
import igl.vel.isr.appdef1_0.Store_Fragments.Cable;
import igl.vel.isr.appdef1_0.Store_Fragments.Herramienta;
import igl.vel.isr.appdef1_0.Store_Fragments.Interruptores;
import igl.vel.isr.appdef1_0.Store_Fragments.Optoelectronica;
import igl.vel.isr.appdef1_0.Store_Fragments.Pics;
import igl.vel.isr.appdef1_0.Store_Fragments.Quimicos;
import igl.vel.isr.appdef1_0.Store_Fragments.Robotica;
import igl.vel.isr.appdef1_0.Store_Fragments.ShopCart;
import igl.vel.isr.appdef1_0.var_per.stringblob;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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

    public MainActivity mainActivity;
    View v;
    ViewPager viewPager;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.main_fragment, container, false);
        mainActivity = (MainActivity) getActivity();
        draw = mainActivity.drawerLayout;
        viewPager = v.findViewById(R.id.imageMain);
        ImageAdapter imageAdapter = new ImageAdapter(v.getContext());
        viewPager.setPageTransformer(true, new Efectos());
        viewPager.setAdapter(imageAdapter);
        Toolbar toolbar = v.findViewById(R.id.toolMain);
        toolbar.setBackgroundColor(Color.parseColor("#43a047"));
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

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 5000, 5000);
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
            case "Arduino":
                Arduino arduino = new Arduino();
                fragmentTransaction.replace(R.id.frame, arduino, null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "PIC'S":
                Pics pics = new Pics();
                fragmentTransaction.replace(R.id.frame, pics, null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Optoelectronica":
                Optoelectronica opto = new Optoelectronica();
                fragmentTransaction.replace(R.id.frame, opto, null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Robotica":
                Robotica robotica = new Robotica();
                fragmentTransaction.replace(R.id.frame, robotica, null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Quimicos":
                Quimicos quimicos = new Quimicos();
                fragmentTransaction.replace(R.id.frame, quimicos, null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Cable":
                Cable cable = new Cable();
                fragmentTransaction.replace(R.id.frame, cable, null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Interruptores":
                Interruptores interruptores = new Interruptores();
                fragmentTransaction.replace(R.id.frame, interruptores, null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case "Herramientas":
                Herramienta herramienta = new Herramienta();
                fragmentTransaction.replace(R.id.frame, herramienta, null);
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
        ListDatos.add(new stringblob("Arduino", R.drawable.arduinoic));
        ListDatos.add(new stringblob("PIC'S", R.drawable.pics));
        ListDatos.add(new stringblob("Optoelectronica", R.drawable.optpelectronica));
        ListDatos.add(new stringblob("Robotica", R.drawable.robotica));
        ListDatos.add(new stringblob("Quimicos", R.drawable.quimicos));
        ListDatos.add(new stringblob("Cable", R.drawable.cable));
        ListDatos.add(new stringblob("Interruptores", R.drawable.interruptores));
        ListDatos.add(new stringblob("Herramientas", R.drawable.herramientas));

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
            case R.id.cart:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                assert fragmentManager != null;
                ShopCart cart = new ShopCart();
                fragmentTransaction.replace(R.id.frame, cart, null);
                fragmentTransaction.addToBackStack(null).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public class Efectos implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(@NonNull View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();
            if (position < -1) {
                view.setAlpha(0f);
            } else if (position <= 5) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }


}
