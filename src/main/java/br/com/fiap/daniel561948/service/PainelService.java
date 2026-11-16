package br.com.fiap.daniel561948.service;

import br.com.fiap.daniel561948.domain.Leitura;
import br.com.fiap.daniel561948.domain.Trecho;
import br.com.fiap.daniel561948.dto.PainelItemDTO;
import br.com.fiap.daniel561948.repository.LeituraRepository;
import br.com.fiap.daniel561948.repository.TrechoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PainelService {
    private final TrechoRepository trechoRepository;
    private final LeituraRepository leituraRepository;

    public PainelService(TrechoRepository trechoRepository,
                         LeituraRepository leituraRepository) {
        this.trechoRepository = trechoRepository;
        this.leituraRepository = leituraRepository;
    }

    @Transactional(readOnly = true)
    public List<PainelItemDTO> montarPainel(Long bairroId) {
        return trechoRepository.findByBairroIdOrderByLogradouroAsc(bairroId).stream()
                .map(this::montarItem)
                .toList();
    }

    private PainelItemDTO montarItem(Trecho trecho) {
        var ultimaLeitura = leituraRepository
                .findFirstByTrechoIdOrderByDataHoraDesc(trecho.getId());

        return new PainelItemDTO(
                trecho.getLogradouro(),
                trecho.getTipoCalcada(),
                trecho.getAltitude(),
                ultimaLeitura.map(Leitura::getLaminaCm).orElse(null),
                ultimaLeitura.map(Leitura::getStatus).orElse(null)
        );
    }
}