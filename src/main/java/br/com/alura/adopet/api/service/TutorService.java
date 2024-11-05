package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizarTutor;
import br.com.alura.adopet.api.dto.CadastrarTutor;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validation.ValidadorTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private List<ValidadorTutor> validadores;

    public void cadastrar(CadastrarTutor tutor){
        validadores.forEach(validadorTutor -> validadorTutor.validar(tutor));
        repository.save(new Tutor(tutor));
    }

    public void atualizar(AtualizarTutor tutor){
        repository.save(new Tutor(tutor));
    }
}
