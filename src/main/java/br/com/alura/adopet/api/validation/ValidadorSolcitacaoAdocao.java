package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;

public interface ValidadorSolcitacaoAdocao {

    void validar(SolicitacaoAdocaoDto dto);
}
