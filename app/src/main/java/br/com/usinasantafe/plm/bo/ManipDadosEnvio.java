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
import br.com.usinasantafe.plm.tb.variaveis.ApontTO;
import br.com.usinasantafe.plm.tb.variaveis.ConfiguracaoTO;

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

    public void salvaApont(ApontTO apontTO) {

        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
        List configList = configuracaoTO.all();
        configuracaoTO = (ConfiguracaoTO) configList.get(0);
        configList.clear();

        String datahora = Tempo.getInstance().datahora();
        apontTO.setMatricLiderApont(configuracaoTO.getMatricLiderConfig());
        apontTO.setDthrApont(datahora);
        apontTO.insert();

        envioDadosPrinc();

    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void envioApont() {

        JsonArray jsonArrayApont = new JsonArray();

        ApontTO apontTO = new ApontTO();
        List apontaList = apontamentosMM();

        for (int i = 0; i < apontaList.size(); i++) {

            apontTO = (ApontTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayApont.add(gson.toJsonTree(apontTO, apontTO.getClass()));


        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("apont", jsonArrayApont);

        String dados = jsonAponta.toString();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApont()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    /////////////////////////////// DELETAR DADOS ///////////////////////////////////////////////

    public void delApont() {

        ApontTO apontTO = new ApontTO();
        List apontaList = apontamentosMM();

        for (int i = 0; i < apontaList.size(); i++) {
            apontTO = (ApontTO) apontaList.get(i);
            apontTO.delete();
        }

    }

    //////////////////////////TRAZER DADOS////////////////////////////

    public List apontamentosMM() {
        ApontTO apontTO = new ApontTO();
        return apontTO.all();
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
            envioApont();
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