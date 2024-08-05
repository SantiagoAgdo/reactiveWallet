package com.santiagoagdo.pruebakeppri.controller;

import com.santiagoagdo.pruebakeppri.model.BalanceResponse;
import com.santiagoagdo.pruebakeppri.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/wallet")
@Validated
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/balance")
    public Mono<BalanceResponse> getBalance(@RequestParam String documento, @RequestParam String numeroCelular) {

        logger.info("Received request to get balance for documento: {} and numeroCelular: {}", documento, numeroCelular);
        return walletService.getBalance(documento, numeroCelular)
                .map(BalanceResponse::new)
                .doOnSuccess(balance -> logger.info("Successfully retrieved balance for documento: {}", documento))
                .doOnError(error -> logger.error("Failed to retrieve balance for documento: {}", documento, error));
    }
}
