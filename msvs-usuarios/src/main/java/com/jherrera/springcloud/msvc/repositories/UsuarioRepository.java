package com.jherrera.springcloud.msvc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jherrera.springcloud.msvc.usuarios.models.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);

    //USANDO QUERY PERSONALIZADO
    @Query("select u from Usuario u where u.email = ?1")
    Optional<Usuario> porEmail(String email);

    //TRUE|FALSE
    boolean existsByEmail(String email);
}
