/*
 * *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2024
 * Instructor: Prof. Lily

 * Name: Jean Marie Ngabonziza
 * Section: 9am section
 * Date: 11/18/24
 * Time: 7:24 PM

 * Project: csci205_final_project
 * Package: org.mahj.feelBitLogin
 * Class: MongoDBConnect

 * Description:
 * This class manages the connection to a MongoDB database. It uses the MongoDB Java Driver
 * to establish a connection, provides access to the database, and ensures proper
 * resource cleanup by closing the connection when it is no longer needed.
 * ****************************************
 */
package org.mahj.feelBitLogin;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * The {@code MongoDBConnect} class handles the connection to a MongoDB database using the
 * MongoDB Java Driver. It provides methods to access the database and close the connection.
 */
public class MongoDBConnect {

    /** The connection URI for connecting to the MongoDB cluster */
    private static final String CONNECTION_URI = "mongodb+srv://jnn004:juma12@cluster0.3pcc4.mongodb.net" +
            "/?retryWrites=true&w=majority&appName=Cluster0";

    /** The name of the database to connect to */
    private static final String DATABASE_NAME = "userDB";

    /** The MongoDB client instance */
    private static MongoClient mongoClient;

    /** The MongoDB database instance */
    private static MongoDatabase database;

    // Static block to initialize the connection
    static {
        try {
            // Create a new MongoDB client and connect to the database
            mongoClient = MongoClients.create(CONNECTION_URI);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Connected to MongoDB successfully!");
        } catch (Exception e) {
            // Handle any exceptions that occur during the connection setup
            e.printStackTrace();
            System.out.println("Failed to connect to MongoDB");
        }
    }

    /**
     * Retrieves the MongoDB database instance.
     *
     * @return the connected {@code MongoDatabase} instance.
     */
    public static MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Closes the connection to the MongoDB database.
     * This method should be called when the connection is no longer needed.
     */
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Connection to MongoDB closed");
        }
    }
}
