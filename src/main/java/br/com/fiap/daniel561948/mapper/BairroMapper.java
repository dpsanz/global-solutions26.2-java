package br.com.fiap.daniel561948.mapper;

import br.com.fiap.daniel561948.domain.Bairro;
import br.com.fiap.daniel561948.dto.BairroDTO;
import org.springframework.stereotype.Component;

@Component
public class BairroMapper {
    public Bairro toEntity(BairroDTO dto) {
        Bairro bairro = new Bairro();
        bairro.setId(dto.getId());
        bairro.setNome(dto.getNome());
        bairro.setZona(dto.getZona());
        bairro.setAreaKm2(dto.getAreaKm2());
        return bairro;
    }
    public BairroDTO toDTO(Bairro bairro) {
        BairroDTO dto = new BairroDTO();
        dto.setId(bairro.getId());
        dto.setNome(bairro.getNome());
        dto.setZona(bairro.getZona());
        dto.setAreaKm2(bairro.getAreaKm2());
        return dto;
    }
}
