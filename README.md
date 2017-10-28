# GuardaFilme Android
[![CircleCI](https://circleci.com/gh/santoslucas/guarda-filme-android.svg?style=svg)](https://circleci.com/gh/santoslucas/guarda-filme-android)

O GuardaFilme é um aplicativo open-source para organizar seus filmes vistos. A ideia é que o aplicativo evolua de pouco em pouco, trazendo novas features e capacidades em cada versão.

A primeira versão programada contempla apenas a busca e adição de filmes vistos, criando um diário simples do que você viu.

A base de dados de filmes é provida pelo [The Movie Database (TMDb)](https://www.themoviedb.org/).

## Bibliotecas e Ferramentas

* [Firebase](https://firebase.google.com/): usado para autenticação e armazenamento de dados;
* [Dagger](https://google.github.io/dagger/): utilizado para injeção de dependências;
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/guide.html): para estrutura e gerenciamento de lifecycle;

## Rodando

Para buildar e rodar o GuardaFilme em sua máquina, você precisa de um projeto no Firebase e uma chave de [API do TMDb](https://www.themoviedb.org/documentation/api).

Para o [Firebase](https://firebase.google.com/docs/android/setup), coloque o arquivo google-services.json do seu projeto na pasta app. Além disso, é necessário configurar as regras de segurança do Firebase Database para permitir a leitura e escrita de dados.

Para configurar o acesso ao TMDb, na pasta app/src/main/res/values, crie um arquivo de resource chamado tmdb.xml com o seguinte conteúdo:

```
<resources>
    <string name="tmdb_key">SUA_CHAVE_DO_TMDB_API</string>
</resources>
```