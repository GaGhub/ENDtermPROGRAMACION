package console;

//Todas las cosas a importar se colocan antes de HelloConsole

//HERRAMIENTA PARA FECHAS
/* Investigando por mi cuenta he descubierto esta herramienta (No lo he visto en los vídeos hasta S03). He visto que es recomendable
para usar fechas porque a diferencia de String, el programa se asegura de que cumpla con el formato. Esto nos ayudará a filtrar en el futuro */
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Comparator; //Esta herramienta es necesaria para poder comparar las fechas-
// HERRAMIENTA PARA poder leer/escuchar el teclado
import java.util.Scanner; /* Importo la herramienta de utilidades para leer el teclado, como vimos en la clase 1. Le digo a JAVA que importe esta herramienta para poder utilizarla*/

// HERRAMIENTAS PARA QUE EL PROGRAMA SEPA QUE ES UNA LISTA Y QUE SEPA CREARLAS
import java.util.List;      // Importo la herramienta general de listas (define que es una lista, para guardar las cosas en orden)
import java.util.ArrayList; // Importo la herramienta para CREAR listas en la memoria.

// IMPORTAMOS LAS CLASES QUE HEMOS CREADO para cuando llamemos al métudo correspondiente tenga los moldes.
import console.model.Gasto; // Importo desde la carpeta model las clases que he creado previamente. De esta manera HelloConsole ya sabe quñe es un "Gasto"
import console.model.Ingreso; //Lo mismo con la clase ingresos.


public class HelloConsole { //Aquí inicio el programa

    /*En esta zona solo puedo declarar, no puedo hacer acciones. Añado esta lista porque al intentar cerrar el scanner antes de la última llave me salía el close en rojo.
     El único que puede cerrar la escucha del teclado es el menú, cuando el usuario selecciona el 0 y se cierra el programa

    Antes de start, creo las ArrayList (carpetas archivadoras). Al ponerlas aquí, las carpetas se crean al encender el programa y se quedan fijas, para que el menú pueda usarlas todas las veces que quiera.
    Si las hubiera puesto dentro del bucle, cada vez que se ejecutara el menú se crearía una nueva lista, sobreescribiendo la anterior. Además, ponerlo aquí hace que tudo el programa puede verlas.*/

    private List<Gasto> listaGastos = new ArrayList<>(); //Creo la lista para gastos
    private List<Ingreso> listaIngresos = new ArrayList<>();// Creo la lista para ingresos

    // Creo el objeto. Le digo que escuche a System.in (entrada del sistema), básicamente es el teclado.
    Scanner scanner = new Scanner(System.in);

    public void start() {// Aquí inicio el menú a mostrar al usuario


        // Creo una variable para guardar el número que elija el usuario, pero lo hago fuera del bucle para que no la olvide cada vez que dé la vuelta. Tiene que vivir fuera del do-while
        int opcion = -1;

        /*Si lo hago con while se preguntará si la opción elegida es distinta de cero, pero como está vacía no lo sabe, así que no entraría.
       En cambio, con "do-while" entra en el bucle sin preguntar, le pide la opción al usuario y luego se pregunta si es distinta de cero.
       */
        // Para dejar el menú lo más limpio, he creado métodos abajo para cada una de las opciones, vinculándolas en el menú a través de la instrucción.
        // Inicio el bucle do-while del menú principal.
        do {
            // Imprimo el menú que quiero mostrar con todas las opciones posibles
            System.out.println("\n = GESTION DE FINANZAS PERSONALES ="); //Le añado el "\n) para obligarle a saltar de línea antes de imprimir el título
            System.out.println("  Introduce un número del 0 al 3 "); //Le añado esta línea para predecir errores.
            System.out.println("========================================");
            System.out.println("1. Gestionar ingresos");
            System.out.println("2. Gestionar gastos");
            System.out.println("3. Ver resumen financiero");
            System.out.println("0. Cerrar el programa");
            System.out.print("Seleccione una opción: ");


            try {

                // El programa se pausa aquí y espera a que el usuario escriba un número
                opcion = scanner.nextInt();

            /* Ahora voy a configurar el switch, que básicamente es el lector de la variable "opcion" y lo envía.
            Configuro los caminos que puede seguir el programa */
                switch (opcion) {
                    case 1: //Si el usuario elige el 1, el programa va por aquí
                        System.out.println("Has seleccionado: Gestionar ingresos");
                        gestionarIngresos(); //Instrucción para llamar a los ingresos
                        break; /* Añado el break, que es el freno para que no siga leyendo el bucle y salga.
                    Si no estuviera leería todas las opciones. */
                    case 2:// Lo mismo en cada caso.
                        System.out.println("Has seleccionado: Gestionar gastos");
                        gestionarGastos(); // Instrucción para llamar a los gastos.
                        break; //Lo añado en todas las opciones.
                    case 3:
                        System.out.println("Has seleccionado: Ver resumen financiero");
                        verResumenFinanciero(); //Instrucción para llamar al métudo del resumen financiero
                        break;
                    case 0:
                        System.out.println("Guardando datos y cerrando el programa...");
                        break;
                    default: /*Para todos aquellos casos que no cumplan los anteriores (5, 6, 7, 8,...), el switch no sabrá qué hacer,
                     así que lo mandamos aquí para que le muestre el mensaje que escribiré debajo. Es como el escudo*/
                        System.out.print("Opción no válida. Elige un número del 0 al 3: ");
                } //Cierro el switch del menú principal
            } //cierro el try
            catch (Exception e) {
                System.out.print("ERROR: Introduce un valor válido. Vuelve a intentarlo: ");
            }
            finally {scanner.nextLine(); // Para ello lo limpio con el salto de línea siempre

            }
        }//Cierro el "do"
        while (opcion != 0); // Aquí es cuando el programa piensa y compara el valor introducido. Siempre que la opción elegida NO sea 0o la , quiero que repita el menú. Si es 0, saldrá del bucle y cerraré el programa.

        scanner.close(); //Cierro el scanner (la herramienta) para finalizar. Es una práctica muy recomendada, porque ahorra memoria en el ordenador

    }// Final menú Start

    /*Entiendo que de cara al Endterm, tudo lo que he realizado aquí deberá estar fuera de HelloConsole en una clase nueva que actúe de intermediario para que la lógica de negocio esté bien diferenciada de la consola*/
    // TODO: INICIO DE CREACIÓN DE MÉTODOS:


    // TODO: MÉTODO INGRESOS

