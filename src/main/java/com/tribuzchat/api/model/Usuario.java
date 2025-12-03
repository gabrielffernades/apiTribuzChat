package com.tribuzchat.api.model;

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
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String icone; // Ícone Material Icons para o usuário


    @Column(name = "data_nascimento", nullable = false)
    private LocalDate data_nascimento;


    @CreationTimestamp
    @Column(name = "data_cadastroUsuario", nullable = false, updatable = false)
    private LocalDateTime data_cadastroUsuario;


    @ManyToMany
    @JoinTable(
    name = "tb_usuario_grupo",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private List<Grupo> grupos = new ArrayList<>();


    @ManyToMany
    @JoinTable(
    name = "tb_usuario_tribo",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "tribo_id")
    )
    private List<Tribo> tribos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tb_usuario_post",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tb_usuario_comentario",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "comentario_id")
    )
    private List<Comentario> comentarios = new ArrayList<>();


}
