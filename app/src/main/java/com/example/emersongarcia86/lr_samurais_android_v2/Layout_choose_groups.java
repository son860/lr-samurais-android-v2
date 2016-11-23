package com.example.emersongarcia86.lr_samurais_android_v2;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Layout_choose_groups extends AppCompatActivity {

    private FloatingActionButton fab;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_choose_groups);

        fab = (FloatingActionButton) findViewById(R.id.fab_add_group);
        list = (ListView) findViewById(R.id.list_groups);

        ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Titulo 2");
        actionBar.setDisplayHomeAsUpEnabled(true);

        String[] data = new String[] {
            "Grupo 1",
            "Grupo 2",
            "Grupo 3"
        };
        ArrayAdapter adapter = new ArrayAdapter(Layout_choose_groups.this, android.R.layout.simple_selectable_list_item, data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Layout_choose_groups.this, "grupo " + i, Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Layout_choose_groups.this, "Adicionar Grupo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
