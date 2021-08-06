package com.company.dao.impl;

import com.company.customExceptions.ObjectNotFoundException;
import com.company.dao.UserDao;
import com.company.model.User;
import com.company.util.MongoInitializer;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.json.JSONObject;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * UserOperations implements UserDao interface
 * This class has all the methods regarding the User Database actions.
 */
public class UserOperations extends MongoInitializer implements UserDao {

    /**
     * Initializes mongoClient using MongoInitializer class and  Returns a User collection from OrderService Database
     *
     * @return returns a MongoCollection
     */
    @Override
    public MongoCollection<Document> usersCollection() {
        MongoClient mongoClient = mongoClientInitializer();
        return mongoClient.getDatabase("OrderService").getCollection("Users");
    }

    /**
     * Takes User object as parameter and Creates a Document of user in Database.
     *
     * @param user user Object passed from service layer
     */
    @Override
    public void createUser(User user) {
        MongoClient mongoClient = mongoClientInitializer();
        MongoCollection<Document> users = usersCollection();
        Document document = Document.parse(user.toJsonString());
        users.insertOne(document);
        mongoClient.close();

    }

    /**
     * Throws exception with User details if user with given id is present else throws Object not found ExceptionResource
     *
     * @param emailAddress Email address of user
     * @return JsonObject of the USER
     * @throws ObjectNotFoundException Prompts the message that the Object not found in DB
     */
    @Override
    public JSONObject getUser(String emailAddress, String password) throws ObjectNotFoundException {
        MongoClient mongoClient = mongoClientInitializer();
        MongoCollection<Document> users = usersCollection();
        Document user = users.find(and(eq("emailAddress", emailAddress), eq("password", password))).first();
        mongoClient.close();
        if (user == null) {
            throw new ObjectNotFoundException("User Id not found in the Database");
        } else {
            return new JSONObject(user.toJson());
        }
    }


    /**
     * deletes the User with given Id from Database if present or else throws object not found exception.
     *
     * @return deleted count
     */
    @Override
    public long deleteUser(String emailAddress, String password) {
        MongoClient mongoClient = mongoClientInitializer();
        MongoCollection<Document> users = usersCollection();
        DeleteResult deleteResult = users.deleteOne(and(eq("emailAddress", emailAddress), eq("password", password)));
        mongoClient.close();
        return deleteResult.getDeletedCount();
    }

    /**
     * update the User of given User id with only updates fields from the previous User data from upJsonObj in the Database
     *
     * @param user Json Object with only changed fields
     * @return updated user object
     */
    @Override
    public JSONObject updateUser(String emailAddress, String password, User user) {
        MongoClient mongoClient = mongoClientInitializer();
        MongoCollection<Document> users = usersCollection();
        BasicDBObject doc = new BasicDBObject();
        doc.append("firstName", user.getFirstName())
                .append("lastName", user.getLastName()).append("emailAddress", user.getEmailAddress())
                .append("phoneNumber", user.getPhoneNumber()).append("password", user.getPassword())
                .append("address.address1", user.getAddress1()).append("address.address2", user.getAddress2())
                .append("address.city", user.getCity()).append("address.state", user.getState())
                .append("address.country", user.getCountry()).append("address.postalCode", user.getPostalCode());

        BasicDBObject update = new BasicDBObject("$set", doc);
        BasicDBObject find = new BasicDBObject().append("emailAddress", emailAddress).append("password", password);
        FindOneAndUpdateOptions findOneAndUpdateOptions = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Document updatedUser = users.findOneAndUpdate(find, update, findOneAndUpdateOptions);
        mongoClient.close();

        assert updatedUser != null;
        return new JSONObject(updatedUser.toJson());

    }

    @Override
    public JSONObject getUserWithEmail(String emailAddress) throws ObjectNotFoundException {
        MongoClient mongoClient = mongoClientInitializer();
        MongoCollection<Document> users = usersCollection();
        Document user = users.find(eq("emailAddress", emailAddress)).first();
        mongoClient.close();
        if (user == null) {
            throw new ObjectNotFoundException("User Id not found in the Database");
        } else {
            return new JSONObject(user.toJson());
        }
    }

}
