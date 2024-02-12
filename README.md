# Aplicación CRUD Shogi

Este proyecto es una aplicación Android para llevar a cabo operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en una lista de productos de un restaurante japones llamado `SHOGI`. La aplicación también cuenta con un sistema de inicio de sesión integrado con Google para autenticar a los usuarios.

## Características principales

- **Operaciones CRUD:**
  - Crear nuevos productos con nombre, cantidad, precio y tipo de stock.
  - Ver la lista completa de productos con detalles como nombre, cantidad, precio y tipo de stock.
  - Actualizar la información de los productos existentes.
  - Eliminar productos de la lista.
  
- **Inicio de sesión con Google:**
  - Los usuarios pueden iniciar sesión utilizando sus cuentas de Google.
  - Se utiliza la autenticación de Firebase Authentication para gestionar el inicio de sesión.

## Arquitectura

El proyecto sigue la Arquitectura de Componentes de Android, haciendo uso de las bibliotecas Navigation, LiveData y ViewModel. Esto proporciona una estructura robusta y escalable para el desarrollo de la aplicación, facilitando la navegación entre fragmentos, la gestión de datos en tiempo real y la separación clara de la lógica de la interfaz de usuario.

## Dependencias del Proyecto

Para compilar y ejecutar el proyecto, se utilizan las siguientes dependencias:

### Nivel de Proyecto

- En el nivel de proyecto, se incluye la dependencia `com.google.gms:google-services:4.4.0` para la configuración de servicios de Google.

### Nivel de Aplicación

- En el nivel de aplicación, se definen las dependencias necesarias para la ejecución de la aplicación. Estas incluyen:
  - Bibliotecas de compatibilidad como `androidx.appcompat:appcompat:1.6.1` y `com.google.android.material:material:1.11.0`, que proporcionan componentes de IU compatibles con versiones anteriores de Android.
  - `androidx.constraintlayout:constraintlayout:2.1.4` para la gestión de la disposición de la interfaz de usuario.
  - Bibliotecas de Google Play Services como `com.google.android.gms:play-services-base:18.3.0` y `com.google.android.gms:play-services-auth:20.7.0` para integrar funcionalidades de Google en la aplicación.
  - Bibliotecas de Firebase como `com.google.firebase:firebase-auth:22.3.1` para la autenticación de usuarios y `com.google.firebase:firebase-analytics` para el análisis de datos de la aplicación.
  - Bibliotecas de prueba como `junit:junit:4.13.2` y `androidx.test.ext:junit:1.1.5` para escribir y ejecutar pruebas unitarias y de integración.

Recuerda que las versiones de las dependencias pueden variar, así que asegúrate de utilizar las versiones más recientes y compatibles con tu proyecto.

## Estructura del proyecto

El proyecto sigue una estructura estándar de aplicación Android:

- **`app/src/main/java/com/vedruna/aplication_crud`:** Contiene los archivos de código fuente de la aplicación, incluyendo las actividades, fragmentos, adaptadores, modelos de datos y la interfaz de servicio para las operaciones CRUD.
- **`app/src/main/res`:** Contiene los recursos de la aplicación, como diseños XML, imágenes y archivos de menú.
- **`app/src/main/AndroidManifest.xml`:** Archivo de manifiesto de la aplicación que declara la configuración y los componentes de la aplicación.
- **`app/build.gradle`:** Archivo de configuración de Gradle para el módulo de la aplicación.

## Configuración del entorno de desarrollo

Para ejecutar y trabajar en este proyecto, necesitarás configurar un entorno de desarrollo de Android:

1. Descarga e instala [Android Studio](https://developer.android.com/studio).
2. Clona este repositorio en tu máquina local.
3. Abre Android Studio y selecciona "Open an existing project". Navega hasta el directorio del proyecto clonado y abre el proyecto android.
4. Espera a que Android Studio sincronice el proyecto y configure las dependencias.

También necesitarás descargar la API:
1. Descarga e instala [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
2. Clona este repositorio en tu máquina local.
3. Abre IntelliJ IDEA y selecciona "Open an existing project". Navega hasta el directorio del proyecto clonado y la API.
4. Espera a que IntelliJ IDEA cargue el proyecto y edite la API según sus necesidades.


## Uso y contribuciones

Si deseas utilizar esta aplicación como base para tu propio proyecto, siéntete libre de hacerlo. También puedes contribuir con nuevas características, mejoras o correcciones de errores mediante pull requests. Acuerdate de modificar en la API el ``application.propperties`` para ajustar tus preferencias en cuanto la BBDD.
