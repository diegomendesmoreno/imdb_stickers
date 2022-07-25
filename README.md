# Stickers ALURA

Projeto em Java que consome a API (não oficial) do IMDb (Internet Movie Database), baixa as imagens dos 250 filmes com maior nota do site ([IMDb Top 250 Movies](https://www.imdb.com/chart/top/)), e finalmente gera novas imagens dos posters dos filmes com um comentário na capa para serem usados como Stickers no WhatsApp.

## Exemplo

### Imagem original
<img src="doc/image_before.jpg" alt="original movie poster" width="200"/>

### Imagem modificada com o comentário
<img src="doc/image_after.png" alt="movie poster with a comment below it" width="200"/>


## Como rodar

- Baixe ou clone o repositório
- Crie um arquivo chamado `data.properties` na raíz do projeto com o seguinte conteúdo:
  - Para a API customizada de linguagens de programação:
    - Na API deployed no **heroku**:
      ```yaml
      prop.api = Languages
      prop.api.key = 
      prop.api.endpoint = https://prog-language-api.herokuapp.com/languages
      ```
    - Em `localhost/`:
      ```yaml
      prop.api = Languages
      prop.api.key = 
      prop.api.endpoint = http://localhost:8080/languages
      ```
  - Para a API do IMDb:
    - Se você usar a API (não oficial) [imdb-api.com](https://imdb-api.com/) (instável no momento):
      ```yaml
      prop.api = IMDb
      prop.api.key = SUA_API_KEY
      prop.api.endpoint = https://imdb-api.com/en/API/Top250Movies/
      ```
    - Se você usar uma mock API (criada pelo instrutor @Alexandre Aquiles - Alura):
      ```yaml
      prop.api = IMDb
      prop.api.key = 
      prop.api.endpoint = https://api.mocki.io/v2/549a5d8b
      ```
    - Se você usar uma mock API (criada por @rezendecas):
      ```yaml
      prop.api = IMDb
      prop.api.key = 
      prop.api.endpoint = https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060
      ```
    - Se você usar uma mock API (criada por @Jhon Santana):
      ```yaml
      prop.api = IMDb
      prop.api.key = 
      prop.api.endpoint = https://alura-imdb-api.herokuapp.com/movies
      ```
    - Se você usar uma mock API (criada por @Jacqueline Oliveira):
      ```yaml
      prop.api = IMDb
      prop.api.key = 
      prop.api.endpoint = https://alura-filmes.herokuapp.com/conteudos
      ```
  - Para a API [APOD (Astronomy Picture of the Day) da NASA](https://api.nasa.gov/):
    ```yaml
    prop.api = NASA
    prop.api.key = DEMO_KEY
    prop.api.start_date = 2022-06-12
    prop.api.end_date = 2022-06-14
    prop.api.endpoint = https://api.nasa.gov/planetary/apod

## Detalhes

- Informações da API (qual API, endpoint e chave) tirados do arquivo `data.properties` não incluído no repositório
- O comentário da imagem de saída tem a fonte de tamanho variável, dependente do tamanho da imagem
- O comentário é centralizado na seção inferior transparente
- O comentário é ajustado automaticamente de acordo com o tamanho da imagem
- Classe `ApiUrl` que retorna uma URL pronta tirado do arquivo `data.properties`
- De acordo com a API utilizada (`prop.api`) a interface correta é chamada
