package com.example.emersongarcia86.lr_samurais_android_v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.com.pi.pi4.MainActivity;
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
                Integer codQuestao = r.get(0).getCodQuestao();

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", "Elena");
                editor.putInt("codQuestao", codQuestao);
                editor.apply();

                startActivity(new Intent(Layout_game_rules.this, Layout_questions.class));
                finish();

//                Intent i = new Intent(Layout_dgcodigo.this, GroupSelectionActivity.class);
//                Bundle b = new Bundle();

//                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Layout_dgcodigo.this);

//                Integer userId = preferences.getInt("userid",0);
//                b.putString("evento", r.get(0).getCodEvento().toString()); //Your id
//                b.putString("participanteId", userId.toString()); //Your id
//                b.putString ("proximaTela", Layout_game_rules.class.getName());

//                i.putExtras(b); //Put your id to your next Intent
//                startActivity(i);
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
