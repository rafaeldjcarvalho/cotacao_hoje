<h1 align="center" style="font-weight: bold;">Cotações-Hoje / Dashboard de Moedas - Backend 💻</h1>

<p align="center">
    <a href="#technologies">Tecnologias</a> • 
    <a href="#started">Começando</a> • 
    <a href="#routes">API Endpoints</a> •
    <a href="#colab">Collaborators</a> •
</p>

<p align="center">
    <b>O Cotações-Hoje é uma aplicação web que consome dados de uma API externa para exibir cotações de moedas em tempo real e seu histórico. O sistema terá um backend em Java/Spring que atuará como um intermediário inteligente, buscando os dados da fonte externa e servindo-os de forma otimizada para um frontend.</b>
</p>

<h2 id="technologies">💻 Tecnologias</h2>

Lista de todas as Tecnologias usadas no backend:
- Java
- Spring
- Módulos do spring:
    - Spring Web
    - Spring Dev Tools
    - Spring Data JPA
    - Loombok
- Maven
- Banco de dados:
    - MySQL
    - H2 Database

API Externa (Fonte de Dados):
- Nome: Awesome Exchange Rate API
- URL Base: https://economia.awesomeapi.com.br/json/
- Vantagens: Gratuita, sem necessidade de autenticação (API Key) e com endpoints fáceis de usar.

<h2 id="started">🚀 Começando</h2>

Aqui está a descrição detalhada para rodar o projeto localmente.

<h3>Pré-requisitos</h3>

Aqui está a lista de todos os pré-requisitos necessários para rodar o projeto.

- [Java JDK 17+](https://adoptium.net/pt-BR/temurin/releases?version=17)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)
- IDE (opcional):
    - Intellij
    - VSCode
    - Eclipse

<h3>Clonando projeto</h3>

Como clonar o projeto

```bash
git clone https://github.com/rafaeldjcarvalho/cotacao_hoje
```


<h3>Rodar o projeto</h3>

- Via IDE(IntelliJ, VSCode, Eclipse):
    - Abra o projeto na IDE.
    - Vá até a pasta do serviço(CotacaoHojeApplication).
    - Execute a classes Application.
- Via terminal:
    - Dentro da pasta do serviço, execute:

    ```bash
    cd cotacao_hoje
    mvn spring-boot:run
    ``` 


<h2 id="routes">📍 API Endpoints</h2>



Fluxo Resumido

```plaintext
Usuário → 
            ↳ 
            ↳ 
                |
                |→ 
                |      ↳
                |
                |→ 
                |      ↳ 
                |
                |→ 
                |      ↳
```

<h2 id="colab">🤝 Collaborators</h2>

Special thank you for all people that contributed for this project.

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/141766102?v=4" width="100px;" alt="Rafael Profile Picture"/><br>
        <sub>
          <b>Rafael de Jesus Carvalho</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
