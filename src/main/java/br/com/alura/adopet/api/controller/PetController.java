package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.ListagemPet;
import br.com.alura.adopet.api.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<ListagemPet>> listarTodosDisponiveis() {
        var pets = petService.listarPetsDisponiveis();
        return ResponseEntity.ok(pets);
    }
}
