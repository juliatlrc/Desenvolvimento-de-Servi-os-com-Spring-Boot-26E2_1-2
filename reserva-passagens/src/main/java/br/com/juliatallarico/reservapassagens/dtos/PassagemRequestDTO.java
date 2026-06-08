package br.com.juliatallarico.reservapassagens.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Dados que o cliente envia pra criar ou atualizar uma passagem.
 * Nao tem o id (quem gera o id e o service) e, como pedido no enunciado,
 * nao coloquei nenhuma anotacao de validacao.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassagemRequestDTO {

    private String passageiro;
    private Integer assento;
    private String origem;
    private String destino;
    private LocalDate data;
    private String status;
}
