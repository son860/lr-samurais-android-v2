package com.example.emersongarcia86.lr_samurais_android_v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

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
        final TextView t = (TextView)findViewById(R.id.text_rules_01);

        Retrofit retrofit = Rest.getInstance().get();
        PIService service = retrofit.create(PIService.class);
        QuestaoEvento eq = new QuestaoEvento();

        String identificador = Application.getEvento();

        Call<List<QuestaoEvento>> call;
        call = service.getQuestaoEvento(identificador);


        call.enqueue(new Callback<List<QuestaoEvento>>() {
            @Override
            public void onResponse(Call<List<QuestaoEvento>> call, Response<List<QuestaoEvento>> response) {
                List<QuestaoEvento> r = response.body();

                Bundle extras = getIntent().getExtras();
                Integer numQ = 0;
                if (extras != null) {
                    numQ = extras.getInt("numQ");
                    if (numQ != null) {
                        numQ = numQ;
                    }
                }

                final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("textoQuestao", r.get(numQ).getTextoQuestao());
                editor.putInt("codQuestao", r.get(numQ).getCodQuestao());
                editor.putInt("codEvento", r.get(numQ).getCodEvento());

//                int numQ = (prefs.getInt("numQ", 0) == 0) ? prefs.getInt("numQ", 0) + 1 : 0;
//                editor.putInt("numQ", numQ + 1);

                StringBuilder textoAlternativas = new StringBuilder();
                StringBuilder idsAlternativas = new StringBuilder();

                for (int i = 0; i < r.get(numQ).getAlternativas().size(); i++) {
                    if (r.get(numQ).getAlternativas().size() == i + 1) {
                        idsAlternativas.append(r.get(numQ).getAlternativas().get(i).getCodAlternativa());
                        textoAlternativas.append(r.get(numQ).getAlternativas().get(i).getTextoAlternativa());
                    } else {
                        idsAlternativas.append(r.get(numQ).getAlternativas().get(i).getCodAlternativa() + ",");
                        textoAlternativas.append(r.get(numQ).getAlternativas().get(i).getTextoAlternativa() + ",");
                    }
                }

                editor.putString("alternativas", textoAlternativas.toString());
                editor.putString("ids_alternativas", idsAlternativas.toString());

                editor.apply();

//                startActivity(new Intent(Layout_game_rules.this, Layout_questions.class));
//                finish();

                Intent intent = new Intent(Layout_game_rules.this, Layout_questions.class);
                intent.putExtra("numQ", numQ);
                startActivity(intent);
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
