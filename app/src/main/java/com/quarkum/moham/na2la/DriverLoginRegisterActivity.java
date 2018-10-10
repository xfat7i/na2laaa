package com.quarkum.moham.na2la;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginRegisterActivity extends AppCompatActivity {

    // creating variables for the buttons and textviews in driver activity login register
    private Button driverLoginButton;
    private Button driverRegisterButton;
    private TextView driverRegisterLink;
    private TextView driverStatus;
    private EditText driverEmailField;
    private EditText driverPasswordField;
    private ProgressDialog loadingBar;

    // adding firebase
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);

        // getting instance from firebase
        mAuth = FirebaseAuth.getInstance();


        // casting the private variables to link it to the buttons and textviews in the xml link

        driverLoginButton = (Button) findViewById(R.id.driver_login_btn);
        driverRegisterButton = (Button) findViewById(R.id.driver_register_btn);
        driverRegisterLink = (TextView) findViewById(R.id.driver_register_link);
        driverStatus = (TextView) findViewById(R.id.driver_status);
        driverEmailField = (EditText) findViewById(R.id.driver_email_field);
        driverPasswordField = (EditText) findViewById(R.id.driver_password_field);


        driverRegisterButton.setVisibility(View.INVISIBLE); // hiding the register button
        driverRegisterButton.setEnabled(false);             // disable the button


        // creating onclick listener for registration link
        driverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                driverLoginButton.setVisibility(View.INVISIBLE);  // hiding the login button
                driverRegisterLink.setVisibility(View.INVISIBLE); // hiding registration link
                driverRegisterButton.setVisibility(View.VISIBLE); //unhide register button
                driverRegisterButton.setEnabled(true);            // enabling the button
                driverStatus.setText("Register Driver");          // to change the text under the logo when the link is pressed
            }
        });

// creating on click listener to prepare the registration button to receive email and password from text field
        driverRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = driverEmailField.getText().toString();
                String password = driverPasswordField.getText().toString();

                RegisterDriver(email, password);
            }
        });


    }

    private void RegisterDriver(String email, String password) {

        // if the user doesn't enter email he will get a toast
        if (TextUtils.isEmpty(email)) {

            Toast.makeText(DriverLoginRegisterActivity.this, "Please Write Your email", Toast.LENGTH_SHORT).show();
        }

        // if the user doesn't enter password he will get a toast

        if (TextUtils.isEmpty(password)) {

            Toast.makeText(DriverLoginRegisterActivity.this, "Please Write a password", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Driver Registration"); // applying loading bar before the firebase auth
            loadingBar.setMessage("Kindly wait until the process is complete");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver Register Was Successful", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss(); // to remove the loading bar after the registration is complete
                            } else {

                                Toast.makeText(DriverLoginRegisterActivity.this, "Registeration Unsuccessful", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss(); // to remove the loading bar if the registration is Not complete

                            }

                        }
                    });

        }
    }
}
