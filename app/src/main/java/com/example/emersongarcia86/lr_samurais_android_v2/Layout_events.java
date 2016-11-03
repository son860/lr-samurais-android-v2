package com.example.emersongarcia86.lr_samurais_android_v2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Layout_events extends AppCompatActivity {

    private Button btn_qrcode;
    private Button btn_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_events);

        btn_qrcode = (Button) findViewById(R.id.btn_qrcode);
        btn_codigo = (Button) findViewById(R.id.btn_codigo);

        btn_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent qrcode_intent = new Intent(Layout_events.this,Layout_qrcode.class);
                startActivity(qrcode_intent);

                if(ContextCompat.checkSelfPermission(Layout_events.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Layout_events.this, new String[]{
                            Manifest.permission.CAMERA
                    }, 1);
                }


            }
        });

        btn_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent codigo_intent = new Intent(Layout_events.this,Layout_dgcodigo.class);
                startActivity(codigo_intent);
            }
        });

    }
}
