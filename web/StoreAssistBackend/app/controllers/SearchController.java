package controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;

import models.ItemLocation;
import models.Store;
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
	
	private static Logger LOG = Logger.getLogger(SearchController.class.getSimpleName());
	
	public static Result index()
	{
		return ok("Welcome to StoreAssist ..");
	}
	
	/**
	 * Returns the location of items within a store.
	 */
	public static Result findItemLocation(String storeIdentifier, String item)
	{
		if(Strings.isNullOrEmpty(item) || item.trim().length() < 2) {
			LOG.severe("Please specify item to search.");
			return ok(Json.toJson("MISSING MANDATORY ITEM PARAMETER"));
		}
		
		LOG.severe("Search Terms : (" + storeIdentifier + ", " + item + ")");
		List<String> items = Arrays.asList(item.split(","));
		Map<String, List<ItemLocation>> itemsLocations = SearchService.searchItemsLocations(storeIdentifier, items);
		
		JsonNode node = Json.toJson(itemsLocations);
		String output = node.toString();
		
		LOG.severe("Output : " + output);
		return ok(Json.toJson(itemsLocations));
	}
	
	/**
	 * Returns the identifier of a store
	 */
	public static Result findStore(String storeIdentifier)
	{
		Store store = SearchService.searchStore(storeIdentifier);
		return ok(Json.toJson(store));
	}
}