    private void gestionarIngresos() {

        // Para crear el submenú de ingresos seguiré los pasos igual que en el menú principal, pero ahora meteré el escudo de errores para que no se cierre el programa.
        // TODO: Será importante después meterlo en el menú principal (el try-catch), porque he caído ahora en que no lo he hecho y se cerrará el programa

        // Creo la variable para controlar la opción que elige el usuario en este submenú:
        /*TODO: ¿Por qué en esta ocasión le asigno un valor? PROGRAMACIÓN DEFENSIVA: Le asigno un valor porque he incluído el escudo try-catch en el "do".
        Aunque ahora no se lo asignara el escudo funcionaría igual, pero si en el futuro meto más cosas antes del try-catch podría tener problemas, lo pongo para saber el valor por defecto en caso de error.    */
        int opcionSubmenu = -1;

        // Creo el bucle do-while que para que el usuario esté en él hasta que elija "0" (Volver)
        do {
            // Imprimo el diseño del submenú
            System.out.println("\n= SUBMENÚ INGRESOS =");
            System.out.println("========================================");
            System.out.println("1. Añadir ingreso");
            System.out.println("2. Modificar ingresos");
            System.out.println("3. Eliminar ingresos");
            System.out.println("4. Mostrar lista de ingresos");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");

            // Leo la opción del usuario (usando un try-catch para que no cierre el programa si mete letras)
            try {
                opcionSubmenu = scanner.nextInt();
            }//Final del try
            catch (Exception e) {
                System.out.println("Automensaje para aprendizaje: El bucle ha pasado por el catch, convirtiendo el valor del usuario al valor predefinido");
                opcionSubmenu = -1; /* Le doy un valor inválido (cualquiera que no esté en el menú) para que el switch sepa qué hacer con un valor conocido y salte el default repitiendo el bucle hasta que seleccione
                una opción válida. Si no le asigno un valor e introduzco un carácter desconocido por el scanner, que espera una variable nextInt(), no se guardará y al switch no le llegará nada y no sabrá qué hacer.
                Esta asignación hace que cualquier valor que no sea conocido por el scanner se convierta en el -1 (por ejemplo), y como no forma parte del menú lo enviará a default
                Repitiendo de nuevo el bucle*/
                scanner.nextLine();
            }
            // Al introducir el escudo try-catch con el finally (para limpiar el buffer), el que estaba aquí me hacía introducir un enter extra para que volviera a aparecer el menú principal. Por eso lo he quitado.

            /* Pasado el escudo, se activa el Switch para decidir qué hacer. Si el valor es válido ejecutará cada caso, si no lo es cogerá el valor introducido
            por el usuario y lo convertirá en el valor que le he dado por defecto (8). Como es de tipo numérico como pide el lector el switch lo conoce y lo procesa,
            pero como no es ninguno de los casos ejecuta el default */

            switch (opcionSubmenu) {
                case 1:

                    System.out.println("\n- AÑADIR NUEVO INGRESO -");
                    System.out.println("========================================");

                    // Le pido la Descripción:
                    scanner.nextLine(); // Limpia el 'Enter' fantasma del buffer
                    System.out.print("Introduce la descripción del ingreso: ");
                    String descripcion = scanner.nextLine(); // Lee toda la frase

                    // Pido el Importe:
                    System.out.print("Introduce el importe en €: ");
                    double importe = ImporteValido(); /* Quito "scanner.nextDouble();" para sustituirlo por el métudo de validación creado abajo para ello.
                      "scanner.nextLine();" También quito el limpiador porque ya lo he incorporado al final del metudo de validación del importe.*/

                    // Le pido la Fecha:
                    System.out.print("Introduce la fecha (Formato AAAA-MM-DD, ej: 2026-03-13): ");
                    // Le quito "String fechaTexto = scanner.nextLine();" al haber creado el métudo de escudo:
                    LocalDate fecha = fechaValida(); // Lo cambio de la conversión del texto a un objeto LocalDate a través de "LocalDate fecha = LocalDate.parse(fechaTexto);" por el métudo creado.

                    // Le pido el Origen:
                    System.out.print("Introduce el origen (ej: Nómina, Venta, Regalo, Cena, Boda...: ");
                    String origen = textoObligatorio();

                    // FABRICO EL OBJETO (Uso el "molde" que creé ayer para ingresos (ingresos.java)
                    Ingreso nuevoIngreso = new Ingreso(descripcion, importe, fecha, origen);

                    // Lo guardo en la CARPETA que he creado para los ingresos (ArrayList)
                    listaIngresos.add(nuevoIngreso);

                    //Le muestro un mensaje de confirmación al usuario
                    System.out.println("¡Ingreso guardado correctamente!");
                    System.out.println("========================================");

                    break;
                case 2:
                    modificarIngreso();
                    break;
                case 3:
                    eliminarIngreso();
                    break;
                case 4:
                    listarIngresos();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break; // Al valer 0, el bucle do-while de abajo se romperá
                default:
                    System.out.print("Opción no válida. Elige una opción del 0 al 4: ");
            }//Final switch INGRESOS

        } //final del "do"
        while (opcionSubmenu != 0); // Se repite MIENTRAS la opción NO sea 5

    }//Final Métudo Ingresos

    // TODO: MÉTODO GASTOS

    private void gestionarGastos() {

        // Para crear el submenú de gastos seguiré los pasos igual que en el menú de ingresos.

        // Creo la variable para controlar la opción que elige el usuario en este submenú y le añado un valor por defecto a nivel defensivo. En este caso podría dejarlo en blanco..
        int opcionSubmenu = 8;

        // Creo el bucle do-while que para que el usuario esté en él hasta que elija "0" (Volver)
        do {
            // 1. Imprimimos el diseño del submenú
            System.out.println("\n= SUBMENÚ GASTOS =");
            System.out.println("========================================");
            System.out.println("1. Añadir gasto");
            System.out.println("2. Modificar gastos");
            System.out.println("3. Eliminar gastos");
            System.out.println("4. Mostrar lista de gastos");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");

            //le digo que intente asignar el valor introducido por el usuario en el teclado:
            try {
                opcionSubmenu = scanner.nextInt();
            }//Fin try

            //Si el valor no es correcto en formato, no quiero que se cierre el programa, quiero atrapar el error y modifique el valor al predefinido
            catch (Exception e) {

                System.out.println("Automensaje de aprendizaje: El bucle ha pasado por el catch, convirtiendo el valor del usuario al valor predefinido");
                opcionSubmenu = 8; /* Le doy un valor inválido (cualquiera que no esté en el menú) para que el switch sepa qué hacer con un valor conocido y salte el default,
                repitiendo el bucle hasta que seleccione una opción válida.*/
                scanner.nextLine();
            }

            // Al introducir el escudo try-catch con el finally (para limpiar el buffer), el que estaba aquí me hacía introducir un enter extra para que volviera a aparecer el menú principal. Por eso lo he quitado.

            switch (opcionSubmenu) {
                    case 1:
                        System.out.println("\n- AÑADIR NUEVO GASTO -");
                        System.out.println("========================================");

                        // Le pido la Descripción:
                        scanner.nextLine(); // Limpia el 'Enter' fantasma del buffer
                        System.out.print("Introduce la descripción del gasto: ");
                        String descripcion = scanner.nextLine(); // Lee toda la frase introducida y limpia la memoria

                        // Pido el Importe:
                        System.out.print("Introduce el importe en €: ");
                        double importe = ImporteValido(); /* Quito "scanner.nextDouble();" para sustituirlo por el métudo de validación creado abajo para ello.
                           También quito "scanner.nextLine();" el limpiador Scanner porque ya lo incorporado en el metudo.*/

                        // Le pido la Fecha:
                        System.out.print("Introduce la fecha (Formato AAAA-MM-DD, ej: 2026-03-13): ");
                        // Le quito "String fechaTexto = scanner.nextLine();" al haber creado el métudo de escudo:
                        LocalDate fecha = fechaValida(); // Lo cambio de la conversión del texto a un objeto LocalDate a través de "LocalDate fecha = LocalDate.parse(fechaTexto);" por el métudo creado.

                        // Le pido la Categoría:
                        String categoria = seleccionarCategoria();

                        // Le pido el Métudo de pago:
                        String metodoPago = seleccionarMetodoPago();

                        // FABRICO EL OBJETO (Uso el "molde" que creé ayer para gastos (ingresos.java) "
                        Gasto nuevoGasto = new Gasto(descripcion, importe, fecha, categoria, metodoPago);

                        // Lo guardo en la CARPETA que he creado para los gastos (ArrayList)
                        listaGastos.add(nuevoGasto);

                        //Le muestro un mensaje de confirmación al usuario
                        System.out.println("Gasto guardado correctamente");
                        break;

                    case 2:
                        modificarGasto();
                        break;

                    case 3:
                        eliminarGasto();
                        break;

                    case 4:
                        listarGastos();
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;

                    default:
                        System.out.print("Opción no válida. Elige una opción del 0 al 4: ");
            }
        }//Final del "do"
        while (opcionSubmenu != 0);
    }//Final Métudo Gasto

    //TODO: MÉTODO RESUMEN FINANCIERO

