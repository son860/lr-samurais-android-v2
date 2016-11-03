package com.example.emersongarcia86.lr_samurais_android_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Layout_resultqrcode extends AppCompatActivity {

    private TextView text_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_qrcode);

        text_result = (TextView) findViewById(R.id.qr_code_area);

        Intent intent_result = getIntent();
        String resultado = intent_result.getStringExtra("QRCode");

        text_result.setText(resultado);
    }
}
