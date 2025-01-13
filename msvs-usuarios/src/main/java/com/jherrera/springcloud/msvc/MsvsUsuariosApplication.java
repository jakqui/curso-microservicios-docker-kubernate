package com.jherrera.springcloud.msvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients//Habilitar comunicacion entre microservicios
@SpringBootApplication
public class MsvsUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvsUsuariosApplication.class, args);
	}

}
