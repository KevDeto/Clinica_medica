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
+ **JWT**: para autenticar usuarios y autorizar el acceso a recursos 
+ **Spring Security**: para la seguridad de la aplicacion
+ **Docker**: para empaquetar la aplicación y sus dependencias en un contenedor facilitando asi el despliegue en diferentes entornos

**Funcionalidades principales**:
+ Gestión de pacientes, médicos, túrnos, citas médicas, servicios médicos y paquetes de servicios
+ Creación y administración de consultas médicas
+ Aplicación de descuentos en paquetes y para pacientes con seguro médico
+ Cálculo de ganancias diarias y mensuales (BONUS)
+ Endpoints documentados con Swagger UI
+ Arquitectura limpia y buenas prácticas de desarrollo

**Mejoras futuras**:
+ Generación de facturas en PDF
+ Tests unitarios e integración con JUnit y Mockito

### **Guia de configuración del Proyecto para Docker**:

Para que el proyecto funcione correctamente, es necesario crear un archivo `.env` en la raíz del proyecto con las siguientes variables de entorno

**Variables para MySQL**:

Estas variables configuran la conexión a la base de datos MySQL.

```env
MYSQL_DATABASE=nombre_de_la_base_de_datos
MYSQL_USER=usuario_de_mysql
MYSQL_PASSWORD=contraseña_de_mysql
MYSQL_ROOT_PASSWORD=contraseña_root_de_mysql
```
**Variables para Spring Boot**:

Estas variables son utilizadas por Spring Boot para conectarse a la base de datos.

```env
SPRING_DATASOURCE_USERNAME=usuario_de_mysql
SPRING_DATASOURCE_PASSWORD=contraseña_de_mysql
SPRING_DATASOURCE_URL=jdbc:mysql://nombre_de_imagen_mysql_en_docker:3306/nombre_de_la_base_de_datos?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
```
**Ejecución del Proyecto con Docker**:

- Clonar el repositorio

  `git clone URL_DEL_REPOSITORIO`

- Navega al directorio del proyecto

  `cd nombre_del_repositorio`

- Construye y ejecuta los contenedores con Docker Compose

  `docker-compose up --build`

### **Prueba de la API REST**

- Realiza una solicitud POST a la siguiente URL para crear un usuario

  `http://localhost:8080/api/v1/user`

- **Cuerpo de la solicitud (JSON):**
```json
{
   "email": "cualquier_cosa@gmail.com",
   "username": "nombre_de_usuario",
   "password": "contraseña",
   "roles": ["USER", "ADMIN"]
}
```
- Realiza una solicitud POST a la siguiente URL para iniciar sesión y obtener un token JWT

  `http://localhost:8080/login`

- **Cuerpo de la solicitud (JSON)**:
```json
  {
    "username": "nombre_de_usuario",
    "password": "contraseña"
  }
```
- Usar el token JWT

El endpoint de inicio de sesión devolverá un token JWT en la respuesta. Usa este token en el encabezado Authorization de las solicitudes posteriores para acceder a los endpoints protegidos:

Authorization: Bearer <token_jwt>

**Nota final**:

La clave secreta JWT (JWT_SECRET_KEY) es fundamental para la seguridad de la aplicación. Debe cumplir con los siguientes requisitos:

- Longitud: La clave debe tener al menos 256 bits (32 caracteres en Base64).

- Formato: La clave debe estar en formato Base64.

Puedes generar una clave en el siguiente sitio: [https://generate-random.org/encryption-key-generator](https://generate-random.org/encryption-key-generator)

