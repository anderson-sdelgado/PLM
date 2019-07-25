package br.com.usinasantafe.plm.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.plm.tb.estaticas.AtividadeTO;
import br.com.usinasantafe.plm.tb.estaticas.ColabTO;
import br.com.usinasantafe.plm.tb.estaticas.EquipTO;
import br.com.usinasantafe.plm.tb.estaticas.OSTO;
import br.com.usinasantafe.plm.tb.estaticas.ParadaTO;
import br.com.usinasantafe.plm.tb.estaticas.RAtivParadaTO;
import br.com.usinasantafe.plm.tb.estaticas.RModeloAtivTO;
import br.com.usinasantafe.plm.tb.estaticas.ROSAtivTO;
import br.com.usinasantafe.plm.tb.estaticas.TurnoTO;
import br.com.usinasantafe.plm.tb.variaveis.ApontTO;
import br.com.usinasantafe.plm.tb.variaveis.BoletimTO;
import br.com.usinasantafe.plm.tb.variaveis.ConfiguracaoTO;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "plmdb";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);
		// TODO Auto-generated constructor stub
		instance = this;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		// TODO Auto-generated method stub
		
		try{

			TableUtils.createTable(cs, AtividadeTO.class);
			TableUtils.createTable(cs, ColabTO.class);
			TableUtils.createTable(cs, EquipTO.class);
			TableUtils.createTable(cs, OSTO.class);
			TableUtils.createTable(cs, ParadaTO.class);
			TableUtils.createTable(cs, RAtivParadaTO.class);
			TableUtils.createTable(cs, RModeloAtivTO.class);
			TableUtils.createTable(cs, ROSAtivTO.class);
			TableUtils.createTable(cs, TurnoTO.class);

			TableUtils.createTable(cs, ConfiguracaoTO.class);
			TableUtils.createTable(cs, ApontTO.class);
			TableUtils.createTable(cs, BoletimTO.class);
			
		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
//				TableUtils.createTable(cs, ConfiguracaoTO.class);
				oldVersion = 2;
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
