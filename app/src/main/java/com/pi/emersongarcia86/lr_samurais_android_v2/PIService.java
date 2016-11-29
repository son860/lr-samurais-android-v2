package com.pi.emersongarcia86.lr_samurais_android_v2;

import java.util.List;

import models.Evento;
import models.Participante;
import models.Placar;
import models.QuestaoEvento;
import models.QuestaoGrupo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by temp.cas on 31/03/2016.
 */

public interface PIService {
    @POST("learningrace1/rest/participante")
    Call<List<Participante>> getParticipante(@Body Participante participante);

    @GET("learningrace1/rest/evento/{identificador}")
    Call<List<Evento>> getEvento(@Path("identificador") String identificador);

    @GET("learningrace1/rest/questaoevento/{identificador}")
    Call<List<QuestaoEvento>> getQuestaoEvento(@Path("identificador") String identificador);

    @POST("learningrace1/rest/questaoevento")
    Call<QuestaoGrupo> getQuestaoGrupo(@Body QuestaoGrupo questaoGrupo);

    @GET("learningrace1/rest/grupo/{codEvento}")
    Call<List<models.ParticipanteGrupo>> getParticipanteGrupo(@Path("codEvento") Integer codEvento);

    @GET("learningrace1/rest/placar/{identificador}")
    Call<List<Placar>> getPlacar(@Path("identificador") String identificador);

}
