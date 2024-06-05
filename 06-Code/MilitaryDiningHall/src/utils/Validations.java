package Utils;

import java.util.Scanner;
import java.util.InputMismatchException;
/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */
public class Validations {
    
    public static int valideInt() {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                userInput = scanner.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Enter a INT number");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }

        return userInput;
    }
}
