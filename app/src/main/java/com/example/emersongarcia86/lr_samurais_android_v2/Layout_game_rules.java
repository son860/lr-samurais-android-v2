package com.example.emersongarcia86.lr_samurais_android_v2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Layout_game_rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_game_rules);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Regras do Jogo");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
