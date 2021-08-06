package com.company.dao;

import com.company.customExceptions.ObjectNotFoundException;
import com.company.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.json.JSONObject;

/**
 * User Interface for DAO layer
 * Following methods are implemented in UserOperations Class in dao.impl package
 * All the Exceptions are thrown back to Service and then to CLI layer
 */
public interface UserDao {

    MongoCollection<Document> usersCollection();

    void createUser(User user);

    JSONObject getUser(String emailAddress, String password) throws ObjectNotFoundException;

    long deleteUser(String emailAddress, String password);

    JSONObject updateUser(String emailAddress, String password, User user);

    JSONObject getUserWithEmail(String emailAddress) throws ObjectNotFoundException;

}
