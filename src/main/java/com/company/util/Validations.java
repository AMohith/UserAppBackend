package com.company.util;

import com.company.model.User;

/**
 * Validations for all input fields in the project.
 */
public class Validations {

    //User model fields Validations
    public static boolean userFirstNameValidation(String firstName) {
        return !(firstName.matches("^[a-zA-Z ]{3,35}$"));
    }

    public static boolean userLastNameValidation(String lastName) {
        return !lastName.matches("^[a-zA-Z ]{3,35}$");
    }

    public static boolean userEmailAddressValidation(String emailAddress) {
        return !emailAddress.matches("^[a-zA-Z0-9._&%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean userPhoneNumberValidation(String phoneNumber) {
        return !(phoneNumber.matches("^\\d{10}$") && Long.parseLong(phoneNumber) != 0);
    }

    public static boolean userAddressLineValidation(String addressLine) {
        return !(addressLine.matches("^[a-zA-Z0-9-:';,./_@& ]{5,100}$") && !addressLine.matches("^[0-9-:';,./_@& ]{5,100}$"));
    }

    public static boolean userCityValidation(String city) {
        return !city.matches("^[a-zA-Z-:',_@& ]{2,85}$");
    }

    public static boolean userStateValidation(String state) {
        return !state.matches("^[a-zA-Z-:',_@& ]{2,60}$");
    }

    public static boolean userCountryValidation(String country) {
        return !country.matches("^[a-zA-Z-:',_@& ]{2,60}$");
    }

    public static boolean userPostalCodeValidation(String postalCode) {
        return !(postalCode.matches("^\\d{6}$") && Long.parseLong(postalCode) != 0);
    }

    public boolean userValidation(User user) {
        boolean notValid;

        notValid = userFirstNameValidation(user.getFirstName()) ||
                userLastNameValidation(user.getLastName()) ||
                userEmailAddressValidation(user.getEmailAddress()) ||
                userPhoneNumberValidation(user.getPhoneNumber()) ||
                userAddressLineValidation(user.getAddress1()) ||
                userAddressLineValidation(user.getAddress2()) ||
                userCountryValidation(user.getCountry()) ||
                userStateValidation(user.getState()) ||
                userCityValidation(user.getCity()) ||
                userPostalCodeValidation(String.valueOf(user.getPostalCode()));


        return !notValid;
    }

}
