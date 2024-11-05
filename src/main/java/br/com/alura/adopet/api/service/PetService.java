package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.ListagemPet;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    public List<ListagemPet> listarPetsDisponiveis(){
        try{
            return repository.findByAdotadoFalse()
                    .stream().map(pet -> new ListagemPet(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getCor(), pet.getPeso()))
                    .collect(Collectors.toList());
        } catch (NullPointerException exception) {
            throw new ValidacaoException(exception.getMessage());
        }
    }
}
