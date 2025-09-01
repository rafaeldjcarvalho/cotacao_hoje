package com.rafael.cotacao_hoje.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cotacoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cotacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 10)
	private String codigoMoeda; //USD-BRL
	
	@Column(nullable = false, length = 100)
	private String nomeFormatado; //DolarAmericano/RealBrasileiro
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	private LocalDateTime dataHoraConsulta; 
}
