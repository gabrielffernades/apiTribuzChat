package com.tribuzchat.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

    @CreationTimestamp
    @Column(name = "data_cadastroComentario", nullable = false, updatable = false)
    private LocalDateTime data_cadastro;;


    @ManyToMany(mappedBy = "comentarios")
    private List<Usuario> usuarios = new ArrayList<>();

}