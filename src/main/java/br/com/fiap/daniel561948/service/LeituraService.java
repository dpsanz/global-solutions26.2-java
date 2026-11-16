package br.com.fiap.daniel561948.service;

import br.com.fiap.daniel561948.domain.Leitura;
import br.com.fiap.daniel561948.domain.Trecho;
import br.com.fiap.daniel561948.dto.LeituraDTO;
import br.com.fiap.daniel561948.mapper.LeituraMapper;
import br.com.fiap.daniel561948.repository.LeituraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeituraService {
    private static final int LIMIAR_ALERTA_CM = 12;

    private final LeituraRepository repository;
    private final TrechoService trechoService;
    private final LeituraMapper mapper;

    public LeituraService(LeituraRepository repository, TrechoService trechoService,
                          LeituraMapper mapper) {
        this.repository = repository;
        this.trechoService = trechoService;
        this.mapper = mapper;
    }

    public List<LeituraDTO> listar() {
        return repository.findAllByOrderByDataHoraDesc().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public LeituraDTO buscarPorId(Long id) {
        return mapper.toDTO(buscarEntidade(id));
    }

    @Transactional
    public LeituraDTO salvar(LeituraDTO dto) {
        Trecho trecho = trechoService.buscarEntidade(dto.getTrechoId());
        String status = calcularStatus(dto.getLaminaCm());
        Leitura leitura;

        if (dto.getId() == null) {
            leitura = mapper.toEntity(dto, trecho, status);
        } else {
            leitura = buscarEntidade(dto.getId());
            leitura.setTrecho(trecho);
            leitura.setDataHora(dto.getDataHora());
            leitura.setLaminaCm(dto.getLaminaCm());
            leitura.setStatus(status);
        }

        return mapper.toDTO(repository.save(leitura));
    }

    @Transactional
    public void excluir(Long id) {
        repository.delete(buscarEntidade(id));
    }

    public String calcularStatus(int laminaCm) {
        if (laminaCm >= LIMIAR_ALERTA_CM) {
            return "ALERTA";
        }
        if (laminaCm >= LIMIAR_ALERTA_CM / 2) {
            return "ATENCAO";
        }
        return "NORMAL";
    }

    private Leitura buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Leitura não encontrada."));
    }
}