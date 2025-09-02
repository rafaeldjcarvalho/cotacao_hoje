package com.rafael.cotacao_hoje.domain.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rafael.cotacao_hoje.domain.service.CotacaoExternaService;

@Component
public class CacheScheduler {
	
	@Autowired
	private CotacaoExternaService cotacaoExternaService;

	@Scheduled(fixedRate = 60000)
	public void atualizarCotacoes() {
		String pares = this.pares();
		this.cotacaoExternaService.buscarCotacoesAtuais(pares)
			.doOnError(e -> new RuntimeException("Erro ao atualizar cotacoes"))
			.subscribe();
	}
	
	private String pares() {
		String[] moedas = this.cotacaoExternaService.getMoedasSuportadas();
		String pares = "";
		for(int i = 0; i < moedas.length; i++) {
			pares += moedas[i];
			if(i < (moedas.length - 1)) {
				pares += ",";
			}
		}
		return pares;
	}
}
