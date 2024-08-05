# Wallet 

El proyecto está enfocado en demostrar el uso de programación reactiva con Java Spring WebFlux. Se integra específicamente con AWS DynamoDB como la única conexión AWS necesaria. El proyecto no incluye roles, autenticación de usuarios u otras características fuera del alcance de la validación de la programación reactiva con WebFlux.

## Características

- **Programación Reactiva**: Utiliza Spring WebFlux para manejar solicitudes de manera reactiva.
- **Integración con AWS DynamoDB**: Conecta con una tabla de DynamoDB llamada `Wallet` para obtener detalles del saldo de la billetera.
- **Un Solo Endpoint**: Proporciona un solo endpoint para consultar el saldo de la billetera.

## Endpoint

### Obtener Saldo de la Billetera

- **Endpoint**: `GET /wallet/balance`
- **Parámetros**:
  - `documento` (parámetro de consulta)
  - `numeroCelular` (parámetro de consulta)
- **Respuesta**:
  - Éxito: `{ "saldo": 10000 }`
  - Error: `{ "mensaje": "Datos no coinciden" }`

#### Ejemplo con cURL

```sh
curl --location 'http://localhost:8080/wallet/balance?documento=1000123456&numeroCelular=30012345678' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
```
----
```
Succes Response
{
    "saldo": 10000
}
```
```
Error Repsonse
{
    "codeError": "001",
    "msmError": "Datos no Coinciden"
}
```

### Tabla DynamoDB
Nombre de la Tabla: Wallet
Datos de Ejemplo:
json

```
{
  "id": "1",
  "documento": "1000123456",
  "numeroCelular": "30012345678",
  "saldo": 10000
}
```

#### Credenciales de AWS
Los parámetros de conexión de AWS están configurados a través de las Credenciales de AWS ubicadas en ~/.aws/credentials.

#### Arquitectura
Patrón de Diseño: Patrón Reactor
El proyecto utiliza el patrón Reactor, que es la base de la programación reactiva en Spring WebFlux.
Pruebas Unitarias
El proyecto incluye pruebas unitarias para validar la funcionalidad de las características implementadas.


#### Requisitos Previos
- Java 11+
- Maven
- Cuenta de AWS con DynamoDB configurado
- Credenciales de AWS configuradas en ~/.aws/credentials

### Ejecutando la Aplicación
1. Clona el repositorio.
2. mvn clean install

  Ejecuta la aplicación.
```
  mvn spring-boot:run
```
#### Ejecutando Pruebas
Ejecuta las pruebas unitarias usando Maven.
```
mvn test
```