    private void verResumenFinanciero() {

        int opcion = -1;
        do {
            System.out.println("\n- RESUMEN FINANCIERO -");
            System.out.println("===================================");
            System.out.println("1. Saldo total");
            System.out.println("2. Total de gastos por categoría");
            System.out.println("3. Categoría más costosa"); // Funcionalidad extra
            System.out.println("4. Gasto medio mensual"); // Funcionalidad extra
            System.out.println("0. Volver al menú principal");
            System.out.print(" Selecciona una opción (del 0 al 4):"  );

            //CREO LAS VARIABLES QUE USARÉ PARA LOS CÁLCULOS. Necesito el total de ingresos y el total de gastos
            //Las configuro con el valor 0.0 por defecto. Estas son dos variables locales dentro del métudo. Solo vivirán aquí dentro.

            //Variables Saldo total
            double totalIngresos = 0.0;
            double totalGastos = 0.0;

            //variables Gastos por categoría
            double totalAlimentacion = 0.0;
            double totalTransporte = 0.0;
            double totalOcio = 0.0;
            double totalVivienda = 0.0;
            double totalSalud = 0.0;
            double totalOtros = 0.0;

            try {

                 opcion = scanner.nextInt();


                    switch (opcion) {
                        //Lo que había creado en un inicio lo convierto en el caso 1.
                        case 1:
                            System.out.println("SALDO TOTAL");
                            System.out.println("===================================");


                            /*Como he visto en los vídeos, para recorrer una ArrayList tenemos el bucle for (nos interesa el contenido y su posición)
                             y el bucle for-each (solo nos interesa el contenido). Como solo quiero leer los importes y me da igual la posición usaré for-each, que es más limpio y directo.
                            Truco para diferenciarlos:
                             El for clásico lleva tres partes en el paréntesis separados por ";" Lo usaré para modificar y eliminar datos (necesito saber la posición)
                            El for-each solo tiene dos separadas por (:) Lo uso ahora porque solo quiero saber los valores de todos, me da igual su ubicación en la lista.*/

                            // BUCLE FOR-EACH para sumar los INGRESOS

                            for (Ingreso i : listaIngresos) { // Se lee: "Por cada "Ingreso" (al que llamaré 'i') en la lista 'listaIngresos'"

                                // Saco el importe usando el Getter que he configurado en la clase ingreso y lo acumulamos.
                                totalIngresos += i.obtenerImporte();
                            } //Final bucle ingresos

                            // BUCLE FOR-EACH para sumar los GASTOS

                            // Se lee: "Por cada "Gasto" (al que llamaré "g") en la lista "listaGastos"..."
                            for (Gasto g : listaGastos) {


                                // Igual que en el anterior, saco el importe usando el Getter y lo acumulo el importe de los gastos
                                totalGastos += g.obtenerImporte();
                            } // FInal bucle gastos

                            // Realizo el cálculo del SALDO FINAL. Para ello debo declarar una nueva variable en la que almacenar el cáculo. Le llamaré saldoTotal:
                            double saldoTotal = totalIngresos - totalGastos;

                            // Muestro los resultados de los cálculos por pantalla para informar al usuario
                            System.out.println("Total Ingresos: " + totalIngresos + " €");
                            System.out.println("Total Gastos:   " + totalGastos + " €");
                            System.out.println("==================="); //Se lo pongo por estética, para separar el resultado de los totales de cada uno.
                            System.out.println("SALDO ACTUAL:   " + saldoTotal + " €");

                          /*A modo extra, voy a crear un simple aviso en caso de que el saldo total sea menos que cero. Es una condición booleana.
                          Si el resultado de la resta es un número negativo, la condición es true y el programa entra en el bloque para imprimir la alerta. Si no, lo ignora y termina */
                            if (saldoTotal < 0) {
                                System.out.println("ALERTA, tu saldo es negativo.");

                            } //Final alerta de saldo

                            break;
                            //Creo la nueva funcionalidad de gastos por categoría.
                        case 2:

                            System.out.println("DESGLOSE DE GASTOS POR CATEGORÍA");
                            System.out.println("===================================");
                            for (Gasto g : listaGastos) {
                                //Utilizo el getter para la clase categoría y gago una suma de los importes por nombre de forma individual.
                                switch (g.obtenerCategoria()) {
                                    case "ALIMENTACIÓN":
                                        totalAlimentacion += g.obtenerImporte();
                                        break;
                                    case "TRANSPORTE":
                                        totalTransporte += g.obtenerImporte();
                                        break;
                                    case "OCIO":
                                        totalOcio += g.obtenerImporte();
                                        break;
                                    case "VIVIENDA":
                                        totalVivienda += g.obtenerImporte();
                                        break;
                                    case "SALUD":
                                        totalSalud += g.obtenerImporte();
                                        break;
                                    case "OTROS":
                                        totalOtros += g.obtenerImporte();
                                        break;
                                }//Final del switch
                            }//final del for
                            //Ahora imprimo por pantalla todos los cáculos.
                            System.out.println("Alimentación: " + totalAlimentacion + " €");
                            System.out.println("Transporte:   " + totalTransporte + " €");
                            System.out.println("Ocio:         " + totalOcio + " €");
                            System.out.println("Vivienda:     " + totalVivienda + " €");
                            System.out.println("Salud:        " + totalSalud + " €");
                            System.out.println("Otros:        " + totalOtros + " €");

                            break;

                        //Funcionalidad extra: Aunque podría fusionarlo con el caso dos añadiendo los cálculos de comparación después de calcular el total de cada categoría lo haré por separado para que quede clara la implementación EXTRA:
                        // Añado un tercer caso en el menú para desarrollar el cálculo de la categoría más costosa
                        //Para ello, reutilizaré la lógica que he implementado en el caso 2, calculando primero el total por categoría.
                        //Además, como he declarado las variables de cálculo en el do, puedo reutilizar las mismas que en el caso dos sin tener que declarar nuevas.
                        case 3:
                            System.out.println("\nCATEGORÍA MÁS COSTOSA");
                            System.out.println("===================================");

                            // Como he hecho en los métodos anteriores, compruebo si hay gastos registrados primero, si no los hay lanzo el mensaje.
                            if (listaGastos.size() == 0) {
                                System.out.println("No hay gastos registrados para calcular.");
                            }
                            else {
                                for (Gasto g : listaGastos) {
                                    //Utilizo el getter para la clase categoría y gago una suma de los importes por nombre de forma individual.
                                    switch (g.obtenerCategoria()) {
                                        case "ALIMENTACIÓN":
                                            totalAlimentacion += g.obtenerImporte();
                                            break;
                                        case "TRANSPORTE":
                                            totalTransporte += g.obtenerImporte();
                                            break;
                                        case "OCIO":
                                            totalOcio += g.obtenerImporte();
                                            break;
                                        case "VIVIENDA":
                                            totalVivienda += g.obtenerImporte();
                                            break;
                                        case "SALUD":
                                            totalSalud += g.obtenerImporte();
                                            break;
                                        case "OTROS":
                                            totalOtros += g.obtenerImporte();
                                            break;
                                    }//Final del switch

                                    }//Final del for
                                }//Final del else

                                /* El siguiente paso es declarar dos variables para realizar los cálculos y otra para guardar el nombre de la categoría. Luego asignamos un valor por defecto a cada una de las variables
                                 para no estén vacías. En este caso selecciono ALIMENTACIÓN para categoría y su importe total para el importe*/
                                String categoriaGanadora = "ALIMENTACIÓN";
                                double importeMaximo = totalAlimentacion;

                                /* Hecho esto, comparo uno a uno el valor de cada categoría con el importe de la variable declarada. Si en algún momento el valor es superior al que haya en ese momento, se actualiza
                                el valor de las dos variables a la nueva categoría y sigue comparando hasta que haya comparado todas las categorías, quedando guardado entonces el valor más elevado*/
                                if (totalTransporte > importeMaximo) {
                                    importeMaximo = totalTransporte;
                                    categoriaGanadora = "TRANSPORTE";
                                }
                                if (totalOcio > importeMaximo) {
                                    importeMaximo = totalOcio;
                                    categoriaGanadora = "OCIO";
                                }
                                if (totalVivienda > importeMaximo) {
                                    importeMaximo = totalVivienda;
                                    categoriaGanadora = "VIVIENDA";
                                }
                                if (totalSalud > importeMaximo) {
                                    importeMaximo = totalSalud;
                                    categoriaGanadora = "SALUD";
                                }
                                if (totalOtros > importeMaximo) {
                                    importeMaximo = totalOtros;
                                    categoriaGanadora = "OTROS";
                                }

                                // Acabada la comparación muestro el valor que se haya quedado en la comparación (el ganador).
                                System.out.println(" La categoría donde más has gastado es [" + categoriaGanadora + "] con un total de " + importeMaximo + " €");

                            break;

                            //FUNCIONALIDAD EXTRA: Creo el caso 4 para la funcionalidad del gasto medio mensual
                        case 4:
                            System.out.println("GASTO MEDIO MENSUAL");
                            System.out.println("===================================");

                            if (listaGastos.size() == 0) {
                                System.out.println("No hay gastos registrados para calcular.");
                            }
                            else {
                                //Para poder guardar los totales por meses necesito una nueva ArrayList que cree carpetas de meses y vaya actualizando los importes de cada uno de ellos.
                                List<String> mesesConGastos = new ArrayList<>();

                                // BUCLE FOR-EACH para sumar los GASTOS, como no me importa el lugar de la lista no necesito me importa el índice.

                                // Se lee: "Por cada "Gasto" (al que llamaré "g") en la lista "listaGastos"
                                for (Gasto g : listaGastos) {

                                    // Igual que en el anterior, saco el importe usando el Getter y lo acumulo el importe de los gastos, reutilizando la variable declarada en el "do" para realizar el cálculo total de gastos.
                                    totalGastos += g.obtenerImporte();

                                    // Extraigo el año y el mes de un gasto (Ej: "2026-3") y lo añado a una nueva variable para que almacene el dato
                                    String mesAnio = g.obtenerFecha().getYear() + "-" + g.obtenerFecha().getMonthValue();

                                    // Si la combinación mesAño aún no ha aparecido en la lista, lo añado. Con el "contains" evito duplicados.
                                    if (!mesesConGastos.contains(mesAnio)) {
                                        mesesConGastos.add(mesAnio);
                                    }//final del if
                                    //Con los datos extraídos y metidos en las carpetas por meses puedo cerrar el bucle.
                                } // Final bucle

                                //Ahora declaro dos nuevas variables para realizar el cálculo medio por cada mes.
                                int numeroMesesConGastos = mesesConGastos.size(); //Contabilizo la cantidad de carpetas que hay en la ArrayList
                                double mediaMensual = totalGastos / numeroMesesConGastos; //Divido el importe total de los gastos entre la cantidad de meses que han registrado un gasto y lo guardo en la variable.
                                System.out.println("Tu gasto medio mensual es de: " + mediaMensual + "€");

                            }//Final else
                            break;
                        case 0:
                            System.out.println("Volviendo al menú principal...");
                            break;
                        default:
                            System.out.print("Opción no válida. Elige una opción del 0 al 4: ");
                            break;
                    }//Final del switch

            } //Final del try
            //Como he hecho siempre, me protejo de los errores de formato.
            catch (Exception e) {
                System.out.print("ERROR Formato incorrecto");
                scanner.nextLine();
            }

        }//Final del do
        while (opcion != 0); // Se repite MIENTRAS la opción NO sea 0

    } // Final RESUMEN FINANCIERO

// TODO: ESCUDO VARIABLES DOUBLE (para importes en este caso):

/* Métudo privado que devuelve un número decimal (double). No necesita recibir datos entre los paréntesis.
    //Dado que cuando el usuario presionaba Enter con el campo vacío solo bajaba de línea y quería que imprimiera un mensaje indicándole que el texto era obligatorio, he cambiado el métudo de validación.
    // He fusionado dos conceptos que aparecen en los vídeos: la herramienta de length y el parsing.*/
    private double ImporteValido() {

        // Declaro la variable "número" (tipo double) y la fijo a 0.0. Aquí se guardará lo que escriba el usuario.
        double numero = 0.0;
        // Declaro la variable booleana "esValido" en "false". Es mi bandera para saber cuándo salir del bucle.
        boolean esValido = false;

        do { // Igual que he hecho en el menú, le obligo a actuar antes de pensar.
            //  Abro el bloque "try". Aquí meteré el código que hace que el programa se cierre.
            try {
                String textoDouble = scanner.nextLine(); //He modificado la entrada de double a String para poder utilizar el length, antes era scanner.nextDouble()
               // Compruebo si pulsó Enter sin escribir nada
                if (textoDouble.length() == 0) { //IntelliJ me recomienda poner "textoDouble.isEmpty()", que hace lo mismo, pero lo dejo como en los vídeos.
                    System.out.print("ERROR: El importe es obligatorio. No puede estar vacío. Inténtalo de nuevo: ");
                } //final primer if
                else {

                    // Si el usuario escribió algo lo transformo a número decimal (double) y, ya que estoy, admito tanto puntos como comas en los decimales.
                    numero = Double.parseDouble(textoDouble.replace(",", "."));

                    //Inicio la comparación numérica.
                     if (numero < 0) { //Si el número es menor a cero le mostraré el error siguiente:
                        System.out.print("ERROR: ¡El importe no puede ser negativo!. Inténtalo de nuevo: ");
                    }//final segundo if

                    //Si el número es 0 o mayor, le digo que entre en el "else"
                     else {
                        //Como el dato es correcto, le digo que cambie la bandera a true. Esto es lo que romperá el bucle y me dejará salir después.
                        esValido = true;
                    }//final segundo else
                }// Final primer else

                // El bloque "catch" es el que atrapa la excepción si el usuario metió letras o símbolos raros en el "try". Es decir, aquí configuraré el error de formato.
            } //final del try
            catch (Exception e) { //Si salta esta excepción, le mostraré el error siguiente:
                System.out.print("ERROR: ¡Formato no válido! Usa números. Inténtalo de nuevo: ");

                // El bloque "finally" es obligatorio que ejecutarlo SIEMPRE, haya habido un error arriba o no.
            }//final del catch
           // Al haber cambiado el tipo de entrada de datos de double a String ya no tengo que limpiar el Buffer, por lo que he quitado el Finally.
            // El bucle se repetirá MIENTRAS mi bandera siga siendo falsa (AUTORECORDATORIO: el símbolo "!" significa NOT (distinto), invierte el valor.
        } //final del "do"
        while (!esValido); //Aquí el bucle es cuando compara. Mientras sea el contrario de válido (es decir, falso), el bucle se repetirá

        // Cuando el bucle termine (porque esValido es true), el métudo me devuelve el número limpio hacia el exterior, es decir, el contenido que se había almacenado el valor para realizar la comparación.
        return numero;
    } // Final escudo de números. Ahora debo conectar este escudo con las líneas de importe de mi métudo ingresos y mi métudo gastos para que funcione.

