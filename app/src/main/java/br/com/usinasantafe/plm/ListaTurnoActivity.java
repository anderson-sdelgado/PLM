package br.com.usinasantafe.plm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.plm.bo.ConexaoWeb;
import br.com.usinasantafe.plm.bo.ManipDadosVerif;
import br.com.usinasantafe.plm.tb.estaticas.EquipTO;
import br.com.usinasantafe.plm.tb.estaticas.TurnoTO;

public class ListaTurnoActivity extends Activity {

    private ProgressDialog progressBar;
    private PLMContext plmContext;
    private List turnoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        plmContext = (PLMContext) getApplication();

        Button buttonRetTurno = (Button) findViewById(R.id.buttonRetTurno);
        Button buttonAtualTurno = (Button) findViewById(R.id.buttonAtualTurno);

        buttonAtualTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaTurnoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaTurnoActivity.this)) {

                            progressBar = new ProgressDialog(ListaTurnoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO TURNO...");
                            progressBar.show();

                            ManipDadosVerif.getInstance().verDados("", "Turno"
                                    , ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaTurnoActivity.this);
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


        EquipTO equipTO = new EquipTO();
        List listEquipTO = equipTO.get("idEquip", plmContext.getApontTO().getIdEquipApont());
        equipTO = (EquipTO) listEquipTO.get(0);
        listEquipTO.clear();

        TurnoTO turnoTO = new TurnoTO();
        turnoList = turnoTO.get("codTurno", equipTO.getCodTurno());

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < turnoList.size(); i++){
            turnoTO = (TurnoTO) turnoList.get(i);
            itens.add(turnoTO.getDescTurno());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ListView turnoListView = (ListView) findViewById(R.id.listaTurno);
        turnoListView.setAdapter(adapterList);

        turnoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                TurnoTO turnoTO = (TurnoTO) turnoList.get(position);
                plmContext.getApontTO().setIdTurnoApont(turnoTO.getIdTurno());
                turnoList.clear();

                Intent it = new Intent(ListaTurnoActivity.this, OSActivity.class);
                startActivity(it);

            }

        });


        buttonRetTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaTurnoActivity.this, EquipActivity.class);
                startActivity(it);
            }
        });

    }

    public void onBackPressed()  {
    }

}
