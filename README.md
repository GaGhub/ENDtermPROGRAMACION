# ENDTerm
- He modificado el launcher para que apunte a gui en lugar de a console.  Ahora apunta a "HelloApplication"
- He creado la carpeta model dentro de java para añadir los enums que me solicitó la profesora en el midterm (Categoría y MetodoPago). Con esto sustituyo los Strings que usaba en el midterm.
- He creado las nuevas clases Gasto e Ingreso, mejoradas. Ahora no guardan la categoría como un texto sin más, si no como una categoría (Enum), evitando errores de escritura.  
- He creado la clase GestorFinanzas. Actuará como "cerebro". Este archivo recupera las funciones que tenía en el midterm. Aquí vivirá la Arraylist y el cálculo del saldo.
- He creado la superclase abstracta "Operacion" dentro de model. En lugar de tener la descripción y el importe repetidos en Gasto e Ingreso, los pondré aquí una sola vez.
- He añadido los archivos proporcionados para el endterm con extensión .fxml a la carpeta con la ruta "src/main/resources/gui".  
---
- FUNCIONAMIENTO de  los archivos:
- El archivo VistaOperaciones.fxml: Recogerá los datos visualmente.
- HelloController.java recibirá esos datos y llamará al GestorFinanzas.
- GestorFinanzas.java realizará los cálculos de saldo que antes tenía en la consola.  
---
- He modificado el archivo VistaOperaciones.fxml para que sepa quien es el controlador, añadiendo "fx:controller="gui.HelloController". También he añadido onAction para decirle al botón que debe hacer cuando haga clic.
- He modificado el archivo NuevaOperacion.fxml para que sepa quien es el controlador, añadiendo fx:controller="gui.NuevaOperacionController".
- He modificado HelloApplication para que busque "VistaOperaciones.fxml" dentro de la carpeta "gui" de resources.  
- PRUEBAS: Actualmente se ejecuta el GUI mostrando el título "Gestión de Finanzas Personales", Mostrando categorías (Tipo, Descripción, Importe(€), Fecha y Origen/Categoria). Además se muestra "No hay operaciones resgistradas.). También aparece el botón (+Nueva Operación). Pero al hacer clic se muestra el mensaje "ERROR: No se ha podido cargar la ventana de Nueva Operación." Lo que indica que reconoce el botón. 

