### Clínica Médica - API REST

Este proyecto es el backend de una aplicación web para la gestión de consultas médicas, pacientes, médicos, servicios y facturaciones en una clínica médica. Forma parte de la 3ra Edición de HackaCode.

**Tecnologías utilizadas**:

+ **Java 17**
+ **Spring Boot 3**
+ **Spring Data JPA**: para persistencia con MySQL
+ **Spring Validation**: validación de datos
+ **Lombok**: reducción de código repetitivo
+ **MapStruct**: mapeo de DTOs
+ **Spring DevTools**: recarga automática en desarrollo
+ **Springdoc OpenAPI (Swagger UI)**: documentación interactiva de la API
+ **MySQL**: base de datos relacional

**Funcionalidades principales**:
+ Gestión de pacientes, médicos, servicios médicos y paquetes de servicios
+ Creación y administración de consultas médicas
+ Aplicación de descuentos en paquetes y para pacientes con seguro médico
+ Cálculo de ganancias diarias y mensuales (BONUS)
+ Endpoints documentados con Swagger UI
+ Arquitectura limpia y buenas prácticas de desarrollo

**Mejoras futuras**:
+ Implementar seguridad con Spring Security y JWT
+ Generación de facturas en PDF
+ Tests unitarios e integración con JUnit y Mockito
