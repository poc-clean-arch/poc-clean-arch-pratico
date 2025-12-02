package br.com.pupposoft.poc.cleanarch.pratico.infra.gateway.database.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.cleanarch.pratico.core.domain.Automovel;
import br.com.pupposoft.poc.cleanarch.pratico.core.domain.Usuario;
import br.com.pupposoft.poc.cleanarch.pratico.core.exception.ErroAoAcessarRepositorioException;
import br.com.pupposoft.poc.cleanarch.pratico.core.gateway.UsuarioGateway;
import br.com.pupposoft.poc.cleanarch.pratico.infra.gateway.database.jpa.entity.UsuarioEntity;
import br.com.pupposoft.poc.cleanarch.pratico.infra.gateway.database.jpa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioJpaGateway implements UsuarioGateway {

	private final UsuarioRepository usuarioRepository;
	
	@Override
	public Optional<Usuario> obterPorCpf(String cpf) {
		try {
			
			Optional<UsuarioEntity> usuarioEntityOp = usuarioRepository.findByCpf(cpf);
			
			if(usuarioEntityOp.isEmpty()) {
				log.info("Usuário não encontrado: cpf={}", cpf);
				return Optional.empty();
			}
			
			UsuarioEntity usuarioEntity = usuarioEntityOp.get();
			
			Usuario usuario = mapToDomain(usuarioEntity);
			
			return Optional.of(usuario);
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarRepositorioException();
		}
		
	}

	@Override
	public Long criar(Usuario usuario) {
		try {
			
			UsuarioEntity usuarioEntity = mapToEntity(usuario);
			
			return usuarioRepository.save(usuarioEntity).getId();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarRepositorioException();
		}
	}

	private Usuario mapToDomain(UsuarioEntity usuarioEntity) {
		List<Automovel> automoveis = usuarioEntity.getAutomoveis().stream().map(ae -> {
			return new Automovel(
					ae.getId(),
					ae.getModelo(),
					ae.getDataModelo(),
					null);
		}).toList();
		
		return new Usuario(
						usuarioEntity.getId(), 
						usuarioEntity.getCpf(), 
						usuarioEntity.getNome(), 
						usuarioEntity.getDataNascimento(), 
						automoveis);
	}
	
	private UsuarioEntity mapToEntity(Usuario usuario) {
		return UsuarioEntity.builder()
				//FIXME: implementar
				
				.build();
	}
	

}
