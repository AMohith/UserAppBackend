package com.company.cli;

import com.company.customExceptions.DisplayEverythingInPreviousStateException;
import com.company.customExceptions.DisplayMessageException;
import com.company.customExceptions.ObjectNotFoundException;

/**
 * User Interface for Controller (CLI Layer)
 * Following methods are implemented in UserController Class in CLI package
 * All the Exceptions are thrown back to CLI Layer from Service or DAO Layer
 */
public interface UserCLI {
    void CRUDAction();

    void createUserCLI() throws DisplayMessageException;

    void getUserCLI() throws ObjectNotFoundException;

    void deleteUserCLI() throws ObjectNotFoundException, DisplayMessageException;

    void updateUserCLI() throws ObjectNotFoundException, DisplayEverythingInPreviousStateException, DisplayMessageException;
}
