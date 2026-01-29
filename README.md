📘 EventHub API

API RESTful para gerenciamento de eventos, desenvolvida como parte do teste técnico para a vaga de Analista de Desenvolvimento Júnior.

A aplicação permite criar, listar, buscar, atualizar e remover eventos, seguindo boas práticas de desenvolvimento backend com Java e Spring Boot.

🚀 Tecnologias Utilizadas

- Java 17

- Spring Boot 3.2.x

- Spring Data JPA

- Bean Validation (Spring Validation)

- MySQL

- Maven

- Swagger / OpenAPI (Springdoc)

📐 Arquitetura

O projeto segue uma arquitetura em camadas, separando claramente as responsabilidades:

controller → service → repository → entity


Além disso:

DTOs são utilizados para entrada e saída de dados

Mapper centraliza a conversão entre Entity e DTO

Interface + implementação no Service

Exceções customizadas e handler global para padronização de erros

🗄🗄️ Modelo de Domínio

🎫 Event

Representa um evento disponível para venda de ingressos.

Campos principais:

    id
    
    name
    
    date
    
    location
    
    capacity (quantidade de ingressos disponíveis)

Regras

- A capacidade inicial deve ser maior que zero

- A cada venda de ingresso, a capacidade é decrementada

- Não é possível vender ingresso quando a capacidade é zero

Regras de Validação

- O nome não pode ser vazio

- A data do evento deve ser futura

- A capacidade é obrigatória 

Essas validações são realizadas utilizando Bean Validation, garantindo respostas adequadas (400 Bad Request) em caso de erro.

⚙️ Banco de Dados

- Um Event pode possuir vários Tickets
- Um Participant pode comprar vários Tickets
- Cada Ticket está associado a um único Event e um único Participant

O Ticket atua como entidade de associação entre Event e Participant, representando a compra de um ingresso.

TABELAS

Event

| Campo	 |Tipo|
|--------|---|
| id	    |BIGINT (PK)|
| name   |	VARCHAR(255)
| date   |	DATETIME
|location|	VARCHAR(255)
|capacity	|INT

Participant

| Campo	 | Tipo
|--------|---|
| id     |	BIGINT (PK)
| name	  |VARCHAR(255)
| email	 |VARCHAR(255)

Ticket

| Campo |	Tipo|
|---|---|
|id |	BIGINT (PK)|
| event_id      | BIGINT (FK)
|participant_id	| BIGINT (FK)
|purchase_date	| DATETIME

Obs: A versão do MySQL utilizada localmente é 5.7.


📌 Endpoints Principais

📅 Eventos

| Método | 	Endpoint     |	Descrição|
|--------|---------------|---|
| POST   | 	/events	     |Criar evento|
| GET	   | /events	      |Listar eventos|
| GET	   | /events/{id}  |	Buscar evento por ID|
| PUT	   | /events/{id}	 | Atualizar evento |
|DELETE |	/events/{id}	| Remover evento|
🎟️ Venda de Ingressos

|Método	| Endpoint	| Descrição|
|---|---|---|
|POST|	/tickets/purchase	| Comprar ingresso|
|GET |	/tickets/participant/{id}|	Histórico por participante (ID)|
|GET	|/tickets/participant?email=	|Histórico por participante (email)|


📊 Métricas

|Método|	Endpoint |	Descrição|
|---|---|---|
|GET|	/tickets/count/event/{eventId}	|Total de ingressos vendidos por evento|

▶️ Como Executar a Aplicação
Pré-requisitos

Java 17+

Maven

MySQL em execução

Passos

    git clone https://github.com/Elizabeth-Barcena/EventHub.git
    
    cd EventHubAPI
    mvn spring-boot:run


A aplicação ficará disponível em:

http://localhost:8080

📖 Documentação da API (Swagger)

Após iniciar a aplicação, acesse:

http://localhost:8080/swagger-ui/index.html


O Swagger permite:

- Visualizar todos os endpoints disponíveis

- Ver os DTOs utilizados

- Testar as requisições diretamente pelo navegador

❗ Tratamento de Erros

A API possui tratamento global de exceções, retornando respostas padronizadas para:

    Recurso não encontrado (404)
    
    Erros de validação (400)
    
    Erros inesperados (500)


👤 Autora

Elizabeth Barcena
GitHub: https://github.com/Elizabeth-Barcena