package controllers;

import static play.data.Form.form;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import models.ItemLocation;
import models.service.SearchService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;

public class MessageController extends Controller {

	private static Logger LOG = Logger.getLogger(MessageController.class.getSimpleName());
	
	// Your Account Sid and Auth Token from twilio.com/user/account
	private static String TWILIO_ACCOUNT_SID = "ACc5e06276e9acc6426aab3b0e57dc8809";
	private static String TWILIO_AUTH_TOKEN  = "6ad472db3444e0f15c4770f8c8b56f83";
	
	private static final TwilioRestClient client = new TwilioRestClient(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
	private static final Account mainAccount = client.getAccount();
	
	// # Trail twilio number
	private static String MY_TWILIO_NUMBER = "+19797733224";
	private static String USER_MOBILE_NUMBER = "+15122010228";
	
	private static String MESSAGE_DELIMITER = ":";
	
	public static Result getItemLocation(String textMsg)
	{
		LOG.severe("Inside text message handler .." + textMsg);
		if(Strings.isNullOrEmpty(textMsg)) {
			return ok();
		}
		
		if(!textMsg.contains(MESSAGE_DELIMITER)) {
			
		}
		
		String[] msgParts = textMsg.split(MESSAGE_DELIMITER);
		String storeIdentifier = msgParts[0].trim();
		String item = msgParts[1].trim();
		
		if(Strings.isNullOrEmpty(item) || item.trim().length() < 2) {
			LOG.severe("Please specify item to search.");
			return ok(Json.toJson("MISSING MANDATORY ITEM PARAMETER"));
		}
		
		return findItemLocation(storeIdentifier, item);	
	}
	
	public static Result postItemLocation()
	{
		DynamicForm dynamicForm = form().bindFromRequest();
		String textMsg = dynamicForm.get("Body");
		LOG.severe("Message " + textMsg);
		LOG.severe("Form parameters : " + dynamicForm.data().toString());
		
		if(Strings.isNullOrEmpty(textMsg)) {
			return ok();
		}
		
		if(!textMsg.contains(MESSAGE_DELIMITER)) {
			
		}
		
		String[] msgParts = textMsg.split(MESSAGE_DELIMITER);
		String storeIdentifier = msgParts[0].trim();
		String item = msgParts[1].trim();
		
		return findItemLocation(storeIdentifier, item);
	}
	
	private static Result findItemLocation(String storeIdentifier, String item)
	{
		if(Strings.isNullOrEmpty(item) || item.trim().length() < 2) {
			LOG.severe("Please specify item to search.");
			return ok(Json.toJson("MISSING MANDATORY ITEM PARAMETER"));
		}
		
		LOG.severe("Search Terms : (" + storeIdentifier + ", " + item + ")");
		List<String> items = Arrays.asList(item.split(","));
		Map<String, List<ItemLocation>> itemsLocations = SearchService.searchItemsLocations(storeIdentifier, items);
		
		StringBuilder output = new StringBuilder();
		output.append("[");
		for(Map.Entry<String, List<ItemLocation>> itemLocations : itemsLocations.entrySet()) {
			output.append(itemLocations.getKey()).append(":").append("{");
			for(ItemLocation location : itemLocations.getValue()) {
				output.append("(");
				output.append(location.getSection()).append(",");
				output.append(location.getAisle()).append(",");
				output.append(location.getShelf());
				output.append(") , ");
			}
			output.append("}");
		}
		output.append("]");
		LOG.severe("Output : " + output.toString());
		
	    // Send an sms
	    final MessageFactory messageFactory = mainAccount.getMessageFactory();
	    final List<NameValuePair> messageParams = Lists.newArrayList();
	    messageParams.add(new BasicNameValuePair("To", USER_MOBILE_NUMBER)); // Replace with a valid phone number
	    messageParams.add(new BasicNameValuePair("From", MY_TWILIO_NUMBER)); // Replace with a valid phone number in your account
	    messageParams.add(new BasicNameValuePair("Body", output.toString()));
	    try {
			messageFactory.create(messageParams);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return ok(Json.toJson(itemsLocations));			
	}
	
}