    // TODO: VALIDACIÓN DE FECHAS
    //  Métudo privado que devuelve un objeto "LocalDate" (una fecha real)
    private LocalDate fechaValida() {

        // Declaro la variable "fecha" y la dejo vacía (null) porque aún no tengo un dato válido.
        LocalDate fecha = null;

        // Nuevamente, fijo mi bandera de control para el bucle
        boolean esValida = false;

        //Le pido que entre en él sin preguntar
        do {
            // Vuelvo a meter en "try" el código que hace que se cierre el programa cuando las fechas fallan
            try {
                // Leo lo que escribe el usuario como si fuera un texto normal. El scanner no sabe lo que es un calendario, por eso no le pido que lo escuche como LocalDate. NO EXISTE: "scanner.nextLocalDate()"
                String textoFecha = scanner.nextLine();

                /* Ahora tengo que convertir la cadena de texto a formato fecha de calendario real.
                Si el usuario escribió "Ayer" o "20-10-2023" (que es un formato incorrecto), saltará el error y saltará el catch.
                Si lo escribe en formato correcto, lo convierte sin problemas. */
                fecha = LocalDate.parse(textoFecha);

                // Si en la línea anterior no saltó, significa que la fecha es correcta y cambiaremos la bandera de control a "true". Nuevamente, esto me permitirá salir del bucle en la comparación durante el while.
                esValida = true;

                //Si el formato no es correcto y no puede hacer la conversión, el catch parará el error, le mandará el aviso al usuario y repetirá el bucle hasta que se cumpla la condición de salida de la bandera de control.
            } //final de try
            catch (Exception e) {
                // Si saltó el error (excepción), le avisamos y el bucle dará otra vuelta
                System.out.print("ERROR: ¡Fecha inválida o formato incorrecto.! Usa AAAA-MM-DD (ej: 2026-03-13): ");


            }//final del catch
            // TODO:  Aquí NO ponemos "finally { scanner.nextLine(); }" porque nextLine() ya lee y limpia toda la línea, no atrapa los datos en el buffer como nextDouble().

        } while (!esValida); // Realizo la comparación con el flag. Repetiré el bucle mientras no tenga una fecha válida. Si se cumple la bandera saldrá.

        // Como antes, le pido que devuelva el objeto LocalDate para poder guardar el dato definitivo.
        return fecha;
    }// Final escudo de fecha. Ahora debo conectar este escudo con las líneas de fechas de mi métudo ingresos y mi métudo gastos para que funcione.

