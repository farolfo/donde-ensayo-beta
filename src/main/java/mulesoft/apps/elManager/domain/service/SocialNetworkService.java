package mulesoft.apps.elManager.domain.service;

import mulesoft.apps.elManager.domain.util.NoSocialNetworkSiteException;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

public interface SocialNetworkService {

	/***
	 * Retorna el numero de likes de ese sitio
	 * throws NoSocialNetworkSiteExeption si no es un sitio de esa red social.
	 * @param website
	 * @return
	 */
	Integer getLikes(String website) throws NoSocialNetworkSiteException, QuotaExcededException;
	
}
