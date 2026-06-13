# Aplicación de Reseñas de Restaurantes (Backend API)

## Resumen rápido
Backend en **Spring Boot** que gestiona restaurantes, fotos y reseñas. Ofrece API REST, almacenamiento de imágenes (multipart) y autenticación JWT.

## Estado
- Endpoints de restaurantes, subida/descarga de fotos y autenticación (login/register) implementados.
- Reseñas: controlador CRUD disponible en `/api/{restaurantId}/reviews` (GET público; POST/PUT/DELETE requieren token).
- Pendientes: validaciones DTO, manejo global de errores y colección de pruebas.

## Tecnologías y configuración clave
- Java 17+, Spring Boot, Spring Security, Spring Data JPA, PostgreSQL (H2 opcional). Maven.
- Fichero de configuración: `src/main/resources/application.properties` (valores por defecto; se recomienda usar variables de entorno).

Valores por defecto actuales (ajustar por entorno):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/restaurant_review
spring.datasource.username=postgres
spring.servlet.multipart.max-file-size=10MB
jwt.secret=<valor_actual_en_properties; mover a env>
```

Variables de entorno recomendadas:

```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/restaurant_review
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=changeme
APP_JWT_SECRET=changeme_secure_value
```

Usuario de prueba creado automáticamente: `test@demo.com` / `test`.

## Arranque rápido
1. Crear BD (p.ej. `restaurant_review`).
2. Ejecutar:

```bash
mvn clean package
mvn spring-boot:run
```

La API escucha en `http://localhost:8080` por defecto.

## Endpoints principales (resumen)
- Público: `POST /api/auth/login`, `POST /api/auth/register`, `GET /api/restaurants`, `GET /api/restaurants/{id}`.
- Protegidos: `POST/PUT/DELETE /api/restaurants`, `POST /api/images/upload`, `GET /api/images/{filename}`.
- Reseñas: CRUD en `/api/{restaurantId}/reviews` (ver arriba).

## Seguridad y notas importantes
- No dejar `jwt.secret` en el repositorio: mover a variable de entorno o vault.
- Validar tipo y tamaño de archivos subidos.

## Contribuir
- Abrir issues/PRs. Para cambios grandes, usar una rama por feature.


