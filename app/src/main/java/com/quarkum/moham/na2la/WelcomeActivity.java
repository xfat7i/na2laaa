package com.quarkum.moham.na2la;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    // creating variables for buttons on the welome page
    private Button driverBtn;
    private Button cstBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // assigning the private variables to the xml button
        driverBtn = (Button) findViewById(R.id.driver_btn);
        cstBtn = (Button) findViewById(R.id.cst_btn);

        // this is to take us from welcome page to the login and registration page by setting on click listener
        // driver button
        driverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent LoginRegisterCustomerIntent = new Intent(WelcomeActivity.this, DriverLoginRegisterActivity.class);
                startActivity(LoginRegisterCustomerIntent);

            }

        });

        // this is to take us from welcome page to the login and registration page by setting on click listener
        // customer button
        cstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginRegisterCustomerIntent = new Intent(WelcomeActivity.this,CustomerLoginRegisterActivity.class);
                startActivity(LoginRegisterCustomerIntent);
            }
        });
    }
}
