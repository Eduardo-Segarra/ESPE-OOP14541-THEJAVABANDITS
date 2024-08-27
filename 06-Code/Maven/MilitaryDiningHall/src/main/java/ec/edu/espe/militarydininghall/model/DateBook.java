/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ismael Travez, The Java Bandits, DCCO-ESPE
 */
public class DateBook {

    private long id;
    private Map<String, Boolean> reservedDays;

    private static DateBook instance;

    private DateBook() {
        reservedDays = new LinkedHashMap<>();
    }

    public static synchronized DateBook getInstance() {
        if (instance == null) {
            instance = new DateBook();
        }
        return instance;
    }

    public DateBook(long id, Map<String, Boolean> reservedDays) {
        this.id = id;
        this.reservedDays = reservedDays;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the reservedDays
     */
    public Map<String, Boolean> getReservedDays() {
        return reservedDays;
    }

    /**
     * @param reservedDays the reservedDays to set
     */
    public void setReservedDays(Map<String, Boolean> reservedDays) {
        this.reservedDays = reservedDays;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addDay(String date, boolean assistance) {
        reservedDays.put(date, assistance);
    }

    public void changeAssistance(String date, boolean assistance) {
        addDay(date, assistance);
    }
}
