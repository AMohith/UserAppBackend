package com.company.service;

import com.company.customExceptions.ObjectNotFoundException;
import com.company.model.User;
import org.json.JSONObject;

/**
 * User Interface for Service Layer
 * Following methods are implemented in UserServiceImpl Class in service.impl package
 * All the Exceptions are thrown back from DAO layer
 */
public interface UserService {
    void createUserService(User user);

    JSONObject getUserService(String emailAddress, String password) throws ObjectNotFoundException;

    long deleteUserService(String emailAddress, String password);

    JSONObject updateUserService(String emailAddress, String password, User user);

    JSONObject getUserServiceWithEmail(String emailAddress) throws ObjectNotFoundException;

}
