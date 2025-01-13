package com.jherrera.springcloud.msvc.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jherrera.springcloud.msvc.services.UsuarioService;
import com.jherrera.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public Map<String, List<Usuario>> listar(){
        //return service.listar();
        return Collections.singletonMap("users", service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usuario, BindingResult result) {
        //POR CAMPO VALIDAR QUE NO TENGA ERRORES
        if(result.hasErrors()){
            return validar(result);
        }

        if(!usuario.getEmail().isEmpty() && service.existePorEmail(usuario.getEmail())){
            return ResponseEntity.badRequest()
            .body(Collections
                .singletonMap("email", "Ya existe un usuario con ese correo electrónico"));
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @PathVariable Long id, @RequestBody Usuario usuario, BindingResult result) {
        //POR CAMPO VALIDAR QUE NO TENGA ERRORES
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<Usuario> o = service.porId(id);
        if(o.isPresent()){
            Usuario usuarioDb = o.get();

            //VALIDAR QUE SI SE CAMBIA EL EMAIL, EL EMAIL NO EXISTA
            if(!usuario.getEmail().isEmpty() && 
                    !usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail()) && 
                    service.porEmail(usuario.getEmail()).isPresent()){
                return ResponseEntity.badRequest()
                .body(Collections
                    .singletonMap("email", "Ya existe un usuario con ese correo electrónico jojo"));
            }
            
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Usuario> o = service.porId(id);
        if(o.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios-por-curso")
    public ResponseEntity<?> obtenerUsuariosPorCurso(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.listarPorIds(ids));
    }
    
    

    //METHODS
    private ResponseEntity<Map<String, String>> validar(BindingResult result){
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo " + error.getField() + " "+error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
