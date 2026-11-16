package br.com.fiap.daniel561948.mapper;

import br.com.fiap.daniel561948.domain.Leitura;
import br.com.fiap.daniel561948.domain.Trecho;
import br.com.fiap.daniel561948.dto.LeituraDTO;
import org.springframework.stereotype.Component;

@Component
public class LeituraMapper {
    public Leitura toEntity(LeituraDTO dto, Trecho trecho, String status) {
        Leitura leitura = new Leitura();
        leitura.setId(dto.getId());
        leitura.setTrecho(trecho);
        leitura.setDataHora(dto.getDataHora());
        leitura.setLaminaCm(dto.getLaminaCm());
        leitura.setStatus(status);
        return leitura;
    }

    public LeituraDTO toDTO(Leitura leitura) {
        Trecho trecho = leitura.getTrecho();

        LeituraDTO dto = new LeituraDTO();
        dto.setId(leitura.getId());
        dto.setTrechoId(trecho.getId());
        dto.setDataHora(leitura.getDataHora());
        dto.setLaminaCm(leitura.getLaminaCm());
        dto.setStatus(leitura.getStatus());
        dto.setTrechoDescricao(trecho.getLogradouro() + " (" + trecho.getBairro().getNome() + ")");
        return dto;
    }
}