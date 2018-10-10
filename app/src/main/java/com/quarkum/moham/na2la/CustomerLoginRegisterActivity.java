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

public class CustomerLoginRegisterActivity extends AppCompatActivity {

    // creating variables for the buttons in cst activity login register
    private Button cstLoginButton;
    private Button cstRegisterButton;
    private TextView cstRegisterLink;
    private TextView cstStatus;
    private EditText cstEmailField;
    private EditText cstPasswordField;
    private ProgressDialog loadingBar;

    // adding firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        // getting instance from firebase
        mAuth = FirebaseAuth.getInstance();

        // casting the private variables to link it to the buttons and textviews in the xml link

        cstLoginButton = (Button) findViewById(R.id.cst_login_btn);
        cstRegisterButton = (Button) findViewById(R.id.cst_register_btn);
        cstRegisterLink = (TextView) findViewById(R.id.cst_register_link);
        cstStatus = (TextView) findViewById(R.id.cst_status);
        cstEmailField = (EditText) findViewById(R.id.cst_email_field);
        cstPasswordField = (EditText) findViewById(R.id.cst_password_field);
        loadingBar = new ProgressDialog(this);


        cstRegisterButton.setVisibility(View.INVISIBLE); // hiding the register button
        cstRegisterButton.setEnabled(false);             // disable the button


        // creating onclick listener for registration link
        cstRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cstLoginButton.setVisibility(View.INVISIBLE);  // hiding the login button
                cstRegisterLink.setVisibility(View.INVISIBLE); // hiding registration link
                cstRegisterButton.setVisibility(View.VISIBLE); //unhide register button
                cstRegisterButton.setEnabled(true);            // enabling the button
                cstStatus.setText("Register Customer");        // to change the text under the logo when the link is pressed
            }
        });


        // creating on click listener to prepare the registration button to receive email and password from text field
        cstRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = cstEmailField.getText().toString();
                String password = cstPasswordField.getText().toString();

                RegisterCustomer(email, password);
            }
        });


    }

    private void RegisterCustomer(String email, String password) {


        // if the user doesn't enter email he will get a toast
        if (TextUtils.isEmpty(email)) {

            Toast.makeText(CustomerLoginRegisterActivity.this, "Please Write Your email", Toast.LENGTH_SHORT).show();
        }

        // if the user doesn't enter password he will get a toast

        if (TextUtils.isEmpty(password)) {

            Toast.makeText(CustomerLoginRegisterActivity.this, "Please Write a password", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Customer Registration"); // applying loading bar before the firebase auth
            loadingBar.setMessage("Kindly wait until the process is complete");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Register Was Successful", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss(); // to remove the loading bar after the registration is complete
                            } else {

                                Toast.makeText(CustomerLoginRegisterActivity.this, "Registeration Unsuccessful", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss(); // to remove the loading bar if the registration is Not complete

                            }

                        }
                    });


        }

    }


}
