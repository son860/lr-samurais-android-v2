package models;

/**
 * Created by hendy on 22/11/2016.
 */

public class Grupo {
    private Integer codGrupo;
    private Integer codEvento;
    private String nmGrupo;
    private Integer codAssunto;
    private Integer codLider;
    private Boolean finalizado;

    public Integer getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(Integer codGrupo) {
        this.codGrupo = codGrupo;
    }

    public Integer getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(Integer codEvento) {
        this.codEvento = codEvento;
    }

    public String getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(String nmGrupo) {
        this.nmGrupo = nmGrupo;
    }

    public Integer getCodAssunto() {
        return codAssunto;
    }

    public void setCodAssunto(Integer codAssunto) {
        this.codAssunto = codAssunto;
    }

    public Integer getCodLider() {
        return codLider;
    }

    public void setCodLider(Integer codLider) {
        this.codLider = codLider;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }
}
