package com.quarkum.moham.na2la;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // linking the image view in xml to the main.java
        iv = (ImageView) findViewById(R.id.logo);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        iv.startAnimation(myanim);


        // creating an intent to to take the user from splash screen to the main activity
        // and creating a timer for the splash screen
        final Intent welcomeIntent = new Intent(this, WelcomeActivity.class);
        Thread timer = new Thread() {

            public void run() {
                try {

                    sleep(5000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                } finally {
                    startActivity(welcomeIntent);
                    finish();
                }

            }


        };
        timer.start();

    }//onCreate bracelet
}
