package br.com.usinasantafe.plm.tb.variaveis;

/**
 * Created by anderson on 24/07/2017.
 */

public class AtualizaTO {

    private Long nroCelular;
    private String versaoAtual;
    private String versaoNova;

    public AtualizaTO() {
    }

    public Long getNroCelular() {
        return nroCelular;
    }

    public void setNroCelular(Long nroCelular) {
        this.nroCelular = nroCelular;
    }

    public String getVersaoAtual() {
        return versaoAtual;
    }

    public void setVersaoAtual(String versaoAtual) {
        this.versaoAtual = versaoAtual;
    }

    public String getVersaoNova() {
        return versaoNova;
    }

    public void setVersaoNova(String versaoNova) {
        this.versaoNova = versaoNova;
    }
}
