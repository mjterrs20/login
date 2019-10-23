package com.example.hajicerdas;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_aplikasi;
    private Button bt_companyProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        bt_aplikasi = (Button)findViewById(R.id.bt_aplikasi);
        bt_aplikasi.setOnClickListener(this);
        bt_companyProfile = (Button)findViewById(R.id.bt_companyProfile);
        bt_companyProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_companyProfile:
                String url = "http://www.haji.kemenag.go.id";
                Intent browser = new Intent(Intent.ACTION_VIEW);
                browser.setData(Uri.parse(url));
                startActivity(browser);
                break;
            case R.id.bt_aplikasi:
                Intent aplikasi = new Intent(MainActivity.this, AplikasiActivity.class);
                startActivity(aplikasi);
                break;
        }
    }
}
