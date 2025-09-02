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
    - Spring Web Flux
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

Aqui está a lista das principais rotas da API e o que elas fazem.

- CotaçãoController:


| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /api/cotacoes/agora/{par}</kbd>      | Busca a cotação mais recente para o par, ver [response details](#get-par-cotacao)
| <kbd>GET /api/cotacoes/agora/dashboard</kbd>     | Busca as cotações mais recente de todos os pares pré-definidos, ver [response details](#get-pares-cotacao)
| <kbd>GET /api/cotacoes/historico/{par}?dias=5</kbd>      | Recupera as cotações de um par até uma quantidade de dias, ver [response details](#get-historico-cotacao)
| <kbd>GET /api/cotacoes/moedas-suportadas</kbd>     | Recupera as moedas suportadas pela aplicação, ver [response details](#get-moeda-cotacao)


<h3 id="get-par-cotacao">GET /api/cotacoes/agora/USD-BRL</h3>

**RESPONSE**
```json
{
    "id": 2,
    "codigoMoeda": "USD-BRL",
    "nomeFormatado": "Dólar Americano/Real Brasileiro",
    "valor": 5.46,
    "dataHoraConsulta": "2025-09-02T15:09:41"
}
```

<h3 id="get-pares-cotacao">GET /api/cotacoes/agora/dashboard</h3>

**RESPONSE**
```json
[
    {
        "id": 2,
        "codigoMoeda": "USD-BRL",
        "nomeFormatado": "Dólar Americano/Real Brasileiro",
        "valor": 5.46,
        "dataHoraConsulta": "2025-09-02T15:09:41"
    },
    {
        "id": 3,
        "codigoMoeda": "EUR-BRL",
        "nomeFormatado": "Euro/Real Brasileiro",
        "valor": 6.35,
        "dataHoraConsulta": "2025-09-02T15:06:19"
    },
    {
        "id": 1,
        "codigoMoeda": "BTC-BRL",
        "nomeFormatado": "Bitcoin/Real Brasileiro",
        "valor": 604882.00,
        "dataHoraConsulta": "2025-09-02T15:09:43"
    }
]
```

<h3 id="get-historico-cotacao">GET /api/cotacoes/historico/USD-BRL?dias=5</h3>

**RESPONSE**
```json
[
    {
        "id": null,
        "codigoMoeda": "USD-BRL",
        "nomeFormatado": "Dólar Americano/Real Brasileiro",
        "valor": 5.4635,
        "dataHoraConsulta": "2025-09-02T15:07:42"
    },
    {
        "id": null,
        "codigoMoeda": "USD-BRL",
        "nomeFormatado": "Dólar Americano/Real Brasileiro",
        "valor": 5.4358,
        "dataHoraConsulta": "2025-09-01T20:49:05"
    },
    {
        "id": null,
        "codigoMoeda": "USD-BRL",
        "nomeFormatado": "Dólar Americano/Real Brasileiro",
        "valor": 5.4281,
        "dataHoraConsulta": "2025-08-31T20:52:57"
    },
    {
        "id": null,
        "codigoMoeda": "USD-BRL",
        "nomeFormatado": "Dólar Americano/Real Brasileiro",
        "valor": 5.4284,
        "dataHoraConsulta": "2025-08-29T18:07:26"
    },
    {
        "id": null,
        "codigoMoeda": "USD-BRL",
        "nomeFormatado": "Dólar Americano/Real Brasileiro",
        "valor": 5.4128,
        "dataHoraConsulta": "2025-08-28T20:37:59"
    }
]
```

<h3 id="get-moeda-cotacao">GET /api/cotacoes/moedas-suportadas</h3>

**RESPONSE**
```json
[
    "USD-BRL",
    "EUR-BRL",
    "BTC-BRL"
]
```

Fluxo Resumido

```plaintext
Frontend → CotacaoController
            ↳ Recupera dados do Banco de dados
                |
                |→ CacheSchedule
                |     ↳ Recupera dados da API externa
                      ↳ Armazena dados no Banco de dados
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
