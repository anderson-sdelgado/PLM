package br.com.usinasantafe.plm.bo;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.plm.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.plm.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.plm.pst.EspecificaPesquisa;
import br.com.usinasantafe.plm.tb.variaveis.ApontTO;
import br.com.usinasantafe.plm.tb.variaveis.BoletimTO;
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

    public void salvaBolAbertoApont(BoletimTO boletimTO, ApontTO apontTO) {

        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
        List configList = configuracaoTO.all();
        configuracaoTO = (ConfiguracaoTO) configList.get(0);
        configList.clear();

        boletimTO.setMatricLiderBoletim(configuracaoTO.getLiderConfig());
        boletimTO.setStatusBoletim(1L);
        boletimTO.insert();

        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBoletim");
        pesquisa.setValor(1L);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idEquipBoletim");
        pesquisa2.setValor(boletimTO.getIdEquipBoletim());
        listaPesq.add(pesquisa2);

        List boletimList = boletimTO.get(listaPesq);
        boletimTO = (BoletimTO) boletimList.get(0);
        boletimList.clear();

        apontTO.setIdBolApont(boletimTO.getIdBoletim());
        apontTO.setDthrApont(boletimTO.getDthrInicioBoletim());
        apontTO.insert();

        envioDadosPrinc();

    }

    public void salvaBolFechado(BoletimTO boletimTO) {

        boletimTO.setStatusBoletim(3L);
        boletimTO.update();

        envioDadosPrinc();

    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void envioBolAberto() {

        BoletimTO boletimTO = new BoletimTO();
        List boletimList = boletinsAberto();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayApont = new JsonArray();

        for (int i = 0; i < boletimList.size(); i++) {

            boletimTO = (BoletimTO) boletimList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimTO, boletimTO.getClass()));

            ApontTO apontTO = new ApontTO();
            List apontList = apontTO.get("idBolApont", boletimTO.getIdBoletim());

            for (int j = 0; j < apontList.size(); j++) {

                apontTO = (ApontTO) apontList.get(j);
                Gson gson = new Gson();
                jsonArrayApont.add(gson.toJsonTree(apontTO, apontTO.getClass()));

            }

            apontList.clear();

        }

        boletimList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonApont = new JsonObject();
        jsonApont.add("apont", jsonArrayApont);

        String dados = jsonBoletim.toString() + "_" + jsonApont.toString();

        Log.i("PMM", "BOLETIM ABERTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertBolAberto()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    public void enviarBolFechado() {

        BoletimTO boletimTO = new BoletimTO();
        List boletimList = boletinsFechado();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayApont = new JsonArray();

        for (int i = 0; i < boletimList.size(); i++) {

            boletimTO = (BoletimTO) boletimList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimTO, boletimTO.getClass()));

            ApontTO apontTO = new ApontTO();
            List apontList = apontTO.get("idBolApont", boletimTO.getIdBoletim());

            for (int j = 0; j < apontList.size(); j++) {

                apontTO = (ApontTO) apontList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayApont.add(gsonItem.toJsonTree(apontTO, apontTO.getClass()));

            }

            apontList.clear();

        }

        boletimList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonApont = new JsonObject();
        jsonApont.add("apont", jsonArrayApont);

        String dados = jsonBoletim.toString() + "_" + jsonApont.toString();

        Log.i("PMM", "BOLETIM FECHADO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertBolFechado()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);
    }

    /////////////////////////////// DELETAR DADOS ///////////////////////////////////////////////

    public void delBolFechado() {

        BoletimTO boletimTO = new BoletimTO();
        List boletimList = boletimTO.get("statusBoletim", 3L);
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < boletimList.size(); i++) {
            boletimTO = (BoletimTO) boletimList.get(i);
            rLista.add(boletimTO.getIdBoletim());
        }

        ApontTO apontTO = new ApontTO();
        List apontaList = apontTO.in("idBolApont", rLista);

        for (int j = 0; j < apontaList.size(); j++) {
            apontTO = (ApontTO) apontaList.get(j);
            apontTO.delete();
        }

        for (int i = 0; i < boletimList.size(); i++) {
            boletimTO = (BoletimTO) boletimList.get(i);
            boletimTO.delete();
        }

    }

    public void atualBolAberto(){

        BoletimTO boletimTO = new BoletimTO();
        List listBoletim = boletimTO.get("statusBoletim", 1L);
        boletimTO = (BoletimTO) listBoletim.get(0);
        boletimTO.setStatusBoletim(2L);
        boletimTO.update();

        ApontTO apontTO = new ApontTO();
        List apontaList = apontTO.get("idBolApont", boletimTO.getIdBoletim());

        for (int j = 0; j < apontaList.size(); j++) {
            apontTO = (ApontTO) apontaList.get(j);
            apontTO.delete();
        }

        apontaList.clear();

    }

    //////////////////////////TRAZER DADOS////////////////////////////

    public List boletinsFechado() {
        BoletimTO boletimTO = new BoletimTO();
        return boletimTO.get("statusBoletim", 3L);
    }

    public List boletinsAberto() {

        BoletimTO boletimTO = new BoletimTO();
        return boletimTO.get("statusBoletim", 1L);

    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verifBolFechado() {
        return boletinsFechado().size() > 0;
    }

    public Boolean verifBolAberto() {
        return boletinsAberto().size() > 0;
    }

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
        if (verifBolFechado()) {
            enviarBolFechado();
        }
        else {
            if (verifBolAberto()) {
                envioBolAberto();
            }
        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifBolFechado())
                && (!verifBolAberto())){
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