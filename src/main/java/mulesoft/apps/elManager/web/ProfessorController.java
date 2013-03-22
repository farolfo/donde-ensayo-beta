package mulesoft.apps.elManager.web;

import javax.servlet.http.HttpSession;

import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.service.SocialNetworkService;
import mulesoft.apps.elManager.web.command.forms.CreateProfessorForm;
import mulesoft.apps.elManager.web.command.forms.FindProfessorForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfessorController extends PlaceController{

	private static final String URL_SEARCH_VIEW = "professor/searchProfessor";
	private static final String URL_DISPLAY_VIEW = "professor/professor";
	private static final String URL_CREATE_VIEW = "professor/createProfessor";
	
	private static final String NAME_SEARCH_FORM = "findProfessorForm";
	private static final String NAME_CREATE_FORM = "createProfessorForm";
	
	@Autowired
	public ProfessorController(PlaceService ps, GeocodingService gs,
			SocialNetworkService sns) {
		super(ps, gs, sns);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView professor(HttpSession session) {
		return place(session, URL_SEARCH_VIEW, NAME_SEARCH_FORM, new FindProfessorForm());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView create(HttpSession session) {
		return create(session, URL_CREATE_VIEW, NAME_CREATE_FORM, new CreateProfessorForm());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView searchNextResults(HttpSession session, @RequestParam String nextResults) {
		return searchNextResults(session, nextResults, new FindProfessorForm(), NAME_SEARCH_FORM, URL_SEARCH_VIEW, URL_DISPLAY_VIEW);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView professor(HttpSession session, FindProfessorForm findProfessorForm, Errors errors) {
		return place(session, findProfessorForm, errors, URL_SEARCH_VIEW, URL_DISPLAY_VIEW);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(HttpSession session, CreateProfessorForm createProfessorForm, Errors errors) {
		return create(session, createProfessorForm, errors, new FindProfessorForm(), URL_CREATE_VIEW, URL_SEARCH_VIEW, NAME_SEARCH_FORM);
	}	

}
