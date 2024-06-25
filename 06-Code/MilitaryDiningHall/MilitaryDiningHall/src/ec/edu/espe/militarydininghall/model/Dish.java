package ec.edu.espe.militarydininghall.model;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */
public class Dish {

    private int month;
    private String breakfast;
    private String lunch;
    private String dinner;

    public Dish() {
        // keep empty for it to work
    }

    public Dish(int month, String breakfast, String lunch, String dinner) {
        this.month = month;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the breakfast
     */
    public String getBreakfast() {
        return breakfast;
    }

    /**
     * @param breakfast the breakfast to set
     */
    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    /**
     * @return the lunch
     */
    public String getLunch() {
        return lunch;
    }

    /**
     * @param lunch the lunch to set
     */
    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    /**
     * @return the dinner
     */
    public String getDinner() {
        return dinner;
    }

    /**
     * @param dinner the dinner to set
     */
    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    @Override
    public String toString() {
        return "Dishes{" + "month=" + month + ", breakfast=" + breakfast + ", lunch=" + lunch + ", dinner=" + dinner + '}';
    }

    public String notification() {
        return "This month the dishes are:\nBreakfast: " + breakfast + " / Lunch: " + lunch + " / Dinner: " + dinner;
    }

}
