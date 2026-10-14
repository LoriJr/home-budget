# Home Budget

API REST para controle do orçamento doméstico, desenvolvida com Java e Spring Boot.

O **Home Budget** tem como objetivo auxiliar no planejamento financeiro mensal, permitindo cadastrar contas a pagar, controlar receitas provenientes do salário e acompanhar as despesas e o saldo previsto para cada mês.

O projeto será desenvolvido inicialmente como um backend REST, com possibilidade de integração futura com um frontend em React.

## Objetivos

- Centralizar as contas domésticas em um único lugar.
- Controlar receitas mensais provenientes do salário.
- Organizar as despesas conforme os períodos de recebimento.
- Acompanhar contas pagas e pendentes.
- Calcular o total de receitas, despesas e saldo mensal.
- Facilitar o planejamento financeiro doméstico.

## Funcionalidades

### Gestão de contas

- Cadastro de contas a pagar.
- Consulta individual e listagem de contas.
- Filtros por mês, categoria e situação.
- Atualização e cancelamento de contas.
- Registro de pagamentos.
- Controle de contas pendentes e pagas.

### Gestão de receitas

- Cadastro das receitas mensais.
- Controle do recebimento do quinto dia útil.
- Controle do recebimento do dia 20.
- Registro das receitas efetivamente recebidas.

### Planejamento financeiro

O orçamento mensal será organizado em dois períodos financeiros:

**Período 1 — Quinto dia útil**
- Contas com vencimento entre os dias 1 e 19.
- Receita prevista para o primeiro recebimento.
- Total de despesas e saldo previsto do período.

**Período 2 — Dia 20**
- Contas com vencimento entre o dia 20 e o último dia do mês.
- Receita prevista para o segundo recebimento.
- Total de despesas e saldo previsto do período.

A classificação das contas será realizada automaticamente com base na data de vencimento.

### Resumo mensal

A aplicação deverá disponibilizar informações como:

- Total de receitas previstas.
- Total de receitas recebidas.
- Total de despesas previstas.
- Total de despesas pagas.
- Total de despesas pendentes.
- Saldo financeiro projetado.
- Saldo financeiro realizado.
- Saldo previsto por período de recebimento.

## Tecnologias

| Tecnologia | Finalidade |
|---|---|
| Java 21 | Linguagem de programação |
| Spring Boot | Desenvolvimento da API REST |
| Spring Web | Criação dos endpoints |
| Spring Data JPA | Persistência de dados |
| PostgreSQL | Banco de dados relacional |
| Flyway | Versionamento do banco de dados |
| Bean Validation | Validação dos dados de entrada |
| JUnit 5 | Testes unitários |
| Mockito | Simulação de dependências nos testes |
| MockMvc | Testes dos endpoints HTTP |
| Springdoc OpenAPI | Documentação da API |
| Docker Compose | Ambiente local de desenvolvimento |
| Maven | Gerenciamento de dependências e build |

*As tecnologias acima representam o stack planejado para o projeto; sua configuração será realizada durante o desenvolvimento.*

## Modelo de domínio inicial

### Bill — Conta a pagar

Representa uma obrigação financeira doméstica.

Principais atributos:

- `id`: identificador da conta.
- `name`: nome da conta.
- `amount`: valor previsto.
- `dueDate`: data de vencimento.
- `category`: categoria da despesa.
- `status`: situação da conta.
- `paidAmount`: valor efetivamente pago.
- `paidAt`: data do pagamento.

### Income — Receita

Representa um recebimento previsto ou realizado.

Principais atributos:

- `id`: identificador da receita.
- `description`: descrição da receita.
- `amount`: valor previsto.
- `type`: tipo de recebimento.
- `expectedDate`: data prevista para recebimento.
- `status`: situação da receita.
- `receivedAt`: data efetiva do recebimento.

### Regras de negócio iniciais

