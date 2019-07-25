package br.com.usinasantafe.plm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.plm.bo.ConexaoWeb;
import br.com.usinasantafe.plm.bo.ManipDadosVerif;
import br.com.usinasantafe.plm.tb.estaticas.OSTO;
import br.com.usinasantafe.plm.tb.estaticas.ROSAtivTO;

public class OSActivity extends ActivityGeneric {

    private PLMContext plmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        plmContext = (PLMContext) getApplication();

        Button buttonOkOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!editTextPadrao.getText().toString().equals("")) {

                    try{

                        plmContext.getBoletimTO().setOsBoletim(Long.parseLong(editTextPadrao.getText().toString()));
                        plmContext.getApontTO().setOsApont(Long.parseLong(editTextPadrao.getText().toString()));

                        OSTO osTO = new OSTO();

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (osTO.hasElements()) {

                            List osList = osTO.get("nroOS", Long.parseLong(editTextPadrao.getText().toString()));

                            if (osList.size() > 0) {

                                if (conexaoWeb.verificaConexao(OSActivity.this)) {
                                    plmContext.getBoletimTO().setStatusConBoletim(1L);
                                    plmContext.getApontTO().setStatusConApont(1L);
                                }
                                else{
                                    plmContext.getBoletimTO().setStatusConBoletim(0L);
                                    plmContext.getApontTO().setStatusConApont(0L);
                                }

                                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                                startActivity(it);
                                finish();

                            }
                            else{

                                if (conexaoWeb.verificaConexao(OSActivity.this)) {

                                    progressBar = new ProgressDialog(v.getContext());
                                    progressBar.setCancelable(true);
                                    progressBar.setMessage("PESQUISANDO A OS...");
                                    progressBar.show();

                                    customHandler.postDelayed(updateTimerThread, 10000);

                                    plmContext.getBoletimTO().setStatusConBoletim(1L);
                                    plmContext.getApontTO().setStatusConApont(1L);

                                    ManipDadosVerif.getInstance().verDados(editTextPadrao.getText().toString(), "OS"
                                            , OSActivity.this, ListaAtividadeActivity.class, progressBar);

                                } else {

                                    plmContext.getBoletimTO().setStatusConBoletim(0L);
                                    plmContext.getApontTO().setStatusConApont(0L);

                                    Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                                    startActivity(it);
                                    finish();

                                }

                            }

                        }
                        else{

                            if (conexaoWeb.verificaConexao(OSActivity.this)) {

                                progressBar = new ProgressDialog(v.getContext());
                                progressBar.setCancelable(true);
                                progressBar.setMessage("PESQUISANDO A OS...");
                                progressBar.show();

                                customHandler.postDelayed(updateTimerThread, 10000);

                                plmContext.getBoletimTO().setStatusConBoletim(1L);
                                plmContext.getApontTO().setStatusConApont(1L);

                                ManipDadosVerif.getInstance().verDados(editTextPadrao.getText().toString(), "OS"
                                        , OSActivity.this, ListaAtividadeActivity.class, progressBar);

                            } else {

                                plmContext.getBoletimTO().setStatusConBoletim(0L);
                                plmContext.getApontTO().setStatusConApont(0L);

                                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                                startActivity(it);
                                finish();

                            }

                        }

                    }
                    catch (NumberFormatException e){

                        AlertDialog.Builder alerta = new AlertDialog.Builder( OSActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("VALOR DE OS INCORRETO! FAVOR VERIFICAR.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }
                }
            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(OSActivity.this, ListaTurnoActivity.class);
        startActivity(it);
        finish();
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(!ManipDadosVerif.getInstance().isVerTerm()) {

                ManipDadosVerif.getInstance().cancelVer();
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                plmContext.getApontTO().setStatusConApont(0L);

                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

}
