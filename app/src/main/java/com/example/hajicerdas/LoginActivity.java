package com.example.hajicerdas;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import model.User;
import utils.SqliteHelper;

public class LoginActivity extends AppCompatActivity{

    //Declaration EditTexts
    EditText editTextNoporsi;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutNoporsi;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        //set click event of login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (validate()) {
                    Intent intent=new Intent (LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });


    }

    //this method used to set Create account TextView text and click event( maltipal colors
    // for TextView yet not supported in Xml so i have done it programmatically)
    private void initCreateAccountTextView() {
//        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
//        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });

        TextView textViewLupaPass = (TextView) findViewById(R.id.textViewLupaPass);
        textViewLupaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextNoporsi = (EditText) findViewById(R.id.editTextNoporsi);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutNoporsi = (TextInputLayout) findViewById(R.id.textInputLayoutNoporsi);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    //This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;


        String Noporsi =  editTextNoporsi.getText().toString();
        String Password = editTextPassword.getText().toString();

        if (Noporsi.isEmpty()) {
            valid = false;
            textInputLayoutNoporsi.setError("Masukkan No Porsi Anda");
        } else {
            if (Noporsi.equals ( "1300498134" ) ) {
                valid = true;
                textInputLayoutNoporsi.setError(null);
            } else {
                valid = false;
                textInputLayoutNoporsi.setError("No porsi Salah");
            }
        }


        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.equals ( "123456789" ) ) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password Salah");
            }
        }

        return valid;
    }
}
