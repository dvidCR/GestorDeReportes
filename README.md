# GestorDeReportes

Aplicación para Ver, Añadir, Actualizar y Borrar el contenido del reporte y generar reportes en PDF y Excel.

## Menú Principal

La primera ventana que se te abrira sera el menú principal, con los siguientes botones:
 - [Ver Contenido](https://github.com/dvidCR/GestorDeReportes/tree/main#ver-contenido)
 - [Añadir Contenido](https://github.com/dvidCR/GestorDeReportes/tree/main#a%C3%B1adir-contenido)
 - [Actualizar Contenido](https://github.com/dvidCR/GestorDeReportes/tree/main#actualizar-contenido)
 - [Borrar Contenido](https://github.com/dvidCR/GestorDeReportes/tree/main#borrar-contenido)
 - [Generar Reporte](https://github.com/dvidCR/GestorDeReportes/tree/main#generar-reporte)
 - [Salir](https://github.com/dvidCR/GestorDeReportes/tree/main#salir)

![img](./IMG/Menu/Menu.png)

## Ver Contenido

El botón de ver contenido muestra una con todos los Productos, Empleados y Ventas que haya.

![img](./IMG/VerContenido/VerContenido.png)

En la parte superiror de la pantlla, al centro, vemos un desplegable, en el cual, podemos cambiar la vista entre los distintos datos.

![img](./IMG/VerContenido/VerContenidoComboBox.png)

En la parte inferior de la pantalla, en el centro, tenemos una barra de busqueda.
Nos servira para filtrar en busqueda de un elemento en concreto de la tabla que estemos viendo.

| Ejemplo 1 | Ejemplo 2 |
|:-----------------:|:-----------------:|
| ![img](./IMG/VerContenido/VerContenidoFiltro1.png) | ![img](./IMG/VerContenido/VerContenidoFiltro2.png) |

## Añadir Contenido

El botón de añadir contenido funciona para que añadamos manualmente un Producto, Empleado o Venta.

Como podemos ver, en la parte superior, en el centro, vemos un desplegable para cambiar entre: 

![img](./IMG/AñadirContenido/AñadirContenidoComboBox.png)

| Insertar Producto | Instertar Empleado | Instertar Venta |
|:-----------------:|:-----------------:|:--------------:|
| ![img](./IMG/AñadirContenido/AñadirContenidoProducto.png) | ![img](./IMG/AñadirContenido/AñadirContenidoEmpleado.png) | ![img](./IMG/AñadirContenido/AñadirContenidoVenta.png) |

Una vez que le demos al botón de añadir, si todo a salido bien, saldra la siguiente ventana.

![img](./IMG/AñadirContenido/AñadirContenido.png)

## Actualizar Contenido

El botón de actualizar contenido funciona para que podamos cambiar los datos ya existentes de Productos, Empleados y/o Ventas.

![img](./IMG/ActualizarContenido/ActualizarContenido.png)

En la parte superior de la pantalla, al centro, tenemos un desplegable, en el cual, podemos cambiar la vista entre los distintos datos a cambiar.

![img](./IMG/ActualizarContenido/ActualizarContenidoComboBox.png)

Los datos solo se pueden actualizar celda por celda de la tabla, es una manera rapida de hacer un cambio en especifico.
Una vez que hayas cambiado el dato en cuestión, y le des al Enter, si todo a salido bien, saldra la siguiente ventana.

![img](./IMG/ActualizarContenido/ActualizarContenidoPrueba.png)

En la parte inferior de la pantalla, al centro, tenemos una barra de busqueda, para hacer una busqueda rápida del elemento en concreto que quieras cambiar.

| Ejemplo 1 | Ejemplo 2 |
|:-----------------:|:-----------------:|
| ![img](./IMG/ActualizarContenido/ActualizarContenidoFiltro1.png) | ![img](./IMG/ActualizarContenido/ActualizarContenidoFiltro2.png) |

## Borrar Contenido

El botón de borrar contenido funciona para que borrar un Producto, Empleado o Venta en concreto, pero tendrás que saberte el ID de dicho elemento.

El primer dato para rellenar que te aparecera será un desplegable para seleccionar en que tabla está el dato que quieres borrar.
El segundo dato es poner el ID del elemento en cuestión que quieras borrar.

![img](./IMG/EliminarContenido/EliminarContenido1.png)

Una vez que hayas rellenado estos campos, y estando totalmente seguros de que es el elemento que quieres borrar, le das al botón de borrar.

Una vez lo presiones te saldrá una ventana de confirmación, que te mostrara los datos completos del elemento que quieres borrar, para asegurarte de que es de verdad el elemnto que quieres borrar.

Si estas totalmente seguro de que lo quieres borrar, le tienes que dar al botón de "Yes", si no lo quieres borrar le das al botón de "No".

![img](./IMG/EliminarContenido/EliminarContenidoConfirmar.png)

Si le has dado al botón de "Yes", te aparecera esta ventana de confirmación.

![img](./IMG/EliminarContenido/EliminarContenido2.png)

## Generar Reporte

El botón de generar reporte funciona para genrar un reporte en PDF y/o en Excel.

En la ventana veremos tres botones:
 - Generar PDF. Para generar solo el reporte del PDF.
 - Generar Excel. Para generar solo el reporte del Excel.
 - Generar Ambos. Para genrar el reporte tanto en PDF como en Excel.

![img](./IMG/GenerarReporte/GenerarReporte.png)

En la siguiente ventana apareceran cuatro opciones:
 - Nombre del archivo: . Aqui pondremos el nombre del reporte.
 **AVISO** 
 **SI LE DAS A GENERAR AMBOS REPORTES, EL NOMBRE QUE PONGAS SERÁ EL NOMBRE DE AMBOS REPORTES.**
 - Ubicación: . Para poner una ubicación tendrás que darle al botón de Seleccionar Ubicación.
 - Seleccionar Ubicación. Se nos abrirar una ventana interactiva para seleccionar la ubicación del archivo.
 - Guardar. Una vez que hayas rellenado ambos campos le daras al botón de guardar para generar el reporte.

![img](./IMG/GenerarReporte/GenerarReporteNombre.png)

Esta es la ventana interactiva para seleccionar la ubicación de reporte.

![img](./IMG/GenerarReporte/GenerarReporteUbicacion.png)

Una vez que le hayas dado al botón de guardar, si todo a salido bien, saldra lo siguiente.

![img](./IMG/GenerarReporte/GenerarReporteExito.png)

## Salir

Simplementa es para cerrar el programa.