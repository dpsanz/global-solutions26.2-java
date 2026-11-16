package br.com.fiap.daniel561948.mapper;

import br.com.fiap.daniel561948.domain.Bairro;
import br.com.fiap.daniel561948.domain.Trecho;
import br.com.fiap.daniel561948.dto.TrechoDTO;
import org.springframework.stereotype.Component;

@Component
public class TrechoMapper {
    public Trecho toEntity(TrechoDTO dto, Bairro bairro) {
        Trecho trecho = new Trecho();
        trecho.setId(dto.getId());
        trecho.setLogradouro(dto.getLogradouro());
        trecho.setTipoCalcada(dto.getTipoCalcada());
        trecho.setAltitude(dto.getAltitude());
        trecho.setBairro(bairro);
        return trecho;
    }

    public TrechoDTO toDTO(Trecho trecho) {
        TrechoDTO dto = new TrechoDTO();
        dto.setId(trecho.getId());
        dto.setLogradouro(trecho.getLogradouro());
        dto.setTipoCalcada(trecho.getTipoCalcada());
        dto.setAltitude(trecho.getAltitude());
        dto.setBairroId(trecho.getBairro().getId());
        dto.setBairroNome(trecho.getBairro().getNome());
        return dto;
    }

}