package br.com.usinasantafe.plm;

import android.app.Application;

import br.com.usinasantafe.plm.tb.variaveis.ApontaTO;

public class PLMContext extends Application {

    private ApontaTO apontaTO;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    public ApontaTO getApontaTO() {
        if (apontaTO == null)
            apontaTO = new ApontaTO();
        return apontaTO;
    }

}
