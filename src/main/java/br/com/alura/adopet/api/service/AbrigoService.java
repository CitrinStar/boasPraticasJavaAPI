package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    public List<Abrigo> listar(){
        return repository.findAll();
    }

    public void cadastrar(Abrigo abrigo){
        boolean nomeJaCadastrado = repository.existsByNome(abrigo.getNome());
        boolean telefoneJaCadastrado = repository.existsByTelefone(abrigo.getTelefone());
        boolean emailJaCadastrado = repository.existsByEmail(abrigo.getEmail());

        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        } else {
            repository.save(abrigo);
        }
    }

    public List<Pet> listarPets(String idOuNome){
        String regex = "\\d+";
        if(idOuNome.matches(regex)){
            Long id = Long.parseLong(idOuNome);
            return repository.getReferenceById(id).getPets();
        }else{
            return repository.findByNome(idOuNome).getPets();
        }
    }

    public void cadastrarPet(String idOuNome, Pet pet){
        String regex = "\\d+";
        Abrigo abrigo;
        if(idOuNome.matches(regex)){
            Long id = Long.parseLong(idOuNome);
            abrigo = repository.getReferenceById(id);
        } else {
            abrigo = repository.findByNome(idOuNome);
        }
        pet.setAbrigo(abrigo);
        pet.setAdotado(false);
        abrigo.getPets().add(pet);
        repository.save(abrigo);
    }
}
