package br.com.usinasantafe.plm;

import android.app.Application;

import br.com.usinasantafe.plm.tb.variaveis.ApontTO;
import br.com.usinasantafe.plm.tb.variaveis.BoletimTO;

public class PLMContext extends Application {

    private BoletimTO boletimTO;
    private ApontTO apontTO;
    public static String versaoAplic = "1.0";
    private int contDataHora;
    private String textoHorimetro;
    private int verPosTela; //1 - Abertura de Boletim, 2 - Fechamento

    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    public BoletimTO getBoletimTO() {
        if(boletimTO == null)
            boletimTO = new BoletimTO();
        return boletimTO;
    }

    public void setBoletimTO(BoletimTO boletimTO) {
        this.boletimTO = boletimTO;
    }

    public ApontTO getApontTO() {
        if (apontTO == null)
            apontTO = new ApontTO();
        return apontTO;
    }

    public int getContDataHora() {
        return contDataHora;
    }

    public void setContDataHora(int contDataHora) {
        this.contDataHora = contDataHora;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public String getTextoHorimetro() {
        return textoHorimetro;
    }

    public void setTextoHorimetro(String textoHorimetro) {
        this.textoHorimetro = textoHorimetro;
    }

    public int getVerPosTela() {
        return verPosTela;
    }

    public void setVerPosTela(int verPosTela) {
        this.verPosTela = verPosTela;
    }
}
