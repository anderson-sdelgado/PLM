package br.com.usinasantafe.plm;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.plm.bo.ConexaoWeb;
import br.com.usinasantafe.plm.bo.ManipDadosEnvio;
import br.com.usinasantafe.plm.bo.ManipDadosVerif;
import br.com.usinasantafe.plm.tb.estaticas.ColabTO;
import br.com.usinasantafe.plm.tb.variaveis.AtualizaTO;
import br.com.usinasantafe.plm.tb.variaveis.ConfiguracaoTO;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuInicialList;
    private ConfiguracaoTO configTO;
    private ProgressDialog progressBar;
    private PLMContext plmContext;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        plmContext = (PLMContext) getApplication();
        configTO = new ConfiguracaoTO();

        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        customHandler.postDelayed(updateTimerThread, 0);

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        configTO = new ConfiguracaoTO();
        List configList = configTO.all();

        progressBar = new ProgressDialog(this);

        if(conexaoWeb.verificaConexao(this))
        {

            configTO = new ConfiguracaoTO();
            configList = configTO.all();
            if(configList.size() > 0){

                progressBar.setCancelable(true);
                progressBar.setMessage("Buscando Atualização...");
                progressBar.show();

                configTO = (ConfiguracaoTO) configList.get(0);
                AtualizaTO atualizaTO = new AtualizaTO();
                atualizaTO.setNroCelular(configTO.getNroCelularConfig());
                atualizaTO.setVersaoAtual(plmContext.versaoAplic);
                ManipDadosVerif.getInstance().verAtualizacao(atualizaTO, this, progressBar);
            }

        }
        else{
            startTimer("N");
        }

        listarMenuInicial();

    }

    private void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAMENTO");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialList = (ListView) findViewById(R.id.listaMenuInicial);
        menuInicialList.setAdapter(adapterList);

        menuInicialList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("APONTAMENTO")) {
                    ColabTO colabTO = new ColabTO();
                    if (colabTO.hasElements() && configTO.hasElements()) {
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuInicialActivity.this, MenuApontActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("CONFIGURAÇÃO")) {
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void startTimer(String retorno) {

        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

        if(progressBar.isShowing()){
            progressBar.dismiss();
        }

        if(alarmeAtivo){

            Log.i("PMM", "NOVO TIMER");

            Intent intent = new Intent("EXECUTAR_ALARME");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        }
        else{
            Log.i("PMM", "TIMER já ativo");
        }

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
            List configList = configuracaoTO.all();
            if(configList.size() > 0) {
                if (ManipDadosEnvio.getInstance().getStatusEnvio() == 1) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 2) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 3) {
                    textViewProcesso.setTextColor(Color.GREEN);
                    textViewProcesso.setText("Todos os Dados já foram Enviados");
                }
            }
            else{
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Aparelho sem Equipamento");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

}
