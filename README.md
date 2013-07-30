donde ensayo? 
=============
--------> http://www.donde-ensayo.com.ar/

Rese�a del proyecto
-----------------------------------------------------------------------------------

  'donde-ensayo' es una aplicaci�n web que brinda los servicios que toda banda
"de garage" necesita hoy en dia: buscar salas de ensayo, estudios de grabaci�n,
lugares para tocar, emisoras de radio por la zona y profesores de instrumentos.
As�, con solo ingresar una ubicaci�n y un radio de cuadras a cubrir, adem�s de
los datos necesarios para cada tipo de b�squeda, mostrar� un resumen de los 
resultados, con la opci�n de ver en detalle cada uno y un mapa de donde se 
encuentra.

  Para obtener los datos necesarios nos valemos de la API de GooglePlaces, la 
cual requiere datos tales como 'longitud' y 'latitud' para trabajar. Para ello,
como no es 'user friendly' pedir al usuario sus coordenadas, �stas se obtienen
por medio de la API de GoogleGeocoding.
  Adem�s, en el detalle se puede mostrar el website del resultado a mostrar, si
es que lo tiene. De tratarce este website de una p�gina de Facebook, se implement�
un peque�o mensaje al costado de dicha web que brinda la cantidad de 'Likes' que
tiene dicha p�gina, para dar as� al usuario una manera de calificar ese resultado,
vali�ndoce de la API de Facebook.

  Adem�s, en el caso de no encontrarce un resultado conocido en las b�squedas,
se implement� la opci�n de agreagar el contenido faltante a la base de datos
consultada.

  Nota: Con el fin de testear el detalle de los 'Likes' de Facebook, se implement�
lo siguiente: Si un resultado no contiene un website se le agregar�, en el 50% de las
veces, un link de la p�gina de Facebook de 'MuleSoft'.



Tecnolog�as utilizadas
-----------------------------------------------------------------------------------
	
  �sta aplicaci�n fue desarrollada con Spring MVC, archivos JSTL y Maven, en Eclipse.
Las pruebas fueron realizadas en un servidor Jetty y se hizo su correspondiente
deploy en un servidor Tomcat, el cual est� adem�s hosteado en Heroku:

    http://elmanager.herokuapp.com  (ver la 'Nota' I y II en la secci�n 'Ejecuci�n')

  Para la carga de los 'detalles' y el mapa se utiliz� AJAX para no cambiar de
p�gina.

  Tambi�n se utilizaron widgets y efectos de jQuery a lo largo del desarrollo,
por ejemplo, en los campos de los formularios en los que solo son v�lidos n�mero,
se utiliz� la funci�n .numeric() para restringir el ingreso de otro tipo de 
caracteres, o en el despliegue de los 'detalles' se utilizaron .fadeIn() y 
.fadeOut(). 

  En cuanto al estilo del sitio, se utiliz� la librer�a de css de Twitter: Bootstrap.



Ejecuci�n
-----------------------------------------------------------------------------------

  Corra los siguientes comandos desde el directorio raiz, donde se encuentra el 
archivo pom.xml :

	$ mvn clean
	$ mvn package
	$ java -jar target/dependency/webapp-runner.jar target/*.war

  Y visite http://localhost:8080.

  Otra opci�n an�loga es visitar la aplicaci�n hosteada en Heroku:

	http://elmanager.herokuapp.com


  Nota I: En el servidor Tomcat las palabras acentuadas no son v�lidas en los
formularios, tanto de b�squeda como de agreagado. Si se ingresan, se obviaran
como caracteres inv�lidos. �sto se puede deber a un error en la codificaci�n.
As� mismo, en los resultados no se muestran caracteres acentuados. En el
servidor Jetty ejecutado desde Eclipse este problema no se encuentra.

  Nota II: Tanto la GoogleMaps v3. API como la GooglePlaces API requieren de
una KEY para su uso. �sta fue pedida y solo permite:
   
	> 1000 accesos/d�a a la GooglePlacesAPI
	> 25000 accesos/d�a a la GoogleMaps v3. API

  En caso de no funcionar la aplicaci�n debido a que se halla exscedido la cuota
otorgada, se encuentr� comentado en el c�digo otra KEY obtendia con otro mail.
Solo se debe reemplazar la anterior y recompilar con 'mvn clean' y 'mvn package'.
  
  Por si acaso, las claves se deben cambiar en:
	>'WEB-INF/jsp/head.jsp':  En el scritp de inclusi�n de la librer�a de GoogleMaps.
	>'mulesoft.apps.elManager.domain.service.impl.GooglePlacesAPIService': En la 
definici�n del string 'KEY'.
