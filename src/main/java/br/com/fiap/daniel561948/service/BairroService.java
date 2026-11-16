package br.com.fiap.daniel561948.service;

import br.com.fiap.daniel561948.domain.Bairro;
import br.com.fiap.daniel561948.dto.BairroDTO;
import br.com.fiap.daniel561948.mapper.BairroMapper;
import br.com.fiap.daniel561948.repository.BairroRepository;
import br.com.fiap.daniel561948.repository.TrechoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BairroService {
    private final BairroRepository repository;
    private final BairroMapper mapper;
    private final TrechoRepository trechoRepository;

    public BairroService(BairroRepository repository, BairroMapper mapper,
                         TrechoRepository trechoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.trechoRepository = trechoRepository;
    }

    public List<BairroDTO> listar() {
        return repository.findAllByOrderByNomeAsc().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public BairroDTO buscarPorId(Long id) {
        return mapper.toDTO(buscarEntidade(id));
    }

    public Bairro buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bairro não encontrado."));
    }

    @Transactional
    public BairroDTO salvar(BairroDTO dto) {
        Bairro bairro;

        if (dto.getId() == null) {
            bairro = mapper.toEntity(dto);
        } else {
            bairro = buscarEntidade(dto.getId());
            bairro.setNome(dto.getNome());
            bairro.setZona(dto.getZona());
            bairro.setAreaKm2(dto.getAreaKm2());
        }

        return mapper.toDTO(repository.save(bairro));
    }

    @Transactional
    public void excluir(Long id) {
        Bairro bairro = buscarEntidade(id);

        if (trechoRepository.existsByBairroId(id)) {
            throw new IllegalStateException("O bairro possui trechos cadastrados.");
        }

        repository.delete(bairro);
    }
}