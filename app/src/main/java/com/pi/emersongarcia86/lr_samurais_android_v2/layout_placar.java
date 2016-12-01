package com.pi.emersongarcia86.lr_samurais_android_v2;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import models.Placar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class layout_placar extends AppCompatActivity {

    private TextView vencedor;
    private TextView vencedor2;
    private TextView vencedor3;
    private TextView vencedor4;
    private TextView vencedor5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_placar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Placar");
        vencedor = (TextView) findViewById(R.id.vencedor1);
        vencedor2 = (TextView) findViewById(R.id.vencedor2);
        vencedor3 = (TextView) findViewById(R.id.vencedor3);
        vencedor4 = (TextView) findViewById(R.id.vencedor4);
        vencedor5 = (TextView) findViewById(R.id.vencedor5);

        Button voltar = (Button) findViewById(R.id.btn_voltar);

        voltar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent codigo_intent = new Intent(layout_placar.this,Layout_events.class);
                startActivity(codigo_intent);
                finish();
            }
        });

        Retrofit retrofit = Rest.getInstance().get();
        PIService service = retrofit.create(PIService.class);
        Placar eq = new Placar();

        String identificador = Application.getEvento();

        Call<List<Placar>> call;
        call = service.getPlacar(identificador);


        call.enqueue(new Callback<List<Placar>>() {
            @Override
            public void onResponse(Call<List<Placar>> call, Response<List<Placar>> response) {
                List<Placar> r = response.body();
                if (r.size() >= 1) {
                    vencedor.setText(r.get(0).getNmgrupo() + " - " + r.get(0).getCorreta() + " Acertos");
                }

                if (r.size() >= 2) {
                    vencedor2.setText(r.get(1).getNmgrupo()+" - "+r.get(1).getCorreta()+" Acertos");
                }

                if (r.size() >= 3) {
                    vencedor3.setText(r.get(2).getNmgrupo()+" - "+r.get(2).getCorreta()+" Acertos");
                }

                if (r.size() >= 4) {
                    vencedor4.setText(r.get(3).getNmgrupo()+" - "+r.get(3).getCorreta()+" Acertos");
                }

                if (r.size() >= 5) {
                    vencedor5.setText(r.get(4).getNmgrupo()+" - "+r.get(4).getCorreta()+" Acertos");
                }
            }

            @Override
            public void onFailure(Call<List<Placar>> call, Throwable t) {
                Log.e("RESPONSE", "error");
            }
        });

    }
}