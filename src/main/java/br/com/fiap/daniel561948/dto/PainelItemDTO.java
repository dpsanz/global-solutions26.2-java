package br.com.fiap.daniel561948.dto;

public record PainelItemDTO(
        String logradouro,
        String tipoCalcada,
        Integer altitude,
        Integer ultimaLamina,
        String ultimoStatus
) {
}