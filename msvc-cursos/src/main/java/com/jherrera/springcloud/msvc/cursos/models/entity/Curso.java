package com.jherrera.springcloud.msvc.cursos.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.jherrera.springcloud.msvc.cursos.models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotEmpty
    private String nombre;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "curso_id")
    private List<CursoUsuario> cursoUsuarios;

	@Transient//ESTO INDICA QUE ESTE DATO ES SOLO UN CAMPO Y NO TIENE NADA QUE VER CON LAS TABLAS
	private List<Usuario> usuarios;


	public Curso(){
		cursoUsuarios = new ArrayList<>();
		usuarios = new ArrayList<>();
	}

	public void addCursoUsuario(CursoUsuario cursoUsuario){
		cursoUsuarios.add(cursoUsuario);
	}

	public void removeCursoUsuario(CursoUsuario cursoUsuario){
		cursoUsuarios.remove(cursoUsuario);
	}

    @Override
	public String toString() {
		try {
	        return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
	    } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
	        e.printStackTrace();
	    }
	    return null;
    }
    
}
