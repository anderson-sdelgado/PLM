package br.com.usinasantafe.plm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.plm.bo.ConexaoWeb;
import br.com.usinasantafe.plm.bo.ManipDadosEnvio;
import br.com.usinasantafe.plm.bo.ManipDadosVerif;
import br.com.usinasantafe.plm.tb.estaticas.ParadaTO;
import br.com.usinasantafe.plm.tb.estaticas.RAtivParadaTO;

public class ListaParadaActivity extends ActivityGeneric {

    private PLMContext plmContext;
    private ArrayAdapter<String> paradaArrayAdapter;
    private ProgressDialog progressBar;
    private String paradaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parada);

        plmContext = (PLMContext) getApplication();

        Button buttonAtualParada = (Button) findViewById(R.id.buttonAtualParada);
        Button buttonRetMenuParada = (Button) findViewById(R.id.buttonRetMenuParada);
        EditText editPesqListParada = (EditText) findViewById(R.id.editPesqListParada);

        RAtivParadaTO rAtivParadaTO = new RAtivParadaTO();
        List rAtivParadaList = rAtivParadaTO.get("idAtiv", plmContext.getApontTO().getAtivApont());

        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < rAtivParadaList.size(); i++) {
            rAtivParadaTO = (RAtivParadaTO) rAtivParadaList.get(i);
            rLista.add(rAtivParadaTO.getIdParada());
        }

        rAtivParadaList.clear();

        ParadaTO paradaTO = new ParadaTO();
        List paradaList = paradaTO.inAndOrderBy("idParada", rLista, "codParada", true);

        String itens[] = new String[paradaList.size()];

        for (int i = 0; i < paradaList.size(); i++) {
            paradaTO = (ParadaTO) paradaList.get(i);
            itens[i] = paradaTO.getCodParada() + " - " + paradaTO.getDescrParada();
        }

        paradaArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_item_lista, R.id.textViewItemList, itens);
        ListView paradaListView = (ListView) findViewById(R.id.listViewMotParada);
        paradaListView.setAdapter(paradaArrayAdapter);

        editPesqListParada.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                ListaParadaActivity.this.paradaArrayAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        buttonAtualParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaParadaActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Paradas...");
                    progressBar.show();

                    ManipDadosVerif.getInstance().verDados("", "Parada"
                            , ListaParadaActivity.this, ListaParadaActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
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

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(ListaParadaActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }
        });

        paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                paradaString = textView.getText().toString();

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                alerta.setTitle("ATENÇÃO");

                String label = "DESEJA REALMENTE REALIZAR A PARADA '" + paradaString + "' ?";

                alerta.setMessage(label);

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ParadaTO paradaTO = new ParadaTO();
                        List paradaList = paradaTO.get("codParada", paradaString.substring(0, paradaString.indexOf('-')).trim());
                        paradaTO = (ParadaTO) paradaList.get(0);
                        plmContext.getApontTO().setParadaApont(paradaTO.getIdParada());
                        paradaList.clear();

                        plmContext.setTextoHorimetro("HORÍMETRO INICIAL:");

                        Intent it = new Intent(ListaParadaActivity.this, HorimetroActivity.class);
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

    }

    public void onBackPressed() {
    }

}
