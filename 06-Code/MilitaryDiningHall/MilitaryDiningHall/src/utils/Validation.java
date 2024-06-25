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

    public class IdValidator {

        public static boolean validateId(long id) {
            // Verify that the ID has exactly 10 digits
            if (String.valueOf(id).length() != 10) {
                return false;
            }

            int[] digits = new int[10];
            int remainder;
            int doubledDigit;
            int evenPositionSum = 0;
            int oddPositionSum = 0;
            int totalSum;
            int checkDigit;

            // Convert long to numerical digits
            for (int i = 9; i >= 0; i--) {
                digits[i] = (int) (id % 10);
                id /= 10;
            }

            // Add the digits in even positions (indexes 0, 2, 4, 6, 8)
            for (int i = 0; i < 9; i += 2) {
                doubledDigit = digits[i] * 2;
                if (doubledDigit > 9) {
                    doubledDigit -= 9;
                }
                evenPositionSum += doubledDigit;
            }

            // Add the digits in odd positions (indexes 1, 3, 5, 7)
            for (int i = 1; i < 9; i += 2) {
                oddPositionSum += digits[i];
            }

            totalSum = evenPositionSum + oddPositionSum;
            remainder = totalSum % 10;
            checkDigit = 10 - remainder;
            if (checkDigit == 10) {
                checkDigit = 0;
            }

            return checkDigit == digits[9];
        }
    }

}
