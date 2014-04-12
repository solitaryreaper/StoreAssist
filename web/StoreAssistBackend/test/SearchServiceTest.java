import java.util.List;

import models.Store;
import models.StoreSearchMetadata;
import models.service.SearchService;
import models.service.SearchServiceImpl;

import org.junit.Test;

import play.Logger;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import static org.fest.assertions.Assertions.*;

public class SearchServiceTest {
	private static SearchService searchService = new SearchServiceImpl();
	
	@Test
	public void testSearchStoreById() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				StoreSearchMetadata meta = new StoreSearchMetadata();
				meta.setStoreId("1");
				
				List<Store> stores = searchService.searchStore(meta);
				assertThat(stores.size()).isEqualTo(1);
			}
		});
	}
	
	@Test
	public void testSearchStoreByName() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				StoreSearchMetadata meta = new StoreSearchMetadata();
				meta.setName("fresh madison market");
				
				List<Store> stores = searchService.searchStore(meta);
				assertThat(stores.size()).isEqualTo(1);
			}
		});
	}
	
	@Test
	public void testSearchStoreByAddress() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				StoreSearchMetadata meta = new StoreSearchMetadata();
				meta.setAddress("703 university avenue");
				
				List<Store> stores = searchService.searchStore(meta);
				assertThat(stores.size()).isEqualTo(1);
			}
		});
	}
	
	@Test
	public void testSearchStoreByWrongAddressAndCorrectZip() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				StoreSearchMetadata meta = new StoreSearchMetadata();
				// This address is wrong but still the search should work because of correct zip.
				meta.setAddress("710 university ave.");
				meta.setZip(53715);
				
				List<Store> stores = searchService.searchStore(meta);
				assertThat(stores.size()).isEqualTo(1);
			}
		});
	}
	
	@Test
	public void testSearchStoreByWrongAddressAndWrongZip() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				StoreSearchMetadata meta = new StoreSearchMetadata();
				// This address is wrong but still the search should work because of correct zip.
				meta.setAddress("710 university ave.");
				meta.setZip(53714);
				
				List<Store> stores = searchService.searchStore(meta);
				assertThat(stores.size()).isEqualTo(0);
			}
		});
	}	
	
}
