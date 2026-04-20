[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/MSM_4GjI)


# PROCESO DE CREACIÓN de la APP 
**Gabriel de Luque**
## Aunque explico aquí LOS AVANCES, voy realizando anotaciones en el código para ir explicando el proceso. En las partes de código que se repitan en otro método (bucles, por ejemplo), dejo los comentarios para que mire donde mire pueda entender todo el proceso. Son comentarios de cara al estudio antes del examen, me gusta explicar cada línea de código de cara al estudio y la interiorizacióción de los contenidos.

---

### Tareas 1 y 2  (Gestión) :
- He creado la carpeta model para crear dentro dos clases separadas del HelloConsole, deparando la lógica de negocio como la profe pide en las recomendaciones.

- Creación de clase (Gasto). Crear sus atributos, forma en la que se solicitan y guardan los datos que introducimos, forma en la que se consultan y se modifican los valores. **Hecho!**
-- Importarlo a la consola. **Hecho!**  
-- Submenú para modificar, eliminar y listar gastos. **HECHO!**  
-- Cqampos obligatorios: **HECHO!** 

- Creación de clase (Ingreso). Crear sus atributos, forma en la que se solicitan y guardan los datos que introducimos, forma en la que se consultan y se modifican los valores. **Hecho!**
-- Importarlo a la consola. **Hecho!**  
-- Submenú para modificar, eliminar y listar ingresos. **HECHO!**  
-- Campos obligatorios: **HECHO!**   
    
---
  
### Tarea 3 (Resumen Financiero)  El trabajo pide dos cosas:  

- Saldo total (suma ingresos - suma gastos). **¡Hecho!**   

- Total de gastos por categoría: Aún no lo he hecho. **HECHO!**  

---

### Tarea 4 (Menú principal): 

- Menú principal configurado. Cumple con la estructura pedida y el uso del do-while. **¡Hecho!** 

---
  
### Tarea 5 (Plan de Pruebas): 

- Diseñado, pero no ejecutado. Lo haré al finalizar el programa**HECHO!**  

---

### Recomendaciones:

- Bucle do-while en el menú principal hasta que el usuario seleccione salir de la app. **HECHO!**  

 --Bucle de los submenús: **HECHO!** 

-  PARA GASTOS:  
       1. Añadir gasto.  **HECHO!** 
       2. Modificar gasto **HECHO!** 
       3. Eliminar gasto **HECHO!** 
       4. Listar gastos **HECHO!** 
       5. Volver.  **HECHO!** 

  - PARA INGRESOS:  
        1. Añadir ingreso.  **HECHO!**   
        2. Modificar ingreso **HECHO!**   
        3. Eliminar ingreso **HECHO!**   
        4. Listar ingreso **HECHO!**   
        5. Volver.   **HECHO!**  
       
- Separación de Lógica y Modelo:  
-- He separado la lógica de negocio en la clase main y clase modelo. He usado los paquetes console y console.model, teniendo mis clases Gasto e Ingreso encapsuladas (atributos privados, Getters y Setters). **HECHO!**  
-- El funcionamiento de la app lo he desarrollado en HelloConsole. **HECHO!**

- Validaciones mínimas obligatorias:  
-- Validación de fechas "a medias" porque se cierra el programa al equivocarme en un tipo de dato.  **HECHO!** He creado un método para ello antes de cerrar HelloConsole y lo he incorporado a los métodos de gasto e ingreso.
-- No permitir importes negativos. **HECHO!**  He creado un método para ello antes de cerrar HelloConsole y lo he incorporado a los métodos de gasto e ingreso.  
-- Gestión de errores por consola (los try-catch para que no se cierre). **HECHO!**  A través de los escudos. Aplicado en menú principal y todos los menús de lista seleccionable, así como en validación de fechas e introducción de números. 
-- Todos los campos son obligatorios, excepto la descripción. **HECHO!**  

---

  ### Funcionalidades Extra:
  - Calcular gasto medio mensual: **HECHO!** 
  - Listar los gastos o ingresos ordenados por fecha (de más reciente a más antiguo): **HECHO!**
  - Mostrar la categoría más costosa: **HECHO!**  
  - Filtrar gastos por rango de fechas: **No hecho**  
  - Mostrar estadísticas adicionales: **No hecho**

La aplicación base funciona correctamente, he creado la estructura básica con todos los submenús. 
Para poder conseguir listar por categoría, voy a crear un nuevo loop a través del switch para que la elección sea cerrada. Esto evitará diferencias entre mayúsculas, minúsculas, etc.. que para JAVA son diferentes. Esto haría imposible que se agruparan. Implica cambiar las forma de solicitar la ese atributo.
Realizada la obligatoriedad en el campo de gastos.
Realizado el escudo de protección ante errores try-catch en el menú principal
Se han realizado todas las funcionalidades requeridas por el trabajo. Explicación realizada sobre el código.
Se procede a realizar el plan de pruebas con éxito.
Se procede a implementar las funcionalidades extra. (Implementados con éxito Ingresos y gastos ordenados por fecha / Categoría más costosa / Gasto medio mensual)
Trabajo finalizado con éxito.
