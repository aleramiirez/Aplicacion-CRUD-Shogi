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

## Pantallas Principales

## Pantalla de Login

<img src="https://github.com/aleramiirez/Aplicacion-CRUD-Shogi/assets/121113496/ac0ca87a-1462-4605-975c-c517e34c9768" width="300" height="550">

### activity_login.xml

Este archivo XML define la interfaz de usuario para la actividad de inicio de sesión (`LoginActivity`). Contiene varios elementos de IU, como un campo de texto para el nombre de usuario, un campo de texto para la contraseña, un botón de inicio de sesión y un botón de inicio de sesión con Google.

El diseño utiliza un `LinearLayout` como contenedor principal y un `CardView` para agrupar los elementos de IU relacionados con el inicio de sesión. El fondo del `LinearLayout` se establece utilizando el archivo `loginbkg.xml`.

### LoginActivity.java

Esta clase Java (`LoginActivity`) corresponde a la actividad de inicio de sesión de la aplicación. Maneja la lógica de autenticación del usuario utilizando Firebase Authentication y Google Sign-In.

En `onCreate()`, se inicializan los elementos de IU y se configuran los listeners para los botones de inicio de sesión y Google Sign-In. También se establece una instancia de Firebase Authentication y se crea un cliente de inicio de sesión de Google.

El método `signIn()` se llama cuando el usuario hace clic en el botón de inicio de sesión con Google, iniciando el flujo de autenticación de Google.

El método `firebaseAuthWithGoogle()` se llama cuando se completa el flujo de inicio de sesión con Google. Autentica al usuario en Firebase utilizando el token de ID proporcionado por Google.

El método `updateUI()` actualiza la interfaz de usuario según el estado de inicio de sesión del usuario.

Finalmente, el método `goHome()` redirige al usuario a la pantalla principal de la aplicación después de iniciar sesión correctamente.

## Pantalla Principal



### activity_main.xml

Este archivo XML define la interfaz de usuario para la actividad principal (`MainActivity`). Contiene un contenedor de fragmentos (`FragmentContainerView`) que aloja la navegación entre fragmentos utilizando la biblioteca de navegación de Android. También incluye una `BottomNavigationView` para la navegación entre las diferentes secciones de la aplicación.

### MainActivity.java

Esta clase Java (`MainActivity`) corresponde a la actividad principal de la aplicación. Se encarga de configurar la navegación entre fragmentos utilizando la biblioteca de navegación y controlar las acciones del usuario en la `BottomNavigationView`.

En `onCreate()`, se configura la `BottomNavigationView` para manejar la navegación entre los fragmentos de la aplicación. Se utiliza un `NavHostFragment` para alojar los fragmentos y un `NavController` para gestionar la navegación.

El método `logOut()` se encarga de cerrar la sesión del usuario actual utilizando Firebase Authentication y redirige al usuario a la pantalla de inicio de sesión (`LoginActivity`).

El método `showLogoutConfirmationDialog()` muestra un cuadro de diálogo de confirmación cuando el usuario intenta cerrar sesión. El diálogo tiene botones "Yes" y "No", con el texto en color principal.

## Pantalla del inventario

<img src="https://github.com/aleramiirez/Aplicacion-CRUD-Shogi/assets/121113496/8710cec5-4478-43be-885b-bc84912427ab" alt="IMG-20240213-WA0013" alt="IMG-20240213-WA0013" width="300" height="550">

### fragment_inventory.xml

Este archivo XML define la interfaz de usuario para el fragmento de inventario (`InventoryFragment`). Contiene un `FrameLayout` como contenedor principal y un `ConstraintLayout` dentro de él para organizar los elementos de la interfaz de usuario.

En este diseño, hay un título de "Shogi Inventory" representado por un `TextView`, seguido de una `ListView` que muestra una lista de productos. El fondo de la pantalla está configurado con un fondo de gradiente.

### InventoryFragment.java

Esta clase Java (`InventoryFragment`) corresponde al fragmento de inventario de la aplicación. Se encarga de obtener los datos de los productos del servidor a través de una solicitud HTTP utilizando Retrofit, y luego muestra los productos en una `ListView`.

En el método `onCreateView()`, se infla el diseño del fragmento y se inicializa la `ListView` para mostrar los productos.

El método `getAll()` utiliza Retrofit para realizar una solicitud GET al servidor y recuperar la lista de productos. Cuando se completa la solicitud, los productos se muestran en la `ListView` utilizando un adaptador personalizado (`ProductAdapter`).

Este fragmento muestra la lista de productos en el inventario de la aplicación y maneja la comunicación con el servidor para obtener los datos necesarios.

## Pantalla para crear productos

<img src="https://github.com/aleramiirez/Aplicacion-CRUD-Shogi/assets/121113496/f5a872fe-5fa5-4203-9463-e90f0d8c2748" alt="IMG-20240213-WA0013" width="300" height="550">

### fragment_add.xml

Este archivo XML define la interfaz de usuario para el fragmento de agregar (`AddFragment`). Contiene un `FrameLayout` como contenedor principal y un `LinearLayout` dentro de él para organizar los elementos de la interfaz de usuario de manera vertical y centrada.

En este diseño, se proporcionan campos para que el usuario ingrese el nombre, la cantidad, el tipo de stock y el precio de un producto. Los campos de nombre y cantidad son campos de texto (`EditText`), mientras que el tipo de stock se selecciona a través de un `Spinner`. También hay un botón para agregar el producto.

### AddFragment.java

Esta clase Java (`AddFragment`) corresponde al fragmento de agregar de la aplicación. Se encarga de permitir al usuario agregar nuevos productos mediante la introducción de datos en los campos proporcionados y luego enviando esos datos al servidor.

