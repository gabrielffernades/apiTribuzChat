package com.tribuzchat.api.controller;

import com.tribuzchat.api.model.Usuario;
import com.tribuzchat.api.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuariosService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuariosService = usuarioService;
    }

//------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario){
        try {
            usuariosService.criarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            error.put("error", e.getClass().getSimpleName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> buscarTodosOsUsuarios(){
        return usuariosService.buscarTodosOsUsuarios();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String nome = loginRequest.get("nome");
            String senha = loginRequest.get("senha");
            
            Usuario usuario = usuariosService.login(nome, senha);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<?> redefinirSenha(@RequestBody Map<String, String> request) {
        try {
            String cpf = request.get("cpf");
            String novaSenha = request.get("novaSenha");
            
            if (cpf == null || cpf.trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "CPF é obrigatório");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            
            if (novaSenha == null || novaSenha.trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Nova senha é obrigatória");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            
            usuariosService.redefinirSenha(cpf, novaSenha);
            
            Map<String, String> success = new HashMap<>();
            success.put("message", "Senha redefinida com sucesso");
            return ResponseEntity.ok(success);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Erro ao redefinir senha: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

}
