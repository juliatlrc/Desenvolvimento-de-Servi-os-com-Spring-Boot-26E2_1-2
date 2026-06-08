package br.com.juliatallarico.reservapassagens.controllers;

import br.com.juliatallarico.reservapassagens.dtos.PassagemRequestDTO;
import br.com.juliatallarico.reservapassagens.dtos.PassagemResponseDTO;
import br.com.juliatallarico.reservapassagens.services.PassagemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Camada de entrada da API. So cuida do HTTP (rotas, status, parametros) e
 * repassa o trabalho pro service. Todas as rotas comecam em /passagens.
 */
@RestController
@RequestMapping("/passagens")
public class PassagemController {

    private final PassagemService service;

    // injecao de dependencia via construtor
    public PassagemController(PassagemService service) {
        this.service = service;
    }

    // GET /passagens -> lista completa
    @GetMapping
    public List<PassagemResponseDTO> listar() {
        return service.listarTodas();
    }

    // GET /passagens/busca?destino=... -> filtra por destino
    // obs: declaro antes do /{id} por organizacao; o Spring ja da preferencia
    // para o caminho literal "busca" sobre a variavel {id}.
    @GetMapping("/busca")
    public List<PassagemResponseDTO> buscarPorDestino(@RequestParam String destino) {
        return service.buscarPorDestino(destino);
    }

    // GET /passagens/{id} -> uma passagem (404 se nao existir)
    @GetMapping("/{id}")
    public PassagemResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // POST /passagens -> cria e retorna 201 Created
    @PostMapping
    public ResponseEntity<PassagemResponseDTO> criar(@RequestBody PassagemRequestDTO dto) {
        PassagemResponseDTO criada = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    // PUT /passagens/{id} -> atualiza (404 se nao existir)
    @PutMapping("/{id}")
    public PassagemResponseDTO atualizar(@PathVariable Long id, @RequestBody PassagemRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    // DELETE /passagens/{id} -> remove e retorna 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
