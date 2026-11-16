package br.com.fiap.daniel561948.dto;

import jakarta.validation.constraints.*;

public class TrechoDTO {
    private Long id;

    @NotBlank
    @Size(max = 120)
    private String logradouro;

    @NotBlank
    @Size(max = 20)
    private String tipoCalcada;

    @NotNull
    @Min(0)
    @Max(2000)
    private Integer altitude;

    @NotNull
    private Long bairroId;

    private String bairroNome;

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

    public Long getBairroId() {
        return bairroId;
    }

    public void setBairroId(Long bairroId) {
        this.bairroId = bairroId;
    }

    public String getBairroNome() {
        return bairroNome;
    }

    public void setBairroNome(String bairroNome) {
        this.bairroNome = bairroNome;
    }
}