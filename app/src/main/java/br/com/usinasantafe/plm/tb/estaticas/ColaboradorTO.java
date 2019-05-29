/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.plm.tb.estaticas;

/**
 *
 * @author anderson
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.plm.pst.Entidade;

@DatabaseTable(tableName="tbcolabest")
public class ColaboradorTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long codColab;
	@DatabaseField
    private String nomeColab;

    public ColaboradorTO() {
    }

	public Long getCodColab() {
		return codColab;
	}

	public void setCodColab(Long codColab) {
		this.codColab = codColab;
	}

	public String getNomeColab() {
		return nomeColab;
	}

	public void setNomeColab(String nomeColab) {
		this.nomeColab = nomeColab;
	}
}
