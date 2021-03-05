# Desafio Pessoa Desenvolvedora Java

## 🏗 O que fazer?

- Você deve realizar um *fork* deste repositório e, ao finalizar, enviar o link do seu repositório para a nossa equipe.
  Lembre-se, **NÃO** é necessário criar um *Pull Request* para isso, nós iremos avaliar e retornar por e-mail o
  resultado do teste

# 🚨 Requisitos

- A API deve ser construída em Java (8 ou superior) utilizando Spring Framework (2.2 ou superior)
- Implementar autenticação seguindo o padrão ***JWT***, lembrando que o token a ser recebido deve estar no formado ***
  Bearer***
- Implementar operações no banco de dados utilizando ***Spring Data JPA*** & ***Hibernate***
- **Bancos relacionais permitidos**
    - *MySQL* (prioritariamente)
    - *PostgreSQL*
- As entidades deversão ser criadas como tabelas utilizando a ferramenta de migração **Flyway**. Portanto, os scripts
  de **migrations** para geração das tabelas devem ser enviados no teste
- Sua API deverá seguir os padrões REST na construção das rotas e retornos
- Sua API deverá conter documentação viva utilizando a *OpenAPI Specification* (**Swagger**)
- Caso haja alguma particularidade de implementação, instruções para execução do projeto deverão ser enviadas

# 🎁 Extra

- Testes unitários
- Teste de integração da API em linguagem de sua preferência (damos importância para pirâmide de testes)
- Cobertura de testes utilizando Sonarqube
- Utilização de *Docker* (enviar todos os arquivos e instruções necessárias para execução do projeto)

# 🕵🏻‍♂️ Itens a serem avaliados

- Estrutura do projeto
- Utilização de código limpo e princípios **SOLID**
- Segurança da API, como autenticação, senhas salvas no banco, *SQL Injection* e outros
- Boas práticas da Linguagem/Framework
- Seu projeto deverá seguir tudo o que foi exigido na seção  [O que desenvolver?](##--o-que-desenvolver)

# 🖥 O que desenvolver?

Você deverá criar uma API que o site [IMDb](https://www.imdb.com/) irá consultar para exibir seu conteúdo, sua API
deverá conter as seguintes funcionalidades:

- Administrador
    - Cadastro
    - Edição
    - Exclusão lógica (desativação)
    - Listagem de usuários não administradores ativos
        - Opção de trazer registros paginados
        - Retornar usuários por ordem alfabética
- Usuário
    - Cadastro
    - Edição
    - Exclusão lógica (desativação)
- Filmes
    - Cadastro (somente um usuário administrador poderá realizar esse cadastro)
    - Voto (a contagem de votos será feita por usuário de 0-4 que indica quanto o usuário gostou do filme)
    - Listagem
        - Opção de filtros por diretor, nome, gênero e/ou atores
        - Opção de trazer registros paginados
        - Retornar a lista ordenada por filmes mais votados e por ordem alfabética
    - Detalhes do filme trazendo todas as informações sobre o filme, inclusive a média dos votos

**Obs.:**

**Apenas os usuários poderão votar nos filmes e a API deverá validar quem é o usuário que está acessando, ou seja, se é
um usuário administrador ou não.**

**Caso não consiga concluir todos os itens propostos, é importante que nos envie a implementação até onde foi possível
para que possamos avaliar**

# Adendo

## Iniciando

Com essas instruções será possível executar esse projeto na máquina local para testes.

### Pré requisitos

* Necessário ter o **MySQL** instalado na máquina para executar o projeto.

Antes de criar o banco de dados, será preciso informar `username` e `password` no arquivo
`application.yml` em `src/main/resource`. As linhas a serem alteradas:

```properties
datasource:
url:jdbc:mysql://localhost:3306/ioasyschallenge
username:root
password:123456
```

Como o projeto usa o **Flyway** também é necessário alterar os dados de login no banco, no arquivo `flyway.properties`
em `ioasys_challenge`. As linhas a serem alteradas:

```properties
flyway.user:root
flyway.password:123456
```

## Inicie a aplicação usando uma IDE (Intellij)

* Faça o download ou clone desse repositório.
* Abra o terminal e mude (cd) para a pasta que contêm o pom.xml
* Abra o Intellij ou Eclipse e selecione a pasta do projeto que contêm o arquivo pom.xml
* Abra o arquivo Spring Boot Application file (procure por @IoasyschallengeApplication)
* Dê um clique com o botão direito e dê um Run

## Acessando endpoint de autenticação

Para obter o token e acessar os demais endpoints protegidos deve:

* Acessar o endpoint **http://localhost:8080/authenticate** informando no body

```json
{
  "username": "ioasys",
  "password": "password"
}
```

Um json será informado no body, segue exemplo:

```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpb2FzeXMiLCJleHAiOjE2MTQ5MzIyODksImlhdCI6MTYxNDkxNDI4OX0.0IEQNyr1y2YtBS6FTnEdTcLDViXiW18igzMxhlELNGSA5Nokv9uNOFTT249yoLfwpjKULK694K67s2Il4Lj4iw"
}
```

Informando esse token no campo Authorization de uma requisição dará acesso aos endpoints protegidos.

## Acessando swagger

* URL para acessar o **SWAGGER-UI**: http://localhost:8080/swagger-ui.html

## To-Do

<ul>
  <li>[ ] Finalizar autenticação via JWT, com os perfis de Administrador e Usuário</li>
  <li>[ ] Retornar usuários não administradores por paginação</li>
  <li>[ ] Implementar votação para os filmes</li>
  <li>[ ] Retornar registros paginados para filmes</li>
  <li>[ ] Retornar lista ordenada de filmes mais votados em ordem alfabética</li>
  <li>[ ] Detalhes do filme</li>
</ul>