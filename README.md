# SpringBoot
Projeto em Spring Boot com Java para a aula de Desenvolvimento JAVA na pós graduação

## Rotas de API

### GET - `/api/book/list`
Retorna a lista de livros no padrão abaixo
```json
[
  {
    "title": "titulo",
    "pages": 200,
    "authorId": 1,
    "author": "Nome do autor",
    "evaluations": [
      {
        "comment": "Comentário",
        "note": 1
      }
    ]
  }
]
```

### POST - `/api/book`
Cria um novo livro, recebe os dados via **body** na estrutura abaixo:
```json
{
	"title": "Título",
	"pages": 100,
	"authorId": 1
}
```

### GET - `/api/book/{id}`
Retorna os dados do livro passado no parâmetro de rota (`{id}`) na seguinte estrutura:
```json
[
  {
    "title": "titulo",
    "pages": 200,
    "authorId": 1,
    "author": "Nome do autor",
    "evaluations": [
      {
        "comment": "Comentário",
        "note": 1
      }
    ]
  }
]
```

### PUT - `/api/book/{id}`
Atualiza os dados do livro passado pelo parâmetro de rota (`{id}`) recendo as informações via **body** na estrutura abaixo:
```json
{
	"title": "Título",
	"pages": 100,
	"authorId": 1
}
```
 
### DELETE - `/api/book/{id}`
Deleta o livro do id passado via paramêtro de rota (`{id}`)

### POST - `/api/book/evaluation/{id}`
Cria um novo comentário sobre o livro, deve passar os dados via **body** seguindo a seguinte estrutura:
```json
{
	"comment": "Comentário",
	"note": 1
}
```

## Regras de acesso desenvolvidas
Para o gerenciamento de tipo e acesso dos usuários, foram criadas três regras:
- **ROLE_VISUALIZAR**: Permite que o usuário visualize os livros e autores. 
- **ROLE_GERENCIA_USUARIOS**: Permite que o usuário gerencie os usuários do sistema, podendo listar, alterar, criar e excluir.
- **ROLE_ATUALIZAR_DADOS**: Libera acesso as regras de  

> **Obs.:** A autenticação foi desenvolvida apenas para o sistema, as apis no momento permanescem públicas.
