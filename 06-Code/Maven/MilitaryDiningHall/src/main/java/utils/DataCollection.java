/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class DataCollection {
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
}
