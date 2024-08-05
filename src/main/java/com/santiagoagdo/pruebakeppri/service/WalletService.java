package com.santiagoagdo.pruebakeppri.service;

import com.santiagoagdo.pruebakeppri.entity.Wallet;
import com.santiagoagdo.pruebakeppri.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Mono<BigDecimal> getBalance(String documento, String numeroCelular) {

        logger.debug("Getting balance for documento: {} and numeroCelular: {}", documento, numeroCelular);
        return walletRepository.findByDocumentoAndNumeroCelular(documento, numeroCelular)
                .map(Wallet::getSaldo)
                .switchIfEmpty(Mono.error(new Exception("Datos no coinciden")))
                .doOnSuccess(balance -> logger.debug("Found balance: {}", balance))
                .doOnError(error -> logger.error("Error getting balance for documento: {}", documento, error));
    }
}