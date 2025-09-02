package com.rafael.cotacao_hoje.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rafael.cotacao_hoje.domain.entity.Cotacao;
import com.rafael.cotacao_hoje.domain.service.CotacaoExternaService;

import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/api/cotacoes")
public class CotacaoController {
	
	@Autowired
	private CotacaoExternaService cotacaoExternaService;
	
	@GetMapping("/agora/{par}")
	public ResponseEntity<Cotacao> buscarCotacaoAgoraPorCodigo(@PathVariable String par) {
		Cotacao Cotacao = this.cotacaoExternaService.buscarCotacaoPorCodigo(par);
		return ResponseEntity.ok(Cotacao);
	}
	
	@GetMapping("/agora/dashboard")
	public ResponseEntity<List<Cotacao>> buscarCotacaoAgora() {
		List<Cotacao> lista = this.cotacaoExternaService.buscarCotacoesRecente();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/historico/{par}")
	public ResponseEntity<Flux<Cotacao>> buscarHistorico(@PathVariable String par, @RequestParam("dias") int dias) {
		Flux<Cotacao> lista = this.cotacaoExternaService.buscarHistoricoDiario(par, dias);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/moedas-suportadas")
	public ResponseEntity<String[]> buscarMoedasSuportadas() {
		String[] moedas = this.cotacaoExternaService.getMoedasSuportadas();
		return ResponseEntity.ok(moedas);
	}
}
