package br.com.usinasantafe.plm.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.plm.pst.Entidade;

@DatabaseTable(tableName="tbapontammvar")
public class ApontTO extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idApont;
	@DatabaseField
	private Long idEquipApont;
	@DatabaseField
	private Long idTurnoApont;
	@DatabaseField
	private Long matricOperApont;
	@DatabaseField
	private Long matricLiderApont;
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

	public Long getIdApont() {
		return idApont;
	}

	public void setIdApont(Long idApont) {
		this.idApont = idApont;
	}

	public Long getIdEquipApont() {
		return idEquipApont;
	}

	public void setIdEquipApont(Long idEquipApont) {
		this.idEquipApont = idEquipApont;
	}

	public Long getIdTurnoApont() {
		return idTurnoApont;
	}

	public void setIdTurnoApont(Long idTurnoApont) {
		this.idTurnoApont = idTurnoApont;
	}

	public Long getMatricOperApont() {
		return matricOperApont;
	}

	public void setMatricOperApont(Long matricOperApont) {
		this.matricOperApont = matricOperApont;
	}

	public Long getMatricLiderApont() {
		return matricLiderApont;
	}

	public void setMatricLiderApont(Long matricLiderApont) {
		this.matricLiderApont = matricLiderApont;
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
