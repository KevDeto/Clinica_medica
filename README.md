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

### **Configuración del Proyecto para Docker**:

Para que el proyecto funcione correctamente, es necesario crear un archivo `.env` en la raíz del proyecto con las siguientes variables de entorno

**Variables para MySQL**:
```env
MYSQL_DATABASE=nombre_de_la_base_de_datos
MYSQL_USER=usuario_de_mysql
MYSQL_PASSWORD=contraseña_de_mysql
MYSQL_ROOT_PASSWORD=contraseña_root_de_mysql
```
**Variables para Spring Boot**:
```env
SPRING_DATASOURCE_USERNAME=usuario_de_mysql
SPRING_DATASOURCE_PASSWORD=contraseña_de_mysql
SPRING_DATASOURCE_URL=jdbc:mysql://nombre_de_imagen_mysql_en_docker:3306/nombre_de_la_base_de_datos?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
```
**Como probar la API REST**:

- Clonar el repositorio:

  `git clone URL_DEL_REPOSITORIO`

- Ir a la siguiente URL y crear una "cuenta" con POST:

  `http://localhost:8080/api/v1/user`
  
```json
{
   "email": "cualquier_cosa@gmail.com",
   "username": "nombre_de_usuario",
   "password": "contraseña",
   "roles": ["USER", "ADMIN"]
}
```
- Ir a la siguiente URL y loguearse con POST:

  `http://localhost:8080/login`
  
```json
  {
    "username": "nombre_de_usuario",
    "password": "contraseña"
  }
```
- Por ultimo utilizar el Token que devuelve el json para tener la autorizacion para las demas consultas HTTP.
