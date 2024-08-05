package com.santiagoagdo.pruebakeppri.model;

import java.math.BigDecimal;

public class BalanceResponse {
    private BigDecimal saldo;

    public BalanceResponse(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}