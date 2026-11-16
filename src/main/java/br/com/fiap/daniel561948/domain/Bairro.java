package br.com.fiap.daniel561948.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "GS129_BAIRRO")
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String nome;
    @Column(nullable = false, length = 10)
    private String zona;
    @Column(nullable = false)
    private Double areaKm2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Double getAreaKm2() {
        return areaKm2;
    }

    public void setAreaKm2(Double areaKm2) {
        this.areaKm2 = areaKm2;
    }
}
