package com.thecat.registrationService.endPoint;

import com.thecat.registrationService.entities.UserJson;
import com.thecat.registrationService.services.impl.RegistrationService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Service use to Login into an application
 *
 * @author froberge
 * @since Oct 2016
 */
@Path("/register")
public class RegistrationEndPoint {


    /**
     * Responsible to register a user
     *
     * @param user {@link UserJson}
     * @return {@link Response}
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registrationPost(UserJson user) {    		
    	try {
    		
    		System.out.println( "registration service" );
    		
    		UserJson u = RegistrationService.getInstance().findUser(user.getEmailAdr() , user.getPassword() );
    		
            return Response.ok()
                    .entity(u)
                    .build();
    	} catch ( Exception e ) {
    		System.out.println( e.getMessage() );
	    	return Response.status(Response.Status.BAD_REQUEST)
	                .entity( "User not found.  Please try Again" )
	                .build();
    	}
    }
}