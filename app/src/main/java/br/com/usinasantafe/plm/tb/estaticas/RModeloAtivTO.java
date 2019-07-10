package br.com.usinasantafe.plm.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.plm.pst.Entidade;

/**
 * Created by anderson on 28/04/2017.
 */

@DatabaseTable(tableName="tbrmodelativest")
public class RModeloAtivTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idModeloAtiv;
    @DatabaseField
    private Long idModelo;
    @DatabaseField
    private Long idAtiv;

    public RModeloAtivTO() {
    }

    public Long getIdModeloAtiv() {
        return idModeloAtiv;
    }

    public void setIdModeloAtiv(Long idModeloAtiv) {
        this.idModeloAtiv = idModeloAtiv;
    }

    public Long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Long idModelo) {
        this.idModelo = idModelo;
    }

    public Long getIdAtiv() {
        return idAtiv;
    }

    public void setIdAtiv(Long idAtiv) {
        this.idAtiv = idAtiv;
    }
}
