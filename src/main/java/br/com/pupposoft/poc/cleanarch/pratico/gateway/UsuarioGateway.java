package br.com.pupposoft.poc.cleanarch.pratico.gateway;

import java.util.Optional;

import br.com.pupposoft.poc.cleanarch.pratico.domain.Usuario;

public interface UsuarioGateway {

	Optional<Usuario> obterPorCpf(String cpf);

	Long criar(Usuario usuario);

}
