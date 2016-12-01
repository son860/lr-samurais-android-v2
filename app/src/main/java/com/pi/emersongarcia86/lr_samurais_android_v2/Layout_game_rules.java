package com.pi.emersongarcia86.lr_samurais_android_v2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import models.QuestaoEvento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Layout_game_rules extends AppCompatActivity {

    private Activity activity;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    private Timer timerAtual = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();
    private boolean carregou = false;
    volatile boolean shutdown = false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_game_rules);

        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Regras do Jogo");
//        actionBar.setDisplayHomeAsUpEnabled(true);
        final TextView t = (TextView) findViewById(R.id.text_rules_01);

        Retrofit retrofit = Rest.getInstance().get();
        PIService service = retrofit.create(PIService.class);
        QuestaoEvento eq = new QuestaoEvento();

        String identificador = Application.getEvento();

        Call<List<QuestaoEvento>> call;
        call = service.getQuestaoEvento(identificador);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            public void run() {
                if (!shutdown) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        };

        call.enqueue(new Callback<List<QuestaoEvento>>() {
            @Override
            public void onResponse(Call<List<QuestaoEvento>> call, Response<List<QuestaoEvento>> response) {
                List<QuestaoEvento> r = response.body();
                final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

                if (!r.isEmpty()) {
                    String statusEvento = r.get(0).getCodStatus();
                    if (statusEvento.trim().equals("F")) {
                        shutdown = true;
                        Intent intent = new Intent(Layout_game_rules.this, layout_placar.class);
                        startActivity(intent);
                        finish();
                    }
                }

                if (r.size() > 0) {
                    carregou = true;

                    Bundle extras = getIntent().getExtras();

                    Integer numQ = 0;
                    if (extras != null) {
                        numQ = extras.getInt("numQ");
                        if (numQ != null) {
                            numQ = numQ;
                        }
                    }

                    if (numQ + 1 == r.size()) {
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

                        Intent intent = new Intent(Layout_game_rules.this, Layout_questions.class);
                        intent.putExtra("numQ", numQ);
                        startActivity(intent);
                    } else {
                        new Handler().postDelayed(runnable, 3000);
                    }
                } else {
                    new Handler().postDelayed(runnable, 3000);
                }
            }


            @Override
            public void onFailure(Call<List<QuestaoEvento>> call, Throwable t) {
                Log.e("RESPONSE", "error");
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public Activity getActivity() {
        return activity;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Layout_game_rules Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
