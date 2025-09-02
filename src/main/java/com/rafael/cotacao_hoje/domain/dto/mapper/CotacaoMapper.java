package com.rafael.cotacao_hoje.domain.dto.mapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.rafael.cotacao_hoje.domain.dto.MoedaDTO;
import com.rafael.cotacao_hoje.domain.entity.Cotacao;

@Component
public class CotacaoMapper {
	
	public Cotacao toEntity(String codigoCompleto, MoedaDTO dto) {
        Cotacao cotacao = new Cotacao();

        cotacao.setCodigoMoeda(codigoCompleto); 

        // Remove espaços e barras para ficar formatado
        cotacao.setNomeFormatado(dto.name());

        // Converte para BigDecimal
        cotacao.setValor(new BigDecimal(dto.bid()));

        // Converte data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        cotacao.setDataHoraConsulta(LocalDateTime.parse(dto.create_date(), formatter));
        
        return cotacao;
    }
	
	public Cotacao fromHistorico(MoedaDTO dto, String codigoMoeda) {
        Cotacao cotacao = new Cotacao();

        // Usa o código passado, pois nem todos os dias têm esse campo
        cotacao.setCodigoMoeda(codigoMoeda);

        // Usa o nome formatado
        cotacao.setNomeFormatado(this.nomeFormatado(codigoMoeda));

        // Converte os valores
        cotacao.setValor(new BigDecimal(dto.bid()));

        // Prioriza create_date, senão converte timestamp
        if (dto.create_date() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            cotacao.setDataHoraConsulta(LocalDateTime.parse(dto.create_date(), formatter));
        } else if (dto.timestamp() != null) {
            long epoch = Long.parseLong(dto.timestamp());
            cotacao.setDataHoraConsulta(LocalDateTime.ofInstant(Instant.ofEpochSecond(epoch), ZoneId.systemDefault()));
        }

        return cotacao;
    }
	
	private String nomeFormatado(String codigoMoeda) {
		switch (codigoMoeda) {
		case "USD-BRL": {
			return "Dólar Americano/Real Brasileiro";
		}
		case "EUR-BRL": {
			return "Euro/Real Brasileiro";
		}
		case "BTC-BRL": {
			return "Bitcoin/Real Brasileiro";
		}
		default:
			throw new IllegalArgumentException("Invalid value: " + codigoMoeda);
		}
	}
}
