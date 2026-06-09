# Microservicio de Seguridad (SOA)

API REST de autenticación construida con Spring Boot. Gestiona el registro e inicio de sesión de usuarios mediante JWT (JSON Web Tokens), con contraseñas cifradas y rutas protegidas. Forma parte de una arquitectura de microservicios (SOA).

## Tecnologías

- Java 17
- Spring Boot 3
- Spring Security
- JWT (jjwt)
- Spring Data JPA
- H2 Database
- Swagger / OpenAPI
- Maven

## Funcionalidades

- Registro de usuarios con contraseña cifrada (BCrypt)
- Inicio de sesión con generación de token JWT
- Validación de token en cada petición mediante un filtro
- Protección de rutas con Spring Security
- Documentación interactiva con Swagger

## Cómo ejecutar

1. Clonar el repositorio:
```
   git clone https://github.com/i2414684-alt/MICROSERVICIOS-SEGURIDAD_SOA.git
```
2. Abrir el proyecto en IntelliJ IDEA (detecta Maven automáticamente).
3. Activar **Enable annotation processing** (Settings → Build, Execution, Deployment → Compiler → Annotation Processors) para Lombok.
4. Ejecutar la clase `MicroservicioSeguridadApplication`.

La aplicación corre en el puerto **8081**.

## Endpoints

| Método | Ruta                     | Descripción                          | Protegido |
|--------|--------------------------|--------------------------------------|-----------|
| POST   | `/api/auth/registro`     | Crear cuenta y obtener token         | No        |
| POST   | `/api/auth/login`        | Iniciar sesión y obtener token       | No        |
| GET    | `/api/usuarios/perfil`   | Recurso protegido de prueba          | Sí        |

## URLs útiles

- Documentación Swagger: `http://localhost:8081/swagger-ui/index.html`
- Consola H2: `http://localhost:8081/h2-console`
- Definición OpenAPI (JSON): `http://localhost:8081/v3/api-docs`

## Estructura del proyecto

```
com.codearti.seguridad
├── entidad         # Entidades JPA (Usuario, Rol)
├── repositorio     # Acceso a datos
├── dto             # Objetos de transferencia
├── seguridad       # JwtServicio y filtro JWT
├── servicio        # Lógica de negocio y autenticación
├── configuracion   # Configuración de seguridad y Swagger
├── controlador     # Endpoints REST
└── excepcion       # Manejo de errores
```