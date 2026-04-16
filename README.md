# CD09 Systems - Music Player API (Backend)

Este repositorio contiene la lógica de servidor desarrollada en Java 21 con Spring Boot. El objetivo principal es gestionar una playlist dinámica utilizando estructuras de datos lineales avanzadas.

## Tecnologías y Versiones
- Lenguaje: Java 21 (LTS)
- Framework: Spring Boot 3.x
- Gestor de Dependencias: Maven
- Estructura de Datos: Lista Doblemente Enlazada (Implementación propia)
- Infraestructura: Railway (Despliegue en la nube)

## Lógica de Estructuras de Datos
El proyecto implementa una Lista Doblemente Enlazada para gestionar las canciones. A diferencia de una lista simple, esta estructura permite:
- Navegación Bidireccional: Control eficiente de las funciones "Siguiente" y "Anterior".
- Optimización de Memoria: Los nodos se crean y eliminan dinámicamente según la interacción del usuario en el frontend.
- Sincronización: Cada vez que se agrega una canción desde la interfaz, el backend instancia un nuevo nodo y lo indexa automáticamente.

## Gestión de Audio y API de Spotify
El sistema consume la Spotify Web API para obtener metadatos y previsualizaciones de audio.

### Nota sobre el Fallback Audio
En este prototipo, se ha implementado una pista de audio de género electrónico como mecanismo de respaldo (fallback). Esta decisión técnica se tomó para:
1. Asegurar la integridad de las pruebas de navegación de la lista.
2. Garantizar que el usuario siempre reciba retroalimentación sonora, incluso si la API de Spotify no proporciona un preview_url para una pista específica.
3. Demostrar la resiliencia del sistema ante posibles fallos de servicios externos.

## Configuración de Variables
Para proteger las credenciales y la integridad del sistema, se utilizan variables de entorno en el servidor:
- SPOTIFY_CLIENT_ID
- SPOTIFY_CLIENT_SECRET
- SERVER_PORT