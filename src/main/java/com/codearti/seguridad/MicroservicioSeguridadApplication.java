package com.codearti.seguridad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroservicioSeguridadApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioSeguridadApplication.class, args);
        System.out.println("\n==> Swagger: http://localhost:8081/swagger-ui/index.html\n");
        System.out.println("\n==> Swagger: http://localhost:8081/h2-console\n");
    }
}