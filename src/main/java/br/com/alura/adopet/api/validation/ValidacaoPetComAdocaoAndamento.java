package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetComAdocaoAndamento implements ValidadorSolcitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        boolean petPossuiAdocaoAndamento = adocaoRepository.existisByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if (petPossuiAdocaoAndamento) {
            throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
