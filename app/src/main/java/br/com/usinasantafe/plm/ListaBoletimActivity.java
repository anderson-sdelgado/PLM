package br.com.usinasantafe.plm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.plm.bo.ManipDadosEnvio;
import br.com.usinasantafe.plm.tb.estaticas.EquipTO;
import br.com.usinasantafe.plm.tb.variaveis.BoletimTO;
import br.com.usinasantafe.plm.tb.variaveis.ConfiguracaoTO;

public class ListaBoletimActivity extends ActivityGeneric {

    private ListView lista;
    private PLMContext plmContext;
    private String textEquip;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_boletim);

        plmContext = (PLMContext) getApplication();

        Button buttonInserirBoletim = (Button) findViewById(R.id.buttonInserirBoletim);
        Button buttonRetornarBoletim = (Button) findViewById(R.id.buttonRetornarBoletim);

        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);
        customHandler.postDelayed(updateTimerThread, 0);

        BoletimTO boletimTO = new BoletimTO();
        List boletimList = boletimTO.dif("statusBoletim", 3L);

        ArrayList<Long> idEquipBolArrayList = new ArrayList<Long>();
        ArrayList<String> itens = new ArrayList<String>();

        for (int i = 0; i < boletimList.size(); i++) {
            boletimTO = (BoletimTO) boletimList.get(i);
            idEquipBolArrayList.add(boletimTO.getIdEquipBoletim());
        }

        boletimList.clear();

        EquipTO equipTO = new EquipTO();
        List equipList = equipTO.in("idEquip", idEquipBolArrayList);

        for (int i = 0; i < equipList.size(); i++) {
            equipTO = (EquipTO) equipList.get(i);
            itens.add(String.valueOf(equipTO.getCodEquip()));
        }

        equipList.clear();

        AdapterList adapterList = new AdapterList(this, itens);
        final ListView atividadeListView = (ListView) findViewById(R.id.listViewBoletim);
        atividadeListView.setAdapter(adapterList);

        atividadeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                textEquip = textView.getText().toString();

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBoletimActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA FECHAR O BOLETIM DO EQUIPAMENTO " + textEquip + "?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EquipTO equipTO = new EquipTO();
                        List equipList = equipTO.get("codEquip", textEquip);
                        equipTO = (EquipTO) equipList.get(0);
                        equipList.clear();

                        BoletimTO boletimTO = new BoletimTO();
                        List boletimList = boletimTO.get("idEquipBoletim", equipTO.getIdEquip());
                        boletimTO = (BoletimTO) boletimList.get(0);
                        boletimList.clear();

                        plmContext.setVerPosTela(2);
                        plmContext.setContDataHora(1);
                        plmContext.setBoletimTO(boletimTO);

                        Intent it = new Intent(ListaBoletimActivity.this, DataHoraActivity.class);
                        startActivity(it);
                        finish();

                    }

                });


                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();


            }

        });

        buttonInserirBoletim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                plmContext.setVerPosTela(1);
                plmContext.setContDataHora(1);
                Intent it = new Intent(ListaBoletimActivity.this, DataHoraActivity.class);
                startActivity(it);
            }
        });

        buttonRetornarBoletim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaBoletimActivity.this, MenuInicialActivity.class);
                startActivity(it);
            }
        });

    }

    public void onBackPressed()  {
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
