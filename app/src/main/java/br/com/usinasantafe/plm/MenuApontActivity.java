package br.com.usinasantafe.plm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.plm.tb.variaveis.ConfiguracaoTO;

public class MenuApontActivity extends ActivityGeneric {

    private ListView opcaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_apont);

        Button buttonRetApont = (Button) findViewById(R.id.buttonRetApont);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAR");
        itens.add("MATRICULA LÍDER");

        AdapterList adapterList = new AdapterList(this, itens);
        opcaoList = (ListView) findViewById(R.id.listApont);
        opcaoList.setAdapter(adapterList);

        opcaoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("APONTAR")) {

                    ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                    List configList = configuracaoTO.all();
                    configuracaoTO = (ConfiguracaoTO) configList.get(0);
                    configList.clear();

                    if(configuracaoTO.getLiderConfig() > 0) {
                        Intent it = new Intent(MenuApontActivity.this, ListaBoletimActivity.class);
                        startActivity(it);
                        finish();
                    }else{
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuApontActivity.this);
                        alerta.setTitle("ATENCAO");
                        alerta.setMessage("FALTA DE CADASTRO DE LÍDER. POR FAVOR, INSIRA A MATRICULA DO LÍDER ABAIXO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();
                    }
                } else if (text.equals("MATRICULA LÍDER")) {
                    Intent it = new Intent(MenuApontActivity.this, LiderActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        buttonRetApont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuApontActivity.this, MenuInicialActivity.class);
                startActivity(it);
            }
        });

    }

    public void onBackPressed()  {
    }

}
