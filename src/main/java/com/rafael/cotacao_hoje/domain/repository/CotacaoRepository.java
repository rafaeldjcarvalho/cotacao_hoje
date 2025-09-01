package com.rafael.cotacao_hoje.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.cotacao_hoje.domain.entity.Cotacao;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
	
	Optional<Cotacao> findByCodigoMoeda(String codigoMoeda);
}
