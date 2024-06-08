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

    public static void valideInt(int lowerOption, int higherOption, int userInput) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                userInput = scanner.nextInt();
                if (userInput < lowerOption || userInput > higherOption) {
                    throw new IllegalArgumentException("The option can't be lower than " + lowerOption + " or higher than " + higherOption + ".");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please type an entire number.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void validateYear() {
        Scanner scanner = new Scanner(System.in);
        int year;

        while (true) {
            try {
                System.out.println("Enter the born year of the chicken: ");
                year = Integer.parseInt(scanner.nextLine());
                if (year < LocalDate.now().getYear()) {
                    throw new IllegalArgumentException("The year can't be higher than the current year.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Please type an entire number for the year.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void validateMonth() {
        Scanner scanner = new Scanner(System.in);
        int month;
        while (true) {
            System.out.print("Enter the month (1-12): ");
            try {
                month = scanner.nextInt();
                if (month < 1 || month > 12) {
                    throw new IllegalArgumentException("Month must be between 1 and 12.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a number between 1 and 12.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void validateDay(int year, int month) {
        Scanner scanner = new Scanner(System.in);
        int day;
        while (true) {
            System.out.print("Enter the day: ");
            try {
                day = scanner.nextInt();
                YearMonth yearMonth = YearMonth.of(year, month);
                int maxDay = yearMonth.lengthOfMonth();
                if (day < 1 || day > maxDay) {
                    throw new IllegalArgumentException("Day must be between 1 and " + maxDay + ".");
                } else {
                    break;
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