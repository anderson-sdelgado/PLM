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
import java.util.Objects;

import br.com.usinasantafe.plm.bo.ConexaoWeb;
import br.com.usinasantafe.plm.bo.ManipDadosVerif;
import br.com.usinasantafe.plm.tb.estaticas.AtividadeTO;
import br.com.usinasantafe.plm.tb.estaticas.REquipAtivTO;
import br.com.usinasantafe.plm.tb.estaticas.ROSAtivTO;

public class ListaAtividadeActivity extends Activity {

    private ProgressDialog progressBar;
    private PLMContext plmContext;
    private ArrayList ativArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividade);

        plmContext = (PLMContext) getApplication();

        Button buttonAtualAtividade = (Button) findViewById(R.id.buttonAtualAtividade);
        Button buttonRetAtividade = (Button) findViewById(R.id.buttonRetAtividade);

        buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaAtividadeActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ATIVIDADES...");
                    progressBar.show();

                    ManipDadosVerif.getInstance().verDados("", "Atividade"
                            , ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
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

        AtividadeTO atividadeTO = new AtividadeTO();
        ArrayList<String> itens = new ArrayList<String>();

        REquipAtivTO rEquipAtivTO = new REquipAtivTO();
        List lrea = rEquipAtivTO.get("idEquip", plmContext.getApontTO().getIdEquipApont());

        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < lrea.size(); i++) {
            rEquipAtivTO = (REquipAtivTO) lrea.get(i);
            rLista.add(rEquipAtivTO.getIdAtiv());
        }

        List ativList = atividadeTO.in("idAtiv", rLista);

        ativArrayList = new ArrayList();

        ROSAtivTO rOSAtivTO = new ROSAtivTO();
        List rOSAtivList = rOSAtivTO.get("nroOS", plmContext.getApontTO().getOsApont());

        if (rOSAtivList.size() > 0) {
            for (int i = 0; i < ativList.size(); i++) {
                atividadeTO = (AtividadeTO) ativList.get(i);
                for (int j = 0; j < rOSAtivList.size(); j++) {
                    rOSAtivTO = (ROSAtivTO) rOSAtivList.get(j);
                    if (Objects.equals(atividadeTO.getIdAtiv(), rOSAtivTO.getIdAtiv())) {
                        ativArrayList.add(atividadeTO);
                    }
                }
            }
        } else {
            for (int i = 0; i < ativList.size(); i++) {
                atividadeTO = (AtividadeTO) ativList.get(i);
                ativArrayList.add(atividadeTO);
            }
        }

        for (int i = 0; i < ativArrayList.size(); i++) {
            atividadeTO = (AtividadeTO) ativArrayList.get(i);
            itens.add(atividadeTO.getCodAtiv() + " - " + atividadeTO.getDescrAtiv());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        final ListView atividadeListView = (ListView) findViewById(R.id.listAtividade);
        atividadeListView.setAdapter(adapterList);

        atividadeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                AtividadeTO atividadeTO = new AtividadeTO();
                atividadeTO = (AtividadeTO) ativArrayList.get(position);
                plmContext.getApontTO().setAtivApont(atividadeTO.getIdAtiv());
                ativArrayList.clear();

                Intent it = new Intent(ListaAtividadeActivity.this, ListaParadaActivity.class);
                startActivity(it);

            }

        });

        buttonRetAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);
                startActivity(it);
            }
        });

    }

    public void onBackPressed()  {
    }

}
