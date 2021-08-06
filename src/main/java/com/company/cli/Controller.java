package com.company.cli;

import com.company.customExceptions.ObjectNotFoundException;
import com.company.model.ExceptionResource;
import com.company.model.User;
import com.company.service.UserService;
import com.company.service.impl.UserServiceImpl;
import com.company.util.MongoLogger;
import com.company.util.Validations;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("Users")
public class Controller {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@QueryParam("emailAddress") String emailAddress, @QueryParam("password") String password) {
        MongoLogger.loggerSuppression();
        UserService userServices = new UserServiceImpl();
        JSONObject userObj;
        try {
            userObj = userServices.getUserService(emailAddress, password);
            JSONObject addressObject = new JSONObject(userObj.get("address").toString());
            User user = new User(userObj.get("id").toString(), userObj.get("firstName").toString(), userObj.get("lastName").toString(),
                    userObj.get("emailAddress").toString(), userObj.get("phoneNumber").toString(),
                    userObj.get("password").toString(),
                    addressObject.get("address1").toString(),
                    addressObject.get("address2").toString(),
                    addressObject.get("city").toString(),
                    addressObject.get("state").toString(),
                    addressObject.get("country").toString(),
                    Integer.parseInt(addressObject.get("postalCode").toString())
            );
            return Response.ok(user)
                    .status(Response.Status.OK)
                    .build();
        } catch (ObjectNotFoundException e) {
            ExceptionResource exceptionResource = new ExceptionResource(e.getMessage(),
                    Response.Status.NO_CONTENT.getStatusCode());
            return Response.noContent()
                    .status(Response.Status.NO_CONTENT)
                    .header("exceptionResource", exceptionResource.toString())
                    .build();
        }

    }

    @POST
    @Path("user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        MongoLogger.loggerSuppression();
        Validations validations = new Validations();
        if (validations.userValidation(user)) {
            user.setId(UUID.randomUUID().toString());
            UserService userServices = new UserServiceImpl();

            JSONObject userWithEmail = null;
            try {
                userWithEmail = userServices.getUserServiceWithEmail(user.getEmailAddress());
                System.out.println(userWithEmail.toString());
                return Response.noContent()
                        .status(Response.Status.NO_CONTENT)
                        .build();
            } catch (ObjectNotFoundException e) {
                userServices.createUserService(user);
                return Response.ok(user)
                        .status(Response.Status.OK)
                        .build();
            }
        } else {
            return Response.noContent()
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }
    }



    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Object delete(@QueryParam("emailAddress") String emailAddress, @QueryParam("password") String password) {
        MongoLogger.loggerSuppression();
        UserService userServices = new UserServiceImpl();
        JSONObject userObj;
        try {
            userObj = userServices.getUserService(emailAddress, password);
            JSONObject addressObject = new JSONObject(userObj.get("address").toString());

            long deletedCount = userServices.deleteUserService(emailAddress, password);

            if (deletedCount != 0) {
                return new User(userObj.get("id").toString(), userObj.get("firstName").toString(), userObj.get("lastName").toString(),
                        userObj.get("emailAddress").toString(), userObj.get("phoneNumber").toString(), userObj.get("password").toString(),
                        addressObject.get("address1").toString(),
                        addressObject.get("address2").toString(),
                        addressObject.get("city").toString(),
                        addressObject.get("state").toString(),
                        addressObject.get("country").toString(),
                        Integer.parseInt(addressObject.get("postalCode").toString())
                );
            }
            return new ExceptionResource("User Present but not deleted from Database", 500);
        } catch (ObjectNotFoundException e) {
            return new ExceptionResource(e.getMessage(), 500);
        }
    }

    @PUT
    @Path("user")
    public Object updateUser(@QueryParam("emailAddress") String emailAddress,
                             @QueryParam("password") String password, User user) {
        MongoLogger.loggerSuppression();
        UserService userServices = new UserServiceImpl();
        JSONObject getUserObj;
        Validations validations = new Validations();
        if (validations.userValidation(user)) {
            try {
                getUserObj = userServices.getUserService(emailAddress, password);
                user.setId(getUserObj.get("id").toString());
                JSONObject userObj = userServices.updateUserService(emailAddress, password, user);
                JSONObject addressObject = new JSONObject(userObj.get("address").toString());

                User userResponse = new User(userObj.get("id").toString(), userObj.get("firstName").toString(), userObj.get("lastName").toString(),
                        userObj.get("emailAddress").toString(), userObj.get("phoneNumber").toString(),
                        userObj.get("password").toString(),
                        addressObject.get("address1").toString(),
                        addressObject.get("address2").toString(),
                        addressObject.get("city").toString(),
                        addressObject.get("state").toString(),
                        addressObject.get("country").toString(),
                        Integer.parseInt(addressObject.get("postalCode").toString())
                );
                return Response.ok(userResponse)
                        .status(Response.Status.OK)
                        .build();

            } catch (ObjectNotFoundException e) {
                ExceptionResource exceptionResource = new ExceptionResource(e.getMessage(), 500);
                return Response.noContent()
                        .status(Response.Status.NO_CONTENT)
                        .header("exceptionResource", exceptionResource.toString())
                        .build();
            }
        } else {
            return Response.noContent()
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }
    }

}

/*
    @POST
    @Path("user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        MongoLogger.loggerSuppression();
        Validations validations = new Validations();
        if (validations.userValidation(user)) {
            user.setId(UUID.randomUUID().toString());
            UserService userServices = new UserServiceImpl();
            userServices.createUserService(user);
            return Response.ok(user)
                    .status(Response.Status.OK)
                    .build();
        } else {
            return Response.noContent()
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }
    }*/ //-POST
