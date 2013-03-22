package mulesoft.apps.elManager.web;

import javax.servlet.http.HttpSession;

import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.service.SocialNetworkService;
import mulesoft.apps.elManager.web.command.forms.CreateRadioForm;
import mulesoft.apps.elManager.web.command.forms.FindRadioForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RadioController extends PlaceController{

	private static final String URL_SEARCH_VIEW = "radio/searchRadio";
	private static final String URL_DISPLAY_VIEW = "radio/radio";
	private static final String URL_CREATE_VIEW = "radio/createRadio";
	
	private static final String NAME_SEARCH_FORM = "findRadioForm";
	private static final String NAME_CREATE_FORM = "createRadioForm";
	
	@Autowired
	public RadioController(PlaceService ps, GeocodingService gs,
			SocialNetworkService sns) {
		super(ps, gs, sns);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView radio(HttpSession session) {
		return place(session, URL_SEARCH_VIEW, NAME_SEARCH_FORM, new FindRadioForm());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView create(HttpSession session) {
		return create(session, URL_CREATE_VIEW, NAME_CREATE_FORM, new CreateRadioForm());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView searchNextResults(HttpSession session, @RequestParam String nextResults) {
		return searchNextResults(session, nextResults, new FindRadioForm(), NAME_SEARCH_FORM, URL_SEARCH_VIEW, URL_DISPLAY_VIEW);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView radio(HttpSession session, FindRadioForm findRadioForm, Errors errors) {
		return place(session, findRadioForm, errors, URL_SEARCH_VIEW, URL_DISPLAY_VIEW);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(HttpSession session, CreateRadioForm createRadioForm, Errors errors) {
		return create(session, createRadioForm, errors, new FindRadioForm(), URL_CREATE_VIEW, URL_SEARCH_VIEW, NAME_SEARCH_FORM);
	}	
}
