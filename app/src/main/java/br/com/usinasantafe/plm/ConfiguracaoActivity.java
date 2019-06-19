package br.com.usinasantafe.plm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.usinasantafe.plm.bo.ConexaoWeb;
import br.com.usinasantafe.plm.bo.ManipDadosReceb;
import br.com.usinasantafe.plm.tb.estaticas.ColabTO;
import br.com.usinasantafe.plm.tb.variaveis.ConfiguracaoTO;

public class ConfiguracaoActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextLinhaConfig;
    private EditText editTextSenhaConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        Button btOkConfig =  (Button) findViewById(R.id.buttonSalvarConfig );
        Button btCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        Button btAtualBDConfig = (Button) findViewById(R.id.buttonAtualizarBD);
        editTextLinhaConfig = (EditText)  findViewById(R.id.editTextLinhaConfig);
        editTextSenhaConfig = (EditText)  findViewById(R.id.editTextSenhaConfig);

        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();

        if (configuracaoTO.hasElements()) {

            List configList = configuracaoTO.all();
            configuracaoTO = (ConfiguracaoTO) configList.get(0);
            configList.clear();

            editTextLinhaConfig.setText(String.valueOf(configuracaoTO.getNroCelularConfig()));
            editTextSenhaConfig.setText(configuracaoTO.getSenhaConfig());

        }


        btOkConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!editTextLinhaConfig.getText().toString().equals("") &&
                        !editTextSenhaConfig.getText().toString().equals("")){

                    ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                    configuracaoTO.deleteAll();
                    configuracaoTO.setNroCelularConfig(Long.parseLong(editTextLinhaConfig.getText().toString()));
                    configuracaoTO.setSenhaConfig(editTextSenhaConfig.getText().toString());
                    configuracaoTO.setLiderConfig(0L);
                    configuracaoTO.insert();

                    Intent it = new Intent(ConfiguracaoActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        btCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(ConfiguracaoActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

        btAtualBDConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(ConfiguracaoActivity.this)){
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();
                    ManipDadosReceb.getInstance().atualizarBD(progressBar);
                    ManipDadosReceb.getInstance().setContext(ConfiguracaoActivity.this);
                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
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

    }
}
