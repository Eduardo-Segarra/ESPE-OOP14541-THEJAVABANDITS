/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.controller;

import com.google.gson.Gson;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.ConnectionString;
import org.bson.Document;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoClient;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class CloudController {

    private static final String[] collections = {
        "commensals", "militaryChefs", "administrators", "generalAdministrator"
    };

    public static boolean create(Object object) {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://segarra:segarra@cluster0.b2q6ac3.mongodb.net/");
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("oop");

            MongoCollection<Document> collection = database.getCollection("commensals");

            Gson gson = new Gson();
            String json = gson.toJson(object);
            Document studentDocument = Document.parse(json);

            collection.insertOne(studentDocument);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String login(String email, String password) {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://segarra:segarra@cluster0.b2q6ac3.mongodb.net/");

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("oop");
            
            for (String accountFiles : collections) {
                MongoCollection<Document> collection = database.getCollection(accountFiles);

                Document query = new Document("email", email).append("password", password);
                Document findedDocument = collection.find(query).first();

                if (findedDocument != null) {
                    return findedDocument.toJson();
                }
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
