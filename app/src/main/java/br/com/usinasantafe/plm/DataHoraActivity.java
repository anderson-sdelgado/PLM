package br.com.usinasantafe.plm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.plm.bo.Tempo;

public class DataHoraActivity extends ActivityGeneric {

    private PLMContext plmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);

        plmContext = (PLMContext) getApplication();

        Button buttonOkDataHora = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancDataHora = (Button) findViewById(R.id.buttonCancPadrao);
        TextView textViewDataHora = (TextView) findViewById(R.id.textViewPadrao);

        switch (plmContext.getContDataHora()) {
            case 1:
                textViewDataHora.setText("DIA:");
                break;
            case 2:
                textViewDataHora.setText("MÊS:");
                break;
            case 3:
                textViewDataHora.setText("ANO:");
                break;
            case 4:
                textViewDataHora.setText("HORA:");
                break;
            case 5:
                textViewDataHora.setText("MINUTOS:");
                break;
        }

        buttonOkDataHora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    int valor = Integer.parseInt(editTextPadrao.getText().toString());

                    Intent it;
                    switch (plmContext.getContDataHora()) {
                        case 1:
                            if((valor <= 31)){
                                plmContext.setDia(valor);
                                plmContext.setContDataHora(plmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("DIA INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                        case 2:
                            if((valor <= 12)){
                                plmContext.setMes(valor);
                                plmContext.setContDataHora(plmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("MÊS INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                        case 3:
                            if((valor >= 2019) && (valor <= 3000)){
                                plmContext.setAno(valor);
                                plmContext.setContDataHora(plmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("ANO INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                        case 4:
                            if(valor <= 23){
                                plmContext.setHora(valor);
                                plmContext.setContDataHora(plmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("HORA INCORRETA! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }

                            break;
                        case 5:
                            if((valor <= 59)){
                                plmContext.setMinuto(valor);

                                if(plmContext.getVerPosTela() == 1){

                                    plmContext.getBoletimTO().setDthrInicioBoletim(Tempo.getInstance().dataHora(plmContext.getDia()
                                            , plmContext.getMes(), plmContext.getAno()
                                            , plmContext.getHora(), plmContext.getMinuto()));

                                    it = new Intent(DataHoraActivity.this, EquipActivity.class);
                                    startActivity(it);
                                    finish();

                                }
                                else{

                                    plmContext.getBoletimTO().setDthrFimBoletim(Tempo.getInstance().dataHora(plmContext.getDia()
                                            , plmContext.getMes(), plmContext.getAno()
                                            , plmContext.getHora(), plmContext.getMinuto()));

                                    plmContext.setTextoHorimetro("HORÍMETRO FINAL:");

                                    it = new Intent(DataHoraActivity.this, HorimetroActivity.class);
                                    startActivity(it);
                                    finish();
                                }

                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("MINUTO INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                    }

                }

            }
        });

        buttonCancDataHora.setOnClickListener(new View.OnClickListener() {

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
        if(plmContext.getContDataHora() > 1){
            plmContext.setContDataHora(plmContext.getContDataHora() - 1);
            Intent it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
            startActivity(it);
            finish();
        }
    }

}
