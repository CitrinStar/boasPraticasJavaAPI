package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastroAbrigo;
import br.com.alura.adopet.api.dto.CadastroPet;
import br.com.alura.adopet.api.dto.ListagemAbrigo;
import br.com.alura.adopet.api.dto.ListagemPet;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<ListagemAbrigo>> listar() {
        return ResponseEntity.ok(abrigoService.listar());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroAbrigo abrigo) {
        try{
            abrigoService.cadastrar(abrigo);
            return ResponseEntity.ok().build();
        } catch (ValidacaoException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<ListagemPet>> listarPets(@PathVariable String idOuNome) {
        try {
            return ResponseEntity.ok(abrigoService.listarPets(idOuNome));
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastroPet pet) {
        try {
            abrigoService.cadastrarPet(idOuNome, pet);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        }
    }

}
