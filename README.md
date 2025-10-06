Mi Primera Aplicación en Android con Java

Este es un proyecto de demostración que muestra los conceptos básicos del desarrollo de aplicaciones Android usando Java y Android Studio. La aplicación consiste en una pantalla simple que muestra un mensaje "Hola mundo" y un botón interactivo.

Características

    Pantalla de inicio simple: Una Activity con un diseño básico de "Hola mundo".

    Botón interactivo: Un botón que, al ser presionado, muestra un mensaje Toast en la pantalla.

    Estructura del proyecto: Demostración de la estructura de un proyecto Android, incluyendo los archivos de código (.java), recursos (.xml) y configuración (AndroidManifest.xml y build.gradle.kts).

    Diseño con XML: La interfaz de usuario fue creada usando un archivo de diseño XML en un LinearLayout.

Estructura del Proyecto

La estructura del proyecto sigue las convenciones estándar de Android. Los archivos clave son:

    app/src/main/java/com/example/myjavaapplication/MainActivity.java:
    Contiene la lógica de la aplicación. Aquí se inicializa la vista (activity_hello.xml) y se implementa la función de onClick del botón para mostrar el Toast.

    app/src/main/res/layout/activity_hello.xml:
    Define el diseño visual de la pantalla principal. Incluye un TextView para el texto "Hola mundo" y un Button para la interacción.

    app/src/main/res/values/strings.xml:
    Almacena el nombre de la aplicación y otras cadenas de texto, lo que facilita la localización.

    app/src/main/res/values/colors.xml:
    Define los colores utilizados en la aplicación, como el color de fondo del botón.

    app/src/main/res/values/themes.xml:
    Establece el tema visual de la aplicación, incluyendo el color de la barra de título.

    app/src/main/AndroidManifest.xml:
    El archivo de manifiesto de la aplicación. Declara la MainActivity como el punto de entrada principal y establece los permisos y configuraciones globales de la app.