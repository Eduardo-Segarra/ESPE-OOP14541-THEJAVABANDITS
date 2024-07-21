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

    public static String findAccountById(String id) {

        ConnectionString connectionString = new ConnectionString("mongodb+srv://segarra:segarra@cluster0.b2q6ac3.mongodb.net/");

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("oop");

            for (String collectionName : database.listCollectionNames()) {
                System.out.println("Searching in collection: " + collectionName);

                MongoCollection<Document> collection = database.getCollection(collectionName);

                Document query = new Document("id", id);
                Document foundDocument = collection.find(query).first();

                if (foundDocument != null) {
                    System.out.println("Document found: " + foundDocument.toJson());
                    return foundDocument.toJson();
                }
            }
            System.out.println("No document found.");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

public static boolean updateCommensalBalance(String id, double additionalBalance) {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://segarra:segarra@cluster0.b2q6ac3.mongodb.net/");
    try (MongoClient mongoClient = MongoClients.create(connectionString)) {
        MongoDatabase database = mongoClient.getDatabase("oop");

        for (String collectionName : collections) {
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document query = new Document("id", id);
            Document foundDocument = collection.find(query).first();

            if (foundDocument != null) {
                Object balanceObj = foundDocument.get("balance");
                double currentBalance = 0;

                // Verifica el tipo del valor y conviértelo a double
                if (balanceObj instanceof Double) {
                    currentBalance = (Double) balanceObj;
                } else if (balanceObj instanceof Integer) {
                    currentBalance = ((Integer) balanceObj).doubleValue();
                } else {
                    // Maneja el caso en que el balance no es ni Double ni Integer
                    System.err.println("Unexpected type for balance: " + balanceObj.getClass().getName());
                    return false;
                }

                double newBalance = currentBalance + additionalBalance;

                Document update = new Document("$set", new Document("balance", newBalance));
                collection.updateOne(query, update);

                return true;
            }
        }
        return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    public static AccountDetails getAccountDetails(String id) {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://segarra:segarra@cluster0.b2q6ac3.mongodb.net/");
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("oop");

            for (String collectionName : collections) {
                MongoCollection<Document> collection = database.getCollection(collectionName);

                Document query = new Document("id", id);
                Document foundDocument = collection.find(query).first();

                if (foundDocument != null) {
                    double currentBalance = foundDocument.getDouble("balance") != null ? foundDocument.getDouble("balance") : 0.0;
                    double lastDeposit = foundDocument.getDouble("lastDeposit") != null ? foundDocument.getDouble("lastDeposit") : 0.0;
                    double latestWithdrawals = foundDocument.getDouble("latestWithdrawals") != null ? foundDocument.getDouble("latestWithdrawals") : 0.0;

                    return new AccountDetails(currentBalance, lastDeposit, latestWithdrawals);
                }
            }

            return null; // Si no se encuentra el documento.
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class AccountDetails {

        public double currentBalance;
        public double lastDeposit;
        public double latestWithdrawals;

        public AccountDetails(double currentBalance, double lastDeposit, double latestWithdrawals) {
            this.currentBalance = currentBalance;
            this.lastDeposit = lastDeposit;
            this.latestWithdrawals = latestWithdrawals;
        }
    }
}

