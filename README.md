# 🎬 Movies API

Bem-vindo ao repositório **Movies API**!  
Esta API foi desenvolvida para possibilitar a leitura e análise dos dados dos indicados e vencedores da categoria **Pior Filme** do *Golden Raspberry Awards*.

O principal objetivo deste projeto é fornecer um serviço **RESTful** que processa uma lista de filmes e retorna informações específicas sobre os **produtores vencedores**.

---

## 🚀 Funcionalidades

A API oferece a seguinte funcionalidade principal:

- **Análise de Produtores Vencedores**  
  Considerando os seguintes critérios:
  - O produtor com o **maior intervalo** entre duas vitórias consecutivas.  
  - O produtor com o **menor intervalo** (vitórias mais rápidas).

---

## 🛠️ Tecnologias

- **Java 21+**
- **Lombok**
- **Gson**
- **Spring Boot**
- **Spring Data JPA**
- **Banco de Dados em Memória H2**
- **JUnit** para testes de integração

---

### Requisitos Funcionais

- **Web Service RESTful**  
  - Segue o nível 2 de maturidade de Richardson.  
  - Utiliza **verbos HTTP** e recursos para representar as operações.
  - Utiliza conceitos de arquitetura hexagonal e princípios da clean architecture.

- **Testes de Integração**  
  - Inclui testes para garantir a consistência dos dados.  
  - Assegura que o processamento está de acordo com as informações fornecidas.

- **Banco de Dados Embarcado**  
  - Utiliza banco de dados em memória **H2**.  
  - Dispensa instalação externa e simplifica a execução do projeto.

---

## 📂 Estrutura do Projeto

```text
movies-api/
├── src/
│ ├── main/
│ │ ├── java/ # Código-fonte Java
│ │ └── resources/
│ │ ├── application.properties # Configuração H2 e Spring Boot
│ │ └── movies.csv # Lista de filmes e produtores
│ └── test/
│ └── java/ # Testes de integração
├── pom.xml # Dependências Maven
└── README.md # Este arquivo
```
---

## ⚙️ Como Executar

### Pré-requisitos

- **Java 21+**
- **Maven 3.8+**

```bash
# Clonar repositório
git clone https://github.com/alesavaris/movies-api.git

# Entrar no diretório do projeto
cd movies-api

# Compilar e executar
mvn spring-boot:run

# Executar os testes
mvn test

```

A API estará disponível em: [http://localhost:8080]

## 🗄 Banco de Dados H2

A aplicação usa um banco de dados **H2 em memória**.  
Ao iniciar, os dados do arquivo `movies.csv` são carregados automaticamente.

**Console H2 disponível em:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

**Configuração padrão:**
- **JDBC URL:** `jdbc:h2:mem:movies`
- **Usuário:** `adm`
- **Senha:** `123`

## 📌 Endpoints Principais

- **GET /movies**  
  Retorna a lista completa de filmes indicados e vencedores da categoria Pior Filme.

- **GET /movies/winners**  
  Retorna o produtor com o maior intervalo entre duas vitórias consecutivas e o produtor com o menor intervalo.


## 📄 Licença

Este projeto está sob a licença MIT.  
Consulte o arquivo [LICENSE](LICENSE) para mais informações.

