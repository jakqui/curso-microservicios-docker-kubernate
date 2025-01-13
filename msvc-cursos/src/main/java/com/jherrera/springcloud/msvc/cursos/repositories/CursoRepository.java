package com.jherrera.springcloud.msvc.cursos.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jherrera.springcloud.msvc.cursos.models.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long>{

    @Modifying//delete insert update lo llevan por default
    @Query("delete from CursoUsuario cu where cu.usuarioId=?1")
    void eliminarCursoUsuario(Long id);

}
