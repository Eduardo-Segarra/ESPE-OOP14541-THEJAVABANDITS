package Utils;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
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
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
