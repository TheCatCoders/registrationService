/**
 * 
 */
package com.thecat.registrationService.services.impl;


import java.io.IOException;

import com.thecat.helper.RESTConnector;
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
	private RegistrationService(){}

	/**
	 *
	 * @return RegistrationService
	 */
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
	 * @param userJson {@link String}
	 */
	public void registerUser(User userJson) {
		String url = "http://clientservice:8080/ClientService/api/clients/create";

		try {
			RESTConnector.connectPost(url, parseInput(userJson));
		}catch (IOException ioe) {
			ioe.printStackTrace();
	}
	}
	
	/**
	 * Method that is responsible to create the service Input.
	 * 
	 * @param user {@link User}
	 * 
	 * @return {@link String}
	 */
	private String parseInput(User user) {
		StringBuilder sb = new StringBuilder();
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
