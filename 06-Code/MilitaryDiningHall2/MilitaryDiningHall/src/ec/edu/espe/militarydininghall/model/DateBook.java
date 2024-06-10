package ec.edu.espe.militarydininghall.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Ismael Travez, The Java Bandits, DCCO-ESPE
 */
public class DateBook {

    private int id;
    private Map<String, Boolean> reservedDays;

    public DateBook() {
        // Se debe dejar vacio para poder funcionar
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
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

    public DateBook(int id, Map<String, Boolean> reservedDays) {
        this.id = id;
        this.reservedDays = reservedDays;
    }

    public void addDay(String date) {
        reservedDays.put(date, false);
        System.out.println("Date added");
    }

    public void removeDay(String date) {
        reservedDays.remove(date);
        System.out.println("Date removed");
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

    @Override
    public String toString() {
        return "DateBook{" + "id=" + id + ", reservedDays=" + reservedDays + '}';
    }

    public String ListOfDays() {
        return "ReservedDays:\n" + reservedDays.keySet();
    }

    public void checkDate(String date) {
        Scanner scanner = new Scanner(System.in);
        String status;
        if (reservedDays.containsKey(date)) {
            if (reservedDays.get(date)) {
                status = "Attended";
            } else {
                status = "Missing";
            }
            System.out.println(date + " : " + status); 
            System.out.println("Change status: \nAttended = true \nMissing = false");
            Boolean dateStatus = scanner.nextBoolean();
            reservedDays.put(date, dateStatus);
        }
        else{
            System.out.println("Date not assigned");
        }
    }

}
