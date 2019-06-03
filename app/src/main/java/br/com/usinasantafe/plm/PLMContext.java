package br.com.usinasantafe.plm;

import android.app.Application;

import br.com.usinasantafe.plm.tb.variaveis.ApontTO;

public class PLMContext extends Application {

    private ApontTO apontTO;
    public static String versaoAplic = "1.0";

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    public ApontTO getApontTO() {
        if (apontTO == null)
            apontTO = new ApontTO();
        return apontTO;
    }

}
