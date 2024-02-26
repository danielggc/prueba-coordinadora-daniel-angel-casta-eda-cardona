# Readme de la Aplicación Coordinadora

## Introducción

Esta aplicación, denominada "Coordinadora", ha sido desarrollada como parte de la prueba de conocimiento en Android para el equipo de proyectos y operaciones de Coordinadora merCantil. La aplicación está diseñada para funcionar en dispositivos Android nativos con un sistema operativo mínimo de Android 8 hasta Android 14. A continuación, se detallan las características y funcionalidades implementadas en la aplicación:

## Características

- **Arquitectura**: La aplicación está desarrollada siguiendo los principios de arquitectura MVP (Modelo-Vista-Presentador) y MVVM (Modelo-Vista-ViewModel).
- **Base de datos local**: Se utiliza SQLite y Room Database para el manejo de la base de datos local.
- **Librerías para peticiones HTTP**: Se hacen peticiones HTTP utilizando librerías como Retrofit.
- **Inyección de dependencias**: Se implementa la inyección de dependencias utilizando Dagger, Dagger 2 o Hilt.
- **Lenguaje de programación**: La aplicación puede desarrollarse tanto en Java como en Kotlin.
- **RxJava**: Si se desarrolla en Java, se trabaja con RxJava.
- **Firebase**: Se utilizan servicios de Firebase para la autenticación de usuarios y el almacenamiento de datos.

## Pantallas

### Pantalla de Login

La pantalla de inicio de sesión presenta los siguientes elementos:

- Título: "COORDINADORA".
- Mensaje de bienvenida.
- Campos de entrada para el código de empleado y el código PIN.

**Validaciones**:
- Si el usuario presiona Enter o el botón "Continuar", se realiza la validación en la colección de usuarios de Firebase.
- Si el usuario no está registrado en la colección, se muestra un mensaje de error.

### Pantalla de Carga de Trabajo

En esta pantalla, se muestra la carga de trabajo en forma de tarjetas, obtenida de un servicio web. El contenido de las tarjetas se muestra como se especifica en el prototipo.

**Funcionalidades**:
- El menú desplegable en la esquina superior derecha ofrece opciones como recargar la lista de carga de trabajo, reordenar la carga y cerrar sesión.
- Al expandir una tarjeta, se puede acceder a una pantalla en orientación landscape con un mapa que muestra la ubicación de la guía seleccionada.

## Implementación y Librerías Utilizadas

La aplicación se desarrolló utilizando Kotlin y siguiendo el patrón de diseño MVVM. A continuación, se enumeran las principales librerías utilizadas:

- Retrofit para las peticiones HTTP.
- Dagger/Hilt para la inyección de dependencias.
- Room para la base de datos local.
- Firebase Authentication, Firestore, Database y Storage para la autenticación de usuarios y el almacenamiento de datos.
- Google Maps para la visualización de mapas.

## Implementación de Inyección de Dependencias

Se ha realizado la inyección de dependencias utilizando Dagger/Hilt, lo que ha permitido una gestión eficiente de las dependencias y una mayor modularidad del código.

## Consideraciones Finales

- **Implementación Completa de Funcionalidades**: La aplicación ha sido implementada en su totalidad, cumpliendo con todos los requisitos especificados en la prueba. Sin embargo, se debe tener en cuenta que una parte del diseño frontend puede requerir ajustes adicionales para su completa finalización.

- **Pantalla de Mapa en Orientación Landscape**: Se ha configurado la pantalla de mapa para mantenerse en modo landscape al expandir una tarjeta desde la pantalla de carga de trabajo. Esto garantiza una visualización óptima de la ubicación de la guía seleccionada en el mapa, mejorando la experiencia del usuario.

Para cualquier consulta adicional o retroalimentación, no dudes en contactar al equipo de desarrollo.

¡Gracias por tu colaboración y por utilizar la aplicación Coordinadora!



![Captura de pantalla 1](Screenshot_2024-02-26-15-01-04-493_com.cursokotlin.mvvmexample.jpg)
![Captura de pantalla 2](Screenshot_2024-02-26-15-01-36-155_com.cursokotlin.mvvmexample.jpg)
![Captura de pantalla 3](Screenshot_2024-02-26-15-02-24-077_com.cursokotlin.mvvmexample.jpg)
![Captura de pantalla 4](Screenshot_2024-02-26-15-01-21-817_com.cursokotlin.mvvmexample.jpg)
![Captura de pantalla 5](Screenshot_2024-02-26-15-01-47-145_com.cursokotlin.mvvmexample.jpg)
![Captura de pantalla 6](Screenshot_2024-02-26-15-02-26-914_com.cursokotlin.mvvmexample.jpg)
![Captura de pantalla 7](Screenshot_2024-02-26-15-01-29-926_com.cursokotlin.mvvmexample.jpg)
![Captura de pantalla 8](Screenshot_2024-02-26-15-01-57-515_com.cursokotlin.mvvmexample.jpg)