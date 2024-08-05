package com.santiagoagdo.pruebakeppri.model;

public class BalanceRequest {
    private String documento;
    private String numeroCelular;

    public BalanceRequest(String documento, String numeroCelular) {
        this.documento = documento;
        this.numeroCelular = numeroCelular;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }
}
