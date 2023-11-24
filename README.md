# Spring Data Mongodb

Exemplo de aplicação Java com Spring Boot conectada a um banco de dados MongoDB, com suporte a transações

Este projeto utiliza o serviço MongoDB Atlas para armazenamento de dados. MongoDB Atlas oferece um ambiente de banco de dados totalmente gerenciado na nuvem.

## Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)

## Configuração do Projeto

1. Clone este repositório:

    ```bash
    git clone git@github.com:Cavassoni/spring-data-mongodb.git
    ```

2. Acesse o diretório do projeto:

    ```bash
    cd spring-data-mongodb
    ```

3. Execute o aplicativo:

    ```bash
    mvn spring-boot:run
    ```

4. Acesse a aplicação em [http://localhost:8080](http://localhost:8080).

## Conexão com o MongoDB Atlas

### Criando uma Conta no MongoDB Atlas

Para utilizar este projeto e configurar seu próprio banco de dados MongoDB na nuvem, siga os passos abaixo:

1. Acesse [MongoDB Atlas](https://www.mongodb.com/cloud/atlas).
2. Clique em "Get Started Free" para criar uma nova conta.
3. Siga as instruções para configurar sua conta, incluindo a criação de um novo cluster.
4. Após a criação do cluster, obtenha a URL de conexão do MongoDB Atlas.

### Configuração

Certifique-se de configurar corretamente a conexão com o MongoDB Atlas. Para isso, ajuste as configurações no arquivo `application.properties` no diretório `src/main/resources`.

```properties
# Configurações do MongoDB Atlas
db.config.uri=mongodb+srv://SEU_USUARIO:SUA_SENHA@clusterXYZ.mongodb.net/
db.config.database=mongo_db
```
