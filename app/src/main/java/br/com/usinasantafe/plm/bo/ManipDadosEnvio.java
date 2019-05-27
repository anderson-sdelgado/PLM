package br.com.usinasantafe.plm.bo;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.plm.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.plm.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.plm.tb.variaveis.ApontaMMTO;
import br.com.usinasantafe.plm.tb.variaveis.BackupApontaTO;

public class ManipDadosEnvio {

    private static ManipDadosEnvio instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private List listDatasFrenteTO;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public ManipDadosEnvio() {
        // TODO Auto-generated constructor stub
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static ManipDadosEnvio getInstance() {
        if (instance == null) {
            instance = new ManipDadosEnvio();
        }
        return instance;
    }

    //////////////////////// SALVAR DADOS ////////////////////////////////////////////

    public void salvaAponta(ApontaMMTO apontaMMTO) {

        String datahora = Tempo.getInstance().datahora();
        apontaMMTO.setDthrAponta(datahora);
        apontaMMTO.insert();

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        backupApontaTO.setDthrAponta(apontaMMTO.getDthrAponta());
        backupApontaTO.setOsAponta(apontaMMTO.getOsAponta());
        backupApontaTO.setAtividadeAponta(apontaMMTO.getAtividadeAponta());
        backupApontaTO.setParadaAponta(apontaMMTO.getParadaAponta());
        backupApontaTO.setTransbAponta(apontaMMTO.getTransbordoAponta());
        backupApontaTO.insert();

        envioDadosPrinc();

    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void envioAponta() {

        JsonArray jsonArrayAponta = new JsonArray();

        ApontaMMTO apontaMMTO = new ApontaMMTO();
        List apontaList = apontamentosMM();

        for (int i = 0; i < apontaList.size(); i++) {

            apontaMMTO = (ApontaMMTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontaMMTO, apontaMMTO.getClass()));


        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);


        String dados = jsonAponta.toString();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApontaMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    /////////////////////////////// DELETAR DADOS ///////////////////////////////////////////////

    public void delApontaMM() {

        ApontaMMTO apontaMMTO = new ApontaMMTO();
        List apontaList = apontaMMTO.all();

    }

    //////////////////////////TRAZER DADOS////////////////////////////

    public List apontamentosMM() {
        ApontaMMTO apontaMMTO = new ApontaMMTO();
        return apontaMMTO.get("statusAponta", 2L);
    }


    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verifAponta() { return apontamentosMM().size() > 0; }



    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public void envioDadosPrinc() {
        if (verifAponta()) {
            envioAponta();
        }
    }

    public boolean verifDadosEnvio() {
        if (!verifAponta()){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }
}