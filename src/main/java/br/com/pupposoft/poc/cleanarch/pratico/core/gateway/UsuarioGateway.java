package br.com.pupposoft.poc.cleanarch.pratico.core.gateway;

import java.util.Optional;

import br.com.pupposoft.poc.cleanarch.pratico.core.domain.Usuario;

public interface UsuarioGateway {

	Optional<Usuario> obterPorCpf(String cpf);

	Long criar(Usuario usuario);

}
