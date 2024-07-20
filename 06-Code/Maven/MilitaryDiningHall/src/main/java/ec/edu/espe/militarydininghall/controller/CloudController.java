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
import com.mongodb.MongoClientURI;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class CloudController {

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

    public static String obtainIdFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("id").getAsString();
    }

    public static String obtainNameFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("name").getAsString();
    }

    public static String obtainEmailFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("email").getAsString();
    }

    public static String obtainPasswordFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("password").getAsString();
    }

    public static String obtainGradeFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("grade").getAsString();
    }

    public static String obtainTypeFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("type").getAsString();
    }

    public static Float obtainBalanceFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return Float.valueOf(jsonObject.get("balance").getAsString());
    }

    public static String login(String email, String password) {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://segarra:segarra@cluster0.b2q6ac3.mongodb.net/");

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("oop");
            MongoCollection<Document> collection = database.getCollection("commensals");

            Document query = new Document("email", email).append("password", password);
            Document findedDocument = collection.find(query).first();

            if (findedDocument != null) {
                return findedDocument.toJson();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
