# Fernanticket T6 🛠️

<img width="434" alt="image" src="https://user-images.githubusercontent.com/72271606/152536368-dfb400cb-b42d-429c-bc66-dabba16a4d7f.png">

Esta aplicación servirá para crear tickets de incidencias. Utilizando 3 tipos de usuarios, administrador,
técnico y usuario normal, con diferentes permisos.

## Instalación y ejecución 

Para poder utilizar esta aplicación, debemos abrir el contenido de FernanTicket - JAR, una vez abierto, encontraremos las clases y el .bat, un ejecutable.
Para abrir la aplicación simplemente haz doble click en el .bat.

## Autores

Hecho por Manuel Hermoso López y Antonio Juan González Izquierdo

## Soporte

Para poder contactar con soporte, tenemos habilitado el GMAIL de cada autor para cualquier duda sobre este proyecto.

## Estado del proyecto

El proyecto está completado hasta los conocimientos del TEMA 5 en Programación, irá mejorando a medida que aumenten los conocimientos en el grado.

## Actualización

Se han agregado funcionalidades:
 
	- Persistencia en toda la aplicación.
    - Uso de log.
    - Envío de log en formato EXCEL.
    - Nuevas opciones para el menú administrador.
    - Uso de fichero properties.


## Persistencia en objetos

Ahora en nuestra aplicación, cada cambio o registro de usuarios que hagamos, se guardará en una archivo local. No dependeremos solo de la memoria RAM.

Añadiendo persistencia en USUARIO, TECNICO y ADMIN.

<img width="101" alt="image" src="https://user-images.githubusercontent.com/72271606/164513194-cd8eefe3-f487-4624-8678-5fadcc26dcde.png">


## Fichero properties

Utilizaremos un fichero properties para la configuración de nuestra aplicación, este fichero guarda:

- El último inicio de sesión de cada usuario.
- Permitir acceso de invitado.

## Envio de TOKEN

Cuando creamos un usuario, se nos asigna un token que llegará por correo.

<img width="817" alt="image" src="https://user-images.githubusercontent.com/72271606/152529688-2b1ee5b8-e188-44fb-8352-cb31c44be917.png">

## Envio de actualizaciones a TELEGRAM

<img width="328" alt="image" src="https://user-images.githubusercontent.com/72271606/164517178-d7578c45-a311-4b68-92f8-b0aab2060f5b.png">

Se puede ver que se envia información de TOKEN de usuarios registrados, e información de incidencias.

## Inicio

En el inicio encontraremos el Inicio de sesión y el registro de un usuario normal. Para empezar, podemos logearnos con un usuario normal o crear uno nuevo.
Después, podremos salir del usuario que hemos iniciado sesión, en su respectivo menú.

### Registro

Para el registro, pulsamos en el menú principal, 2:

<img width="490" alt="image" src="https://user-images.githubusercontent.com/72271606/148749657-c8707957-da44-41b1-9545-60d22dcfbcba.png">

## Administrador

<img width="403" alt="image" src="https://user-images.githubusercontent.com/72271606/160387219-ee7b8076-d079-4942-9cfb-6f3833e936ba.png">

### Usuario: admin / Contraseña: admin

### Menú de admin:

1.- Consultar todas las inicencias abiertas.  
2.- Consultar todas las incidencias cerradas. 
3.- Consultar los técnicos.                   
4.- Consultar incidencias por término.        
5.- Asignar una incidencia a un técnico.      
6.- Dar de alta un técnico.                   
7.- Borrar un técnico.                        
8.- Consultar los usuarios.                   
9.- Estadísticas de la aplicación.            
10.- Cerrar sesión.
11.- Imprimir properties.
12.- Enviar incidencias pendientes, EXCEL.

El administrador puede: Eliminar y agregar técnicos, asignar incidencias a técnicos, consultar todas las incidencias,
consultar todos los usuarios y técnicos en la aplicación, apagar la aplicación.

#### Consultar todas las inicencias abiertas. 

Consultamos las incidencias que hay en este momento abiertas. De usuario y de técnico.

#### Consultar todas las incidencias cerradas. 

Consultamos las incidencias que hay en este momento cerradas. Solo muestra las de técnico.

#### Consultar los técnicos. 

Consultamos todos los técnicos que hay en el programa.

#### Consultar incidencias por término.

Consultamos las incidencias por las letras que introducimos.

#### Asignar una incidencia a un tecnico

Para asignar una incidencia, primero hay que crearla, luego entrar en Administrador, y después de asignarla
a un técnico, este técnico la arregla. Primero se elige la incidencia, y luego el técnico.

#### Dar de alta un técnico.

Se introduce un técnico nuevo, no tiene la misma ID que tienen los otros, al igual que el email al registralo, este no tiene TOKEN como en el registro de usuario.

#### Borrar un técnico.

Se borra un técnico, moviendo sus incidencias abiertas a otro. En el caso de que tenga una cerrada, no se puede eliminar.

#### Consultar todos los usuarios
Ver todos los usuarios:

