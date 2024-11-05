package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.CadastrarTutor;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoTutorExistente implements ValidadorTutor{

    @Autowired
    private TutorRepository repository;

    @Override
    public void validar(CadastrarTutor tutor) {
        boolean telefoneJaCadastrado = repository.existsByTelefone(tutor.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(tutor.email());

        if (telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }
    }
}
