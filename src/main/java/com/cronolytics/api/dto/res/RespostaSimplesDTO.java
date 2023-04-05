package com.cronolytics.api.dto.res;

import java.math.BigInteger;

public class RespostaSimplesDTO {
    private int id;
    private String label;
    private int qtdRespostas;
    private double pctTotal;

    public RespostaSimplesDTO(Integer id, String label, BigInteger qtdRespostas) {
        this.id = id.intValue();
        this.label = label;
        this.qtdRespostas = qtdRespostas.intValue();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getQtdRespostas() {
        return qtdRespostas;
    }

    public void setQtdRespostas(int qtdRespostas) {
        this.qtdRespostas = qtdRespostas;
    }

    public double getPctTotal() {
        return pctTotal;
    }

    public void setPctTotal(double pctTotal) {
        this.pctTotal = pctTotal;
    }
}
