import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import models.Constants;
import models.SearchFilter;
import models.service.ReportingService;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Ignore;
import org.junit.Test;

import play.Logger;


public class ReportingServiceTest {
	ReportingService reportService = new ReportingService();
	
	@Ignore
	public void testItemSearchSummaryReport() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				SearchFilter filter = new SearchFilter();
				try {
					filter.setStartTime(DateUtils.parseDate("2010-01-01 00:00:00", Constants.DATE_FORMAT));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				filter.setEndTime(new Date());
				
				Map<String, Double> itemSearchSummary = reportService.fetchOverallSearchSummaryReport(5, filter);
				Logger.debug(itemSearchSummary.toString());
				assertThat(itemSearchSummary.size()).isGreaterThan(0);
			}
		});
	}	
	
	@Test
	public void testTimeBasedSearchSummaryReport() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				SearchFilter filter = new SearchFilter();
				try {
					filter.setStartTime(DateUtils.parseDate("2010-01-01 00:00:00", Constants.DATE_FORMAT));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				filter.setEndTime(new Date());
				
				Map<String, Integer> timeBasedSearchSummaryReport = reportService.getTimeBasedSearchSummaryReport(filter);
				Logger.debug(timeBasedSearchSummaryReport.toString());
				assertThat(timeBasedSearchSummaryReport.size()).isGreaterThan(0);
			}
		});
	}	
}
