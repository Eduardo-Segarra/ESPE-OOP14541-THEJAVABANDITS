package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
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

    public static int validateYear() {
        Scanner scanner = new Scanner(System.in);
        int year = 0;

        while (true) {
            try {
                year = Integer.parseInt(scanner.nextLine());
                if (year < LocalDate.now().getYear()) {
                    throw new IllegalArgumentException("The year can't be lower than the current year.");
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
                if (month == LocalDate.now().getMonthValue()) {
                    if (day < LocalDate.now().getDayOfMonth() || day > maxDay) {
                        throw new IllegalArgumentException("Day must be between " + LocalDate.now().getDayOfMonth() + " and " + maxDay + ".");
                    } else {
                        return day;
                    }
                } else {
                    if (day < 1 || day > maxDay) {
                        throw new IllegalArgumentException("Day must be between 1 and " + maxDay + ".");
                    } else {
                        return day;
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a valid day.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public class IdValidator {

        public static boolean validateId(long id) {
        // Verificar que el ID tiene exactamente 10 dígitos
        if (String.valueOf(id).length() != 10) {
            return false;
        }

        int[] digits = new int[10];
        int remainder, mul, evenSum = 0, oddSum = 0, totalSum, checkDigit;

        // Convertir el long en dígitos numéricos
        for (int i = 9; i >= 0; i--) {
            digits[i] = (int) (id % 10);
            id /= 10;
        }

        // Sumar los dígitos en posiciones pares (índices 0, 2, 4, 6, 8)
        for (int i = 0; i < 9; i += 2) {
            mul = digits[i] * 2;
            if (mul > 9) {
                mul -= 9;
            }
            evenSum += mul;
        }

        // Sumar los dígitos en posiciones impares (índices 1, 3, 5, 7)
        for (int i = 1; i < 9; i += 2) {
            oddSum += digits[i];
        }

        totalSum = evenSum + oddSum;
        remainder = totalSum % 10;
        checkDigit = 10 - remainder;
        if (checkDigit == 10) {
            checkDigit = 0;
        }

        return checkDigit == digits[9];
    }
    }
}