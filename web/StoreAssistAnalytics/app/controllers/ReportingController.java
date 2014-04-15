package controllers;

import java.util.Date;
import java.util.Map;

import models.Constants;
import models.ReportType;
import models.SearchFilter;
import models.SearchFilter.AggregativeLevel;
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
        return ok(main.render("Fresh Madison Market"));
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
    	
    	String item = "";
    	Result result = null;
    	if(filter.getReportType().equals(ReportType.OVERALL_ITEM_SEARCH_SUMMARY_REPORT)) {
    		result = generateOverallItemSearchSummaryReport(filter);
    	}
    	else if(filter.getReportType().equals(ReportType.OVERALL_CATEGORY_SEARCH_SUMMARY_REPORT)) {
    		result = generateOverallCategorySearchSummaryReport(filter);
    	}
    	else if(filter.getReportType().equals(ReportType.SEARCH_BY_ITEM_REPORT)) {
    		result = getItemSearchSummaryReport(item, filter);
    	}
    	else if(filter.getReportType().equals(ReportType.SEARCH_BY_TIME_REPORT)) {
    		result = getTimeBasedSearchSummaryReport(filter);
    	}
    	
    	Logger.info("Results : " + result.toString());
    	return result;
    	
    }
    
    private static Result generateOverallItemSearchSummaryReport(SearchFilter filter)
    {
    	filter.setSummaryLevel(AggregativeLevel.ITEM);
    	
    	int numResults = Constants.NUM_SEARCH_SUMMARY_RESULTS;
    	Map<String, Double> summaryReport = reportService.getOverallItemSearchSummaryReport(numResults, filter);
    	
    	return ok(Json.toJson(summaryReport));
    }
    
    private static Result generateOverallCategorySearchSummaryReport(SearchFilter filter)
    {
    	filter.setSummaryLevel(AggregativeLevel.ITEM);
    	
    	int numResults = Constants.NUM_SEARCH_SUMMARY_RESULTS;
    	Map<String, Double> summaryReport = reportService.getOverallCategorySearchSummaryReport(numResults, filter);
    	
    	return ok(Json.toJson(summaryReport));
    }    
    
    private static Result getItemSearchSummaryReport(String item, SearchFilter filter)
    {
    	filter.setSummaryLevel(AggregativeLevel.TIME_DAY);
    	
    	Map<String, Integer> summaryReport = reportService.getItemBasedSearchSummaryReport("Avocados", filter);
    	return ok(Json.toJson(summaryReport));
    }
    
    private static Result getTimeBasedSearchSummaryReport(SearchFilter filter)
    {
    	filter.setSummaryLevel(AggregativeLevel.TIME_DAY);
    	
    	Map<String, Integer> summaryReport = reportService.getTimeBasedSearchSummaryReport(filter);
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
