package com.tribuzchat.api.service;

import com.tribuzchat.api.model.Comentario;
import com.tribuzchat.api.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository){
        this.comentarioRepository = comentarioRepository;
    }

//------------------------------------------------------------------------------------------

    public void criarComentario(Comentario comentario){
        comentarioRepository.save(comentario);
    }


    public List<Comentario> buscarTodosOsComentario(){
        return comentarioRepository.findAll();
    }



}
