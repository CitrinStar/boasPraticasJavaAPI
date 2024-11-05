package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.CadastroPet;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPet tipo;
    private String nome;
    private String raca;
    private Integer idade;
    private String cor;
    private Float peso;
    private Boolean adotado;
    @ManyToOne
    private Abrigo abrigo;
    @OneToOne(mappedBy = "pet")
    private Adocao adocao;

    public Pet(CadastroPet cadastroPet, Abrigo abrigo) {
        this.tipo = cadastroPet.tipo();
        this.nome = cadastroPet.nome();
        this.raca = cadastroPet.raca();
        this.idade = cadastroPet.idade();
        this.cor = cadastroPet.cor();
        this.peso = cadastroPet.peso();
        this.abrigo = abrigo;
        this.adotado = false;
    }

    public Pet() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPet getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }
    public Integer getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    public Float getPeso() {
        return peso;
    }

    public Boolean getAdotado() {
        return adotado;
    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public Adocao getAdocao() {
        return adocao;
    }
}