En el método `onCreateView()`, se infla el diseño del fragmento y se inicializan los campos de texto (`EditText`) y el `Spinner` para que el usuario pueda ingresar los datos del producto. También se configura un `Button` para manejar la acción de agregar un producto nuevo.

El método `create()` utiliza Retrofit para realizar una solicitud POST al servidor con los datos del producto proporcionados por el usuario. Una vez que se completa la solicitud, se muestra un mensaje al usuario indicando si el producto se agregó correctamente o no.

Este fragmento permite al usuario agregar nuevos productos a la base de datos de la aplicación CRUD y maneja la comunicación con el servidor para enviar los datos del producto.

## Pantalla para actualizar productos

<img src="https://github.com/aleramiirez/Aplicacion-CRUD-Shogi/assets/121113496/e252e97a-b10c-4224-b70b-8b84904dfb09" alt="IMG-20240213-WA0013" width="300" height="550">

### fragment_update.xml

Este archivo XML define la interfaz de usuario para el fragmento de actualización (`UpdateFragment`). Contiene un `FrameLayout` como contenedor principal y un `LinearLayout` dentro de él para organizar los elementos de la interfaz de usuario de manera vertical y centrada.

En este diseño, se proporcionan campos para que el usuario seleccione un producto de una lista desplegable (`Spinner`) y actualice su cantidad, tipo de stock y precio. Los campos de cantidad y precio son campos de texto (`EditText`), mientras que el tipo de stock se selecciona a través de otro `Spinner`. También hay un botón para actualizar el producto.

### UpdateFragment.java

Esta clase Java (`UpdateFragment`) corresponde al fragmento de actualización de la aplicación. Se encarga de permitir al usuario seleccionar un producto existente y actualizar su información, como la cantidad, el tipo de stock y el precio.

En el método `onCreateView()`, se infla el diseño del fragmento y se inicializan los campos de texto (`EditText`) y los `Spinner` para que el usuario pueda seleccionar un producto y actualizar su información. También se configura un botón para manejar la acción de actualizar el producto seleccionado.

El método `loadProductNames()` utiliza Retrofit para cargar la lista de nombres de productos desde el servidor y llenar el `Spinner` con estos nombres. Cuando se selecciona un producto en el `Spinner`, los campos de texto se actualizan automáticamente con la cantidad y el precio del producto seleccionado.

El método `actualizar()` utiliza Retrofit para enviar los datos actualizados del producto al servidor y actualizarlos en la base de datos. Una vez que se completa la solicitud, se muestra un mensaje al usuario indicando si la actualización fue exitosa o no.

Este fragmento permite al usuario actualizar la información de productos existentes en la base de datos de la aplicación CRUD y maneja la comunicación con el servidor para enviar los datos actualizados del producto.

## Pantalla para borrar productos

<img src="https://github.com/aleramiirez/Aplicacion-CRUD-Shogi/assets/121113496/3c689132-6e0c-40d4-8c4a-4486261c783e" alt="IMG-20240213-WA0013" width="300" height="550">

### fragment_delete.xml

Este archivo XML define la interfaz de usuario para el fragmento de eliminación (`DeleteFragment`). Contiene un `FrameLayout` como contenedor principal y un `LinearLayout` dentro de él para organizar los elementos de la interfaz de usuario de manera vertical y centrada.

En este diseño, se proporciona un `Spinner` para que el usuario seleccione un producto a eliminar de una lista de productos disponibles y un botón para realizar la acción de eliminar.

### DeleteFragment.java

Esta clase Java (`DeleteFragment`) corresponde al fragmento de eliminación de la aplicación. Se encarga de permitir al usuario seleccionar un producto existente y eliminarlo de la base de datos.

En el método `onCreateView()`, se infla el diseño del fragmento y se inicializa el `Spinner` para que el usuario pueda seleccionar un producto a eliminar. También se configura el botón de eliminación para manejar la acción de eliminación.

El método `loadProductNames()` utiliza Retrofit para cargar la lista de nombres de productos desde el servidor y llenar el `Spinner` con estos nombres. Cuando se selecciona un producto en el `Spinner`, se muestra un cuadro de diálogo de confirmación para asegurarse de que el usuario realmente desea eliminar el producto seleccionado.

El método `setupDeleteButton()` configura el botón de eliminación para mostrar el cuadro de diálogo de confirmación cuando se hace clic en él.

El método `showConfirmationDialog()` muestra un cuadro de diálogo de confirmación personalizado para asegurarse de que el usuario realmente desea eliminar el producto seleccionado.

El método `delete()` utiliza Retrofit para enviar una solicitud de eliminación al servidor y eliminar el producto seleccionado de la base de datos. Una vez que se completa la solicitud, se muestra un mensaje al usuario indicando si la eliminación fue exitosa o no.

Este fragmento permite al usuario eliminar productos existentes de la base de datos de la aplicación CRUD y maneja la comunicación con el servidor para realizar la eliminación.

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

Si deseas utilizar esta aplicación como base para tu propio proyecto, siéntete libre de hacerlo. También puedes contribuir con nuevas características, mejoras o correcciones de errores mediante pull requests. Acuerdate de modificar en la APIShogi el ``application.propperties`` para ajustar tus preferencias en cuanto la BBDD. En Aplicacion Crud, dentro de la carpeta ``utils`` esta la clase ``constants``, recuerda poner tu dirección IP, puedes conseguirla abriendo el cmd y escribiendo el comando ``ipconfig``, hay tendrás tu ipv4.
