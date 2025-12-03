package com.tribuzchat.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "tb_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;


    @CreationTimestamp
    @Column(name = "data_cadastroPost", nullable = false, updatable = false)
    private LocalDateTime data_cadastro;

    @ManyToMany(mappedBy = "posts")
    private List<Usuario> usuarios = new ArrayList<>();

}
