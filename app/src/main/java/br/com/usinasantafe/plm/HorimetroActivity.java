package br.com.usinasantafe.plm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.plm.bo.ManipDadosEnvio;

public class HorimetroActivity extends ActivityGeneric {

    private PLMContext plmContext;
    private Double horimetroNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horimetro);

        plmContext = (PLMContext) getApplication();

        Button buttonOkHorimetro = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancHorimetro = (Button) findViewById(R.id.buttonCancPadrao);
        TextView textViewHorimetro = (TextView) findViewById(R.id.textViewPadrao);

        textViewHorimetro.setText(plmContext.getTextoHorimetro());

        buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String horimetro = editTextPadrao.getText().toString();
                    horimetroNum = Double.valueOf(horimetro.replace(",", "."));

                    if(plmContext.getVerPosTela() == 1){

                        plmContext.getBoletimTO().setHodometroInicialBoletim(horimetroNum);
                        ManipDadosEnvio.getInstance().salvaBolAbertoApont(plmContext.getBoletimTO(), plmContext.getApontTO());

                    }
                    else{

                        plmContext.getBoletimTO().setHodometroFinalBoletim(horimetroNum);
                        ManipDadosEnvio.getInstance().salvaBolFechado(plmContext.getBoletimTO());

                    }

                    Intent it = new Intent(HorimetroActivity.this, ListaBoletimActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancHorimetro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        if (plmContext.getVerPosTela() == 1) {
            Intent it = new Intent(HorimetroActivity.this, ListaParadaActivity.class);
            startActivity(it);
            finish();
        } else {
            Intent it = new Intent(HorimetroActivity.this, ListaBoletimActivity.class);
            startActivity(it);
            finish();
        }
    }

}
