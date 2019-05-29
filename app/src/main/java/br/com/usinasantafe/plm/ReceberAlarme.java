package br.com.usinasantafe.plm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.plm.bo.ManipDadosEnvio;
import br.com.usinasantafe.plm.bo.Tempo;
import br.com.usinasantafe.plm.pst.DatabaseHelper;


public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		if(Tempo.getInstance().getDataHora() != null) {
			Tempo.getInstance().getDataHora().setTime(Tempo.getInstance().getDataHora().getTime() + 60000L);
		}
		Log.i("PMM", "DATA HORA = " + Tempo.getInstance().datahora());
		if(ManipDadosEnvio.getInstance().verifDadosEnvio()){
			Log.i("PMM", "ENVIANDO");
			ManipDadosEnvio.getInstance().envioDados(context);
		}
	}

}