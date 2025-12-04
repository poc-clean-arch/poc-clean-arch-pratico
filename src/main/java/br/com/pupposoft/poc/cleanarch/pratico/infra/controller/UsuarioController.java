package br.com.pupposoft.poc.cleanarch.pratico.infra.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.poc.cleanarch.pratico.core.domain.Motorista;
import br.com.pupposoft.poc.cleanarch.pratico.core.usecase.CriarUsuarioUsecase;
import br.com.pupposoft.poc.cleanarch.pratico.infra.controller.json.UsuarioJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private CriarUsuarioUsecase criarUsuarioUsecase;
	
	@PostMapping
	public Long criar(@Valid @RequestBody UsuarioJson usuarioJson) {
		return criarUsuarioUsecase.criar(mapToDomain(usuarioJson));
	}

	private Motorista mapToDomain(UsuarioJson usuarioJson) {
		return new Motorista(
				usuarioJson.getId(),
				usuarioJson.getNome(),
				usuarioJson.getCpf(),
				usuarioJson.getDataNascimento(),
				null//FIXME: implementar automoveis //NOSONAR
				);
	}
}
