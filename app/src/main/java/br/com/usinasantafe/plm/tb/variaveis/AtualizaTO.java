package br.com.usinasantafe.plm.tb.variaveis;

/**
 * Created by anderson on 24/07/2017.
 */

public class AtualizaTO {

    private Long idEquipAtualizacao;
    private Long idCheckList;
    private String versaoAtual;
    private String versaoNova;

    public AtualizaTO() {
    }

    public Long getIdEquipAtualizacao() {
        return idEquipAtualizacao;
    }

    public void setIdEquipAtualizacao(Long idEquipAtualizacao) {
        this.idEquipAtualizacao = idEquipAtualizacao;
    }

    public Long getIdCheckList() {
        return idCheckList;
    }

    public void setIdCheckList(Long idCheckList) {
        this.idCheckList = idCheckList;
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
