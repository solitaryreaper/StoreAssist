import java.util.List;

import models.SearchLog;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;


public class SearchLogRetrievalTest {
	
	/*------------------------------ Item search log test cases -------------------------------*/
	@Test
	public void testGetSearchLogs() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				List<SearchLog> logs = SearchLog.getAllSearchLogs();
				assertThat(logs.size()).isEqualTo(0);
			}
		});
	}
}
