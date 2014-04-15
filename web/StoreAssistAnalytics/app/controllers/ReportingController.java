package controllers;

import java.util.Date;
import java.util.Map;

import models.Constants;
import models.ReportType;
import models.SearchFilter;
import models.service.ReportingService;

import org.apache.commons.lang3.time.DateUtils;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.main;

public class ReportingController extends Controller {

	private static ReportingService reportService = new ReportingService();
	
    public static Result index() {
        return ok(main.render());
    }
    
    /**
     * Generates analysis report based on the date and report type filters 
     */
    public static Result generateReport(String reportType, String startTime, String endTime)
    {
    	startTime = startTime + Constants.DEFAULT_DATETIME_SUFFIX;
    	endTime = endTime + Constants.DEFAULT_DATETIME_SUFFIX;
    	reportType = reportType.trim();
    	
    	SearchFilter filter = getSearchFilter(reportType, startTime, endTime);
    	
    	Result result = null;
    	if(filter.getReportType().equals(ReportType.SEARCH_SUMMARY_REPORT)) {
    		result = generateOverallSummaryReport(filter);
    	}
    	
    	Logger.info("Results : " + result.toString());
    	return result;
    	
    }
    
    private static Result generateOverallSummaryReport(SearchFilter filter)
    {
    	int numResults = Constants.NUM_SEARCH_SUMMARY_RESULTS;
    	Map<String, Double> summaryReport = reportService.fetchSearchSummaryReport(numResults, filter);
    	
    	return ok(Json.toJson(summaryReport));
    }
    
    private static SearchFilter getSearchFilter(String reportType, String startTime, String endTime)
    {
    	Date startDate = null;
    	Date endDate = null;
    	ReportType report = null;
    	try {
        	startDate = DateUtils.parseDate(startTime, Constants.DATE_FORMAT);
        	endDate = DateUtils.parseDate(endTime, Constants.DATE_FORMAT);
        	report = ReportType.getReportType(reportType); 		
    	}
    	catch(Exception e) {
    		Logger.error("Failed to parse inputs. " + startTime + ", " + endTime + ", " + reportType);
    		e.printStackTrace();
    	}
    	
    	return new SearchFilter(startDate, endDate, report);
    			
    }
}
