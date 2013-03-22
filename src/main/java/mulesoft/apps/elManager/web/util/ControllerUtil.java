package mulesoft.apps.elManager.web.util;

import org.springframework.web.servlet.view.RedirectView;


public class ControllerUtil {

	private static final String CONTROLLER_URL_PREFIX = "/bin";
	
	public static RedirectView redirectView(String absoluteURL) {
		if (!absoluteURL.startsWith("/")) {
			System.out.println("jdklsajdsklaj");
		}
		return new RedirectView(CONTROLLER_URL_PREFIX + absoluteURL, true);
	}
	
}
