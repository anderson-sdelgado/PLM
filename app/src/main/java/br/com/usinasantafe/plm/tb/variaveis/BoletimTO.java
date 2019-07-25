package br.com.usinasantafe.plm.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.plm.pst.Entidade;

@DatabaseTable(tableName="tbboletimvar")
public class BoletimTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idBoletim;
    @DatabaseField
    private Long matricOperBoletim;
    @DatabaseField
    private Long matricLiderBoletim;
    @DatabaseField
    private Long idEquipBoletim;
    @DatabaseField
    private Long idTurnoBoletim;
    @DatabaseField
    private Double hodometroInicialBoletim;
    @DatabaseField
    private Double hodometroFinalBoletim;
    @DatabaseField
    private Long osBoletim;
    @DatabaseField
    private Long ativPrincBoletim;
    @DatabaseField
    private String dthrInicioBoletim;
    @DatabaseField
    private String dthrFimBoletim;
    @DatabaseField
    private Long statusBoletim;  //1 - Aberto Sem Enviar; 2 - Aberto Enviado; 3 - Fechado
    @DatabaseField
    private Long statusConBoletim;  //0 - OffLine; 1 - OnLine

    public BoletimTO() {
    }

    public Long getIdBoletim() {
        return idBoletim;
    }

    public void setIdBoletim(Long idBoletim) {
        this.idBoletim = idBoletim;
    }

    public Long getMatricOperBoletim() {
        return matricOperBoletim;
    }

    public void setMatricOperBoletim(Long matricOperBoletim) {
        this.matricOperBoletim = matricOperBoletim;
    }

    public Long getMatricLiderBoletim() {
        return matricLiderBoletim;
    }

    public void setMatricLiderBoletim(Long matricLiderBoletim) {
        this.matricLiderBoletim = matricLiderBoletim;
    }

    public Long getIdEquipBoletim() {
        return idEquipBoletim;
    }

    public void setIdEquipBoletim(Long idEquipBoletim) {
        this.idEquipBoletim = idEquipBoletim;
    }

    public Long getIdTurnoBoletim() {
        return idTurnoBoletim;
    }

    public void setIdTurnoBoletim(Long idTurnoBoletim) {
        this.idTurnoBoletim = idTurnoBoletim;
    }

    public Double getHodometroInicialBoletim() {
        return hodometroInicialBoletim;
    }

    public void setHodometroInicialBoletim(Double hodometroInicialBoletim) {
        this.hodometroInicialBoletim = hodometroInicialBoletim;
    }

    public Double getHodometroFinalBoletim() {
        return hodometroFinalBoletim;
    }

    public void setHodometroFinalBoletim(Double hodometroFinalBoletim) {
        this.hodometroFinalBoletim = hodometroFinalBoletim;
    }

    public Long getOsBoletim() {
        return osBoletim;
    }

    public void setOsBoletim(Long osBoletim) {
        this.osBoletim = osBoletim;
    }

    public Long getAtivPrincBoletim() {
        return ativPrincBoletim;
    }

    public void setAtivPrincBoletim(Long ativPrincBoletim) {
        this.ativPrincBoletim = ativPrincBoletim;
    }

    public String getDthrInicioBoletim() {
        return dthrInicioBoletim;
    }

    public void setDthrInicioBoletim(String dthrInicioBoletim) {
        this.dthrInicioBoletim = dthrInicioBoletim;
    }

    public String getDthrFimBoletim() {
        return dthrFimBoletim;
    }

    public void setDthrFimBoletim(String dthrFimBoletim) {
        this.dthrFimBoletim = dthrFimBoletim;
    }

    public Long getStatusBoletim() {
        return statusBoletim;
    }

    public void setStatusBoletim(Long statusBoletim) {
        this.statusBoletim = statusBoletim;
    }

    public Long getStatusConBoletim() {
        return statusConBoletim;
    }

    public void setStatusConBoletim(Long statusConBoletim) {
        this.statusConBoletim = statusConBoletim;
    }
}
