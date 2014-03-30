package controllers;

import java.util.List;

import models.ItemLocation;
import models.service.SearchService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * REST API for StoreAssist backend.
 * 
 * @author excelsior
 *
 */
public class SearchController extends Controller {
	
	public static Result index()
	{
		return ok("Welcome to StoreAssist ..");
	}
	
	/**
	 * Returns the location of an item within a store.
	 */
	public static Result findItemLocation(String storeIdentifier, String item)
	{
		List<ItemLocation> itemLocations = SearchService.searchItemLocation(storeIdentifier, item);
		return ok(Json.toJson(itemLocations));
	}
	
	/**
	 * Returns the identifier of a store
	 */
	public static Result findStore(String storeIdentifier)
	{
		return ok();
	}
}
