/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class Validations {

    public static int valideInt(int lowerOption, int higherOption) {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        
        while (true) {
            try {
                userInput = scanner.nextInt();
                if (userInput < lowerOption || userInput > higherOption) {
                    throw new IllegalArgumentException("The option can't be lower than " + lowerOption + " or higher than " + higherOption + ".");
                } else {
                    return userInput;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please type an entire number.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int validateYear() {
        Scanner scanner = new Scanner(System.in);
        int year = 0;

        while (true) {
            try {
                year = Integer.parseInt(scanner.nextLine());
                if (year < LocalDate.now().getYear()) {
                    throw new IllegalArgumentException("The year can't be higher than the current year.");
                } else {
                    return year;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Please type an entire number for the year.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int validateMonth() {
        Scanner scanner = new Scanner(System.in);
        int month;

        while (true) {
            try {
                month = scanner.nextInt();
                if (month < LocalDate.now().getMonthValue()) {
                    throw new IllegalArgumentException("Month must be between " + LocalDate.now().getMonthValue() + " and 12.");
                } else {
                    return month;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a number between 1 and 12.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int validateDay(int year, int month) {
        Scanner scanner = new Scanner(System.in);
        int day;

        while (true) {
            try {
                day = scanner.nextInt();
                YearMonth yearMonth = YearMonth.of(year, month);
                int maxDay = yearMonth.lengthOfMonth();
                if (day < LocalDate.now().getDayOfMonth() || day > maxDay) {
                    throw new IllegalArgumentException("Day must be between " + LocalDate.now().getDayOfMonth() + " and " + maxDay + ".");
                } else {
                    return day;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a valid day.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
