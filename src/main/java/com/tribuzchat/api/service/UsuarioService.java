package com.tribuzchat.api.service;

import com.tribuzchat.api.model.Usuario;
import com.tribuzchat.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

//------------------------------------------------------------------------------------------

    public void criarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }


    public List<Usuario> buscarTodosOsUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario login(String nome, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByNomeAndSenha(nome, senha);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new RuntimeException("Usuário ou senha inválidos");
    }

}
