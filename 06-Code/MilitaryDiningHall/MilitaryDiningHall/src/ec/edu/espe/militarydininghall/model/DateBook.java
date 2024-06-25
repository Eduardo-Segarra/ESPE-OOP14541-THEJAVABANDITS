package ec.edu.espe.militarydininghall.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import utils.FileManager;
import utils.Validation;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */
public class DateBook {

    public static void bookDay(int id) {
        int day;
        int month;
        int year = 2024;
        DateBook dateBook = FileManager.loadDateBook(id);
        String date;
        System.out.print("Please enter the month of your booking ");
        month = Validation.validateMonth();
        // Notification for the month selected
        Dish dishes = FileManager.loadDishesByMonth(month);
        System.out.println(dishes.notification());
        System.out.print("Please enter the day of your booking ");
        day = Validation.validateDay(year, month);
        date = day + "/" + month + "/" + year;
        dateBook.addDay(date);
        FileManager.saveDateBook(dateBook);
    }

    public static void cancelDayBook(int id) {
        Scanner scanner = new Scanner(System.in);
        DateBook dateBook = FileManager.loadDateBook(id);
        if (dateBook.ListOfDays().isBlank() == false) {
            System.out.println(dateBook.ListOfDays());
            System.out.println("What day you want to cancel the booking?(Type the whole date):");
            String date = scanner.nextLine();
        } else {
            System.out.println("No dates added");
        }
    }

    private long id;
    private Map<String, Boolean> reservedDays;

    public DateBook() {
        // It have to be empty for working
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

    public DateBook(long id, Map<String, Boolean> reservedDays) {
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
            System.out.println("Change status: \ntrue = Attended \nfalse = Missing");
            Boolean dateStatus = Validation.validBoolean();
            reservedDays.put(date, dateStatus);
        }
        else{
            System.out.println("Date not assigned");
        }
    }
    
  
}