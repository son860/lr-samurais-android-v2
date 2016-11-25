package com.example.emersongarcia86.lr_samurais_android_v2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.emersongarcia86.lr_samurais_android_v2.Layout_game_rules.MY_PREFS_NAME;

public class Layout_questions extends AppCompatActivity {
    TextView textElement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_questions);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Integer restoredCodQuestao = prefs.getInt("codQuestao", 0);


        TextView questao = (TextView)findViewById(R.id.questao);
        questao.setText(restoredCodQuestao.toString());
    }
}
