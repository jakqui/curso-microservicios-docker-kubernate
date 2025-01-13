package com.jherrera.springcloud.msvc.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

//CONEXION A LOCAL
//@FeignClient(name = "msvc-cursos", /*url="localhost:8002"*/ url="host.docker.internal:8002")
//CONEXION ENTRE MICROSERVICIOS EN CONTENEDORES
//@FeignClient(name = "msvc-cursos", /*url="localhost:8002"*/ url="msvc-cursos:8002")
//APLICATION PROPERTIES
@FeignClient(name = "msvc-cursos", url="${msvc.cursos.url}")
public interface CursoClienteRest {

    @DeleteMapping("/eliminar-curso-usuario/{id}")
    void eliminarCursoUsuarioPorId(@PathVariable Long id);
}
