package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizarTutor;
import br.com.alura.adopet.api.dto.CadastrarTutor;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastrarTutor tutor) {

        try{
            tutorService.cadastrar(tutor);
            return ResponseEntity.ok().build();
        } catch(ValidacaoException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizarTutor tutor) {
        tutorService.atualizar(tutor);
        return ResponseEntity.ok().build();
    }

}
