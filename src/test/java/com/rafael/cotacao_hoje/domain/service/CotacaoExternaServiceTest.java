package com.rafael.cotacao_hoje.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.rafael.cotacao_hoje.domain.dto.mapper.CotacaoMapper;
import com.rafael.cotacao_hoje.domain.entity.Cotacao;
import com.rafael.cotacao_hoje.domain.repository.CotacaoRepository;

public class CotacaoExternaServiceTest {
	
	@Mock
	private WebClient webClient;
	
	@Mock
	private CotacaoMapper cotacaoMapper;
	
	@Mock
	private CotacaoRepository cotacaoRepository;
	
	@Autowired
	@InjectMocks
	private CotacaoExternaService service;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("Deve receber uma cotacao com sucesso do BD")
	public void buscarCotacaoPorCodigoCase1() {
		String par = "USD-BRL";
		when(cotacaoRepository.findByCodigoMoeda(anyString())).thenReturn(Optional.of(new Cotacao()));
		
		Cotacao result = this.service.buscarCotacaoPorCodigo(par);
		
		verify(cotacaoRepository, times(1)).findByCodigoMoeda(par);
		assertThat(result).isNotNull();
	}
	
	@Test
	@DisplayName("Não deve receber uma cotacao, quando o par não exite")
	public void buscarCotacaoPorCodigoCase2() {
		Exception thrown = assertThrows(RuntimeException.class, () -> {
			this.service.buscarCotacaoPorCodigo(anyString());
		});
		
		assertThat(thrown.getMessage()).isEqualTo("Price not found");
	}
	
	@Test
	@DisplayName("Deve receber a lista com sucesso do BD")
	public void buscarCotacoesRecenteCase1() {
		when(cotacaoRepository.findByCodigoMoeda(anyString())).thenReturn(Optional.of(new Cotacao()));
		
		List<Cotacao> result = this.service.buscarCotacoesRecente();
		
		verify(cotacaoRepository, times(6)).findByCodigoMoeda(anyString());
		assertThat(result.isEmpty()).isFalse();
		assertThat(result.size()).isEqualTo(3);
	}
	
	@Test
	@DisplayName("Deve lançar uma RuntimeException, quando a lista está vazia")
	public void buscarCotacoesRecenteCase2() {
		when(cotacaoRepository.findByCodigoMoeda(anyString())).thenReturn(Optional.empty());
		
		Exception thrown = assertThrows(RuntimeException.class, () -> {
			this.service.buscarCotacoesRecente();
		});
		
		verify(cotacaoRepository, times(3)).findByCodigoMoeda(anyString());
		assertThat(thrown.getMessage()).isEqualTo("Price no exist");
	}
}
