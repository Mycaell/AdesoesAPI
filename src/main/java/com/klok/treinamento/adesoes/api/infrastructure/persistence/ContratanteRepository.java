package com.klok.treinamento.adesoes.api.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klok.treinamento.adesoes.api.domain.model.Contratante;

@Repository
public interface ContratanteRepository extends JpaRepository<Contratante, Long>{

}
