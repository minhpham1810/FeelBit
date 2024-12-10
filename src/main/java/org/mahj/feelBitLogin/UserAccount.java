/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2024
 * Instructor: Prof. Lily
 *
 * Name: Jean Marie Ngabonziza
 * Section: 9am section
 * Date: 11/21/24
 * Time: 11:34 AM
 *
 * Project: csci205_final_project
 * Package: org.mahj.feelBitLogin
 * Class: UserAccount
 *
 * Description:
 * This class handles user account management functionalities for a MongoDB database.
 * It provides methods for creating new user accounts, logging in users,
 * and verifying existing usernames.
 *
 * ****************************************
 */
package org.mahj.feelBitLogin;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * The {@code UserAccount} class provides methods to manage user accounts,
 * including account creation, login functionality, and verification of usernames.
 * It interacts with a MongoDB collection to store and retrieve user data.
 */
public class UserAccount {
    /**
     * The MongoDB collection storing user account information.
     */
    private final MongoCollection<Document> userCollection;

    public MongoCollection<Document> getUserCollection() {
        return userCollection;
    }

    /**
     * Constructs a {@code UserAccount} instance and initializes the connection
     * to the MongoDB collection "users".
     */
    public UserAccount() {
        this.userCollection = MongoDBConnect.getDatabase().getCollection("users");
    }

}
