package com.rafael.cotacao_hoje.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.rafael.cotacao_hoje.domain.dto.CotacaoDTO;
import com.rafael.cotacao_hoje.domain.entity.Cotacao;

import reactor.core.publisher.Flux;

@Service
public class CotacaoExternaService {
	
	@Autowired
	private WebClient webClient;
	
	//private String moedasSuportadas = "USD-BRL,EUR-BRL,BTC-BRL";
	
	// Trocar Flux<CotacaoDTO> por List<Cotacao>
	// Precisa salvar os dados no BD
	public Flux<CotacaoDTO> buscarCotacoesAtuais(String pares) {
		Flux<CotacaoDTO> listaAtual = webClient.get()
				.uri("last/" + pares)
				.retrieve()
				.bodyToFlux(CotacaoDTO.class);
		return listaAtual;
	}
	
	public Flux<CotacaoDTO> buscarHistoricoDiario(String par, int dias) {
		Flux<CotacaoDTO> listaAtual = webClient.get()
				.uri("daily/" + par + "/" + dias)
				.retrieve()
				.bodyToFlux(CotacaoDTO.class);
		return listaAtual;
	}

}
