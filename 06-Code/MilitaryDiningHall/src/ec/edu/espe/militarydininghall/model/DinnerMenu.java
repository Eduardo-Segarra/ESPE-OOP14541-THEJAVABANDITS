/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.model;

/**
 *
 * @author THEJAVABANDITS,DCCO-ESPE
 */
public class DinnerMenu {
    private String breakfastName;
    private String lunchName;
    private String snackName;

    public DinnerMenu(String breakfastName, String lunchName, String snackName) {
        this.breakfastName = breakfastName;
        this.lunchName = lunchName;
        this.snackName = snackName;
    }

    public String getBreakfastName() {
        return breakfastName;
    }

    public void setBreakfastName(String breakfastName) {
        this.breakfastName = breakfastName;
    }

    public String getLunchName() {
        return lunchName;
    }

    public void setLunchName(String lunchName) {
        this.lunchName = lunchName;
    }

    public String getSnackName() {
        return snackName;
    }

    public void setSnackName(String snackName) {
        this.snackName = snackName;
    }

    @Override
    public String toString() {
        return "DinnerMenu{" +
                "breakfastName='" + breakfastName + '\'' +
                ", lunchName='" + lunchName + '\'' +
                ", snackName='" + snackName + '\'' +
                '}';
    }

    public void changeDishName(String mealType, String newName) {
        switch (mealType) {
            case "breakfast":
                setBreakfastName(newName);
                break;
            case "lunch":
                setLunchName(newName);
                break;
            case "snack":
                setSnackName(newName);
                break;
            default:
                System.out.println("Invalid meal type");
        }
        System.out.println("Dish name changed for " + mealType + ": " + newName);
        // Lógica para cambiar el nombre de un plato
    }
}