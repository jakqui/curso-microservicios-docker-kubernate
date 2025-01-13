package com.jherrera.springcloud.msvc.cursos.controllers;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jherrera.springcloud.msvc.cursos.models.Usuario;
import com.jherrera.springcloud.msvc.cursos.services.CursoService;

import feign.FeignException;

@RestController
public class CursoUsuarioController {

    @Autowired
    private CursoService service;

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId){
        Optional<Usuario> o;
        try{
            o = service.asignarUsuario(usuario, cursoId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Collections.singletonMap("mensaje", "No existe el usuario por el id o error en la comunicación: "+e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/crear-usuario/{cursoId}")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId){
        Optional<Usuario> o;
        try{
            o = service.crearUsuario(usuario, cursoId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Collections.singletonMap("mensaje", "No se pudo crear el usuario o error en la comunicación: "+e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId){
        Optional<Usuario> o;
        try{
            o = service.eliminarUsuario(usuario, cursoId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Collections.singletonMap("mensaje", "No existe el usuario por el id o error en la comunicación: "+e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-curso-usuario/{id}")
    public ResponseEntity<?> eliminarCursoUsuarioPorId(@PathVariable Long id){
        service.eliminarCursoUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }
    
}
