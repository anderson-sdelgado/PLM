package br.com.usinasantafe.plm.conWEB;

public class UrlsConexaoHttp {

    private int tipoEnvio = 1;

    public static String urlPrincipal = "http://www.usinasantafe.com.br/plmqa/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/plmqa/";

    //public static String localPSTVariavel = "br.com.usinasantafe.plm.tb.variaveis.";
    public static String localPSTEstatica = "br.com.usinasantafe.plm.tb.estaticas.";
    public static String localUrl = "br.com.usinasantafe.plm.conWEB.UrlsConexaoHttp";

    public static String AtividadeTO = urlPrincipal + "atividade.php";
    public static String ParadaTO = urlPrincipal + "parada.php";
    public static String TurnoTO = urlPrincipal + "turno.php";
    public static String ColabTO = urlPrincipal + "colab.php";
    public static String EquipTO = urlPrincipal + "equip.php";
    public static String RModeloAtivTO = urlPrincipal + "rmodeloativ.php";
    public static String RAtivParadaTO = urlPrincipal + "rativparada.php";

    public UrlsConexaoHttp() {
        // TODO Auto-generated constructor stub
    }

    public String getsInsertBolAberto() {
        return urlPrincEnvio + "inserirbolaberto.php";
    }

    public String getsInsertBolFechado() {
        return urlPrincEnvio + "inserirbolfechado.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincEnvio + "equip.php";
        } else if (classe.equals("OS")) {
            retorno = urlPrincEnvio + "os.php";
        } else if (classe.equals("Atividade")) {
            retorno = urlPrincEnvio + "atualativ.php";
        } else if (classe.equals("Parada")) {
            retorno = urlPrincEnvio + "atualparada.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualaplic.php";
        } else if (classe.equals("Colab")) {
            retorno = urlPrincEnvio + "colab.php";
        } else if (classe.equals("Turno")) {
            retorno = urlPrincEnvio + "turno.php";
        }
        return retorno;
    }

}
