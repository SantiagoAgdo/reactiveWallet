package com.santiagoagdo.pruebakeppri.repository;

import com.santiagoagdo.pruebakeppri.entity.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class WalletRepository {

    private static final Logger logger = LoggerFactory.getLogger(WalletRepository.class);

    private final DynamoDbAsyncClient dynamoDbClient;
    private final String tableName = "Wallet";
    private final String indexName = "DocumentoNumeroCelularIndex";


    public WalletRepository(DynamoDbAsyncClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public Mono<Wallet> findByDocumentoAndNumeroCelular(String documento, String numeroCelular) {
        logger.debug("Querying DynamoDB for documento: {} and numeroCelular: {}", documento, numeroCelular);

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":documento", AttributeValue.builder().s(documento).build());
        expressionAttributeValues.put(":numeroCelular", AttributeValue.builder().s(numeroCelular).build());

        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .indexName(indexName)
                .keyConditionExpression("documento = :documento AND numeroCelular = :numeroCelular")
                .expressionAttributeValues(expressionAttributeValues)
                .build();

        return Mono.fromFuture(() -> dynamoDbClient.query(queryRequest))
                .flatMapMany(response -> Mono.justOrEmpty(response.items()))
                .singleOrEmpty()
                .map(item -> {
                    Wallet wallet = new Wallet();
                    wallet.setId(item.get(0).get("id").s());
                    wallet.setDocumento(item.get(0).get("documento").s());
                    wallet.setNumeroCelular(item.get(0).get("numeroCelular").s());
                    wallet.setSaldo(new BigDecimal(item.get(0).get("saldo").n()));
                    return wallet;
                })
                .doOnError(error -> logger.error("Error querying DynamoDB for documento: {}", documento, error));

    }
}