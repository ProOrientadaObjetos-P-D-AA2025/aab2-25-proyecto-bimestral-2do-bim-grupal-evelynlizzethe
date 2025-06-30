# Trabajo final del 2do.Bimestre.

Este trabajo final se lo realizará de forma grupal aplicando los R.A. obtenidos en el transcurso del bimestre. Grupos de máximo 2 personas con el fin de garantizar los RA por integrante. 

## Tema:

Gestión de telefonía móvil estudiantil: Mov-UTPL.

## Objetivo:

Aplicación los pilares de la POO: herencia y polimorfismo, generando resultados desde/hacia DB.

## Problemática

La carrera de Telecomunicación-UTPL a impulsado un proyecto de comunicación móvil para la comunidad Universitaria, por cuanto dispone de toda la infraestructura tecnológica, pero se encuentra detenida por que requiere de un Sistema de Gestión de sus clientes y facturación, con ciertos planes celulares según la siguiente información: 

### Datos básicos del cliente:
> Conformado por sus nombres, pasaporte/cédula, ciudad, marca, modelo y numero de celular, pago mensual. (Importante: Ud. debe agregar al menos 2 atributos adicionales)
> 

### Tipos de planes móviles: 

>Los planes se caracterizan según su clasificación de la siguiente forma: 
>
> 1. ### PlanPostPagoMinutosMegasEconomico
> Quienes deben almacenar la siguiente información: minutos, costo minutos, megas expresados en gigas, costo por cada gigas, porcentaje de descuento.
>
> 2. ### PlanPostPagoMinutos
> Con los siguientes datos: minutos nacionales, costo minuto nacional, minutos internacionales, costo minuto internacional. 
>
> 3. ### PlanPostPagoMegas
> La información para este tipo de plan es: megas expresados en gigas, costo por cada giga, tarifa base.
>
> 4. ### PlanPostPagoMinutosMegas
> Con datos como: minutos, costo minutos, megas expresados en gigas, costo por cada giga.


## Tareas a realizar:

En base a los lineamientos generales, Ud. debe realizar el análisis, diseño, codificación, implementación y pruebas del "Sistema de Gestión de clientes y facturación Mov-UTPL. Para lo cual las actividades a considerar son:

- Facilitar las operaciones CRUD de los clientes de la telefónica. 
- Generación de las facturas por cliente/plan, en base a los lineamientos anteriores. 
- Considerar que los clientes/estudiantes pueden disponer de no más de 2 tipos de planes. 
- Recuerde que el motor de DB debe ser SQLite, para todo el sistema (Clientes, planes, facturas, etc.)
- Las operaciones CRUD deben facilitar la gestión de clientes y/o planes. 
- No olvide que es imprescindible antes de la codificación/pruebas, documentar de forma oportuna su análisis y diseño en UML, aplicando de forma oportuna y eficiente la herencia y el polimorfismo.
- Se debe aplicar la arquitectura MVC, considerando sus principios de diseño. 
- En su repositorio GIT coloque cada entregable según la carpeta creada para el fin. 
- Considere las criterios de evaluación en la "RUBRICA DE EVALUACIÓN PARA PROYECTOS DE FIN DE BIMESTRE" adjunta en su EVA.

## Entregables:

En cada una de las carpetas de este repositorio GIT Ud. debe colocar los productos según las siguientes instrucciones: 

1. MODELADO: diagramas UML haciendo uso de DiaUML, o StarUML. Adjunte los archivos fuente .dia/.mdj así como la exportación/captura de la img .png, .jpg, o equivalentes. 
2. SOLUCION: Proyecto NetBeans/IntelliJ aplicando la arquitectura MVC. 
3. INFORME: Un documento con la siguiente estructura/información:
	- Caratula: con los datos universitarios del grupo de trabajo. 
	- Análisis: de la solución con tablas, figuras, esquemas, etc. que evidencien un pre-procesamiento del problema. 
	- Diseño: diagramas UML bien documentados. 
	- Codificación: Fragmentos de código relevantes y documentados, con la URL Git referenciada. 
	- Resultados: Capturas de la información de entrada/salida del sistema que evidencien su funcionamiento.

