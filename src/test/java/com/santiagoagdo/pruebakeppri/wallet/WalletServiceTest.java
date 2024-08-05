package com.santiagoagdo.pruebakeppri.wallet;

import com.santiagoagdo.pruebakeppri.entity.Wallet;
import com.santiagoagdo.pruebakeppri.repository.WalletRepository;
import com.santiagoagdo.pruebakeppri.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    private WalletService walletService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        walletService = new WalletService(walletRepository);
    }

    @Test
    void getBalance_shouldReturnBalance_whenWalletExists() {
        String documento = "123456";
        String numeroCelular = "987654321";
        BigDecimal expectedBalance = new BigDecimal("100.00");

        Wallet wallet = new Wallet();
        wallet.setDocumento(documento);
        wallet.setNumeroCelular(numeroCelular);
        wallet.setSaldo(expectedBalance);

        when(walletRepository.findByDocumentoAndNumeroCelular(documento, numeroCelular)).thenReturn(Mono.just(wallet));

        StepVerifier.create(walletService.getBalance(documento, numeroCelular)).expectNext(expectedBalance).verifyComplete();
    }

    @Test
    void getBalance_shouldReturnEmptyMono_whenWalletDoesNotExist() {
        String documento = "123456";
        String numeroCelular = "987654321";

        when(walletRepository.findByDocumentoAndNumeroCelular(documento, numeroCelular)).thenReturn(Mono.empty());

        StepVerifier.create(walletService.getBalance(documento, numeroCelular))
                .expectErrorMatches(throwable ->
                        throwable instanceof Exception && throwable.getMessage().equals("Datos no coinciden")
                );

    }
}