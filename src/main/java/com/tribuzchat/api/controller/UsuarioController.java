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
    @ResponseStatus(HttpStatus.CREATED)
    public void criarUsuario(@RequestBody Usuario usuario){
        usuariosService.criarUsuario(usuario);
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

}
