/**
 * 
 */
package com.thecat.registrationService.services.impl;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import com.thecat.registrationService.entities.User;

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
	 * Method that will allow us to connect to the database and find a given user.  
	 * Nothing is return, if their is and error the error code is thrown
	 * 
	 * @param emailAddress {@link String}
	 * @param password {@link String} 
	 * @throws
	 */
	public void registerUser(User userJson) {
		try {
			URL url = new URL( "hhttp://clientservice:8080/ClientService/api/clients/create" );
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			OutputStream os = conn.getOutputStream();
			os.write(parseInput(userJson).getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			conn.disconnect();
			
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		
		  } catch (IOException e) {
			e.printStackTrace();
		  }
	}
	
	/**
	 * Method that is responsible to create the service Input.
	 * 
	 * @param userJson {@link User}
	 * 
	 * @return {@link String}
	 */
	private String parseInput(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append( "{" );
		sb.append( "\"emailAdr\":" );
		sb.append( "\"" );
		sb.append( user.getEmailAdr() );
		sb.append( "\"" );
		sb.append( "," );
		sb.append( "\"age\":" );
		sb.append( "\"" );
		sb.append( user.getAge() );
		sb.append( "\"" );
		sb.append( "," );		
		sb.append( "\"gender\":" );
		sb.append( "\"" );
		sb.append( user.getGender() );
		sb.append( "\"" );
		sb.append( "," );		
		sb.append( "\"username\":" );
		sb.append( "\"" );
		sb.append( user.getUsername() );
		sb.append( "\"" );
		sb.append( "," );		
		sb.append( "\"password\":" );
		sb.append( "\"" );
		sb.append(user.getPassword());
		sb.append( "\"" );
		sb.append( "}" );

		return sb.toString();
	}
}
