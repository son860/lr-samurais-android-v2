package com.example.emersongarcia86.lr_samurais_android_v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import br.com.pi.pi4.MainActivity;
import models.Alternativa;
import models.QuestaoEvento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.emersongarcia86.lr_samurais_android_v2.R.id.edtext_codigo;

public class Layout_game_rules extends AppCompatActivity {

    private Activity activity;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_game_rules);

        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Regras do Jogo");
//        actionBar.setDisplayHomeAsUpEnabled(true);


        Retrofit retrofit = Rest.getInstance().get();
        PIService service = retrofit.create(PIService.class);
        QuestaoEvento eq = new QuestaoEvento();

        String identificador = "ZZZZZZ";

        Call<List<QuestaoEvento>> call;
        call = service.getQuestaoEvento(identificador);


        call.enqueue(new Callback<List<QuestaoEvento>>() {
            @Override
            public void onResponse(Call<List<QuestaoEvento>> call, Response<List<QuestaoEvento>> response) {
                List<QuestaoEvento> r = response.body();

                SharedPreferences.Editor    editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("textoQuestao", r.get(0).getTextoQuestao());
                editor.putInt("codQuestao", r.get(0).getCodQuestao());

                StringBuilder textoAlternativas = new StringBuilder();
                for (int i = 0; i < r.get(0).getAlternativas().size(); i++) {
                    if (r.get(0).getAlternativas().size() == i + 1) {
                        textoAlternativas.append(r.get(0).getAlternativas().get(i).getTextoAlternativa());
                    } else {
                        textoAlternativas.append(r.get(0).getAlternativas().get(i).getTextoAlternativa() + ",");
                    }
                }

                editor.putString("alternativas", textoAlternativas.toString());
                editor.apply();

                startActivity(new Intent(Layout_game_rules.this, Layout_questions.class));
                finish();
            }

            @Override
            public void onFailure(Call<List<QuestaoEvento>> call, Throwable t) {
                Log.e("RESPONSE", "error");
            }
        });
    }

    public Activity getActivity() {
        return activity;
    }
}
