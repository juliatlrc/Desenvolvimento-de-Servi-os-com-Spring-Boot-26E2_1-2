package br.com.juliatallarico.reservapassagens.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Representa uma passagem de onibus.
 *
 * Usei o Lombok pra nao precisar escrever getters, setters, toString e os
 * construtores na mao:
 *  - @Data gera getters/setters, equals, hashCode e toString
 *  - @AllArgsConstructor gera um construtor com todos os atributos
 *  - @NoArgsConstructor gera o construtor vazio
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passagem {

    private Long id;
    private String passageiro;
    private Integer assento;
    private String origem;
    private String destino;
    private LocalDate data;
    private String status;
}