    // TODO: MÉTODOS DE LISTADO

    /* Antes de poder modificar o eliminar, tengo que poder listar los ingresos y los gastos. De esta manera, sabré cuál es la posición que ocupan para, a través del setter,
    decirle cuál es el que quiero modificar/eliminar. Para ello crearé otro métudo y lo vincularé*/

    /* Éste será privado y no devolverá nada (void), solo quiero que imprima por pantalla.
       Su objetivo es recorrer la carpeta ingresos (ArrayList) y mostrarme todos los ingresos numerados,
       para que el usuario sepa qué número elegir si luego quiere modificar o borrar uno. */

    //TODO: LISTAR INGRESOS:

    private void listarIngresos() {
        System.out.println("\n= LISTADO DE INGRESOS =");
        System.out.println("========================================");

        // BUENA PRÁCTICA: Primero compruebo si la lista está vacía.
        /*Podría hacerlo sin "if" y no daría error, pero quiero mostrar un mensaje al usuario si está vacía. Si no lo pongo no ocurriría nada y el usuario podría
        pensar que el programa no funciona o que se ha quedado colgado.*/

        if (listaIngresos.size() == 0) { //Significa: Si el tamaño de la lista es igual a cero...
            System.out.println("No hay ingresos registrados. La lista está vacía"); //Imprimimos por pantalla que no hay.
        }
        else {

            // Añadido en la funcionalidad extra de LISTAR POR FECHA (De más reciente a más antiguo)
            // Lo añado justo antes de realizar la consulta dentro del for porque con la herramienta "sort" Java cogerá todas las carpetas y las recolocará en la nueva posición por orden de fecha.
            //Al hacerlo así, hacemos que coincida la posición mostrada por pantalla con la interna de Java, evitando borrar o modificar un archivo que no queramos.
            listaIngresos.sort(Comparator.comparing(Ingreso::obtenerFecha).reversed());

            /* Aquí hago un BUCLE FOR CLÁSICO: A diferencia del for-each, aquí SÍ necesito saber el número de posición (índice).
           Empiezo en 0 (las listas en Java empiezan en 0). Luego repito mientras "i" sea menor que el tamaño de la lista y Sumo 1 en cada vuelta (i++). */
            for (int i = 0; i < listaIngresos.size(); i++) { //Le digo que "i" vale cero, y que mientras el valor de i sea menos al tamaño de la lista siga sumando 1.

                // Con el getter, saco el ingreso de la posición "i" en cada vuelta, (la primera es 0, la segunda 1, la tercera 2.... Así hasta que ya no cumpla la condición del punto anterior.
                Ingreso ingresoActual = listaIngresos.get(i);

                // Imprimo el número (i + 1 para que empiece en 1 a la vista) y el contenido (usando tu toString). Esto es para que el primero de la lista no sea un cero.
                System.out.println((i + 1) + ". " + ingresoActual.toString()); /* Cuando haga el métudo para el setter, tengo que acordarme de restar uno a la opción elegida por el usuario para que coincida con la lista mostrada,
                 porque si no borrará uno que no toca.*/
            }

        } //Final else INGRESOS
        System.out.println("========================================");
    } //Final métudo LISTAR INGRESOS

    /* Repito el proceso para la lista de gastos. */

    // TODO: LISTAR GASTOS:

    private void listarGastos() {
        System.out.println("\n= LISTADO DE GASTOS =");
        System.out.println("========================================");

        if (listaGastos.size() == 0) {
            System.out.println("Aún no hay gastos registrados en el sistema.");
        }
        else {

            // Añadido en la funcionalidad extra de LISTAR POR FECHA (De más reciente a más antiguo)
           // Lo añado justo antes de realizar la consulta dentro del for porque con la herramienta "sort" Java cogerá todas las carpetas y las recolocará en la nueva posición por orden de fecha.
            //Al hacerlo así, hacemos que coincida la posición mostrada por pantalla con la interna de Java, evitando borrar o modificar un archivo que no queramos.
            listaGastos.sort(Comparator.comparing(Gasto::obtenerFecha).reversed());

            for (int i = 0; i < listaGastos.size(); i++) {
                Gasto gastoActual = listaGastos.get(i);
                System.out.println((i + 1) + ". " + gastoActual.toString());
            }//final del for
        }// Final else gastos
        System.out.println("========================================");
    } //Final métudo LISTADO GASTOS

    // TODO MÉTODOS DE MODIFICACIÓN

    /* TODO: MODIFICAR IMPORTE DE INGRESO */
    private void modificarIngreso() {
        System.out.println("\n- MODIFICAR INGRESOS -");
        System.out.println("========================================");

        // Igual que antes, si la lista está vacía, no hay nada que modificar, por lo que informo al usuario de que la lista está vacía.
        if (listaIngresos.size() == 0){
            System.out.println("No hay ingresos registrados en esta lista.");
            return; // La palabra "return" cuando no va acompañada de nada más funciona como salida de emergencia. Abandona el métudo inmediatamente.
            //Nada de lo que escriba debajo de un return suelto se ejecutará
        }

        // Si la lista no está vacía, ejecuto la instrucción de listar para que el usuario vea las opciones. Por eso necesitaba crear esa opción antes.
        listarIngresos();

        /* Creo la variable que almacenará la opción que elija el usuario. Igual que he hecho en otros métodos, como debe ser numérico, le asigno un valor predeterminado que no está en la lista (como un número negativo)
        De esta manera, si el usuario introduce un carácter que no está en la lista, se limpia el último valor introducido y se le reasigna el valor por defecto  */
        int numeroElegido = -1;
        boolean indiceValido = false; // El índice es la posición que ocupa en la lista y por defecto le digo que es falso por defecto. Cuando sea verdadero saldrá del bucle y continuará el métudo.

        // Ahora creo el bucle para asegurarme de que elige un número de la lista que realmente exista, para ello lo haré con do-while
        do { //le pido que entre directamente y le solicite un número.
            System.out.print("Elige el número del ingreso que deseas modificar (o pulsa 0 para cancelar): ");
            try {
                numeroElegido = scanner.nextInt(); //Como siempre, la solicito dentro del try para que si el usuario introduce un valor que no es correcto, el catch actúe.

                // Si el usuario no quiere modificar ningún valor y pulsa el 0, cancelo el bucle y salgo directamente
                if (numeroElegido == 0) {
                    System.out.println("Operación cancelada.");
                    return; // Salimos del métudo instantáneamente.
                }//Final if

                // Pero si introduce un valor distinto a cero, compruebo si el número introducido está dentro de los límites de la lista
                // El número tiene que ser mayor que 0, y menor o igual al tamaño total de la lista.
                if (numeroElegido > 0 && numeroElegido <= listaIngresos.size()) { //Si el valor elegido es mayor a cero y además es menor o igual al total del tamaño de la lista, cambiaremos el valor de la
                    //bandera a true, saliendo del bucle
                    indiceValido = true; // El número es válido, bajamos la bandera para salir del bucle
                } //Final if
                else { //Si el número no cumple con los criterios anteriores, la bandera continuará en falso, imprimiré un mensaje de que el valor no está en la lista y le diré que vuelva a seleccionar uno.
                    System.out.print("El número no está en la lista. Vuelve a seleccionar uno: ");
                }//Final else

            } //Final del try
            catch (Exception e) { //Si el formato del valor no es un número entero, atrapo el error y le imprimo el mensaje
                System.out.print("ERROR: El formato es incorrecto. Introduce un número entero que aparezca en la lista: ");
            } //Final del catch
            finally {
                scanner.nextLine(); // Como hago siempre con los números, limpio el Enter (Buffer)
            }
        } while (!indiceValido); // Si el índice es contrario al marcado al inicio continuo en el código.

        // Teniendo en cuenta que antes he adulterado la lista para que empiece en 1 y no en 0 (como hace JAVA), tengo que cambiar el valor introducido para que coincidan.
        //Podría haber seguido utilizando la variable numeroElegido, pero prefiero cambiarla para recordar el porqué del -1. Alternativa (numeroElegido = numeroElegido - 1;)
        int indiceJava = numeroElegido - 1; // Al número elegido por el usuario le quitas 1 (Si elige el 1 en la lista, corresponde con el 0 de JAVA)

        // Ahora voy a la Array de ingresos y extraigo la "carpeta" exacta del archivador asociado al ese índice con el getter
        Ingreso ingresoModificado = listaIngresos.get(indiceJava);

        // Muestro el importe antiguo como recordatorio visual, para que sepa que está modificando (podría imprimir todos los atributos que quisiera, solo debo usar los getter correspondientes.).
        System.out.println("Has elegido: " + ingresoModificado.obtenerDescripcion()); // public String obtenerDescripcion() {return descripcion; }
        System.out.println("Importe actual: " + ingresoModificado.obtenerImporte() + "€"); //   public double obtenerImporte() {return importe;}

        /* A partir de aquí podría crear un nuevo bucle de selección de opciones para dar a elegir al usuario qué parte del objeto quiere modificar,
        pero como el trabajo solo pide el importe, no es necesario.*/

        // Ahora solicito el nuevo importe usando es escudo que he creado antes para obligar a introducir un número válido.
        System.out.print("Introduce el NUEVO importe en € (decimal separado con coma): ");
        double nuevoImporte = ImporteValido(); //Llamo a la instrucción con el catch integrado para evitar cierres de programa.

        // Finalmente, uso el Setter para sobreescribir únicamente ese dato en el objeto que quiero modificar.
        ingresoModificado.fijarImporte(nuevoImporte); // public void fijarImporte(double importe) {this.importe = importe;}

        //Muestro un mensaje de confirmación y el nuevo resgistro ya modificado.
        System.out.println("Importe modificado correctamente.");
        System.out.println("Nuevo registro:  " + ingresoModificado.obtenerDescripcion() + " --> " + ingresoModificado.obtenerImporte() + " € ");
        System.out.println("========================================");
    }//Final para modificar IMPORTE de la lista INGRESO

