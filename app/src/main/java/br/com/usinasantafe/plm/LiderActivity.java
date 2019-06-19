package br.com.usinasantafe.plm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.plm.bo.ConexaoWeb;
import br.com.usinasantafe.plm.bo.ManipDadosVerif;
import br.com.usinasantafe.plm.tb.estaticas.ColabTO;
import br.com.usinasantafe.plm.tb.estaticas.OSTO;
import br.com.usinasantafe.plm.tb.estaticas.ROSAtivTO;
import br.com.usinasantafe.plm.tb.variaveis.ConfiguracaoTO;

public class LiderActivity extends ActivityGeneric {

    private PLMContext plmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lider);

        plmContext = (PLMContext) getApplication();

        Button buttonOkLider = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancLider = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(LiderActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(LiderActivity.this)) {

                            progressBar = new ProgressDialog(LiderActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO LÍDER...");
                            progressBar.show();

                            ManipDadosVerif.getInstance().verDados("", "Colab"
                                    , LiderActivity.this, LiderActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(LiderActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });

                            alerta.show();

                        }


                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                alerta.show();

            }

        });

        buttonOkLider.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    ColabTO colabTO = new ColabTO();
                    List colabList = colabTO.get("codColab", Long.parseLong(editTextPadrao.getText().toString()));

                    if (colabList.size() > 0) {

                        colabTO = (ColabTO) colabList.get(0);

                        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                        List configList = configuracaoTO.all();
                        configuracaoTO = (ConfiguracaoTO) configList.get(0);
                        configList.clear();

                        configuracaoTO.setLiderConfig(colabTO.getCodColab());
                        configuracaoTO.update();

                        OSTO osTO = new OSTO();
                        osTO.deleteAll();

                        ROSAtivTO rosAtivTO = new ROSAtivTO();
                        rosAtivTO.deleteAll();

                        Intent it = new Intent(LiderActivity.this, MenuApontActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(LiderActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("MATRÍCULA DO OPERADOR INEXISTENTE! FAVOR VERIFICA A MESMA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }

                    colabList.clear();

                }

            }

        });

        buttonCancLider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(LiderActivity.this, ListaTurnoActivity.class);
        startActivity(it);
        finish();
    }

}
