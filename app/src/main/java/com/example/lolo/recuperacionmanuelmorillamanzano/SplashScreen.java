package com.example.lolo.recuperacionmanuelmorillamanzano;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Lanzaremos despues de 3000ms(3s), el MainActivity
                Intent intent=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();//para que si le das para atras no vuelva a salir la splash

            }
        }, 3000);
    }
}
