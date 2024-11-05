package br.com.pupposoft.poc.cleanarch.pratico.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Automovel {
	private Long id;
	private String modelo;
	private LocalDate dataModelo;
	private Usuario usuario;
	
	public boolean isAntigo() {
		return dataModelo.isBefore(LocalDate.now().minusYears(3));
	}
}
