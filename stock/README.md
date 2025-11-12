# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

- [Official Gradle documentation](https://docs.gradle.org)
- [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.7/gradle-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/3.5.7/gradle-plugin/packaging-oci-image.html)
- [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.7/reference/data/sql.html#data.sql.jpa-and-spring-data)
- [Spring Web](https://docs.spring.io/spring-boot/3.5.7/reference/web/servlet.html)
- [OpenFeign](https://docs.spring.io/spring-cloud-openfeign/reference/)

### Guides

The following guides illustrate how to use some features concretely:

- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

- [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
- [Declarative REST calls with Spring Cloud OpenFeign sample](https://github.com/spring-cloud-samples/feign-eureka)

## 📦 Servicio de Gestión de Inventario (Stock)

Este microservicio, construido con **Spring Boot**, gestiona el inventario (Stock) de productos. Proporciona una API RESTful para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la entidad Stock y está diseñado para ser la fuente única de verdad para las cantidades de producto disponibles. Utiliza **Gradle** para la construcción y **Flyway** para las migraciones de base de datos.

### 🚀 Tecnologías

- Lenguaje: Java 17+

- Framework: Spring Boot 3.x

- Base de Datos: PostgreSQL

- Construcción: Gradle

- Migraciones: FlywayDB

- Documentación API: SpringDoc (OpenAPI 3)

### 🛠️ Requisitos

Para construir y ejecutar la aplicación localmente, necesitas:

- JDK 17 o superior.

- Gradle (se incluye el Wrapper).

- Una instancia de PostgreSQL en ejecución.

### ⚙️ Configuración

Configura la conexión a la base de datos en `src/main/resources/application.properties` (o `application.yml`).

Asegúrate de que la URL, usuario y contraseña de PostgreSQL sean correctos:

```
# Ejemplo de configuración de PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/stock_db
spring.datasource.username=dbuser
spring.datasource.password=dbpass
```

### Migraciones con Flyway

El servicio utiliza Flyway para gestionar el esquema. Al iniciar la aplicación por primera vez, **Flyway** creará la tabla stock y otras estructuras necesarias basándose en los scripts SQL ubicados en `src/main/resources/db/migration/`.

### ▶️ Ejecución Local

1. Construir el Proyecto
   Utiliza el Gradle Wrapper para limpiar y generar el JAR ejecutable:

```
./gradlew clean build
```

2. Ejecutar la Aplicación
   Ejecuta el JAR generado en la carpeta build/libs:

```
java -jar build/libs/*.jar
```

La aplicación estará disponible en http://localhost:9090 (asumiendo que usa un puerto diferente al servicio de productos).

### 🖥️ API Endpoints

El servicio expone la siguiente API REST en el contexto /api/stock:
| Método | Endpoint | Descripción | Cuerpo de Solicitud |
|---|---|---|---|
| POST | /api/stock | Registra una nueva cantidad de inventario para un producto. | Objeto StockRequest |
| GET | /api/stock/{id} |Obtiene el registro de inventario por ID. | N/A |
| GET | /api/stock | Obtiene todos los registros de inventario con paginación. | N/A |
| PUT | /api/stock/{id} | Actualiza el inventario existente por ID. | Objeto StockRequest |
| DELETE | /api/stock/{id} | Elimina un registro de inventario. | N/A |

### Documentación (Swagger/OpenAPI)
Accede a la interfaz gráfica de **Swagger** UI para probar y visualizar los endpoints una vez que la aplicación esté ejecutándose:
```
http://localhost:9090/swagger-ui.html
```

### 🐳 Docker
El proyecto está configurado con un `Dockerfile` para usar un multi-stage build con Gradle, lo que resulta en una imagen Docker optimizada y de tamaño reducido.
Construir la Imagen:
```
docker build -t stock-service:latest .
```
Ejecutar el Contenedor:
```
docker run -d -p 9090:9090 --name stock-app stock-service:latest
```