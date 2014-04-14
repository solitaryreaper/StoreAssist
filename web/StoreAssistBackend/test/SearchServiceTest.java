import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import java.util.List;

import models.Constants;
import models.ItemLocation;
import models.Store;
import models.StoreSearchMetadata;
import models.service.SearchService;
import models.service.SearchServiceImpl;

import org.junit.Ignore;
import org.junit.Test;

import play.Logger;

public class SearchServiceTest {
	private static SearchService searchService = new SearchServiceImpl();

	/*------------------------------ Store search test cases -------------------------------*/
	@Ignore
	public void testSearchStoreById() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				StoreSearchMetadata meta = new StoreSearchMetadata();
				meta.setStoreId(1);
				Logger.debug("Metadata : " + meta.toString());
				
				List<Store> stores = searchService.searchStore(meta);
				assertThat(stores.size()).isEqualTo(1);
			}
		});
	}
	
	@Ignore
	public void testSearchStoreByName() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				StoreSearchMetadata meta = new StoreSearchMetadata();
				meta.setName("fresh madison market");
				Logger.debug("Metadata : " + meta.toString());
				
				List<Store> stores = searchService.searchStore(meta);
				assertThat(stores.size()).isEqualTo(1);
			}
		});
	}
	
	@Ignore
	public void testSearchStoreByAddress() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				StoreSearchMetadata meta = new StoreSearchMetadata();
				meta.setAddress("703 university avenue");
				Logger.debug("Metadata : " + meta.toString());
				
				List<Store> stores = searchService.searchStore(meta);
				assertThat(stores.size()).isEqualTo(1);
			}
		});
	}
	
	@Ignore
	public void testSearchStoreByWrongAddressAndCorrectZip() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				StoreSearchMetadata meta = new StoreSearchMetadata();
				// This address is wrong but still the search should work because of correct zip.
				meta.setAddress("710 university ave.");
				meta.setZip(53715);
				Logger.debug("Metadata : " + meta.toString());
				
				List<Store> stores = searchService.searchStore(meta);
				assertThat(stores.size()).isEqualTo(1);
			}
		});
	}
	
	@Ignore
	public void testSearchStoreByWrongAddressAndWrongZip() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				StoreSearchMetadata meta = new StoreSearchMetadata();
				// This address is wrong but still the search should work because of correct zip.
				meta.setAddress("710 university ave.");
				meta.setZip(53714);
				Logger.debug("Metadata : " + meta.toString());
				
				List<Store> stores = searchService.searchStore(meta);
				assertThat(stores.size()).isEqualTo(0);
			}
		});
	}	
	
	/*------------------------------ Item name auto-completion test cases -------------------------------*/	
	@Ignore
	public void testAutoCompleteItemNames() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				List<String> items = searchService.searchItemsByName("vegetable", Constants.FRESH_MADISON_MARKET);
				assertThat(items.size()).isEqualTo(2);
				
				items = searchService.searchItemsByName("veget", Constants.FRESH_MADISON_MARKET);
				assertThat(items.size()).isEqualTo(2);
				
				items = searchService.searchItemsByName("lamp", Constants.FRESH_MADISON_MARKET);
				assertThat(items.size()).isEqualTo(0);	
			}
		});
	}
	
	/*------------------------ Item location search test cases --------------------------*/
	@Test
	public void testItemLocationSearch() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				List<ItemLocation> locations = searchService.searchItemLocations(Constants.FRESH_MADISON_MARKET, "vegetable", false);
				assertThat(locations.size()).isEqualTo(2);
				
				locations = searchService.searchItemLocations(Constants.FRESH_MADISON_MARKET, "vegetables", true);
				assertThat(locations.size()).isEqualTo(1);				
			}
		});		
	}
}
