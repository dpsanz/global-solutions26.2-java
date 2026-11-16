package br.com.fiap.daniel561948.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "GS129_TRECHO")
public class Trecho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String logradouro;

    @Column(nullable = false, length = 20)
    private String tipoCalcada;

    @Column(nullable = false)
    private Integer altitude;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bairro_id", nullable = false)
    private Bairro bairro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTipoCalcada() {
        return tipoCalcada;
    }

    public void setTipoCalcada(String tipoCalcada) {
        this.tipoCalcada = tipoCalcada;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
}