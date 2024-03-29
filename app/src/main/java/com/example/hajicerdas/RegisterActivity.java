package com.example.hajicerdas;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import model.User;
import utils.SqliteHelper;

public class RegisterActivity extends AppCompatActivity{

    //Declaration EditTexts
    EditText editTextUserName;
    EditText editTextNohp;
    EditText editTextNoporsi;
    EditText editTextPassword;

    //Declaration TextInputLayout

    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutNohp;
    TextInputLayout textInputLayoutNoporsi;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonRegister;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = editTextUserName.getText().toString();
                    String Nohp = editTextNohp.getText ().toString ();
                    String Noporsi = editTextNoporsi.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Check in the database is there any user associated with  this email
                    // Stoped check email
                    if (!sqliteHelper.isEmailExists(Noporsi)) {


                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User (null, UserName, Nohp, Noporsi, Password));
                        Snackbar.make(buttonRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                        System.out.print ( "check" );
//                        Toast.makeText(getApplicationContext(),"Berhasil Terdaftar",Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Snackbar.make(buttonRegister, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    //this method used to set Login TextView click event
    private void initTextViewLogin() {
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextNoporsi = (EditText) findViewById(R.id.editTextNoporsi);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextNohp = (EditText) findViewById(R.id.editTextNohp);
        textInputLayoutNoporsi = (TextInputLayout) findViewById(R.id.textInputLayoutNoporsi);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        textInputLayoutNohp = (TextInputLayout) findViewById(R.id.textInputLayoutNohp);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = editTextUserName.getText().toString();
        String Noporsi = editTextNoporsi.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            textInputLayoutUserName.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                textInputLayoutUserName.setError(null);
            } else {
                valid = false;
                textInputLayoutUserName.setError("Username is to short!");
            }
        }

//        //Handling validation for Email field
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
//            valid = false;
//            textInputLayoutEmail.setError("Please enter valid email!");
//        } else {
//            valid = true;
//            textInputLayoutEmail.setError(null);
//        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }


        return valid;
    }
}
