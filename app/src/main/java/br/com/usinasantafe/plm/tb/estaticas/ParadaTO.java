package br.com.usinasantafe.plm.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.plm.pst.Entidade;

/**
 * Created by anderson on 05/05/2017.
 */

@DatabaseTable(tableName="tbparadaest")
public class ParadaTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idParada;
    @DatabaseField
    private Long codParada;
    @DatabaseField
    private String descrParada;

    public ParadaTO() {
    }

    public Long getIdParada() {
        return idParada;
    }

    public void setIdParada(Long idParada) {
        this.idParada = idParada;
    }

    public Long getCodParada() {
        return codParada;
    }

    public void setCodParada(Long codParada) {
        this.codParada = codParada;
    }

    public String getDescrParada() {
        return descrParada;
    }

    public void setDescrParada(String descrParada) {
        this.descrParada = descrParada;
    }

}
