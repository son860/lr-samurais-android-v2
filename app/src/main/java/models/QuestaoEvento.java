package models;

/**
 * Created by hendy on 22/11/2016.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestaoEvento {
    private Integer codQuestao;
    private String codTipoQuestao;
    private String textoQuestao;
    private String textoAlternativa;
    private String codStatus;
    private Integer codEvento;
    private List<Alternativa> alternativas = new ArrayList<Alternativa>();


    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public Integer getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(Integer codEvento) {
        this.codEvento = codEvento;
    }

    public Integer getCodQuestao() {
        return codQuestao;
    }

    public void setCodQuestao(Integer codQuestao) {
        this.codQuestao = codQuestao;
    }

    public String getCodTipoQuestao() {
        return codTipoQuestao;
    }

    public void setCodTipoQuestao(String codTipoQuestao) {
        this.codTipoQuestao = codTipoQuestao;
    }

    public String getTextoQuestao() {
        return textoQuestao;
    }

    public void setTextoQuestao(String textoQuestao) {
        this.textoQuestao = textoQuestao;
    }

    public String getTextoAlternativa() {
        return textoAlternativa;
    }

    public void setTextoAlternativa(String textoAlternativa) {
        this.textoAlternativa = textoAlternativa;
    }

    public String getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(String codStatus) {
        this.codStatus = codStatus;
    }

    public boolean add(Alternativa alternativa) {
        return alternativas.add(alternativa);
    }
}
