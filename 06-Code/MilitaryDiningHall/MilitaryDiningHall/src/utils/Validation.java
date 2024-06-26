package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class Validation {

    private static final String[] jsonFiles = {
        "commensals.json", "militaryChefs.json", "administrators.json", "generalAdministrator.json"
    };

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

    public static boolean validBoolean() {
        Scanner scanner = new Scanner(System.in);
        boolean userInput;

        while (true) {
            try {
                userInput = scanner.nextBoolean();
                if (userInput == true || userInput == false) {
                    return userInput;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please type true or false.");
                scanner.next();
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
                if (month < LocalDate.now().getMonthValue() || month > 12) {
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
                LocalDate today = LocalDate.now();

                if (month == today.getMonthValue() && year == today.getYear()) {
                    if (day < today.getDayOfMonth() || day > maxDay) {
                        throw new IllegalArgumentException("Day must be between " + today.getDayOfMonth() + " and " + maxDay + ".");
                    }
                } else {
                    if (day < 1 || day > maxDay) {
                        throw new IllegalArgumentException("Day must be between 1 and " + maxDay + ".");
                    }
                }

                return day;

            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a valid day.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String validateGradeAccount() {
        Scanner scanner = new Scanner(System.in);
        String grade;

        while (true) {
            grade = scanner.nextLine();

            if (grade.equals("Army General") || grade.equals("Major General")
                    || grade.equals("Brigadier General") || grade.equals("Colonel")
                    || grade.equals("Lieutenant Colonel") || grade.equals("Major")
                    || grade.equals("Captain") || grade.equals("Lieutenant")
                    || grade.equals("Second lieutenant") || grade.equals("Cadet")
                    || grade.equals("Senior sub officer") || grade.equals("1st Sub officer")
                    || grade.equals("2nd Sub officer") || grade.equals("1st Sergeant")
                    || grade.equals("2nd Sergeant") || grade.equals("1st Corporal")
                    || grade.equals("2nd Corporal") || grade.equals("Soldier")
                    || grade.equals("Conscript") || grade.equals("publicServant")) {
                return grade;
            } else {
                System.out.println("Invalid entry. Please enter a valid grade.");
            }
        }

    }

    public static long validateId() {
        Scanner scanner = new Scanner(System.in);

        long id = 0;
        String idInput, limitId = "2499999999";
        boolean isValid = false;
        boolean isDuplicate = false;

        while (!isValid || isDuplicate) {
            isDuplicate = false;
            System.out.println("Enter your ID (10 digits):");
            idInput = scanner.nextLine();
            if (idInput.length() == 10) {
                id = Long.parseLong(idInput);
                if(id < Long.parseLong(limitId)){
                  for (String accountFile : jsonFiles) {
                    if (isDuplicate = FileManager.findAccountById(accountFile, id)) {
                        break;
                    }
                }
                if (isDuplicate) {
                    System.out.println("The ID has already been entered. Please try again.");
                }else{
                    return id;
                }  
                }else{
                    System.out.println("The ID is incorrect, the first two digits should be between 01 to 24.");
                }
                
            } else {
                System.out.println("Invalid input. Please enter a numeric 10-digit ID.");
            }
        }
        return 0;
    }

}
