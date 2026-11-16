package br.com.fiap.daniel561948.repository;

import br.com.fiap.daniel561948.domain.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findAllByOrderByNomeAsc();
}
