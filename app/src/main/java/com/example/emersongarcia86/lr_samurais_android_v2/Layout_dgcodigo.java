package com.example.emersongarcia86.lr_samurais_android_v2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.pi.pi4.GroupSelectionActivity;
import models.Evento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Layout_dgcodigo extends Activity {

    private Button btn_codigo;
    private EditText edtext_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_dgcodigo);

        btn_codigo = (Button) findViewById(R.id.btn_codigo);
        edtext_codigo = (EditText) findViewById(R.id.edtext_codigo);

        String result_qrcode = getIntent().getStringExtra("QRCode");
        if (result_qrcode != null && !result_qrcode.equals("")) {
            edtext_codigo.setText(result_qrcode);
        }

        btn_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Validador do campo de código */
                if(edtext_codigo.length() == 8){

                    Retrofit retrofit = Rest.getInstance().get();
                    PIService service = retrofit.create(PIService.class);
                    Evento e = new Evento();

                    String identificador = edtext_codigo.getText().toString();
                    e.setIdentificador(identificador);

                    Call<List<Evento>> call;
                    call = service.selectEvento("38934888");

                    call.enqueue(new Callback<List<Evento>>() {
                        @Override
                        public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                            Log.d("RESPONSE", "success");

                            Intent i = new Intent(Layout_dgcodigo.this, GroupSelectionActivity.class);
                            Bundle b = new Bundle();

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Layout_dgcodigo.this);
                            Integer userId = preferences.getInt("userid",0);
                            Log.d("testeHendy",userId.toString());

                            //b.putString("evento", response.body().getCodEvento().toString()); //Your id
                            b.putString("participanteId", userId.toString()); //Your id
                            b.putString ("proximaTela", Layout_game_rules.class.getName());

                            i.putExtras(b); //Put your id to your next Intent
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(Call<List<Evento>> call, Throwable t) {
                            Log.e("RESPONSE", "error");
                        }
                    });

                    Log.d("LIMIT", "Call passed");

                }

                else if(edtext_codigo.getText().toString().trim() == null) {

                    Toast.makeText(getApplication(),
                            "Campo Vazio! Por favor, preencha o campo novamente.",
                            Toast.LENGTH_LONG).show();

                }

                else {

                    Toast.makeText(getApplication(),
                            "Código inválido! Tente novamente!",
                            Toast.LENGTH_LONG).show();

                    edtext_codigo.setText("");
                }
            }
        });
    }
}
