package mulesoft.apps.elManager.domain.service;

import java.util.ArrayList;

import mulesoft.apps.elManager.domain.model.Place;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

public interface PlaceService {

	/***
	 * Retorna los Places correspondientes al keysword, desde esa lat y long con radius metros de distancia.
	 * Se debe pasar un strng vacio en nextResults para hacer una primera busqueda.
	 * Retorna 20 resutlados como maximo y, de haber mas resultados pendientes, setea un string que referencia a dichos datos.
	 * @param keywords
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @param moreResultsPending
	 * @return
	 */
	ArrayList<Place> search(String keywords, double latitude, double longitude, int radius, StringBuilder nextResults) throws QuotaExcededException;
	
	/**
	 * Retorna el place de el reference indicado, con todos los detalles
	 * @param place
	 * @return
	 */
	Place details(String reference) throws QuotaExcededException;
	
	boolean create(double latitude, double longitude, String name, String fomratted_address, String telephone, String website) throws QuotaExcededException;

}
