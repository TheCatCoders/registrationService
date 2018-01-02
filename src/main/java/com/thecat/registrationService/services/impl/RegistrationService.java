/**
 * 
 */
package com.thecat.registrationService.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

import com.thecat.registrationService.entities.UserJson;

/**
 * Implement the Registration Service
 *
 * @author froberge
 * @since January 2017
 */
public class RegistrationService {

	private static RegistrationService registerService = null;
	
	/**
	 * Private constructor to prevent the creation of the RegistrationService
	 */
	private RegistrationService(){
		
	}
	
	public static RegistrationService getInstance() {
		if ( registerService == null ) {
			registerService = new RegistrationService();
		}

		return registerService;
	}

	/**
	 * Method that will allow us to connect to the database and find a given user
	 * 
	 * @param emailAddress {@link String}
	 * @param password {@link String} 
	 * @return {@link UserJson}
	 * @throws
	 */
	public UserJson findUser(String emailAdress, String password) {
		UserJson user = null;
		
		try {
			URL url = new URL( "http://dbservice-coffeshop.192.168.64.2.nip.io/DatabaseService/api/db/select" );
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			OutputStream os = conn.getOutputStream();
			os.write(parseInput(emailAdress, password).getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
		
			
			user = parseOutput(new BufferedReader(new InputStreamReader((conn.getInputStream()))));
			conn.disconnect();
			
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		
		  } catch (IOException e) {
			e.printStackTrace();
		  }
		
		return user;
	}
	
	/**
	 * Method that is responsible to create the service Input.
	 * 
	 * @param emailAdress {@link String}
	 * @param password {@link String}
	 * 
	 * @return {@link String}
	 */
	private String parseInput(String emailAdress, String password) {
		StringBuffer sb = new StringBuffer();
		sb.append( "{" );
		sb.append( "\"emailAdr\":" );
		sb.append( "\"" );
		sb.append( emailAdress );
		sb.append( "\"" );
		sb.append( "," );
		sb.append( "\"password\":" );
		sb.append( "\"" );
		sb.append(password);
		sb.append( "\"" );
		sb.append( "}" );

		return sb.toString();
	}

	/**
	 * Create the response from what was retrun in the service.
	 * 
	 * @param response {@link BufferedReader}
	 * @return {@link UserJson}
	 */
    private UserJson parseOutput( BufferedReader response) throws IOException {
    	StringBuilder sb = new StringBuilder();

    	try {
		    String line;
		    while ((line = response.readLine()) != null) {
		        sb.append(line);
		    }
		    
		   JSONObject obj =  new JSONObject( sb.toString() );
		   UserJson user = new UserJson();
		   
	    	user.setAge(obj.getString("age"));
	    	user.setEmailAdr(obj.getString("emailAdr"));
	    	user.setGender(obj.getString("gender"));
	    	user.setUsername(obj.getString("username"));
	    	user.setPassword(obj.getString("password"));
	    
		    return user;
    	} catch (IOException ioe) {
			throw ioe;
		}
    }

}
