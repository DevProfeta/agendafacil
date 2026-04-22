## 📸 Demonstração

### API funcionando
![API](images/api.png)

### Docker rodando
![Docker](images/docker.png)

### CI/CD no GitHub
![CI](images/ci.png)



# Agendafacil

API REST simples de agendamentos desenvolvida com Spring Boot, criada para fins acadêmicos como base para práticas de DevOps (containerização, CI/CD).

---

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 3.2.5 |
| Maven | 3.9+ |
| Docker | 20+ |

---

## Estrutura de Endpoints

### Agenda (`/agenda`)

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/agenda/status` | Verifica se a API está online |
| GET | `/agenda/contatos` | Lista todos os contatos |
| POST | `/agenda/contatos` | Cria um novo contato |
| DELETE | `/agenda/contatos/{id}` | Remove um contato pelo ID |

### Agendamentos (`/agendamentos`)

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/agendamentos` | Lista todos os agendamentos |
| POST | `/agendamentos` | Adiciona um novo agendamento |

> Os dados são armazenados em memória. Ao reiniciar a aplicação, os dados voltam ao estado inicial.

---

## Como Rodar Localmente

### Pré-requisitos

- Java 17 instalado
- Maven 3.9+ instalado

### Passos

**1. Clone o repositório:**

```bash
git clone <url-do-repositorio>
cd agendafacil
```

**2. Compile e inicie a aplicação:**

```bash
mvn spring-boot:run
```

**3. Acesse em:** `http://localhost:8080`

---

## Como Rodar com Docker

### Opção 1 — Build manual

**1. Gere o JAR:**

```bash
mvn clean package
```

**2. Construa a imagem:**

```bash
docker build -t agendafacil .
```

**3. Inicie o container:**

```bash
docker run -p 8080:8080 agendafacil
```

### Opção 2 — Docker Compose

```bash
docker compose up
```

A aplicação ficará disponível em `http://localhost:8080`.

---

## Exemplos de Uso (curl)

### Verificar status da API

```bash
curl http://localhost:8080/agenda/status
```

Resposta:
```json
{
  "status": "online",
  "app": "agendafacil"
}
```

---

### Listar agendamentos

```bash
curl http://localhost:8080/agendamentos
```

Resposta:
```json
[
  "Consulta médica - 22/04 às 10h",
  "Reunião de equipe - 23/04 às 14h",
  "Revisão do carro - 24/04 às 09h"
]
```

---

### Criar agendamento

```bash
curl -X POST http://localhost:8080/agendamentos \
  -H "Content-Type: application/json" \
  -d '"Dentista - 25/04 às 15h"'
```

Resposta:
```
Agendamento recebido: Dentista - 25/04 às 15h
```

---

### Listar contatos

```bash
curl http://localhost:8080/agenda/contatos
```

---

### Criar contato

```bash
curl -X POST http://localhost:8080/agenda/contatos \
  -H "Content-Type: application/json" \
  -d '{"nome": "João Silva", "telefone": "11999990000"}'
```

Resposta:
```json
{
  "id": 1,
  "nome": "João Silva",
  "telefone": "11999990000"
}
```

---

### Remover contato

```bash
curl -X DELETE http://localhost:8080/agenda/contatos/1
```

Resposta: `204 No Content`

---

## Integração Contínua (CI)

O projeto possui um pipeline de CI configurado via **GitHub Actions** (`.github/workflows/ci.yml`).

A cada push na branch `main`, o pipeline executa automaticamente:

1. Checkout do código
2. Configuração do Java 17 (Temurin)
3. Build completo com `mvn clean package`

Isso garante que o código na branch principal sempre compila e passa nos testes.

---

## Estrutura do Projeto

```
agendafacil/
├── src/
│   └── main/
│       └── java/com/agendafacil/
│           ├── AgendafacilApplication.java
│           └── controller/
│               ├── AgendaController.java
│               └── AgendamentoController.java
├── .github/
│   └── workflows/
│       └── ci.yml
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```
