
# ENDTerm - GABRIEL DE LUQUE RAMIS DE AYREFLOR

- He modificado el launcher para que apunte a gui en lugar de a console.  Ahora apunta a "HelloApplication"
- He creado la carpeta model dentro de java para añadir los enums que me solicitó la profesora en el midterm (Categoría y MetodoPago). Con esto sustituyo los Strings que usaba en el midterm.
- He creado las nuevas clases Gasto e Ingreso, mejoradas. Ahora no guardan la categoría como un texto sin más, si no como una categoría (Enum), evitando errores de escritura.
- He creado la clase GestorFinanzas. Actuará como "cerebro". Este archivo recupera las funciones que tenía en el midterm. Aquí vivirá la Arraylist y el cálculo del saldo.
- He creado la superclase abstracta "Operacion" dentro de model. En lugar de tener la descripción y el importe repetidos en Gasto e Ingreso, los pondré aquí una sola vez.
- He añadido los archivos proporcionados para el endterm con extensión .fxml a la carpeta con la ruta "src/main/resources/gui".
- FUNCIONAMIENTO de  los archivos:
- El archivo VistaOperaciones.fxml: Recogerá los datos visualmente.
- HelloController.java recibirá esos datos y llamará al GestorFinanzas.
- GestorFinanzas.java realizará los cálculos de saldo que antes tenía en la consola.
- He modificado el archivo VistaOperaciones.fxml para que sepa quien es el controlador, añadiendo "fx:controller="Controller.OperacionesController". También he añadido onAction para decirle al botón que debe hacer cuando haga clic.
- He modificado el archivo NuevaOperacion.fxml para que sepa quien es el controlador, añadiendo fx:controller="Controller.NuevaOperacionController".
- He modificado HelloApplication para que busque "VistaOperaciones.fxml" dentro de la carpeta "gui" de resources.
- PRUEBAS: Actualmente, se ejecuta GUI mostrando el título "Gestión de Finanzas Personales", Mostrando categorías (Tipo, Descripción, Importe(€), Fecha y Origen/Categoria). Además se muestra "No hay operaciones resgistradas.). También aparece el botón (+Nueva Operación). Pero al hacer clic se muestra el mensaje "ERROR: No se ha podido cargar la ventana de Nueva Operación." Lo que indica que reconoce el botón.
- He creado la clase NuevaOperacionController. Este archivo sirve para que los botones "Cancelar" y "Guardar" funcionen.
- Añado la línea "onAction="#onCancelarClick" en la etiqueta de "cancelar" del archivo NuevaOperacion.fxml para que sepa qué hacer al clicar"
- Hago lo mismo en el botón "Guardar", pero con la línea onAction="#onGuardarClick"
- He editado el archivo module-info.java (gestiona los permisos de todo el proyecto). Le añado la línea "opens model to javafx.base;"para qie JavaFX pueda entrar. Lo he hecho porque me salaba un error de acceso.
- He actualizado los archivos "Gasto", "Ingreso", "HelloCOntroller" y "Operaciones" porque en la tabla no salían ni el tipo ni la categoría. Faltaban líneas de código para relacionar los archivos.
- Ahora que sé que se crean correctamente, he cambiado las categorías que estaban fijadas como "OTROS" o "EFECTIVO" escribiera lo que escribiera el usuario para que devuelva la información escrita.
- Modifico la implantación de las ArrayList por los TreeSet. Como ya había implementado la interfaz "Comparable" en la clase "Operacion" para comparar por fecha, el TreeSet utilizará ese método "compareTo" cada vez que añada un elemento. Ya no necesitaré llamar a Collections.sort().
- En el gestor de finanzas vuelco el TreeSet a una ArrayList para poder transportar los datos. Después, en HelloController creo la lista observable para devolverla en la tabla.
- Me he dado cuenta de que el ejercicio requería crear un controlador para Operaciones, yo había editado directamente el HelloController. Así que lo he reestructurado entero creando la carpeta "Controller" y añadiendo dentro los archivos "NuevaOperacionController" y "OperacionesController"
- En la clase "Operacion", añado la segunda condición en caso de empate en fecha, siendo el importe el encargado del desempate. Sin esta segunda opción, el programa borraría cualquier gasto nuevo que coincida en día con uno anterior.
- He creado en model una clase llamada "TestData" para crear los datos de prueba, ya que en HelloConsole, como proponía el enunciado, me generaba conflictos. Luego, en OperacionesController, llamo al método dentro de "Initiale"
- El siguiente paso ha sido comenzar con la persistencia de datos. Para ello he abierto XAMPP y he iniciado SQL.
- Con la aplicación HeidoSQL he ejecutado un script para crear la base de datos y una única taba compartida entre ingresos y gastos.
- Dentro de la carpeta "java" he creado el paquete "dao" para alojar los archivos encargados de la conexión con la base de datos.
- He creado el archivo "ConexionDB" dentro para alojar el código con el patrón Singletone.
- Dentro del paquete "dao", he creado el archivo "DaoOperacion", que es la interfaz que establece qué acciones pueden realizar con la base de datos.
- Dentro del mismo paquete "dao" he creado otro archivo "DaoOperacionImplementacion". Este archivo se encarga de la seguridad con "PreparedStatement". Con ello gestiono las excepciones y evito cierres de programa en caso de error de conexión.
- He modificado el archivo "GestorFinanzas" para que actue de puente hacia la base de datos. Era necesario actualizarlo porque cambia su función. Yan no es un almacén de datos, sino el intermediario que le pide los datos al DAO que están alojados en mySQL.
- He ejecutado el launcher, he introducido nuevos datos además del de prueba y he cerrado la aplicación e intelliJ. Al abrir la aplicación de nuevo los datos continuaban ahí, por lo que funciona.  
- He configurado alertas para que se muestren en una ventana si un campo está vacío. Para ello he creado condiciones para cada campo en el archivo "NuevaOperacionController"


