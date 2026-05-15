# Sistema Automotivo — Gestão de Estoque de Veículos

Sistema CRUD para gerenciamento de estoque de veículos, desenvolvido como projeto acadêmico na UNIFECAF.

**Aluno:** Reuel S. 
**Curso:** Análise e Desenvolvimento de Sistemas — UNIFECAF

---

## Tecnologias

- Java 17 + Spring Boot 3.2
- Spring Data JPA / Hibernate
- MySQL 8
- HTML / CSS / JavaScript

---

## Como rodar

1. Certifique-se que o MySQL está rodando na porta `3306`
2. A senha padrão configurada é `root` — ajuste em `src/main/resources/application.properties` se necessário
3. Execute a aplicação pelo VS Code (botão ▶ Run no `SistemaAutomotivoApplication.java`) ou via terminal:
   ```bash
   java -jar target/sistema-automotivo-1.0.0.jar
   ```
4. Acesse: [http://localhost:8080](http://localhost:8080)

---

## Funcionalidades

- Cadastro, edição e exclusão de veículos
- Filtros por marca, modelo, status, ano e preço
- Controle de status: Disponível, Reservado, Vendido
- Painel com totalizadores do estoque
- API REST completa

---

## Estrutura

```
src/main/java/com/unifecaf/sistemaautomotivo/
├── entity/       # Marca, Modelo, Veiculo
├── repository/   # Interfaces JPA
├── service/      # Regras de negócio
├── controller/   # Endpoints REST
├── dto/          # Objetos de requisição
└── exception/    # Tratamento de erros
```
