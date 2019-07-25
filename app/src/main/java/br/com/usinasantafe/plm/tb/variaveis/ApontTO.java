package br.com.usinasantafe.plm.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.plm.pst.Entidade;

@DatabaseTable(tableName="tbapontvar")
public class ApontTO extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idApont;
	@DatabaseField
	private Long idBolApont;
	@DatabaseField
	private Long osApont;
	@DatabaseField
	private Long ativApont;
	@DatabaseField
	private Long paradaApont;
	@DatabaseField
	private String dthrApont;
	@DatabaseField
	private Long statusConApont;  //0 - OffLine; 1 - OnLine

	public ApontTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdBolApont() {
		return idBolApont;
	}

	public void setIdBolApont(Long idBolApont) {
		this.idBolApont = idBolApont;
	}

	public Long getOsApont() {
		return osApont;
	}

	public void setOsApont(Long osApont) {
		this.osApont = osApont;
	}

	public Long getAtivApont() {
		return ativApont;
	}

	public void setAtivApont(Long ativApont) {
		this.ativApont = ativApont;
	}

	public Long getParadaApont() {
		return paradaApont;
	}

	public void setParadaApont(Long paradaApont) {
		this.paradaApont = paradaApont;
	}

	public String getDthrApont() {
		return dthrApont;
	}

	public void setDthrApont(String dthrApont) {
		this.dthrApont = dthrApont;
	}

	public Long getStatusConApont() {
		return statusConApont;
	}

	public void setStatusConApont(Long statusConApont) {
		this.statusConApont = statusConApont;
	}
}
