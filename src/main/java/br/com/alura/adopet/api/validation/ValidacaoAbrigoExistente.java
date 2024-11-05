package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.CadastroAbrigo;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoAbrigoExistente implements ValidadorAbrigo{

    @Autowired
    private AbrigoRepository repository;
    @Override
    public void validar(CadastroAbrigo abrigo) {
        boolean nomeJaCadastrado = repository.existsByNome(abrigo.nome());
        boolean telefoneJaCadastrado = repository.existsByTelefone(abrigo.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(abrigo.email());

        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }
    }
}
