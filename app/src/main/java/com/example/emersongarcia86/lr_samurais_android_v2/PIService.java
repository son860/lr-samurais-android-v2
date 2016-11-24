package com.example.emersongarcia86.lr_samurais_android_v2;

import java.util.List;

import br.com.pi.pi4.entity.ParticipanteGrupo;
import models.Evento;
import models.Participante;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by temp.cas on 31/03/2016.
 */
public interface PIService {
    @POST("learningrace1/rest/participante")
    Call<List<Participante>> getParticipante(@Body Participante participante);

    @POST("learningrace1/rest/evento")
    Call<List<Evento>> selectEvento(@Body String identificador);
}