    // TODO: MÉTODO DE MODIFICACIÓN GASTO

    private void modificarGasto() {
        System.out.println("\n- MODIFICAR GASTOS-");
        System.out.println("========================================");

        // Igual que antes, si la lista está vacía, no hay nada que modificar, por lo que informo al usuario de que la lista está vacía.
        if (listaGastos.size() == 0){
            System.out.println("No hay gastos registrados en esta lista.");
            return; // La palabra "return" cuando no va acompañada de nada más funciona como salida de emergencia. Abandona el métudo inmediatamente.
            //Nada de lo que escriba debajo de un return suelto se ejecutará
        }

        // Si la lista no está vacía, ejecuto la instrucción de listar para que el usuario vea las opciones. Por eso necesitaba crear esa opción antes.
        listarGastos();

        /* Creo la variable que almacenará la opción que elija el usuario. Igual que he hecho en otros métodos, como debe ser numérico, le asigno un valor predeterminado que no está en la lista (como un número negativo)
        De esta manera, si el usuario introduce un carácter que no está en la lista, se limpia el último valor introducido y se le reasigna el valor por defecto  */
        int numeroElegido = -1; //En esta ocasión le asigno un número negativo, dado que el tamaño de una lista es impredecible y así me aseguro de que el valor que asigno no estará.
        boolean indiceValido = false; // El índice es la posición que ocupa en la lista y por defecto le digo que es falso por defecto. Cuando sea verdadero saldrá del bucle y continuará el métudo.

        // Ahora creo el bucle para asegurarme de que elige un número de la lista que realmente exista, para ello lo haré con do-while
        do { //le pido que entre directamente y le solicite un número.
            System.out.print("Elige el número del gasto que deseas modificar (o pulsa 0 para cancelar): ");
            try {
                numeroElegido = scanner.nextInt(); //Como siempre, la solicito dentro del try para que si el usuario introduce un valor que no es correcto, el catch actúe.

                // Si el usuario no quiere modificar ningún valor y pulsa el 0, cancelo el bucle y salgo directamente
                if (numeroElegido == 0) {
                    System.out.println("Operación cancelada.");
                    return; // Salimos del métudo instantáneamente.
                }//Final if

                // Pero si introduce un valor distinto a cero, compruebo si el número introducido está dentro de los límites de la lista
                // El número tiene que ser mayor que 0, y menor o igual al tamaño total de la lista.
                if (numeroElegido > 0 && numeroElegido <= listaGastos.size()) { //Si el valor elegido es mayor a cero y además es menor o igual al total del tamaño de la lista, cambiaremos el valor de la
                    //bandera a true, saliendo del bucle
                    indiceValido = true; // El número es válido, bajamos la bandera para salir del bucle
                } //Final if
                else { //Si el número no cumple con los criterios anteriores, la bandera continuará en falso, imprimiré un mensaje de que el valor no está en la lista y le diré que vuelva a seleccionar uno.
                    System.out.print("El número no está en la lista. Vuelve a seleccionar uno: ");
                }//Final else

            } //Final del try
            catch (Exception e) { //Si el formato del valor no es un número entero, atrapo el error y le imprimo el mensaje
                System.out.print("ERROR: El formato es incorrecto. Introduce un número entero que aparezca en la lista: ");
            } //Final del catch
            finally {
                scanner.nextLine(); // Como hago siempre con los números, limpio el Enter (Buffer)
            }
        } while (!indiceValido); // Si el valor del índice es contrario al marcado al inicio, continuo en el código.

        // Teniendo en cuenta que antes he adulterado la lista para que empiece en 1 y no en 0 (como hace JAVA), tengo que cambiar el valor introducido para que coincidan.
        //Podría haber seguido utilizando la variable numeroElegido, pero prefiero cambiarla para recordar el porqué del -1. Alternativa (numeroElegido = numeroElegido - 1;)
        int indiceJava = numeroElegido - 1; // Al número elegido por el usuario le quitas 1 (Si elige el 1 en la lista, corresponde con el 0 de JAVA)

        // Ahora voy a la Array de gastos y extraigo la "carpeta" exacta del archivador asociado al ese índice con el getter
        Gasto gastoModificado = listaGastos.get(indiceJava);

        // Muestro el importe antiguo como recordatorio visual, para que sepa que está modificando (podría imprimir todos los atributos que quisiera, solo debo usar los getter correspondientes.).
        System.out.println("Has elegido: " + gastoModificado.obtenerDescripcion()); // public String obtenerDescripcion() {return descripcion; }
        System.out.println("Importe actual: " + gastoModificado.obtenerImporte() + "€"); //   public double obtenerImporte() {return importe;}

        /* A partir de aquí podría crear un nuevo bucle de selección de opciones para dar a elegir al usuario qué parte del objeto quiere modificar,
        pero como el trabajo solo pide el importe, no es necesario.*/

        // Ahora solicito el nuevo importe usando el escudo que he creado antes para obligar a introducir un número válido.
        System.out.print("Introduce el NUEVO importe en € (decimal separado con coma): ");
        double nuevoImporte = ImporteValido(); //Llamo a la instrucción con el catch integrado para evitar cierres de programa.

        // Finalmente, uso el Setter para sobreescribir únicamente ese dato en el objeto que quiero modificar.
        gastoModificado.fijarImporte(nuevoImporte); // public void fijarImporte(double importe) {this.importe = importe;}

        //Muestro un mensaje de confirmación y el nuevo resgistro ya modificado.
        System.out.println("Importe modificado correctamente.");
        System.out.println("Nuevo registro:  " + gastoModificado.obtenerDescripcion() + " --> " + gastoModificado.obtenerImporte() + " € ");
        System.out.println("========================================");

    }//Final para modificar IMPORTE de la lista GASTO

