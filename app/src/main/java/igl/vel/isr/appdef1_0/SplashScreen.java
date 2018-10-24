package igl.vel.isr.appdef1_0;

import android.content.Intent;
import android.os.Handler;
import android.support.design.animation.AnimationUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import igl.vel.isr.appdef1_0.Login.loginActivity;

public class SplashScreen extends AppCompatActivity {
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imagen = findViewById(R.id.splashImage);
       Animation animationin = android.view.animation.AnimationUtils.loadAnimation(this,R.anim.blink_anim);
       imagen.startAnimation(animationin);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}
