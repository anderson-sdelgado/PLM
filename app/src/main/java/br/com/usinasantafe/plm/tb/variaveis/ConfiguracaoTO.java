package br.com.usinasantafe.plm.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.plm.pst.Entidade;

@DatabaseTable(tableName="tbconfigvar")
public class ConfiguracaoTO extends Entidade {
	
	private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idConfig;
	@DatabaseField
	private Long nroCelularConfig;
	@DatabaseField
	private Long liderConfig;
    @DatabaseField
	private String senhaConfig;

	public ConfiguracaoTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdConfig() {
		return idConfig;
	}

	public Long getNroCelularConfig() {
		return nroCelularConfig;
	}

	public void setNroCelularConfig(Long nroCelularConfig) {
		this.nroCelularConfig = nroCelularConfig;
	}

	public String getSenhaConfig() {
		return senhaConfig;
	}

	public void setSenhaConfig(String senhaConfig) {
		this.senhaConfig = senhaConfig;
	}

	public Long getLiderConfig() {
		return liderConfig;
	}

	public void setLiderConfig(Long liderConfig) {
		this.liderConfig = liderConfig;
	}
}