    /*Hecho lo anterior, solo me queda diseñar los métudos para eliminar registros, que básicamente es lo mismo que en el antes, pero usando el setter nativo de las
    Array list que borra la carpeta entera (el índice elegido con todos los atributos)
    */
    /* TODO: ELIMINAR INGRESO */
    private void eliminarIngreso() {
        System.out.println("\n- ELIMINAR INGRESOS -");
        System.out.println("========================================");

        // Igual que antes, si la lista está vacía, no hay nada que eliminar, por lo que informo al usuario de que la lista está vacía.
        if (listaIngresos.size() == 0){
            System.out.println("No hay ingresos registrados en esta lista.");
            return; // La palabra "return" cuando no va acompañada de nada más funciona como salida de emergencia. Abandona el métudo inmediatamente.
            //Nada de lo que escriba debajo de un return suelto se ejecutará.
        }

        // Si la lista no está vacía, ejecuto la instrucción de listar para que el usuario vea las opciones.
        listarIngresos();

        /* Creo la variable que almacenará la opción que elija el usuario. Igual que he hecho en otros métodos, como debe ser numérico, le asigno un valor predeterminado que no está en la lista (como un número negativo)
        De esta manera, si el usuario introduce un carácter que no está en la lista, se limpia el último valor introducido y se le reasigna el valor por defecto  */
        int numeroElegido = -1;
        boolean indiceValido = false; // El índice es la posición que ocupa en la lista y por defecto le digo que es falso por defecto. Cuando sea verdadero saldrá del bucle y continuará el métudo.

        // Ahora creo el bucle para asegurarme de que elige un número de la lista que realmente exista, para ello lo haré con do-while
        do { //le pido que entre directamente y le solicite un número.
            System.out.print("Elige el número del ingreso que deseas eliminar (o pulsa 0 para cancelar): ");
            try {
                numeroElegido = scanner.nextInt(); //Como siempre, la solicito dentro del try para que si el usuario introduce un valor que no es correcto, el catch actúe.

                // Si el usuario no quiere eliminar ningún valor y pulsa el 0, cancelo el bucle y salgo directamente
                if (numeroElegido == 0) {
                    System.out.println("Operación cancelada.");
                    return; // Salimos del métudo instantáneamente.
                }//Final if

                // Pero si introduce un valor distinto a cero, compruebo si el número introducido está dentro de los límites de la lista
                // El número tiene que ser mayor que 0, y menor o igual al tamaño total de la lista.
                if (numeroElegido > 0 && numeroElegido <= listaIngresos.size()) { //Si el valor elegido es mayor a cero y además es menor o igual al total del tamaño de la lista, cambiaremos el valor de la
                    //bandera a true, para poder salir del bucle
                    indiceValido = true; // El número es válido, bajamos la bandera para salir del bucle
                } //Final if
                else { //Si el número no cumple con los criterios anteriores, la bandera continuará en falso, imprimiré un mensaje de que el valor no está en la lista y le diré que vuelva a seleccionar uno.
                    System.out.print("El número no está en la lista. Vuelve a seleccionar uno: ");
                }//Final else

            } //Final del try
            catch (Exception e) { //Si el formato del valor no es un número entero, atrapo el error y le imprimo el mensaje
                System.out.print("ERROR: El formato es incorrecto. Introduce un número entero que aparezca en la lista: ");
            }//Cierro el catch
            finally {
                scanner.nextLine(); // Como hago siempre con los números (int y double), limpio el Enter (Buffer)
            }
        }// Termino el "do"
        while (!indiceValido); // Si el índice es contrario al marcado al inicio continuo en el código.

        // Teniendo en cuenta que antes he adulterado la lista para que empiece en 1 y no en 0 (como hace JAVA), tengo que cambiar el valor introducido para que coincidan.
        //Podría haber seguido utilizando la variable numeroElegido, pero prefiero cambiarla para recordar el porqué del -1. Alternativa (numeroElegido = numeroElegido - 1;)
        int indiceJava = numeroElegido - 1; // Al número elegido por el usuario le quitas 1 (Si elige el 1 en la lista, corresponde con el 0 de JAVA)

        // Ahora voy a la Array de ingresos y extraigo la "carpeta" exacta del archivador asociado al ese índice con el getter
        Ingreso ingresoEliminado = listaIngresos.get(indiceJava);

        // Muestro el importe antiguo como recordatorio visual, para que sepa que está modificando (podría imprimir todos los atributos que quisiera, solo debo usar los getter correspondientes.).
        System.out.println("Has seleccionado: " + ingresoEliminado.obtenerDescripcion() + " con un importe de " + ingresoEliminado.obtenerImporte() + " € ");
     //   Hago una concatenación con los getters: "public String obtenerDescripcion() {return descripcion; }"  y  "public double obtenerImporte() {return importe;} "

        // Finalmente, uso el Setter de las arraylist para eliminar el registro/índice/carpeta
        listaIngresos.remove(indiceJava);

        //Muestro un mensaje de confirmación.
        System.out.println("Registro eliminado correctamente");
        System.out.println("========================================");
    }//Final para eliminar INGRESO

    /* TODO: ELIMINAR GASTO */
    private void eliminarGasto() {
        System.out.println("\n- ELIMINAR GASTOS-");
        System.out.println("========================================");

        // Igual que antes, si la lista está vacía, no hay nada que eliminar, por lo que informo al usuario de que la lista está vacía.
        if (listaGastos.size() == 0){
            System.out.println("No hay gastos registrados en esta lista.");
            return; // La palabra "return" cuando no va acompañada de nada más funciona como salida de emergencia. Abandona el métudo inmediatamente.
            //Nada de lo que escriba debajo de un return suelto se ejecutará.
        }

        // Si la lista no está vacía, ejecuto la instrucción de listar para que el usuario vea las opciones.
        listarGastos();

        /* Creo la variable que almacenará la opción que elija el usuario. Igual que he hecho en otros métodos, como debe ser numérico, le asigno un valor predeterminado que no está en la lista (como un número negativo)
        De esta manera, si el usuario introduce un carácter que no está en la lista, se limpia el último valor introducido y se le reasigna el valor por defecto  */
        int numeroElegido = -1;
        boolean indiceValido = false; // El índice es la posición que ocupa en la lista y por defecto le digo que es falso por defecto. Cuando sea verdadero saldrá del bucle y continuará el métudo.

        // Ahora creo el bucle para asegurarme de que elige un número de la lista que realmente exista, para ello lo haré con do-while
        do { //le pido que entre directamente y le solicite un número.
            System.out.print("Elige el número del gasto que deseas eliminar (o pulsa 0 para cancelar): ");
            try {
                numeroElegido = scanner.nextInt(); //Como siempre, la solicito dentro del try para que si el usuario introduce un valor que no es correcto, el catch actúe.

                // Si el usuario no quiere eliminar ningún valor y pulsa el 0, cancelo el bucle y salgo directamente
                if (numeroElegido == 0) {
                    System.out.println("Operación cancelada.");
                    return; // Salimos del métudo instantáneamente.
                }//Final if

                // Pero si introduce un valor distinto a cero, compruebo si el número introducido está dentro de los límites de la lista
                // El número tiene que ser mayor que 0, y menor o igual al tamaño total de la lista.
                if (numeroElegido > 0 && numeroElegido <= listaGastos.size()) { //Si el valor elegido es mayor a cero y además es menor o igual al total del tamaño de la lista, cambiaremos el valor de la
                    //bandera a true, para poder salir del bucle
                    indiceValido = true; // El número es válido, bajamos la bandera para salir del bucle
                } //Final if
                else { //Si el número no cumple con los criterios anteriores, la bandera continuará en falso, imprimiré un mensaje de que el valor no está en la lista y le diré que vuelva a seleccionar uno.
                    System.out.print("El número no está en la lista. Vuelve a seleccionar uno: ");
                }//Final else

            } //Final del try
            catch (Exception e) { //Si el formato del valor no es un número entero, atrapo el error y le imprimo el mensaje
                System.out.print("ERROR: El formato es incorrecto. Introduce un número entero que aparezca en la lista: ");
            }//Cierro el catch
            finally {
                scanner.nextLine(); // Como hago siempre con los números (int y double), limpio el Enter (Buffer)
            }
        }// Termino el "do"
        while (!indiceValido); // Si el índice es contrario al marcado al inicio continuo en el código.

        // Teniendo en cuenta que antes he adulterado la lista para que empiece en 1 y no en 0 (como hace JAVA), tengo que cambiar el valor introducido para que coincidan.
        //Podría haber seguido utilizando la variable numeroElegido, pero prefiero cambiarla para recordar el porqué del -1. Alternativa (numeroElegido = numeroElegido - 1;)
        int indiceJava = numeroElegido - 1; // Al número elegido por el usuario le quitas 1 (Si elige el 1 en la lista, corresponde con el 0 de JAVA)

        // Ahora voy a la Array de ingresos y extraigo la "carpeta" exacta del archivador asociado al ese índice con el getter
       Gasto gastoEliminado = listaGastos.get(indiceJava);

        // Muestro el importe antiguo como recordatorio visual, para que sepa que está modificando (podría imprimir todos los atributos que quisiera, solo debo usar los getter correspondientes.).
        System.out.println("Has seleccionado: " + gastoEliminado.obtenerDescripcion() + " con un importe de " + gastoEliminado.obtenerImporte() + " € ");
        //   Hago una concatenación con los getters: "public String obtenerDescripcion() {return descripcion; }" y "public double obtenerImporte() {return importe;} "

        // Finalmente, uso el Setter de las arraylist para eliminar el registro/índice/carpeta
        listaGastos.remove(indiceJava);

        //Muestro un mensaje de confirmación.
        System.out.println("Registro eliminado correctamente");
        System.out.println("========================================");
    }//Final para eliminar GASTOS

