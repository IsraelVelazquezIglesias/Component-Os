package igl.vel.isr.appdef1_0.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import igl.vel.isr.appdef1_0.MainActivity;
import igl.vel.isr.appdef1_0.R;

public class loginActivity extends AppCompatActivity {

    //Boolean band =false;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        fragment_login1 fragment_login = new fragment_login1();
        Intent intent3 = new Intent(this, MainActivity.class);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            startActivity(intent3);
            this.finish();
        }
        else
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.loginframelay, fragment_login, null);
            fragmentTransaction.commit();
        }
        /*cargarPreferencias();

        if(band ==false) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.loginframelay, fragment_login, null);
            fragmentTransaction.commit();
        }

        else if(band ==true){
            startActivity(intent3);
            this.finish();
        }

    }

    private void cargarPreferencias() {

        SharedPreferences login = getSharedPreferences("Tag", Context.MODE_PRIVATE);

        band = login.getBoolean("Bandera",false);
    }*/
    }
}