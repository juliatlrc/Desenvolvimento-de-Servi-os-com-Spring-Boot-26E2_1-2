# Reserva de Passagens - API REST

TP2 - Júlia Caroline Tallarico

API RESTful em Spring Boot para gerenciar reservas de passagens de ônibus. CRUD
em memória (sem banco de dados), com as camadas separadas em `controllers`,
`services`, `dtos` e `models`. Dependências: Spring Web, Spring Boot DevTools e
Lombok.

## Como rodar

```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

## Endpoints

| Método | Rota                          | Descrição                          | Status |
|--------|-------------------------------|------------------------------------|--------|
| GET    | `/passagens`                  | Lista todas as passagens           | 200    |
| GET    | `/passagens/{id}`             | Busca uma passagem pelo id         | 200/404|
| GET    | `/passagens/busca?destino=`   | Filtra passagens por destino       | 200    |
| POST   | `/passagens`                  | Cria uma passagem                  | 201/400|
| PUT    | `/passagens/{id}`             | Atualiza uma passagem              | 200/404|
| DELETE | `/passagens/{id}`             | Remove uma passagem                | 204/404|

### Exemplo de corpo (POST/PUT)

```json
{
  "passageiro": "Diego Alves",
  "assento": 4,
  "origem": "Santos",
  "destino": "Sao Paulo",
  "data": "2026-06-15",
  "status": "CONFIRMADA"
}
```

Ao subir, já existem 3 passagens cadastradas no construtor do service para
facilitar os testes.
