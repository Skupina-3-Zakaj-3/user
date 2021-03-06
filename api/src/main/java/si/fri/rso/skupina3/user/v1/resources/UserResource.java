package si.fri.rso.skupina3.user.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
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

@Log
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

    @GET
    @Path("{userId}")
    public Response getUserById(@PathParam("userId") Integer userId) {

        String userName = userBean.getUserNameByUserId(userId);

        if (userName != null){
            return Response.status(Response.Status.OK).entity(userName).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

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


    @PUT
    @Path("{userId}")
    public Response putUser(@PathParam("userId") Integer userId, User user){

        user = userBean.putUser(userId, user);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") Integer userId){

        boolean deleted = userBean.deleteUser(userId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createUser(User user) {

//        if ((imageMetadata.getTitle() == null || imageMetadata.getDescription() == null || imageMetadata.getUri() == null)) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//        else {
        user = userBean.createUser(user);
//        }

        return Response.status(Response.Status.CONFLICT).entity(user).build();

    }

}
