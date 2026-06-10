# Aplicación de reviews de restaurantes

Resumen

Aplicación backend en desarrollo para gestionar restaurantes y sus reseñas, construida con Spring Boot. 
Provee API REST para registrar restaurantes, subir fotos y autenticación mediante JWT.

Estado del proyecto

- En desarrollo: endpoints para restaurantes y gestión de fotos están implementados.
- Autenticación (login/register) con JWT disponible.
- Módulo de reseñas (endpoints para crear/editar/eliminar reseñas) está pendiente de implementación.

Tecnologías

- Java 17+, Spring Boot, Spring Security
- JPA / Hibernate
- PostgreSQL para desarrollo; se puede usar H2 para pruebas rápidas

Configuración

- Revisar `src/main/resources/application.properties` para la configuración de la base de datos y tamaño máximo de ficheros.
- Se recomienda configurar un secret JWT seguro en variables de entorno o un vault en lugar de commitearlo en el repositorio.

Usuario de prueba

El proyecto crea un usuario de prueba al iniciar si no hay usuarios:
- email: `test@demo.com`
- password: `test`

Arranque

1. Crear la base de datos PostgreSQL.
2. Construir y ejecutar:
   mvn clean package
   mvn spring-boot:run
3. El servicio se expondrá en `http://localhost:8080` por defecto.

Endpoints principales

Public (no requiere autenticación):
- POST /api/auth/login — Autenticar usuario. Payload: {"email":"...","password":"..."}
  - Respuesta: { "token": "<jwt>", "expiresIn": 86400 }
- POST /api/auth/register — Registrar usuario. Payload: CreateUserRequest (username, firstname, lastname, email, password)
- GET /api/restaurants — Listar restaurantes
- GET /api/restaurants/{id} — Obtener restaurante por id

Protegidos (requieren header `Authorization: Bearer <token>`):
- POST /api/restaurants (consumes multipart/form-data)
  - Campos: `restaurant` (JSON) y `files` (uno o varios archivos)
  - Ejemplo (curl):
    curl -X POST http://localhost:8080/api/restaurants \
      -H "Authorization: Bearer <TOKEN>" \
      -F "restaurant=@restaurant.json;type=application/json" \
      -F "files=@photo1.jpg"

- PUT /api/restaurants/{id} — Actualizar restaurante (JSON)
- DELETE /api/restaurants/{id} — Eliminar restaurante
- POST /api/images/upload — Subir foto (multipart) — devuelve DTO de la foto
- GET /api/images/{filename} — Descargar/mostrar imagen (requiere token)

Notas sobre payloads (DTOs)

- Login: LoginUserRequest { email, password }
- Auth response: AuthResponse { token, expiresIn }
- Crear restaurante: CreateRestaurantRequest (name, cuisineType, contactInformation, address, operatingHours)
- Respuesta restaurante: RestaurantResponse incluye id, name, cuisineType, contactInformation, address, operatingHours, photos, createdBy

Seguridad

- Spring Security con JWT: `JwtAuthenticationFilter` extrae el token del header `Authorization: Bearer ...`.
- `SecurityConfig` permite públicamente:
  - GET `/api/restaurants/**`
  - POST `/api/auth/login` y `/api/auth/register`
  - Resto de rutas requieren autenticación.

Estado y tareas pendientes (para el README y el proyecto)

- Implementar endpoints y servicios para reseñas (Review): actualmente existe la entidad `Review` pero no hay controller ni métodos expuestos.
- Añadir validaciones y manejo de errores más completo y ejemplos de payload/respuesta de error.
- Mover secretos (JWT, DB) a variables de entorno o configuración segura.
- Añadir ejemplos Postman/Insomnia y colecciones para testing manual.

Pruebas y desarrollo

- Ejecutar pruebas: `mvn test`
- Importar el proyecto como Maven en tu IDE
- Para desarrollo rápido sin PostgreSQL: cambiar a H2 en `application.properties` y ajustar `spring.jpa.hibernate.ddl-auto`

Contribuciones

- Abrir issues o pull requests. Para cambios grandes, crear una rama por feature y describir claramente la implementación.

Contacto

Abrir un issue en el repositorio para dudas o reportes.
