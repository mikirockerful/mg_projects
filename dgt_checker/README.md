# DGT Checker

Automatismo para avisar cuando hay disponible cita previa para la DGT.

Utiliza Selenium IDE y Geckodriver (Firefox) para repetir el test cada cierto tiempo y enviar un correo usando la API de Gmail si el test pasa.

Para obtener el archivo ".side", se usa Selenium IDE, que permite automatizar acciones del navegador.

El comando "selenium-side-runner" es la forma de ejecutar un archivo SIDE por linea de comandos. Es un poco rollo de configurar.

El archivo "dgt_madrid.side" corresponde a la solitud para "Trámites de oficina" en "Madrid", tal como está la página de la DGT en junio 2019. Se basa en comprobar que no existan ciertos elementos en el DOM del resultado, que son los que aparecen cuando se indica que no hay citas libres.

El "send_gmail" está publicado en otro repo de mi GitHub, bien documentado.
