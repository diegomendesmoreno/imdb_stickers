# Stickers ALURA

Projeto em Java que consome a API (não oficial) do IMDb (Internet Movie Database), baixa as imagens dos 250 filmes com maior nota do site ([IMDb Top 250 Movies](https://www.imdb.com/chart/top/)), e finalmente gera novas imagens dos posters dos filmes com um comentário na capa para serem usados como Stickers no WhatsApp.

## Exemplo

### Imagem original
<img src="doc/image_before.jpg" alt="original movie poster" width="200"/>

### Imagem modificada com o comentário
<img src="doc/image_after.png" alt="movie poster with a comment below it" width="200"/>


## Como rodar

The workspace contains two folders by default, where:

- Baixe ou clone o repositório
- Crie um arquivo chamado `data.properties` com:
  - Se você usar a API (não oficial) [imdb-api.com](https://imdb-api.com/) (instável no momento):
    ```yaml
    prop.api.key = SUA_API_KEY
    prop.api.endpoint = https://imdb-api.com/en/API/Top250Movies/
    ```
  - Se você usar uma mock API (criada pelo instrutor @Alexandre Aquiles - Alura):
    ```yaml
    prop.api.key = 
    prop.api.endpoint = https://api.mocki.io/v2/549a5d8b
    ```
  - Se você usar uma mock API (criada por @rezendecas):
    ```yaml
    prop.api.key = 
    prop.api.endpoint = https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060
    ```
  - Se você usar uma mock API (criada por @Jhon Santana):
    ```yaml
    prop.api.key = 
    prop.api.endpoint = https://alura-imdb-api.herokuapp.com/movies
    ```
  - Se você usar uma mock API (criada por @Jacqueline Oliveira):
    ```yaml
    prop.api.key = 
    prop.api.endpoint = https://alura-filmes.herokuapp.com/conteudos
    ```

## Detalhes

- O comentário da imagem de saída tem a fonte de tamanho variável, dependente do tamanho da imagem
- O comentário é centralizado na seção inferior transparente
- O comentário é ajustado automaticamente de acordo com o tamanho da imagem
