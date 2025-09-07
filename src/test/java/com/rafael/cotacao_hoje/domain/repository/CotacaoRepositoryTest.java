package com.rafael.cotacao_hoje.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.rafael.cotacao_hoje.domain.entity.Cotacao;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class CotacaoRepositoryTest {
	
	@Autowired
	private CotacaoRepository repository;
	
	@Autowired
	private EntityManager entityManager;
	
	private Cotacao createCotacao(Cotacao dados) {
		Cotacao newCotacao = new Cotacao(dados.getId(), dados.getCodigoMoeda(), dados.getNomeFormatado(), dados.getValor(), dados.getDataHoraConsulta());
		this.entityManager.persist(newCotacao);
		return newCotacao;
	}
	
	@Test
	@DisplayName("Deve receber a cotacao com sucesso do BD")
	public void findByCodigoMoedaCase1() {
		String codigoMoeda = "USD-BRL";
		Cotacao data = new Cotacao(null, codigoMoeda, "Dolar/Real", new BigDecimal(5.4), LocalDateTime.now());
		this.createCotacao(data);
		
		Optional<Cotacao> result = this.repository.findByCodigoMoeda(codigoMoeda);
		
		assertThat(result.isPresent()).isTrue();
	}
	
	@Test
	@DisplayName("Nao deve receber a cotacao do BD, quando a cotacao nao existe")
	public void findByCodigoMoedaCase2() {
		String codigoMoeda = "USD-BRL";
		
		Optional<Cotacao> result = this.repository.findByCodigoMoeda(codigoMoeda);
		
		assertThat(result.isEmpty()).isTrue();
	}
}
