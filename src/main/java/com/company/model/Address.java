package com.company.model;

public abstract class Address {
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private int postalCode;


    /**
     * Address Constructor with all the below parameters. It constructs an object with values directly.
     *
     * @param address1   Address line 1 of User
     * @param address2   Address line 2 of User
     * @param city       City of the user
     * @param state      State of the User
     * @param country    Country of the user
     * @param postalCode Postal code of the User
     */
    public Address(String address1, String address2, String city,
                   String state, String country, int postalCode) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
    }

    /**
     * Basic Address class Constructor.
     */
    public Address() {

    }

    /**
     * Address class, toJsonString method
     *
     * @return returns JSON String of Address object
     */
    public String toJsonString() {
        return
                "{ \"address1\" : \"" + address1 + "\"" +
                        ", \"address2\" : \"" + address2 + "\"" +
                        ", \"city\" : \"" + city + "\"" +
                        ", \"state\" : \"" + state + "\"" +
                        ", \"country\" : \"" + country + "\"" +
                        ", \"postalCode\" : " + postalCode + " }"
                ;
    }

    /**
     * Following are the Getters and Setters of all the variables of Address class.
     */
    public String getAddress1() {
        return address1;
    }


    public void setAddress1(String address1) {
        this.address1 = address1;
    }


    public String getAddress2() {
        return address2;
    }


    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

}
