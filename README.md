Reseña del proyecto
-----------------------------------------------------------------------------------

  'donde-ensayo' es una aplicación web que brinda los servicios que toda banda
"de garage" necesita hoy en dia: buscar salas de ensayo, estudios de grabación,
lugares para tocar, emisoras de radio por la zona y profesores de instrumentos.
Así, con solo ingresar una ubicación y un radio de cuadras a cubrir, además de
los datos necesarios para cada tipo de búsqueda, mostrará un resumen de los 
resultados, con la opción de ver en detalle cada uno y un mapa de donde se 
encuentra.

  Para obtener los datos necesarios nos valemos de la API de GooglePlaces, la 
cual requiere datos tales como 'longitud' y 'latitud' para trabajar. Para ello,
como no es 'user friendly' pedir al usuario sus coordenadas, éstas se obtienen
por medio de la API de GoogleGeocoding.
  Además, en el detalle se puede mostrar el website del resultado a mostrar, si
es que lo tiene. De tratarce este website de una página de Facebook, se implementó
un pequeño mensaje al costado de dicha web que brinda la cantidad de 'Likes' que
tiene dicha página, para dar así al usuario una manera de calificar ese resultado,
valiéndoce de la API de Facebook.

  Además, en el caso de no encontrarce un resultado conocido en las búsquedas,
se implementó la opción de agreagar el contenido faltante a la base de datos
consultada.

  Nota: Con el fin de testear el detalle de los 'Likes' de Facebook, se implementó
lo siguiente: Si un resultado no contiene un website se le agregará, en el 50% de las
veces, un link de la página de Facebook de 'MuleSoft'.



Tecnologías utilizadas
-----------------------------------------------------------------------------------
	
  Ésta aplicación fue desarrollada con Spring MVC, archivos JSTL y Maven, en Eclipse.
Las pruebas fueron realizadas en un servidor Jetty y se hizo su correspondiente
deploy en un servidor Tomcat, el cual está además hosteado en Heroku:

    http://elmanager.herokuapp.com  (ver la 'Nota' I y II en la sección 'Ejecución')

  Para la carga de los 'detalles' y el mapa se utilizó AJAX para no cambiar de
página.

  También se utilizaron widgets y efectos de jQuery a lo largo del desarrollo,
por ejemplo, en los campos de los formularios en los que solo son válidos número,
se utilizó la función .numeric() para restringir el ingreso de otro tipo de 
caracteres, o en el despliegue de los 'detalles' se utilizaron .fadeIn() y 
.fadeOut(). 

  En cuanto al estilo del sitio, se utilizó la librería de css de Twitter: Bootstrap.



Ejecución
-----------------------------------------------------------------------------------

  Corra los siguientes comandos desde el directorio raiz, donde se encuentra el 
archivo pom.xml :

	$ mvn clean
	$ mvn package
	$ java -jar target/dependency/webapp-runner.jar target/*.war

  Y visite http://localhost:8080.

  Otra opción análoga es visitar la aplicación hosteada en Heroku:

	http://elmanager.herokuapp.com


  Nota I: En el servidor Tomcat las palabras acentuadas no son válidas en los
formularios, tanto de búsqueda como de agreagado. Si se ingresan, se obviaran
como caracteres inválidos. Ésto se puede deber a un error en la codificación.
Así mismo, en los resultados no se muestran caracteres acentuados. En el
servidor Jetty ejecutado desde Eclipse este problema no se encuentra.

  Nota II: Tanto la GoogleMaps v3. API como la GooglePlaces API requieren de
una KEY para su uso. Ésta fue pedida y solo permite:
   
	> 1000 accesos/día a la GooglePlacesAPI
	> 25000 accesos/día a la GoogleMaps v3. API

  En caso de no funcionar la aplicación debido a que se halla exscedido la cuota
otorgada, se encuentrá comentado en el código otra KEY obtendia con otro mail.
Solo se debe reemplazar la anterior y recompilar con 'mvn clean' y 'mvn package'.
  
  Por si acaso, las claves se deben cambiar en:
	>'WEB-INF/jsp/head.jsp':  En el scritp de inclusión de la librería de GoogleMaps.
	>'mulesoft.apps.elManager.domain.service.impl.GooglePlacesAPIService': En la 
definición del string 'KEY'.
