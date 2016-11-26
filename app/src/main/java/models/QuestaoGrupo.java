package models;


import java.sql.Date;

public class QuestaoGrupo {
    private Integer codQuestao;
    private Integer codAlternativa;
    private Integer codGrupo;
    private Date tempo;
    private String textoResp;
    private Boolean correta;

    public Integer getCodQuestao() {
        return codQuestao;
    }

    public void setCodQuestao(Integer codQuestao) {
        this.codQuestao = codQuestao;
    }

    public Integer getCodAlternativa() {
        return codAlternativa;
    }

    public void setCodAlternativa(Integer codAlternativa) {
        this.codAlternativa = codAlternativa;
    }

    public Integer getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(Integer codGrupo) {
        this.codGrupo = codGrupo;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

    public String getTextoResp() {
        return textoResp;
    }

    public void setTextoResp(String textoResp) {
        this.textoResp = textoResp;
    }

    public Boolean getCorreta() {
        return correta;
    }

    public void setCorreta(Boolean correta) {
        this.correta = correta;
    }
}