- Valores monetários serão representados por `BigDecimal`.
- Datas de vencimento e movimentação financeira serão representadas por `LocalDate`.
- Contas terão situação inicial `PENDING`.
- Receitas terão situação inicial `EXPECTED`.
- Contas com vencimento até o dia 19 pertencerão ao primeiro período financeiro.
- Contas com vencimento a partir do dia 20 pertencerão ao segundo período.
- Contas canceladas não serão consideradas no orçamento mensal.
- Receitas previstas serão diferenciadas das receitas efetivamente recebidas.
- Saldos e totais serão calculados pelo backend.

## Endpoints planejados

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/v1/bills` | Cadastrar conta |
| GET | `/api/v1/bills` | Listar contas |
| GET | `/api/v1/bills/{id}` | Consultar conta |
| PUT | `/api/v1/bills/{id}` | Atualizar conta |
| PATCH | `/api/v1/bills/{id}/pay` | Registrar pagamento |
| PATCH | `/api/v1/bills/{id}/cancel` | Cancelar conta |
| POST | `/api/v1/incomes` | Cadastrar receita |
| GET | `/api/v1/incomes` | Listar receitas |
| PATCH | `/api/v1/incomes/{id}/receive` | Registrar recebimento |
| GET | `/api/v1/budget/summary?month=2026-10` | Consultar resumo mensal |

Os endpoints representam o planejamento inicial e poderão ser ajustados conforme a implementação das regras de negócio.

## Arquitetura

O projeto seguirá uma organização em camadas, separando as responsabilidades de apresentação, aplicação, domínio e infraestrutura.

```text
com.viratech.homebudget
├── domain
│   ├── model
│   ├── enums
│   └── repository
├── application
│   ├── service
│   └── dto
│       ├── request
│       └── response
├── infrastructure
│   └── persistence
│       ├── entity
│       ├── repository
│       └── mapper
└── presentation
    ├── controller
    └── exception
```

A estrutura definitiva poderá evoluir conforme as necessidades do projeto.

## Como executar

### Pré-requisitos

- Java 21.
- Maven.
- Docker e Docker Compose.
- PostgreSQL, disponibilizado localmente ou por container.

### Configuração

Configure as variáveis de ambiente necessárias para conexão com o banco de dados, incluindo URL JDBC, usuário e senha.

Não versione credenciais ou segredos no repositório.

### Executar a aplicação

Após configurar o projeto e o banco de dados:

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Os comandos pressupõem que o Maven Wrapper esteja presente no repositório.

### Executar os testes

Linux/macOS:

```bash
./mvnw test
```

Windows:

```powershell
.\mvnw.cmd test
```

## Documentação da API

Após a configuração do Springdoc OpenAPI, a documentação interativa poderá ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

O endereço pressupõe que a aplicação esteja executando na porta 8080.

## Roadmap

- [ ] Configurar o projeto Spring Boot.
- [ ] Configurar PostgreSQL e Flyway.
- [ ] Implementar o cadastro e a consulta de contas.
- [ ] Implementar atualização, cancelamento e pagamento de contas.
- [ ] Implementar o cadastro e o recebimento de receitas.
- [ ] Implementar a classificação automática por período financeiro.
- [ ] Implementar o resumo financeiro mensal.
- [ ] Implementar testes unitários e de integração.
- [ ] Documentar a API e as regras de negócio.
- [ ] Desenvolver o frontend em React.
- [ ] Avaliar funcionalidades futuras, como contas recorrentes e alertas de vencimento.

## Possíveis evoluções

- Cadastro de despesas recorrentes.
- Histórico de movimentações financeiras.
- Controle de pagamentos parciais.
- Saldo inicial e outras fontes de receita.
- Alertas para vencimentos próximos.
- Dashboard com gráficos mensais.
- Autenticação e proteção dos dados financeiros.

## Licença

A licença do projeto será definida futuramente.

---

**Home Budget** — organização financeira doméstica com Java e Spring Boot.
