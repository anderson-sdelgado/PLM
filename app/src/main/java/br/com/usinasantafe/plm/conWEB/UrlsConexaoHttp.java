package br.com.usinasantafe.plm.conWEB;

public class UrlsConexaoHttp {

    private int tipoEnvio = 1;

    public static String urlPrincipal = "http://www.usinasantafe.com.br/plmdev/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/plmdev/";

    //public static String localPSTVariavel = "br.com.usinasantafe.plm.tb.variaveis.";
    public static String localPSTEstatica = "br.com.usinasantafe.plm.tb.estaticas.";
    public static String localUrl = "br.com.usinasantafe.plm.conWEB.UrlsConexaoHttp";

    public static String AtividadeTO = urlPrincipal + "atividade.php";
    public static String ParadaTO = urlPrincipal + "parada.php";
    public static String TurnoTO = urlPrincipal + "turno.php";
    public static String ColaboradorTO = urlPrincipal + "colaborador.php";
    public static String EquipTO = urlPrincipal + "equip.php";
    public static String REquipAtivTO = urlPrincipal + "requipativ.php";
    public static String RAtivParadaTO = urlPrincipal + "rativparada.php";

    public UrlsConexaoHttp() {
        // TODO Auto-generated constructor stub
    }

    public String getsInsertApontaMM() {
        return urlPrincEnvio + "inserirapontmm2.php";
    }

    public String getsInsertBolFechadoFert() {
        return urlPrincEnvio + "inserirbolfechadofert2.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincEnvio + "verifequip2.php";
        } else if (classe.equals("OS")) {
            retorno = urlPrincEnvio + "verifos2.php";
        } else if (classe.equals("Atividade")) {
            retorno = urlPrincEnvio + "verifativ2.php";
        } else if (classe.equals("Parada")) {
            retorno = urlPrincEnvio + "verifativpar2.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualizaaplic2.php";
        } else if (classe.equals("Operador")) {
            retorno = urlPrincEnvio + "motorista2.php";
        } else if (classe.equals("Turno")) {
            retorno = urlPrincEnvio + "turno2.php";
        } else if (classe.equals("EquipSeg")) {
            retorno = urlPrincEnvio + "equipseg2.php";
        } else if (classe.equals("CheckList")) {
            retorno = urlPrincEnvio + "verifchecklist2.php";
        } else if (classe.equals("GrafPlantio")) {
            retorno = urlPrincEnvio + "grafplantio2.php";
        } else if (classe.equals("Pneu")) {
            retorno = urlPrincEnvio + "verifpneu2.php";
        } else if (classe.equals("Bocal")) {
            retorno = urlPrincEnvio + "bocal2.php";
        } else if (classe.equals("PressaoBocal")) {
            retorno = urlPrincEnvio + "pressaobocal2.php";
        }
        return retorno;
    }

}
