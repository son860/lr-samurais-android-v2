package com.example.emersongarcia86.lr_samurais_android_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Layout_qrcode extends AppCompatActivity {

    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(Layout_qrcode.this);

        setContentView(scannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        scannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {

                Intent intent_process = new Intent(Layout_qrcode.this, Layout_dgcodigo.class);
                intent_process.putExtra("QRCode", result.getText());
                startActivity(intent_process);
                finish();
            }
        });

        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }
}