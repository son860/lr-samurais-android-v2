package com.example.emersongarcia86.lr_samurais_android_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import models.Evento;
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


        btn_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = Rest.getInstance().get();
                PIService service = retrofit.create(PIService.class);
                Evento e = new Evento();

                String codEvento = edtext_codigo.getText().toString();
                e.setCodEvento(codEvento);
                /* Validador do campo de código */
                if(edtext_codigo.length() == 8){

                    Intent edtext_intent = new Intent(Layout_dgcodigo.this,Layout_choose_groups.class);
                    startActivity(edtext_intent);

                    Toast.makeText(getApplication(),
                            "Login realizado com sucesso!",
                            Toast.LENGTH_LONG).show();

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
