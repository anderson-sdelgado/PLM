package br.com.usinasantafe.plm.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.plm.pst.Entidade;

@DatabaseTable(tableName="tbapontammvar")
public class ApontaTO extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idAponta;
	@DatabaseField
	private Long idEquipAponta;
	@DatabaseField
	private Long idTurnoAponta;
	@DatabaseField
	private Long matricOperadorAponta;
	@DatabaseField
	private Long matricLiderAponta;
	@DatabaseField
	private Long osAponta;
	@DatabaseField
	private Long atividadeAponta;
	@DatabaseField
	private Long paradaAponta;
	@DatabaseField
	private String dthrAponta;
	@DatabaseField
	private Long statusConAponta;  //0 - OffLine; 1 - OnLine

	public ApontaTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdAponta() {
		return idAponta;
	}

	public void setIdAponta(Long idAponta) {
		this.idAponta = idAponta;
	}

	public Long getIdEquipAponta() {
		return idEquipAponta;
	}

	public void setIdEquipAponta(Long idEquipAponta) {
		this.idEquipAponta = idEquipAponta;
	}

	public Long getIdTurnoAponta() {
		return idTurnoAponta;
	}

	public void setIdTurnoAponta(Long idTurnoAponta) {
		this.idTurnoAponta = idTurnoAponta;
	}

	public Long getMatricOperadorAponta() {
		return matricOperadorAponta;
	}

	public void setMatricOperadorAponta(Long matricOperadorAponta) {
		this.matricOperadorAponta = matricOperadorAponta;
	}

	public Long getMatricLiderAponta() {
		return matricLiderAponta;
	}

	public void setMatricLiderAponta(Long matricLiderAponta) {
		this.matricLiderAponta = matricLiderAponta;
	}

	public Long getOsAponta() {
		return osAponta;
	}

	public void setOsAponta(Long osAponta) {
		this.osAponta = osAponta;
	}

	public Long getAtividadeAponta() {
		return atividadeAponta;
	}

	public void setAtividadeAponta(Long atividadeAponta) {
		this.atividadeAponta = atividadeAponta;
	}

	public Long getParadaAponta() {
		return paradaAponta;
	}

	public void setParadaAponta(Long paradaAponta) {
		this.paradaAponta = paradaAponta;
	}

	public String getDthrAponta() {
		return dthrAponta;
	}

	public void setDthrAponta(String dthrAponta) {
		this.dthrAponta = dthrAponta;
	}

	public Long getStatusConAponta() {
		return statusConAponta;
	}

	public void setStatusConAponta(Long statusConAponta) {
		this.statusConAponta = statusConAponta;
	}

}
