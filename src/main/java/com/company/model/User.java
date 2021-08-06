package com.company.model;


/**
 * Address class is base class for User class.
 */
public class User extends Address {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String password;


    /**
     * User Constructor with all the below parameters. It constructs an object with values directly.
     *
     * @param firstName    First Name of the User
     * @param lastName     Last Name of the User
     * @param emailAddress Email Address of the User
     * @param phoneNumber  Phone Number of the User
     */
    public User(String id, String firstName, String lastName, String emailAddress, String phoneNumber, String password,
                String address1, String address2, String city, String state, String country, int postalCode) {

        super(address1, address2, city, state, country, postalCode);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;

    }


    /**
     * Basic User Constructor.
     */
    public User() {
        super();
    }

    /**
     * User class, toJsonString method
     *
     * @return returns JSON String of User Object including Address.
     * super keyword is used to call Address class, toJsonString method.
     */
    public String toJsonString() {
        return "{ " +
                "\"id\" : \"" + id + "\"" +
                ", \"firstName\" : \"" + firstName + "\"" +
                ", \"lastName\" : \"" + lastName + "\"" +
                ", \"emailAddress\" : \"" + emailAddress + "\"" +
                ", \"phoneNumber\" : \"" + phoneNumber + "\"" +
                ", \"password\" : \"" + password + "\"" +
                ", \"address\" : "
                + super.toJsonString() + " }";
    }

    /**
     * Following are the Getters and Setters of all the variables of User class.
     * Setter for Id field is unnecessary.
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
