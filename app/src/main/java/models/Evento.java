package models;

/**
 * Created by hendy on 22/11/2016.
 */

public class Evento {
    public String codEvento;
    public String descricao;
    public String data;
    public Integer codTipoEvento;
    public String codStatus;
    public Integer codProfessor;
    public String identificador;

    public String getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(String codEvento) {
        this.codEvento = codEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCodTipoEvento() {
        return codTipoEvento;
    }

    public void setCodTipoEvento(Integer codTipoEvento) {
        this.codTipoEvento = codTipoEvento;
    }

    public String getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(String codStatus) {
        this.codStatus = codStatus;
    }

    public Integer getCodProfessor() {
        return codProfessor;
    }

    public void setCodProfessor(Integer codProfessor) {
        this.codProfessor = codProfessor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}
