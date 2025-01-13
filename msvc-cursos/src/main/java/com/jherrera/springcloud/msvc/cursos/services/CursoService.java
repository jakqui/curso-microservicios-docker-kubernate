package com.jherrera.springcloud.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import com.jherrera.springcloud.msvc.cursos.models.Usuario;
import com.jherrera.springcloud.msvc.cursos.models.entity.Curso;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);
    Optional<Curso> porIdConUsuarios(Long id);

    void eliminarCursoUsuarioPorId(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);
}
