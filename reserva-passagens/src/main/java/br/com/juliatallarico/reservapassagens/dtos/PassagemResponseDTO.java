package br.com.juliatallarico.reservapassagens.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Dados que a API devolve pro cliente. Aqui o id aparece, porque ja foi
 * gerado pelo service.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassagemResponseDTO {

    private Long id;
    private String passageiro;
    private Integer assento;
    private String origem;
    private String destino;
    private LocalDate data;
    private String status;
}
