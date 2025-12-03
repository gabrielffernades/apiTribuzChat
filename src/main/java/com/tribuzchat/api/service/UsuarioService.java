package com.tribuzchat.api.service;

import com.tribuzchat.api.model.Usuario;
import com.tribuzchat.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

//------------------------------------------------------------------------------------------

    // Lista de ícones disponíveis para usuários
    private static final List<String> ICONES_DISPONIVEIS = Arrays.asList(
        "person", "face", "account_circle", "sentiment_satisfied", "mood",
        "person_outline", "face_3", "face_4", "face_5", "face_6",
        "tag_faces", "waving_hand", "self_improvement", "sports_esports",
        "music_note", "palette", "code", "fitness_center", "book",
        "camera_alt", "restaurant", "flight", "school", "business",
        "science", "psychology", "favorite", "star", "celebration"
    );

    private final Random random = new Random();

    public void criarUsuario(Usuario usuario){
        // Se o usuário não tiver ícone, atribuir um aleatório
        if (usuario.getIcone() == null || usuario.getIcone().trim().isEmpty()) {
            String iconeAleatorio = ICONES_DISPONIVEIS.get(random.nextInt(ICONES_DISPONIVEIS.size()));
            usuario.setIcone(iconeAleatorio);
        }
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

    public void redefinirSenha(String cpf, String novaSenha) {
        Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
        if (usuario.isEmpty()) {
            throw new RuntimeException("CPF não encontrado");
        }
        Usuario usuarioEncontrado = usuario.get();
        usuarioEncontrado.setSenha(novaSenha);
        usuarioRepository.save(usuarioEncontrado);
    }

}
