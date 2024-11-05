package br.com.pupposoft.poc.cleanarch.pratico.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.poc.cleanarch.pratico.controller.json.UsuarioJson;
import br.com.pupposoft.poc.cleanarch.pratico.domain.Usuario;
import br.com.pupposoft.poc.cleanarch.pratico.usecase.CriarUsuarioUsecase;
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

	private Usuario mapToDomain(UsuarioJson usuarioJson) {
		return new Usuario(
				usuarioJson.getId(),
				usuarioJson.getNome(),
				usuarioJson.getCpf(),
				usuarioJson.getDataNascimento(),
				null//FIXME: implementar automoveis //NOSONAR
				);
	}
}
