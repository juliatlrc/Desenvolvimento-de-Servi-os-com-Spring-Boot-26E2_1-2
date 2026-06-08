package br.com.juliatallarico.reservapassagens.services;

import br.com.juliatallarico.reservapassagens.dtos.PassagemRequestDTO;
import br.com.juliatallarico.reservapassagens.dtos.PassagemResponseDTO;
import br.com.juliatallarico.reservapassagens.models.Passagem;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Camada de servico: e aqui que fica toda a regra de negocio.
 * Como o TP pede CRUD em memoria, guardo as passagens numa lista e controlo
 * os ids com um contador, sem banco de dados.
 */
@Service
public class PassagemService {

    private List<Passagem> passagens = new ArrayList<>();
    private Long idCounter = 1L;

    // Construtor: ja deixo 3 passagens cadastradas pra facilitar os testes.
    public PassagemService() {
        passagens.add(new Passagem(idCounter++, "Ana Souza", 1, "Sao Paulo", "Rio de Janeiro",
                LocalDate.of(2026, 6, 10), "CONFIRMADA"));
        passagens.add(new Passagem(idCounter++, "Bruno Lima", 2, "Campinas", "Curitiba",
                LocalDate.of(2026, 6, 11), "CONFIRMADA"));
        passagens.add(new Passagem(idCounter++, "Carla Dias", 3, "Belo Horizonte", "Vitoria",
                LocalDate.of(2026, 6, 12), "PENDENTE"));
    }

    // ---------- metodos auxiliares de conversao ----------

    private Passagem toEntity(PassagemRequestDTO dto) {
        Passagem p = new Passagem();
        p.setPassageiro(dto.getPassageiro());
        p.setAssento(dto.getAssento());
        p.setOrigem(dto.getOrigem());
        p.setDestino(dto.getDestino());
        p.setData(dto.getData());
        p.setStatus(dto.getStatus());
        return p;
    }

    private PassagemResponseDTO toResponse(Passagem p) {
        return new PassagemResponseDTO(p.getId(), p.getPassageiro(), p.getAssento(),
                p.getOrigem(), p.getDestino(), p.getData(), p.getStatus());
    }

    // ---------- operacoes CRUD ----------

    // GET /passagens
    public List<PassagemResponseDTO> listarTodas() {
        return passagens.stream().map(this::toResponse).toList();
    }

    // GET /passagens/{id}
    public PassagemResponseDTO buscarPorId(Long id) {
        Passagem p = encontrarOuFalhar(id);
        return toResponse(p);
    }

    // POST /passagens
    public PassagemResponseDTO criar(PassagemRequestDTO dto) {
        // verifica se o assento ja esta ocupado antes de cadastrar
        boolean assentoOcupado = passagens.stream()
                .anyMatch(p -> p.getAssento().equals(dto.getAssento()));
        if (assentoOcupado) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O assento " + dto.getAssento() + " ja esta ocupado.");
        }

        Passagem nova = toEntity(dto);
        nova.setId(idCounter++);
        passagens.add(nova);
        return toResponse(nova);
    }

    // PUT /passagens/{id}
    public PassagemResponseDTO atualizar(Long id, PassagemRequestDTO dto) {
        Passagem p = encontrarOuFalhar(id);
        p.setPassageiro(dto.getPassageiro());
        p.setAssento(dto.getAssento());
        p.setOrigem(dto.getOrigem());
        p.setDestino(dto.getDestino());
        p.setData(dto.getData());
        p.setStatus(dto.getStatus());
        return toResponse(p);
    }

    // DELETE /passagens/{id}
    public void deletar(Long id) {
        boolean removeu = passagens.removeIf(p -> p.getId().equals(id));
        if (!removeu) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Passagem com id " + id + " nao encontrada.");
        }
    }

    // GET /passagens/busca?destino=
    public List<PassagemResponseDTO> buscarPorDestino(String destino) {
        return passagens.stream()
                .filter(p -> p.getDestino().equalsIgnoreCase(destino))
                .map(this::toResponse)
                .toList();
    }

    // metodo auxiliar: busca pelo id ou lanca 404
    private Passagem encontrarOuFalhar(Long id) {
        return passagens.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Passagem com id " + id + " nao encontrada."));
    }
}
