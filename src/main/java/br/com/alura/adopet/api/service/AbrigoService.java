package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigo;
import br.com.alura.adopet.api.dto.CadastroPet;
import br.com.alura.adopet.api.dto.ListagemAbrigo;
import br.com.alura.adopet.api.dto.ListagemPet;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validation.ValidadorAbrigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private List<ValidadorAbrigo> validadores;

    public List<ListagemAbrigo> listar(){

        return repository.findAll().stream()
                .map(abrigo -> new ListagemAbrigo(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail()))
                .collect(Collectors.toList());
    }

    public void cadastrar(CadastroAbrigo abrigo){
        validadores.forEach(validadorAbrigo -> validadorAbrigo.validar(abrigo));
        repository.save(new Abrigo(abrigo.nome(), abrigo.telefone(), abrigo.email()));
    }

    public List<ListagemPet> listarPets(String idOuNome){
        String regex = "\\d+";
        if(idOuNome.matches(regex)){
            Long id = Long.parseLong(idOuNome);
            return repository.getReferenceById(id).getPets()
                    .stream()
                    .map(pet -> new ListagemPet(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getCor(), pet.getPeso()))
                    .collect(Collectors.toList());
        }else{
            return repository.findByNome(idOuNome).getPets()
                    .stream()
                    .map(pet -> new ListagemPet(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getCor(), pet.getPeso()))
                    .collect(Collectors.toList());
        }
    }

    public void cadastrarPet(String idOuNome, CadastroPet cadastroPet){
        String regex = "\\d+";
        Abrigo abrigo;
        if(idOuNome.matches(regex)){
            Long id = Long.parseLong(idOuNome);
            abrigo = repository.getReferenceById(id);
        } else {
            abrigo = repository.findByNome(idOuNome);
        }
        Pet pet = new Pet(cadastroPet, abrigo);
        abrigo.getPets().add(pet);
        repository.save(abrigo);
    }
}
