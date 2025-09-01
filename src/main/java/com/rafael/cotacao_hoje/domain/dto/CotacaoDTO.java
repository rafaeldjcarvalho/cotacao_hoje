package com.rafael.cotacao_hoje.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CotacaoDTO (
		String code,
		String codeIn,
		String name,
		BigDecimal bid,
		LocalDateTime create_date) {}
