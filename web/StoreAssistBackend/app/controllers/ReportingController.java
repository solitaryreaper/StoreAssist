package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Constants;
import models.SearchFilter;
import models.SearchLog;
import models.service.ReportingService;

import org.apache.commons.lang3.time.DateUtils;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.logs;
import views.html.main;

import com.google.common.collect.Lists;

public class ReportingController extends Controller {

	private static ReportingService reportService = new ReportingService();
	
    public static Result index() {
    	List<String> itemNames = reportService.getAllItemNames();
        return ok(main.render("Fresh Madison Market", itemNames));
    }
    
    public static Result generateOverallItemSearchSummaryReport(String startTime, String endTime)
    {
    	startTime = startTime + Constants.DEFAULT_DATETIME_SUFFIX;
    	endTime = endTime + Constants.DEFAULT_DATETIME_SUFFIX;
    	
    	SearchFilter filter = getSearchFilter(startTime, endTime);
    	
    	int numResults = Constants.NUM_SEARCH_SUMMARY_RESULTS;
    	Map<String, Double> summaryReport = reportService.getOverallItemSearchSummaryReport(numResults, filter);
    	
    	return ok(Json.toJson(summaryReport));
    }
    
    public static Result generateOverallCategorySearchSummaryReport(String startTime, String endTime)
    {
    	startTime = startTime + Constants.DEFAULT_DATETIME_SUFFIX;
    	endTime = endTime + Constants.DEFAULT_DATETIME_SUFFIX;
    	
    	SearchFilter filter = getSearchFilter(startTime, endTime);
    	
    	int numResults = Constants.NUM_SEARCH_SUMMARY_RESULTS;
    	Map<String, Double> summaryReport = reportService.getOverallCategorySearchSummaryReport(numResults, filter);
    	
    	return ok(Json.toJson(summaryReport));
    }    
    
    public static Result getItemSearchSummaryReport(String item, String startTime, String endTime)
    {
    	startTime = startTime + Constants.DEFAULT_DATETIME_SUFFIX;
    	endTime = endTime + Constants.DEFAULT_DATETIME_SUFFIX;
    	
    	SearchFilter filter = getSearchFilter(startTime, endTime);
    	filter.setItems(Lists.newArrayList(item));
    	
    	Map<String, Integer> summaryReport = reportService.getItemBasedSearchSummaryReport(item, filter);
    	return ok(Json.toJson(summaryReport));
    }
    
    public static Result getTimeBasedSearchSummaryReport(String startTime, String endTime)
    {
    	startTime = startTime + Constants.DEFAULT_DATETIME_SUFFIX;
    	endTime = endTime + Constants.DEFAULT_DATETIME_SUFFIX;
    	
    	SearchFilter filter = getSearchFilter(startTime, endTime);
    	
    	Map<String, Integer> summaryReport = reportService.getTimeBasedSearchSummaryReport(filter);
    	return ok(Json.toJson(summaryReport));
    }
    
    private static SearchFilter getSearchFilter(String startTime, String endTime)
    {
    	Date startDate = null;
    	Date endDate = null;
    	try {
        	startDate = DateUtils.parseDate(startTime, Constants.DATE_FORMAT);
        	endDate = DateUtils.parseDate(endTime, Constants.DATE_FORMAT);
    	}
    	catch(Exception e) {
    		Logger.error("Failed to parse inputs. " + startTime + ", " + endTime);
    		e.printStackTrace();
    	}
    	
    	return new SearchFilter(startDate, endDate);
    			
    }
    
    public static Result showSearchLogs(int numLogs)
    {
    	List<SearchLog> searchLogs = reportService.getSearchLogs(numLogs);
    	return ok(logs.render("Fresh Madison Market", searchLogs));
    }
}