    //TODO:  ELECCIÓN DE CATEGORÍA en GASTOS

    //Creo el métudo con el tipo de variable que quier, en este caso no debe ser vacía, sino tipo String.
    private String seleccionarCategoria() {

        //Como en los casos anteriores le asigno un valor predeterminado a la opción que almacena la variable
        int opcionCategoria = -1;
        String categoriaElegida = ""; //Declaramos la variable antes del bucle para que sobreviva y no muera al terminarlo. Es la forma en la que el return deja de estar en rojo.
        boolean indiceValido = false; //Variable de control (Mi bandera)

        //Imprimo las opciones
        System.out.println("INTRODUCE LA CATEGORÍA");
        System.out.println("----------------------------------");
        System.out.println(" 1. ALIMENTACIÓN ");
        System.out.println(" 2. TRANSPORTE ");
        System.out.println(" 3. OCIO ");
        System.out.println(" 4. VIVIENDA ");
        System.out.println(" 5. SALUD ");
        System.out.println(" 6. OTROS ");
        System.out.print("Elige una opción (Del 1 al 6): ");

    //Entro en el bucle
        do {
            try {

                opcionCategoria = scanner.nextInt();

                switch (opcionCategoria) {

                    case 1:
                        categoriaElegida = "ALIMENTACIÓN";
                        indiceValido = true;
                        System.out.println("ALIMENTACIÓN");
                        break;
                    case 2:
                        categoriaElegida = "TRANSPORTE";
                        indiceValido = true;
                        System.out.println("TRANSPORTE");
                        break;
                    case 3:
                        categoriaElegida = "OCIO";
                        indiceValido = true;
                        System.out.println("OCIO");
                        break;
                    case 4:
                        categoriaElegida = "VIVIENDA";
                        indiceValido = true;
                        System.out.println("VIVIENDA");
                        break;
                    case 5:
                        categoriaElegida = "SALUD";
                        indiceValido = true;
                        System.out.println("SALUD");
                        break;
                    case 6:
                        categoriaElegida = "OTROS";
                        indiceValido = true;
                        System.out.println("OTROS");
                        break;
                    default: // Cualquier valor que no esté en la lista le informo de que vuelva a escribir
                        System.out.println("Opción no valida. Selecciona un número que esté en la lista: ");

                }//Final del switch
            }//Final del try
            catch (Exception e) { //Atrapo los errores e imprimo un mensaje para que vuelva a intentarlo
                System.out.print("ERROR: La opción elegida no es válida. Vuelve a intentarlo: ");
            }
            finally {
                scanner.nextLine(); // Limpio el buffer como siempre
            }
        }//Final del "do".
        while (!indiceValido); //Repetir el bucle hasta que la opción elegida sea válida.

        return categoriaElegida;
    }//Final de SELECCIÓN DE CATEGORÍA de GASTO


    //TODO:  ELECCIÓN DE MÉTODO DE PAGO en GASTOS

    //Creo el métudo con el tipo de variable que quiero, en este caso no debe ser vacía, sino tipo String, porque quiero que me devuelva variable con contenido.
    private String seleccionarMetodoPago() {

        //Como en los casos anteriores le asigno un valor predeterminado a la opción que almacena la variable
        int opcionMetodoPago = -1;
        String metodoElegido = ""; //Declaramos la variable antes del bucle para que sobreviva y no muera al terminarlo. Es la forma en la que el return deja de estar en rojo, asignándole algo a la variable
        boolean indiceValido = false; //Variable de control (Mi bandera)

        //Imprimo las opciones
        System.out.println("INTRODUCE LA FORMA DE PAGO");
        System.out.println("----------------------------------");
        System.out.println(" 1. EFECTIVO ");
        System.out.println(" 2. TARJETA ");
        System.out.println(" 3. TRANSFERENCIA ");
        System.out.println(" 4. BIZUM ");
        System.out.print("Elige una opción (Del 1 al 4): ");

        //Entro en el bucle
        do {
            try {

                opcionMetodoPago = scanner.nextInt();

                switch (opcionMetodoPago) {

                    case 1:
                        metodoElegido = "EFECTIVO";
                        indiceValido = true;
                        System.out.println("EFECTIVO");
                        break;
                    case 2:
                        metodoElegido = "TARJETA";
                        indiceValido = true;
                        System.out.println("TARJETA");
                        break;
                    case 3:
                        metodoElegido = "TRANSFERENCIA";
                        indiceValido = true;
                        System.out.println("TRANSFERENCIA");
                        break;
                    case 4:
                        metodoElegido = "BIZUM";
                        indiceValido = true;
                        System.out.println("BIZUM");
                        break;
                    default: // Cualquier valor que no esté en la lista le informo de que vuelva a escribir
                        System.out.print("Opción no valida. Selecciona un número que esté en la lista: ");

                }//Final del switch
            }//Final del try
            catch (Exception e) { //Atrapo los errores e imprimo un mensaje para que vuelva a intentarlo
                System.out.print("ERROR: La opción elegida no es válida. Vuelve a intentarlo: ");
            }
            finally {
                scanner.nextLine(); // Limpio el buffer como siempre
            }
        }//Final del "do".
        while (!indiceValido); //Repetir el bucle hasta que la opción elegida sea válida.

        return metodoElegido;
    }//Final de SELECCIÓN DE MÉTUDO DE PAGO de GASTO

    //TODO: Método para CAMPOS STRING OBLIGATORIOS: Creo un métudo por si en el futuro añado más campos tenerlo hecho directamente.

    private String textoObligatorio() {
        String textoObligatorio = ""; //declaramos la variable como campo de texto
        do {
            textoObligatorio = scanner.nextLine(); //leemos al usuario y lo guardamos en la variable
            if (textoObligatorio.length() == 0) { //Cuento los caracteres de lo que ha escrito el usuario. So el numero es igual a 0 se imprime el mensaje.
                System.out.print("ERROR: Cumplimenta el campo para continuar:  ");
            }//final if
        }//final do textoObligatorio.
        while (textoObligatorio.length() == 0); //Hago la comparación, mientras no haya carácteres repito el bucle.

        return textoObligatorio; //Si la longitud es superior a uno devuelvo el texto y lo guardo.
    }//FINAL Metudo CAMPOS STRING OBLIGATORIOS
} //Final HelloConsole

//Final del programa