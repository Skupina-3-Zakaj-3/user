package si.fri.rso.skupina3.user.v1.resources;

import si.fri.rso.skupina3.lib.User;
import si.fri.rso.skupina3.user.services.beans.UserBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@ApplicationScoped
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private Logger log = Logger.getLogger(UserResource.class.getName());

    @Inject
    private UserBean userBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getUser() {

        List<User> users = userBean.getUser();

        return Response.status(Response.Status.OK).entity(users).build();
    }

    /*@GET
    @Path("google/{googleId}")
    public Response getGoogleUser(User user, @PathParam("googleId") String googleId) {

        User testUser = new User();

        System.out.println(googleId);

        testUser = userBean.getByGoogleId(googleId);


        return Response.status(Response.Status.OK).entity(testUser).build();

    }*/


    @POST
    @Path("google")
    public Response identifyGoogleUser(User user) {

        String googleIdParam = user.getGoogleId();

        User existingUser = userBean.getByGoogleId(googleIdParam);

        if (existingUser != null){
            return Response.status(Response.Status.OK).entity(existingUser).build();
        } else {
            user = userBean.createUser(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        }


    }

}
