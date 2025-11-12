# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

- [Official Gradle documentation](https://docs.gradle.org)
- [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.7/gradle-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/3.5.7/gradle-plugin/packaging-oci-image.html)
- [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.7/reference/data/sql.html#data.sql.jpa-and-spring-data)
- [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/3.5.7/reference/data/nosql.html#data.nosql.redis)
- [Spring Web](https://docs.spring.io/spring-boot/3.5.7/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

- [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

## Servicio de Gestión de Productos

Este microservicio, desarrollado con Spring Boot, proporciona una API RESTful para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la entidad Producto. El proyecto está configurado para utilizar Gradle como herramienta de construcción y Flyway para la gestión de migraciones de base de datos.

### 🚀 Tecnologías

Lenguaje: Java 17+

Framework: Spring Boot 3.x

Base de Datos: PostgreSQL

Construcción: Gradle

Migraciones: FlywayDB

Documentación API: SpringDoc (OpenAPI 3)

### 🛠️ Requisitos

Para construir y ejecutar la aplicación localmente, necesitas:

JDK 17 o superior.

Gradle (se incluye el Wrapper).

Una instancia de PostgreSQL en ejecución.

### ⚙️ Configuración

Antes de ejecutar, configura la conexión a la base de datos en src/main/resources/application.properties (o application.yml).

Asegúrate de que la URL, usuario y contraseña de PostgreSQL sean correctos:

```
# Ejemplo de configuración de PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/product_db
spring.datasource.username=dbuser
spring.datasource.password=dbpass
```

### Migraciones con Flyway

El servicio está configurado para usar Flyway. La primera vez que inicies la aplicación, Flyway creará el esquema y la tabla product basándose en los scripts SQL ubicados en src/main/resources/db/migration/.

## ▶️ Ejecución Local

1. Construir el Proyecto
   Utiliza el Gradle Wrapper para limpiar y construir el JAR ejecutable:

```console
./gradlew clean build
```

2. Ejecutar la Aplicación
   Ejecuta el JAR generado en la carpeta build/libs:

```console
java -jar build/libs/*.jar
```

La aplicación se ejecutará por defecto en http://localhost:8080.

### 🖥️ API Endpoints

El servicio expone la siguiente API REST en el contexto /api/productos:
| Método | Endpoint | Descripción | Cuerpo de Solicitud |
|---|---|---|---|
| POST | /api/productos | Crea un nuevo producto. | Objeto ProductRequest |
| GET | /api/productos/{id} | Obtiene un producto por ID. | N/A |
| GET | /api/productos | Obtiene todos los productos con paginación y ordenamiento. | N/A |
| PUT | /api/productos/{id} | Actualiza un producto existente por ID. | Objeto ProductRequest |
| DELETE | /api/productos/{id} | Elimina un producto por ID. | N/A |

### Documentación (Swagger/OpenAPI)
Una vez que la aplicación esté en ejecución, puedes acceder a la interfaz gráfica de Swagger UI para probar los endpoints:
```console
http://localhost:8080/swagger-ui.html
```
### 🐳 Docker
El proyecto incluye un Dockerfile con un multi-stage build para construir una imagen ligera usando Gradle.

Construir la Imagen:
```console
docker build -t product-service:latest .
```
Ejecutar el Contenedor:
```console
docker run -d -p 8080:8080 --name product-app product-service:latest
```