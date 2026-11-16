package br.com.fiap.daniel561948.repository;

import br.com.fiap.daniel561948.domain.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {
    List<Leitura> findAllByOrderByDataHoraDesc();

    Optional<Leitura> findFirstByTrechoIdOrderByDataHoraDesc(Long trechoId);

    boolean existsByTrechoId(Long trechoId);

    void deleteByTrechoId(Long trechoId);
}