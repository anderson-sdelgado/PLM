package br.com.usinasantafe.plm;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.plm.tb.estaticas.ColaboradorTO;
import br.com.usinasantafe.plm.tb.variaveis.ConfiguracaoTO;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuInicialList;
    private ConfiguracaoTO configTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        configTO = new ConfiguracaoTO();

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        listarMenuInicial();

    }

    private void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("BOLETIM");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialList = (ListView) findViewById(R.id.listaMenuInicial);
        menuInicialList.setAdapter(adapterList);

        menuInicialList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("BOLETIM")) {
                    ColaboradorTO colaboradorTO = new ColaboradorTO();
                    if (colaboradorTO.hasElements() && configTO.hasElements()) {
                        Intent it = new Intent(MenuInicialActivity.this, EquipActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("CONFIGURAÇÃO")) {
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
