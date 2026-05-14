# Roteiro do Vídeo Pitch — Sistema Automotivo

**Aluno:** Reuell Serafim de Lima Queiroz — UNIFECAF
**Duração total:** até 4 minutos
**Ferramenta sugerida para gravar:** OBS Studio (gratuito) ou a gravação de tela do Windows (Win + G)

---

## Como se preparar antes de gravar

1. Deixe o **VS Code aberto** com a estrutura de pastas do projeto visível na lateral esquerda.
2. Deixe o **terminal do Spring Boot rodando** (já com o `Started SistemaAutomotivoApplication`).
3. Deixe a **tela web aberta** em outra aba do navegador (http://localhost:8080).
4. Abra o **MySQL Workbench** (ou DBeaver) já conectado, mostrando as tabelas `marca`, `modelo` e `veiculo`.
5. Faça um teste do áudio antes — fale tranquilo, sem pressa.

---

## ROTEIRO COM FALA E TEMPO

### Bloco 1 — Introdução (0:00 – 0:30)

> *Tela: capa do PowerPoint da Parte Teórica OU tela web do sistema aberta.*

**Fala:**
> "Olá, professor. Meu nome é **Reuell Serafim de Lima Queiroz**, aluno da UNIFECAF, e este é o pitch do meu projeto: **Sistema Automotivo — Gestão de Estoque de Veículos**.
>
> O objetivo do sistema é resolver um problema real de concessionárias: o controle desorganizado de estoque, geralmente feito em planilhas. Eu desenvolvi um sistema completo com **back-end em Java e Spring Boot, banco de dados MySQL e interface web**, que permite cadastrar, consultar, filtrar, atualizar e remover veículos do estoque."

---

### Bloco 2 — Arquitetura e código (0:30 – 1:30)

> *Tela: VS Code mostrando a estrutura de pastas.*

**Fala:**
> "A arquitetura segue o padrão **em camadas**, separando responsabilidades:
>
> — Na pasta `entity` estão as **três entidades principais**: `Marca`, `Modelo` e `Veiculo`. Aqui apliquei os pilares da **Programação Orientada a Objetos**: encapsulamento, com atributos privados; herança, porque os repositórios estendem `JpaRepository`; e polimorfismo, com interfaces resolvidas em tempo de execução pelo Spring.
>
> — Na pasta `repository`, as interfaces JPA cuidam do acesso ao banco automaticamente.
>
> — Na pasta `service` ficam as **regras de negócio**, como criar veículo, atualizar status, aplicar filtros.
>
> — Na pasta `controller` ficam os **endpoints REST** que recebem as requisições HTTP.
>
> Tudo isso conectado ao **MySQL** via JPA / Hibernate, configurado no `application.properties`."

> *Dica: passe o cursor sobre cada pasta enquanto fala, abrindo rapidamente um arquivo de cada (não precisa ler o código, só mostrar que existe).*

---

### Bloco 3 — Demonstração ao vivo: CRUD completo (1:30 – 3:15)

> *Tela: navegador em http://localhost:8080.*

**Fala (siga a sequência abaixo):**

**(a) Visão geral [10s]:**
> "Esta é a interface do sistema. No topo temos um resumo: total de veículos, disponíveis, reservados e vendidos. O banco já vem populado com **12 veículos de exemplo**."

**(b) Filtro — READ [15s]:**
> "Vou aplicar um filtro: marca **Toyota**, status **Disponível**." → *Clique em Filtrar.*
> "A consulta foi feita via API REST, no endpoint `/api/veiculos`."

**(c) Cadastrar — CREATE [25s]:**
> *Limpe os filtros, role até o formulário de cadastro.*
> "Agora vou cadastrar um veículo novo: selecionar **Honda**, modelo **Civic**, ano **2024**, cor **Preto**, preço **160 mil**, quilometragem **5 mil**, status **Disponível**." → *Clique em Salvar.*
> "Veículo cadastrado. Repare que ele apareceu na tabela e o contador de disponíveis subiu."

**(d) Atualizar — UPDATE [20s]:**
> *Clique em "Editar" no veículo recém-criado.*
> "Vou alterar o preço para **155 mil** e o status para **Reservado**." → *Salvar.*
> "Atualização concluída — o registro foi alterado no banco em tempo real."

**(e) Vender (mudança de status — PATCH) [10s]:**
> *Clique em "Vender" em algum veículo.*
> "Aqui faço uma alteração de status via endpoint PATCH — esse veículo passou para vendido."

**(f) Excluir — DELETE [10s]:**
> *Clique em "Excluir" em um veículo qualquer.*
> "E por fim, posso excluir um veículo do estoque. Operação confirmada — CRUD completo demonstrado."

---

### Bloco 4 — Banco de dados (3:15 – 3:40)

> *Tela: MySQL Workbench.*

**Fala:**
> "No banco MySQL, o sistema criou automaticamente três tabelas: **`marca`**, **`modelo`** e **`veiculo`**, com **relacionamento de chave estrangeira** entre elas — uma marca tem vários modelos, e um modelo tem vários veículos.
>
> Aqui posso ver os dados que acabei de cadastrar refletidos diretamente na tabela `veiculo`." → *Rode um `SELECT * FROM veiculo;`*

---

### Bloco 5 — Encerramento (3:40 – 4:00)

> *Tela: volte para a interface web.*

**Fala:**
> "Encerrando: o **Sistema Automotivo** entrega um CRUD completo e funcional, com arquitetura limpa em camadas, validações no back-end, API REST documentada e interface web amigável. O código está disponível no meu GitHub.
>
> Agradeço a atenção, professor. Obrigado!"

---

## Checklist final antes de gravar

- [ ] MySQL está rodando
- [ ] Aplicação Spring Boot iniciada sem erros
- [ ] Tela web carrega em http://localhost:8080
- [ ] MySQL Workbench conectado e mostrando as tabelas
- [ ] VS Code com as pastas abertas
- [ ] Microfone testado, sem ruído
- [ ] Resolução da tela em pelo menos 1280×720
- [ ] Já fez uma "prova" sem gravar, pra cronometrar
- [ ] Vídeo final tem no máximo 4 minutos

---

## Dicas de gravação

1. **Fale devagar** — 4 minutos parece pouco, mas dá tempo de tudo se você não correr.
2. **Não leia o roteiro durante a gravação** — pratique 2 vezes antes pra falar de cabeça (o resultado fica natural).
3. **Se errar, não pare** — emende e continue, é difícil que o professor pegue um pequeno tropeço.
4. **Mostre o mouse claramente** quando estiver clicando em botões — ajuda quem assiste a acompanhar.
5. **Bom corte de encerramento:** termine com a tela web aberta e mostrando o sistema completo, dá uma sensação de "produto pronto".

Boa sorte! 🚀
