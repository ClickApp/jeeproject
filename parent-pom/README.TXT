Testing
-Tests unitarios (en cada proyecto).
-Tests de integración (en proyecto creado a esos efectos).

Trazas
-Las trazas se generarán utilizando la API slf4j y el framework logback.
-Se implementará un aspecto mediante la clase CustomizableTraceInterceptor que genere trazas de todos los métodos.

Dependencias y Versionado
-Todos los números de versión de las dependencias, deben gestionarse desde el parent POM.
-Para cada tarea que se pase a la columna Pending Corrections, se deberá generar un tag en GIT, y subir el último dígito del número de versión (0.0.1, 0.0.2, etc.)

Crear modelo de datos (10)
-Se deberán considerar las tablas empresa, empleado.
-Mapear tablas normalizadas en clases mediante Hibernate Tools.
-Realizar tests de integración.

Crear API de servicios (10)
-Crear un servicio por cada método de CRUD de cada una de las entidades correspondientes a las tablas normalizadas (esto no incluye categorías y subcategorías)
-Crear un servicio que permita establecer una variación de sueldo en base a un porcentaje que se recibirá como parámetro.

Crear implementación de servicios (10)

Implementar un frontend de servicios (25)
-Ofrecer todos los servicios mediante una API REST
-Ofrecer el servicio de aumento de salarios también mediante una API SOAP

Implementar un frontend Web (25)

Securizar aplicaciones (25)
-Securizar el frontend Web mediante Spring Security