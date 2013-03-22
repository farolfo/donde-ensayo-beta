package mulesoft.apps.elManager.web;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import mulesoft.apps.elManager.domain.model.Place;
import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.service.SocialNetworkService;
import mulesoft.apps.elManager.domain.util.NoSocialNetworkSiteException;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;
import mulesoft.apps.elManager.web.command.forms.CreatePlaceForm;
import mulesoft.apps.elManager.web.command.forms.FindNextResultsForm;
import mulesoft.apps.elManager.web.command.forms.PlaceForm;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public abstract class PlaceController{

	protected PlaceService placeService;
	protected SocialNetworkService socialNetworkService;
	protected GeocodingService geocodingService;
	
	
	public PlaceController(PlaceService ps, GeocodingService gs, SocialNetworkService sns) {
		this.placeService = ps;
		this.geocodingService = gs;
		this.socialNetworkService = sns;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView details(HttpSession session, @RequestParam String reference, @RequestParam Integer index) {
		ModelAndView mav = new ModelAndView();
		Place place;
		try {
			place = placeService.details(reference);
		} catch (QuotaExcededException e1) {
			mav.setViewName("utils/errorDetailsQuota");
			return mav;
		}
		if(place == null){
			place = new Place(reference, null, null, "", "", "");
		}else{
			try{
				place.setFacebookLikes(socialNetworkService.getLikes(place.getWebsite()));
			}catch(NoSocialNetworkSiteException e){
				place.setFacebookLikes(null);
			} catch (QuotaExcededException e) {
				place.setFacebookLikes(null);
			}
		}
		mav.addObject("hasFacebookLikes", new Boolean(place.getFacebookLikes() != null));
		mav.addObject("index", index);
		mav.addObject("placeToShow", place);
		mav.setViewName("utils/details");
		return mav;
	}
	
	public ModelAndView create(HttpSession session, CreatePlaceForm createPlaceForm, Errors errors, PlaceForm findPlaceForm, String urlCreateView, String urlSearchView, String nameFindForm) {
		ModelAndView mav = new ModelAndView();
		try {
			if(createPlaceForm.create(geocodingService, placeService, errors)){
				mav.addObject(nameFindForm, findPlaceForm);
				mav.addObject("succesOnCreating", new Boolean(true));
				mav.addObject("problemWhileSearching", new Boolean(false));
				mav.addObject("quotaExceded", new Boolean(false));
				mav.setViewName(urlSearchView);
				return mav;			
			}
		} catch (QuotaExcededException e) {
			mav.setViewName(urlCreateView);
			mav.addObject("quotaExceded", new Boolean(true));
		}
		mav.setViewName(urlCreateView);
		mav.addObject("quotaExceded", new Boolean(false));
		return mav;
	}
	
	public ModelAndView place(HttpSession session, PlaceForm findPlaceForm, Errors errors, String urlSearchView, String urlDisplayPlaces) {
		ModelAndView mav = new ModelAndView();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		ArrayList<Place> places = null;
		try {
			places = findPlaceForm.search(geocodingService, placeService, errors, sb);
		} catch (QuotaExcededException e) {
			mav.addObject("quotaExceded", new Boolean(true));
			mav.setViewName(urlSearchView);
			mav.addObject("succesOnCreating", new Boolean(false));
			mav.addObject("problemWhileSearching", new Boolean(true));
			return mav;
		}
		if(places != null){
			String nextPages = sb.toString();
			if(!nextPages.equals("")){
                mav.addObject("moreResultsPending", new Boolean(true));
                mav.addObject("nextResults", nextPages);
			}else{
				mav.addObject("moreResultsPending", new Boolean(false));
                mav.addObject("nextResults", "");
			}
			mav.addObject("searchResultPlaces", places);
			mav.setViewName(urlDisplayPlaces);
			return mav;			
		}
		mav.addObject("problemWhileSearching", new Boolean(false));
		mav.addObject("succesOnCreating", new Boolean(false));
		mav.addObject("quotaExceded", new Boolean(false));
		mav.setViewName(urlSearchView);
		return mav;
	}
	
	public ModelAndView searchNextResults(HttpSession session, String nextResults, PlaceForm findPlaceForm, String nameSearchForm, String urlSearchView, String urlDisplayView) {
		ModelAndView mav = new ModelAndView();
		StringBuilder sb = new StringBuilder();
		sb.append(nextResults);
		FindNextResultsForm form = new FindNextResultsForm(sb);
		ArrayList<Place> places = null;
		try {
			places = form.search(placeService);
		} catch (QuotaExcededException e) {
			mav.addObject(nameSearchForm, findPlaceForm);
			mav.addObject("quotaExceded", new Boolean(true));
			mav.setViewName(urlSearchView);
			mav.addObject("succesOnCreating", new Boolean(false));
			mav.addObject("problemWhileSearching", new Boolean(true));
			return mav;
		}
		if(places != null){
			nextResults = sb.toString();
			if(!nextResults.equals("")){
                mav.addObject("moreResultsPending", new Boolean(true));
                mav.addObject("nextResults", nextResults);
			}else{
				mav.addObject("moreResultsPending", new Boolean(false));
                mav.addObject("nextResults", "");
			}
			mav.addObject("searchResultPlaces", places);
			mav.setViewName(urlDisplayView);
			return mav;			
		}
		mav.addObject(nameSearchForm, findPlaceForm);
		mav.addObject("quotaExceded", new Boolean(false));
		mav.setViewName(urlSearchView);
		mav.addObject("succesOnCreating", new Boolean(false));
		mav.addObject("problemWhileSearching", new Boolean(true));
		return mav;
	}
	
	public ModelAndView create(HttpSession session, String urlCreateView, String nameCreateForm, CreatePlaceForm createPlaceForm) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("quotaExceded", new Boolean(false));
		mav.setViewName(urlCreateView);
		mav.addObject(nameCreateForm, createPlaceForm);
		return mav;
	}
	
	public ModelAndView place(HttpSession session, String urlSearchView, String nameSearchForm, PlaceForm findPlaceForm) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(urlSearchView);
		mav.addObject("quotaExceded", new Boolean(false));
		mav.addObject("problemWhileSearching", new Boolean(false));
		mav.addObject("succesOnCreating", new Boolean(false));
		mav.addObject(nameSearchForm, findPlaceForm);
		return mav;
	}
	
}
