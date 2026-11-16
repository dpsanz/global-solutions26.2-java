package br.com.fiap.daniel561948.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class LeituraDTO {
    private Long id;

    @NotNull
    private Long trechoId;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataHora;

    @NotNull
    @Min(0)
    @Max(300)
    private Integer laminaCm;

    private String status;
    private String trechoDescricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrechoId() {
        return trechoId;
    }

    public void setTrechoId(Long trechoId) {
        this.trechoId = trechoId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getLaminaCm() {
        return laminaCm;
    }

    public void setLaminaCm(Integer laminaCm) {
        this.laminaCm = laminaCm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrechoDescricao() {
        return trechoDescricao;
    }

    public void setTrechoDescricao(String trechoDescricao) {
        this.trechoDescricao = trechoDescricao;
    }
}