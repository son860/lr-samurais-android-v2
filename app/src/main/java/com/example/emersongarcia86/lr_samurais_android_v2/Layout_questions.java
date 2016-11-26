package com.example.emersongarcia86.lr_samurais_android_v2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.emersongarcia86.lr_samurais_android_v2.Layout_game_rules.MY_PREFS_NAME;

public class Layout_questions extends AppCompatActivity {
    TextView textElement;
    private ListView alternativas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_questions);

        alternativas = (ListView) findViewById(R.id.alternativas);
        
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        TextView questao = (TextView)findViewById(R.id.questao);
        questao.setText(prefs.getString("textoQuestao", ""));
        String textoAlternativas = prefs.getString("alternativas", "");
        String[] data = textoAlternativas.split(",");

        ArrayAdapter adapter = new ArrayAdapter(Layout_questions.this, android.R.layout.simple_selectable_list_item, data);
        alternativas.setAdapter(adapter);

    }
}
