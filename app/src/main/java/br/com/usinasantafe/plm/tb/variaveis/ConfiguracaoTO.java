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
	private Long matricLiderConfig;
    @DatabaseField
	private String senhaConfig;

	public ConfiguracaoTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(Long idConfig) {
		this.idConfig = idConfig;
	}

	public Long getMatricLiderConfig() {
		return matricLiderConfig;
	}

	public void setMatricLiderConfig(Long matricLiderConfig) {
		this.matricLiderConfig = matricLiderConfig;
	}

	public String getSenhaConfig() {
		return senhaConfig;
	}

	public void setSenhaConfig(String senhaConfig) {
		this.senhaConfig = senhaConfig;
	}
}