<img width="172" alt="image" src="https://user-images.githubusercontent.com/72271606/148750688-79347093-da6f-46b0-af4e-331a0a77e77e.png">

#### Estadísticas de la aplicación.

Muestra información redundante de la aplicación.

#### Cerrar sesión
Para cerrar sesión:

<img width="486" alt="image" src="https://user-images.githubusercontent.com/72271606/148750918-59a996c1-768f-4bf1-bfd0-6c7176ea2cc7.png">

## Técnico

<img width="382" alt="image" src="https://user-images.githubusercontent.com/72271606/160387152-160e6fb8-3759-4860-a0b3-4a51f2edcfcb.png">

### Usuario segundo técnico: tech / Contraseña: tech

La función de técnico se basa en resolver los tickets generados por los usuarios y asignados por el administrador.

Este es el menu de técnico:

  1. Consultar incidencias asignadas
  2. Marcar incidencia como cerrada
  3. Consultar incidencias que he resuelto
  4. Mostrar mi perfil
  5. Cambiar clave de acceso
  6. Cerrar sesión

#### Consultar las incidencias que tengo asignadas

Si queremos esta opción, pulsamos 1. En este caso, encontraremos la incidencia RESUELTA del usuario
normal USER.

#### Marcar una incidencia como cerrada

Para marcar una incidencia como cerrada, en la opción 2. Aquí seleccionamos la incidencia que no esté
resuelta aún, asignada previamente por el administrador. Una vez la incidencia sea resuelta, aparecerá
como RESUELTA.


#### Consultar las incidencias que he resuelto

Comprobamos las que ha resuelto:

<img width="405" alt="image" src="https://user-images.githubusercontent.com/72271606/148750247-bff49663-bffc-4f82-85fc-5a6c5231c6c2.png">

#### Mostrar mi perfil

Mostrar el perfil de Técnico.

<img width="443" alt="image" src="https://user-images.githubusercontent.com/72271606/148750311-eea6486c-23b3-4da4-bf73-ec0fdd0a3999.png">

#### Cambiar clave de acceso

Cambiar clave de acceso en técnico.

<img width="445" alt="image" src="https://user-images.githubusercontent.com/72271606/148750362-cabd6c22-86da-4ad7-a5f0-82b00b890188.png">

#### Cerrar sesión

Cerrar sesión.

<img width="489" alt="image" src="https://user-images.githubusercontent.com/72271606/148750410-66e32472-6f27-49ec-a670-5a409d21ca5e.png">


## Usuario Normal

<img width="304" alt="image" src="https://user-images.githubusercontent.com/72271606/160387100-ac8a5477-8452-4608-a3d2-8c68a050b879.png">

### Usuario: user / Contraseña: user

  Se pueden dar de alta en la aplicación ellos mismos, pueden crear tickets de incidencias,
  tambien pueden consultar el estado de las incidencias que ellos mismos han creado.
  
  Este es el menú de Usuario:
  
  1. Registrar nueva incidencia
  2. Consultar incidencias abiertas
  3. Consultar incidencias cerradas
  4. Mostrar mi perfil
  5. Cambiar contraseña
  6. Cerrar sesión

#### Registrar nueva incidencia

Para poder registrar una incidencia en el usuario USER, hacemos lo siguiente, completando toda la
información que nos pide:

<img width="462" alt="image" src="https://user-images.githubusercontent.com/72271606/148749779-087c97db-37d9-4cc3-af5e-6554ed61f2e6.png">

#### Consultar incidencias abiertas

Para la consulta de incidencias abiertas, pulsamos la opción 2. En este caso nos aparecerá la incidencia 1,
es la que hemos agregado anteriormente.

<img width="446" alt="image" src="https://user-images.githubusercontent.com/72271606/148749849-df71b4af-9c67-4b48-8de1-b6eecf30c5fe.png">

Si resolvemos la incidencia, nos encontraremos esto:

<img width="376" alt="image" src="https://user-images.githubusercontent.com/72271606/148749878-a72f4a57-01e0-4461-9c59-e01490868d16.png">

#### Consultar incidencia cerrada

En este caso, hemos hecho que un técnico nos cierre la incidencia, para que podamos visualizar si se ha
completado.

#### Mostrar mi perfil

Si pulsamos la opción 4, encontraremos los datos de nuestro perfil actual:

<img width="401" alt="image" src="https://user-images.githubusercontent.com/72271606/148749947-fad26d67-4753-4091-8a86-a344717f3523.png">

#### Cambio de contraseña

Para cambiar la contraseña, elegimos la opción 5, repitiendo la contraseña para que funcione
correctamente.

<img width="275" alt="image" src="https://user-images.githubusercontent.com/72271606/148749999-45b6b476-e3d1-4151-8e86-379b66df116c.png">

#### Cerrar sesión

A la hora de cerrar sesión, tenemos que elegir la opción 6.

<img width="484" alt="image" src="https://user-images.githubusercontent.com/72271606/148750055-724ff16a-4090-4d51-8391-a8c7615b550c.png">
