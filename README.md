# Chromium Android Browser

Aplicación Android lista para servir como base de un navegador Chromium con soporte de extensiones.

## ¿Qué incluye?

- Proyecto Android con Kotlin y Gradle
- Pantalla principal de bienvenida
- BrowserActivity con WebView para navegación
- Módulo de extensiones con descubrimiento desde assets
- Ejemplo de extensión en `app/src/main/assets/extensions/hello-world`
- Documentación de arquitectura para integrar un motor Chromium real

## Nota importante sobre extensiones reales

En Android, una extensión de navegador real en Chromium no puede ejecutarse solo con `WebView`. Para tener compatibilidad real con extensiones de Chromium, normalmente hace falta:

- un navegador basado en Chromium o CEF (Chromium Embedded Framework)
- un runtime de extensiones compatible con Manifest V3
- permisos del sistema y almacenamiento de extensiones
- un backend de carga de CRX/ZIP con registro de scripts y permisos

Este proyecto entrega la base de la app y la estructura de extensiones para una implementación real con un motor Chromium Android nativo.

## Estructura principal

- `app/` - módulo Android
- `app/src/main/java/com/peruxd/chromiumbrowser/` - código Kotlin
- `app/src/main/assets/extensions/` - extensiones de ejemplo
- `README.md` - documentación del proyecto

## Cómo ejecutar

1. Instala Android Studio
2. Abre la carpeta del proyecto
3. Sincroniza Gradle
4. Ejecuta la app en un emulador o dispositivo Android

## Requisitos

- Android Studio Giraffe o superior
- JDK 17
- Android SDK 34
- Gradle 8.x

## Próximo paso recomendado

Integrar un motor Chromium real (CEF, Chromium custom build o navegador Android con soporte de extensiones) en la capa de renderizado y dejar la UI/gestion de extensiones que ya está preparada aquí.
