package com.rafael.cotacao_hoje.domain.dto;

public record MoedaDTO(
		String code,
		String codein,
		String name,
		String high,
		String low,
		String varBid,
		String pctChange,
		String bid,
		String ask,
		String timestamp,
		String create_date){}
