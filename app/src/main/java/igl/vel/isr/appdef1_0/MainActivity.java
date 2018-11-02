package igl.vel.isr.appdef1_0;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import igl.vel.isr.appdef1_0.Login.loginActivity;
import igl.vel.isr.appdef1_0.var_per.itemCart;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,DrawerLayout.DrawerListener
{


   private NavigationView navigationView;
   public DrawerLayout drawerLayout;
    String usr;
    String cro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment fragmento = new mainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragmento);
        transaction.commit();

        drawerLayout = findViewById(R.id.Drawer_Layout);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        if(user.getDisplayName() == null)
        {
            usr = getIntent().getStringExtra("nombre");
        }
        else {
            usr = user.getDisplayName();
           }
        cro = user.getEmail();
        navigationView = findViewById(R.id.navi_view);
        View hView = navigationView.getHeaderView(0);
        TextView email = hView.findViewById(R.id.emailh);
        TextView user_name = hView.findViewById(R.id.user_name);

        user_name.setText(usr);
        email.setText(cro);


        navigationView.setNavigationItemSelectedListener(this);

    }

    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.Drawer_Layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected( MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.inicio:
                mainFragment fragmento = new mainFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,fragmento);
                transaction.commit();
                break;

            case R.id.compras:

                Toast.makeText(this, "Compras", Toast.LENGTH_SHORT).show();
                break;

            case R.id.descuentos:
                Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ayuda:
                Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show();
                break;

            case R.id.configuracion:
                Toast.makeText(this, "Configuracion", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cerrar:
                cerrarsec();
                break;

            default:
                break;
        }
        DrawerLayout drawer = findViewById(R.id.Drawer_Layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cerrarsec() {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent intent = new Intent(MainActivity.this, loginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDrawerSlide(@NonNull View view, float v) {
        Toast.makeText(this, "Slide", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerOpened(@NonNull View view) {
        Toast.makeText(this, "Abrir", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        Toast.makeText(this, "Cerrar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerStateChanged(int i) {
        Toast.makeText(this, "State_Changed", Toast.LENGTH_SHORT).show();
    }


}

