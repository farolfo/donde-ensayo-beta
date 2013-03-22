package mulesoft.apps.elManager.domain.util;

public class StringCommons {

	
	/***
	 * Reemplaza cada letra acentuada por su equivalente sin acentuar.
	 * Retorna el string cambiado, MODIFICA el str de argumento
	 * DEPRECATED para el uso de la aplicacion en Heroku
	 * @param str
	 * @return
	 */
	public static String decodeAccents(String str){
		str = str.replace("á", "a");
		str = str.replace("é", "e");
		str = str.replace("í", "i");
		str = str.replace("ó", "o");
		str = str.replace("ú", "u");
		str = str.replace("Á", "A");
		str = str.replace("É", "E");
		str = str.replace("Í", "I");
		str = str.replace("Ó", "O");
		str = str.replace("Ú", "U");
		return str;
	}
}
