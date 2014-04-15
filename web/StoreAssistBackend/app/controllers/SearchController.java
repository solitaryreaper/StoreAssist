package controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;

import models.ItemLocation;
import models.Store;
import models.StoreSearchMetadata;
import models.service.SearchService;
import models.service.SearchServiceImpl;
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
	
	private static SearchService searchService = new SearchServiceImpl();
	
	public static Result index()
	{
		return ok("Welcome to StoreAssist ..");
	}
	
	/**
	 * Returns the location of items within a store.
	 */
	public static Result searchItemLocation(int storeId, String item)
	{
		if(Strings.isNullOrEmpty(item) || item.trim().length() < 2) {
			LOG.severe("Please specify item to search.");
			return ok(Json.toJson("MISSING MANDATORY ITEM PARAMETER"));
		}
		
		LOG.severe("Search Terms : (" + storeId + ", " + item + ")");
		List<String> items = Arrays.asList(item.split(","));
		Map<String, List<ItemLocation>> itemsLocations = searchService.searchItemsLocations(storeId, items, false);
		
		JsonNode node = Json.toJson(itemsLocations);
		String output = node.toString();
		
		LOG.severe("Output : " + output);
		return ok(Json.toJson(itemsLocations));
	}
	
	/**
	 * Returns list of possible item name auto-completion proposals.
	 * 
	 * @param itemNameRegex
	 * @param storeId
	 * @return
	 * 
	 * TODO : Add ranking function for results and only retain 4 results.
	 */
	public static Result getItemNameAutoCompletions(String itemNameRegex, int storeId)
	{
		List<String> itemNameProposals = searchService.searchItemsByName(itemNameRegex, storeId);
		return ok(Json.toJson(itemNameProposals));
	}
	
	/**
	 * Returns list of stores in a zip code.
	 */
	public static Result searchStoresByZipCode(int zipCode)
	{
		StoreSearchMetadata searchMeta = new StoreSearchMetadata();
		searchMeta.setZip(zipCode);
		List<Store> stores = searchService.searchStore(searchMeta);
		return ok(Json.toJson(stores));
	}
	
	/**
	 * Returns list of stores corresponding to an address.
	 * @param address
	 * @return
	 */
	public static Result searchStoresByAddress(String address)
	{
		StoreSearchMetadata searchMeta = new StoreSearchMetadata();
		searchMeta.setAddress(address);
		List<Store> stores = searchService.searchStore(searchMeta);
		return ok(Json.toJson(stores));
	}
	
}
