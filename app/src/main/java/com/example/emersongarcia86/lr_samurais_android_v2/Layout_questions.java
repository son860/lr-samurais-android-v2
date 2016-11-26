package com.example.emersongarcia86.lr_samurais_android_v2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import models.QuestaoGrupo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.emersongarcia86.lr_samurais_android_v2.Layout_game_rules.MY_PREFS_NAME;

public class Layout_questions extends AppCompatActivity {
    TextView textElement;
    private ListView alternativas;
    private String[] ids_alternativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_questions);

        alternativas = (ListView) findViewById(R.id.alternativas);
        
        final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        TextView questao = (TextView)findViewById(R.id.questao);
        questao.setText(prefs.getString("textoQuestao", ""));
        String textoAlternativas = prefs.getString("alternativas", "");
        String[] data = textoAlternativas.split(",");
        ids_alternativas = prefs.getString("ids_alternativas", "").split(",");

        ArrayAdapter adapter = new ArrayAdapter(Layout_questions.this, android.R.layout.simple_selectable_list_item, data);
        alternativas.setAdapter(adapter);

        alternativas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Retrofit retrofit = Rest.getInstance().get();
                PIService service = retrofit.create(PIService.class);
                QuestaoGrupo qe = new QuestaoGrupo();

                qe.setCodQuestao(prefs.getInt("codQuestao", 0));
                qe.setCodAlternativa(Integer.parseInt(ids_alternativas[i]));
                qe.setCodGrupo(177);
                qe.setCorreta(true);

                Call<QuestaoGrupo> call;
                call = service.getQuestaoGrupo(qe);

                call.enqueue(new Callback<QuestaoGrupo>() {
                    @Override
                    public void onResponse(Call<QuestaoGrupo> call, Response<QuestaoGrupo> response) {
                        QuestaoGrupo lpg ;
                        lpg = response.body();
                        startActivity(new Intent(Layout_questions.this, Layout_game_rules.class));
                        finish();

                    }

                    @Override
                    public void onFailure(Call<QuestaoGrupo> call, Throwable t) {
                        Log.e("Login response", t.getMessage());
                    }
                });
            }
        });

    }
}
