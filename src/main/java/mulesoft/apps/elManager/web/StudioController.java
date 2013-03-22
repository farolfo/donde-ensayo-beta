package mulesoft.apps.elManager.web;

import javax.servlet.http.HttpSession;

import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.service.SocialNetworkService;
import mulesoft.apps.elManager.web.command.forms.CreateStudioForm;
import mulesoft.apps.elManager.web.command.forms.FindStudioForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudioController extends PlaceController{
	
	private static final String URL_SEARCH_VIEW = "studio/searchStudio";
	private static final String URL_DISPLAY_VIEW = "studio/studio";
	private static final String URL_CREATE_VIEW = "studio/createStudio";
	
	private static final String NAME_SEARCH_FORM = "findStudioForm";
	private static final String NAME_CREATE_FORM = "createStudioForm";

	@Autowired
	public StudioController(PlaceService ps, GeocodingService gs,
			SocialNetworkService sns) {
		super(ps, gs, sns);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView studio(HttpSession session) {
		return place(session, URL_SEARCH_VIEW, NAME_SEARCH_FORM, new FindStudioForm());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView create(HttpSession session) {
		return create(session, URL_CREATE_VIEW, NAME_CREATE_FORM, new CreateStudioForm());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView searchNextResults(HttpSession session, @RequestParam String nextResults) {
		return searchNextResults(session, nextResults, new FindStudioForm(), NAME_SEARCH_FORM, URL_SEARCH_VIEW, URL_DISPLAY_VIEW);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView studio(HttpSession session, FindStudioForm findStudioForm, Errors errors) {
		return place(session, findStudioForm, errors, URL_SEARCH_VIEW, URL_DISPLAY_VIEW);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(HttpSession session, CreateStudioForm createStudioForm, Errors errors) {
		return create(session, createStudioForm, errors, new FindStudioForm(), URL_CREATE_VIEW, URL_SEARCH_VIEW, NAME_SEARCH_FORM);
	}	
	
}
