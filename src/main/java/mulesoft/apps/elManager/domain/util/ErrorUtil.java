package mulesoft.apps.elManager.domain.util;

import java.util.List;

import org.springframework.validation.Errors;


public class ErrorUtil {

	public static void rejectAll(Errors errors, List<AppError> errorsList) {
		for(AppError e: errorsList) {
			errors.reject(e.getTranslationKey());
		}
	}
}
