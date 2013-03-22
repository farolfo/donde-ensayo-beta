package mulesoft.apps.elManager.web;

import javax.servlet.http.HttpSession;

import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.service.SocialNetworkService;
import mulesoft.apps.elManager.web.command.forms.CreatePlaceToPlayForm;
import mulesoft.apps.elManager.web.command.forms.FindPlaceToPlayForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlacetoplayController extends PlaceController{

	private static final String URL_SEARCH_VIEW = "placetoplay/searchPlacetoplay";
	private static final String URL_DISPLAY_VIEW = "placetoplay/placetoplay";
	private static final String URL_CREATE_VIEW = "placetoplay/createPlacetoplay";
	
	private static final String NAME_SEARCH_FORM = "findPlaceToPlayForm";
	private static final String NAME_CREATE_FORM = "createPlaceToPlayForm";
	
	@Autowired
	public PlacetoplayController(PlaceService ps, GeocodingService gs,
			SocialNetworkService sns) {
		super(ps, gs, sns);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView placetoplay(HttpSession session) {
		return place(session, URL_SEARCH_VIEW, NAME_SEARCH_FORM, new FindPlaceToPlayForm());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView create(HttpSession session) {
		return create(session, URL_CREATE_VIEW, NAME_CREATE_FORM, new CreatePlaceToPlayForm());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView searchNextResults(HttpSession session, @RequestParam String nextResults) {
		return searchNextResults(session, nextResults, new FindPlaceToPlayForm(), NAME_SEARCH_FORM, URL_SEARCH_VIEW, URL_DISPLAY_VIEW);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView placetoplay(HttpSession session, FindPlaceToPlayForm findPlacetoplayForm, Errors errors) {
		return place(session, findPlacetoplayForm, errors, URL_SEARCH_VIEW, URL_DISPLAY_VIEW);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(HttpSession session, CreatePlaceToPlayForm createPlacetoplayForm, Errors errors) {
		return create(session, createPlacetoplayForm, errors, new FindPlaceToPlayForm(), URL_CREATE_VIEW, URL_SEARCH_VIEW, NAME_SEARCH_FORM);
	}	

}
