# Sistema Automotivo — Gestão de Estoque de Veículos

Sistema CRUD em **Java + Spring Boot + MySQL** com interface web simples para gestão de estoque de veículos de uma concessionária.

**Aluno:** Reuell Serafim de Lima Queiroz
**Instituição:** UNIFECAF

---

## 1. Tecnologias utilizadas

- **Java 17** (linguagem)
- **Spring Boot 3.2** (framework backend)
- **Spring Web** (API REST)
- **Spring Data JPA / Hibernate** (persistência)
- **Spring Validation** (validação de dados)
- **MySQL 8** (banco de dados)
- **Lombok** (redução de boilerplate)
- **HTML / CSS / JavaScript puro** (frontend)

---

## 2. Pré-requisitos para rodar

| Item | Como verificar |
|---|---|
| Java JDK 17 ou superior | `java -version` no terminal |
| MySQL 8 instalado e rodando | Serviço `MySQL` ativo nos Serviços do Windows |
| VS Code com Extension Pack for Java | Aba de extensões do VS Code |

> Não precisa instalar o Maven separadamente — o **Extension Pack for Java** do VS Code já inclui suporte a Maven e baixa as dependências automaticamente.

---

## 3. Como rodar (passo a passo)

### Passo 1 — Verificar o MySQL
Garanta que o MySQL Server está rodando na porta padrão (`3306`) com usuário `root` **sem senha**.

Se o seu MySQL tiver senha, altere o arquivo:
`src/main/resources/application.properties` → linha `spring.datasource.password=`

### Passo 2 — Abrir o projeto no VS Code
1. Abra o VS Code
2. **Arquivo → Abrir Pasta** → selecione a pasta `sistema-automotivo`
3. Aguarde o VS Code detectar o projeto Maven (canto inferior direito mostra o progresso)
4. Quando acabar o "Building...", as dependências estão prontas

### Passo 3 — Executar a aplicação
1. Abra o arquivo `src/main/java/com/unifecaf/sistemaautomotivo/SistemaAutomotivoApplication.java`
2. Clique em **▶ Run** acima da função `main` (ou aperte `F5`)
3. Aguarde o log mostrar `Started SistemaAutomotivoApplication in X seconds`

### Passo 4 — Acessar
- **Tela web (frontend):** http://localhost:8080
- **API REST:** http://localhost:8080/api/veiculos
- **Resumo do estoque:** http://localhost:8080/api/veiculos/resumo

---

## 4. Endpoints da API

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/marcas` | Lista todas as marcas |
| POST | `/api/marcas` | Cria uma marca |
| PUT | `/api/marcas/{id}` | Atualiza uma marca |
| DELETE | `/api/marcas/{id}` | Remove uma marca |
| GET | `/api/modelos` | Lista todos os modelos (filtro opcional `?marcaId=`) |
| POST | `/api/modelos` | Cria um modelo |
| PUT | `/api/modelos/{id}` | Atualiza um modelo |
| DELETE | `/api/modelos/{id}` | Remove um modelo |
| GET | `/api/veiculos` | Lista veículos (filtros: `marcaId`, `modeloId`, `status`, `anoMin`, `anoMax`, `precoMin`, `precoMax`) |
| GET | `/api/veiculos/resumo` | Totalizadores por status |
| POST | `/api/veiculos` | Cadastra veículo |
| PUT | `/api/veiculos/{id}` | Atualiza veículo |
| PATCH | `/api/veiculos/{id}/status` | Altera status (DISPONIVEL / RESERVADO / VENDIDO) |
| DELETE | `/api/veiculos/{id}` | Remove veículo |

---

## 5. Estrutura do projeto

```
sistema-automotivo/
├── pom.xml                                  # Dependências Maven
├── database.sql                             # Script do banco (documentação)
├── README.md
└── src/main/
    ├── java/com/unifecaf/sistemaautomotivo/
    │   ├── SistemaAutomotivoApplication.java
    │   ├── entity/      # Marca, Modelo, Veiculo, StatusVeiculo, Categoria
    │   ├── repository/  # MarcaRepository, ModeloRepository, VeiculoRepository
    │   ├── service/     # Regras de negócio
    │   ├── controller/  # Endpoints REST
    │   ├── dto/         # Objetos de requisição
    │   └── exception/   # Tratamento de erros
    └── resources/
        ├── application.properties
        ├── data.sql                         # Dados iniciais
        └── static/                          # Frontend
            ├── index.html
            ├── css/style.css
            └── js/app.js
```

---

## 6. Aplicação dos pilares da POO

- **Encapsulamento:** todos os atributos das entidades são `private`, acessados apenas via getters/setters gerados pelo Lombok.
- **Herança:** os repositórios herdam de `JpaRepository`, ganhando CRUD pronto.
- **Polimorfismo:** uso de interfaces (`JpaRepository`) com implementação fornecida pelo Spring em tempo de execução.
- **Abstração:** arquitetura em camadas (Controller → Service → Repository) — cada uma com responsabilidade única.

---

## 7. Roteiro de prints para a entrega

Para o PDF de prints, capture:

1. **Estrutura do projeto** no VS Code (árvore de pastas aberta)
2. **Console do Spring Boot** mostrando `Started SistemaAutomotivoApplication`
3. **Tela web** em http://localhost:8080 com a tabela de veículos
4. **Formulário de cadastro** aberto preenchido
5. **Filtros aplicados** (ex: filtrar por marca Toyota)
6. **Banco de dados no MySQL Workbench** mostrando as tabelas `marca`, `modelo`, `veiculo` populadas
7. (Opcional) Resposta JSON de `/api/veiculos` aberto no navegador ou Postman

---

## 8. Repositório GitHub (passo a passo)

```bash
# 1. Dentro da pasta do projeto:
cd sistema-automotivo
git init
git add .
git commit -m "Sistema Automotivo - versao inicial"

# 2. Crie um repositório vazio em github.com (sem README, sem .gitignore)

# 3. Conecte e envie:
git remote add origin https://github.com/SEU_USUARIO/sistema-automotivo.git
git branch -M main
git push -u origin main
```

---

## 9. Problemas comuns

| Problema | Solução |
|---|---|
| `Access denied for user 'root'` | Verifique a senha no `application.properties` |
| `Communications link failure` | MySQL Server não está rodando — inicie pelo Painel de Serviços do Windows |
| Porta 8080 já em uso | Mude `server.port=8080` para outra porta (ex: 8081) |
| Tabelas não criadas | Verifique se o banco `sistema_automotivo` existe (o Spring tenta criar automaticamente, mas em alguns casos pode falhar) |
