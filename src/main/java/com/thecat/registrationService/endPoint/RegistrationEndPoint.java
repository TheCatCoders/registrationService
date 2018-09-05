package com.thecat.registrationService.endPoint;

import com.thecat.registrationService.entities.User;
import com.thecat.registrationService.services.impl.RegistrationService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * API use to Register a use to the System
 *
 * @author froberge
 * @since January 2017
 */
@Path("/register")
public class RegistrationEndPoint {


    /**
     * Responsible to register a user
     *
     * @param user {@link User}
     * @return {@link Response}
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registrationPost(User user) {
    	try {
    		// This should be replace by a call to the login service
    		RegistrationService.getInstance().registerUser(user);
    		
        return Response.ok().build();
    	} catch ( Exception e ) {
	    	return Response.status(Response.Status.BAD_REQUEST)
	                .entity( "User not found.  Please try Again" )
	                .build();
    	}
    }
}