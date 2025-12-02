package br.com.pupposoft.poc.cleanarch.pratico.core.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.cleanarch.pratico.core.domain.Usuario;
import br.com.pupposoft.poc.cleanarch.pratico.core.exception.UsuarioComAutomovelAntigoException;
import br.com.pupposoft.poc.cleanarch.pratico.core.exception.UsuarioExistenteException;
import br.com.pupposoft.poc.cleanarch.pratico.core.exception.UsuarioMenorIdadeException;
import br.com.pupposoft.poc.cleanarch.pratico.core.exception.UsuarioSemAutomovelCadastradoException;
import br.com.pupposoft.poc.cleanarch.pratico.core.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CriarUsuarioUsecase {

	private final UsuarioGateway usuarioGateway;

	public Long criar(Usuario usuario) {

		Optional<Usuario> usuarioOp = usuarioGateway.obterPorCpf(usuario.getCpf());
		
//		//TODO: as regras tendem a crescer e por isso deveriam estar fora daqui.
		if(usuarioOp.isPresent()) {
			log.warn("Usuário ja existe com cpf informado. {}", usuario.getCpf());
			throw new UsuarioExistenteException();
		}

		if(usuario.isMenorIdade()) {
			log.warn("Usuário menor de idade. idade={}", usuario.getIdade());
			throw new UsuarioMenorIdadeException();
		}

		if(usuario.semAutomovel()) {
			log.warn("Usuário sem automovel");
			throw new UsuarioSemAutomovelCadastradoException();
		}

		if(usuario.temCarroAntigo()) {
			log.warn("Usuário possui automoveis antigos");
			throw new UsuarioComAutomovelAntigoException();
		}

		return usuarioGateway.criar(usuario);
	}
}
