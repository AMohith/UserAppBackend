package com.company.service.impl;

import com.company.customExceptions.ObjectNotFoundException;
import com.company.dao.UserDao;
import com.company.dao.impl.UserOperations;
import com.company.model.User;
import com.company.service.UserService;
import org.json.JSONObject;

/**
 * UserServiceImpl class implements userService interface
 * this class has all the service methods for User
 */
public class UserServiceImpl implements UserService {

    /**
     * User Object is passed to DAO layer for user creation
     *
     * @param user User Object that is created from inputs in CLI layer
     */
    @Override
    public void createUserService(User user) {
        UserDao userOperations = new UserOperations();
        userOperations.createUser(user);
    }

    /**
     * Id is passed down to DAO layer for getting User details in ExceptionResource
     *
     * @param emailAddress Email Id of the User
     * @return Returns JsonObject of the User with given id
     * @throws ObjectNotFoundException Prompts the message that the Object not found in DB
     */
    @Override
    public JSONObject getUserService(String emailAddress, String password) throws ObjectNotFoundException {
        UserDao userOperations = new UserOperations();
        return userOperations.getUser(emailAddress, password);
    }

    /**
     * Id is passed down to DAO layer for Deletion of the Respective User
     *
     * @return deleted count
     */
    @Override
    public long deleteUserService(String emailAddress, String password) {
        UserDao userOperations = new UserOperations();
        return userOperations.deleteUser(emailAddress, password);
    }

    /**
     * Both the id and json object are passed down to DAO for Updation
     *
     * @param user Json Object with only changed fields
     * @return updated user object
     */
    @Override
    public JSONObject updateUserService(String emailAddress, String password, User user) {
        UserDao userOperations = new UserOperations();
        return userOperations.updateUser(emailAddress, password, user);
    }

    @Override
    public JSONObject getUserServiceWithEmail(String emailAddress) throws ObjectNotFoundException {
        UserDao userOperations = new UserOperations();
        return userOperations.getUserWithEmail(emailAddress);
    }


}
