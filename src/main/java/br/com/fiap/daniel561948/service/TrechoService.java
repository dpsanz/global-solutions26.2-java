package br.com.fiap.daniel561948.service;

import br.com.fiap.daniel561948.domain.Bairro;
import br.com.fiap.daniel561948.domain.Trecho;
import br.com.fiap.daniel561948.dto.TrechoDTO;
import br.com.fiap.daniel561948.mapper.TrechoMapper;
import br.com.fiap.daniel561948.repository.LeituraRepository;
import br.com.fiap.daniel561948.repository.TrechoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrechoService {
    private final TrechoRepository repository;
    private final LeituraRepository leituraRepository;
    private final BairroService bairroService;
    private final TrechoMapper mapper;

    public TrechoService(TrechoRepository repository, LeituraRepository leituraRepository,
                         BairroService bairroService, TrechoMapper mapper) {
        this.repository = repository;
        this.leituraRepository = leituraRepository;
        this.bairroService = bairroService;
        this.mapper = mapper;
    }

    public List<TrechoDTO> listar() {
        return repository.findAllByOrderByAltitudeAscLogradouroAsc().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public TrechoDTO buscarPorId(Long id) {
        return mapper.toDTO(buscarEntidade(id));
    }

    public Trecho buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trecho não encontrado."));
    }

    @Transactional
    public TrechoDTO salvar(TrechoDTO dto) {
        Bairro bairro = bairroService.buscarEntidade(dto.getBairroId());
        Trecho trecho;

        if (dto.getId() == null) {
            trecho = mapper.toEntity(dto, bairro);
        } else {
            trecho = buscarEntidade(dto.getId());
            trecho.setLogradouro(dto.getLogradouro());
            trecho.setTipoCalcada(dto.getTipoCalcada());
            trecho.setAltitude(dto.getAltitude());
            trecho.setBairro(bairro);
        }

        return mapper.toDTO(repository.save(trecho));
    }

    @Transactional
    public void excluir(Long id) {
        Trecho trecho = buscarEntidade(id);
        leituraRepository.deleteByTrechoId(id);
        leituraRepository.flush();
        repository.delete(trecho);
    }
}