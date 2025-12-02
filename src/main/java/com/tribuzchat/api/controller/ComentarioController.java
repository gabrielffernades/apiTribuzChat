package com.tribuzchat.api.controller;

import com.tribuzchat.api.model.Comentario;
import com.tribuzchat.api.service.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService){
        this.comentarioService = comentarioService;
    }

//------------------------------------------------------------------------------------------

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarComentario(@RequestBody Comentario comentario){
        comentarioService.criarComentario(comentario);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Comentario> buscarTodosOsComentario(){
        return comentarioService.buscarTodosOsComentario();
    }

}

