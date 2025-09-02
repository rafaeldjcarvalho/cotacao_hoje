package com.rafael.cotacao_hoje.domain.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.rafael.cotacao_hoje.domain.dto.MoedaDTO;
import com.rafael.cotacao_hoje.domain.dto.mapper.CotacaoMapper;
import com.rafael.cotacao_hoje.domain.entity.Cotacao;
import com.rafael.cotacao_hoje.domain.repository.CotacaoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class CotacaoExternaService {
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private CotacaoMapper cotacaoMapper;
	
	@Autowired
	private CotacaoRepository cotacaoRepository;
	
	//private String moedasSuportadas = "USD-BRL,EUR-BRL,BTC-BRL";
	
	public Flux<Cotacao> buscarCotacoesAtuais(String pares) {
		return webClient.get()
				.uri("last/" + pares)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, MoedaDTO>>() {})
				.flatMapMany(map -> Flux.fromIterable(map.entrySet()))
				.map(entry -> cotacaoMapper.toEntity(entry.getKey(), entry.getValue()))
				.flatMap(this::salvarCotacao);
	}
	
	public Flux<Cotacao> buscarHistoricoDiario(String par, int dias) {
		return this.buscarHistorico(par, dias)
				.map(dto -> cotacaoMapper.fromHistorico(dto, par));
	}
	
	private Flux<MoedaDTO> buscarHistorico(String par, int dias) {
		return webClient.get()
				.uri("daily/" + par + "/" + dias)
				.retrieve()
				.bodyToFlux(MoedaDTO.class);
	}
	
	/* Esse método funciona, mas misturar JPA com WebFlux não é recomendável 
	 * em produção por questões de desempenho e controle de recursos.
	 */
	public Mono<Cotacao> salvarCotacao(Cotacao cotacao) {
		return Mono.fromCallable(() -> cotacaoRepository.save(cotacao))
	               .subscribeOn(Schedulers.boundedElastic()); // Executa fora do thread principal
	}

}
