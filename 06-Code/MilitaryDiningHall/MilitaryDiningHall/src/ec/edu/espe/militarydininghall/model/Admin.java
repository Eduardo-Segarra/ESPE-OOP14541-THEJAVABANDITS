
package ec.edu.espe.militarydininghall.model;

import com.google.gson.Gson;
import ec.edu.espe.militarydininghall.view.MenuManager;
import java.util.Scanner;
import utils.FileManager;
import utils.Validation;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class Admin {

    private long id;
    private String name;
    private String email;
    private String password;
    private String grade;
    private String type;
    private float balance;

    public Admin(long id, String name, String email, String password, String grade, String type, float balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.grade = grade;
        this.type = type;
        this.balance = balance;
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
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

    public static void seeAccountBalance(int id, String fileName, String email, String password) {
        String data = "";
        data = FileManager.findAccount(fileName + ".json", id, email, password);
        String[] parts = data.split(":");

        System.out.println("Your account balance is: " + parts[6]);
    }

    public static void updateAccountBalance(int id, String email, String password) {
        Scanner scanner = new Scanner(System.in);
        int idSearch = 0, attempts;
        float updateBalance;
        String foundAccount = "", fileName = "";
        String[] partsData;

        for (attempts = 0; attempts < 5; attempts++) {
            System.out.println("Please type the ID of the account you want to update the account balance:");
            try {
                idSearch = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                foundAccount = FileManager.findAccount("commensals.json", idSearch, "", "");
                if (foundAccount != null) {
                    fileName = "commensals";
                    break;
                }

                foundAccount = FileManager.findAccount("administrators.json", idSearch, "", "");
                if (foundAccount != null) {
                    fileName = "administrators";
                    break;
                }

                foundAccount = FileManager.findAccount("generalAdministrator.json", idSearch, "", "");
                if (foundAccount != null) {
                    fileName = "generalAdministrator";
                    break;
                }

                if (attempts < 4) {
                    System.out.println("Incorrect ID. You have " + (4 - attempts) + " attempt(s) left.");
                } else if (attempts == 4) {
                    MenuManager.displayAdminMenu(id, email, password);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                scanner.next(); // Consume the invalid input
                attempts--; // Do not count this as a valid attempt
            }
        }

        if (foundAccount == null) {
            System.out.println("Account not found. Returning to admin menu.");
            MenuManager.displayAdminMenu(id, email, password);
            return;
        }

        partsData = foundAccount.split(":");

        Commensal outdatedAccountBalance = new Commensal(Long.parseLong(partsData[0]), partsData[1], partsData[2], partsData[3], partsData[4], partsData[5], Float.parseFloat(partsData[6]));

        do {
        System.out.println("Enter the desired amount: ");
        while (!scanner.hasNextFloat()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next(); // Consume the invalid input
        }
        updateBalance = scanner.nextFloat();
        if (updateBalance <= 0) {
            System.out.println("Amount must be greater than zero. Please try again.");
        }
    } while (updateBalance <= 0);

    partsData[6] = Float.toString(updateBalance);

    Commensal newAccountBalance = new Commensal(Long.parseLong(partsData[0]), partsData[1], partsData[2], partsData[3], partsData[4], partsData[5], Float.parseFloat(partsData[6]));

    FileManager.updateAccount(newAccountBalance, fileName, outdatedAccountBalance);
}

    public static void editRegister(int id, String email, String password) {
        Scanner scanner = new Scanner(System.in);
        int attempts, idSearch = 0;
        String foundAccount = "", fileName = "";
        String[] partsData;

        for (attempts = 0; attempts < 5; attempts++) {
            System.out.println("Enter de ID: ");
            try {
                idSearch = scanner.nextInt();

                foundAccount = FileManager.findAccount("commensals.json", idSearch, "", "");
                if (foundAccount != null) {
                    break;
                }

                foundAccount = FileManager.findAccount("administrators.json", idSearch, "", "");
                if (foundAccount != null) {
                    break;
                }

                foundAccount = FileManager.findAccount("generalAdministrator.json", idSearch, "", "");
                if (foundAccount != null) {
                    break;
                }

                if (attempts < 5 && foundAccount == null) { // Check to avoid printing after the last attempt
                    System.out.println("Incorrect ID. You have " + (5 - attempts) + " attempt(s) left.");
                } else if (attempts == 5) {
                    MenuManager.displayAdminMenu(id, email, password);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                scanner.next(); // Consume the invalid input
                attempts--; // Do not count this as a valid attempt
            }

        }

        if (foundAccount == null) {
            System.out.println("Account not found. Returning to the admin menu.");
            MenuManager.displayAdminMenu(id, email, password);
            return;
        }

        partsData = foundAccount.split(":");
        scanner.nextLine();

        DateBook dateBook = FileManager.loadDateBook(Long.parseLong(partsData[0]));
        if (dateBook != null) {
            System.out.println(dateBook.ListOfDays());
            System.out.println("Select the date to check: ");
            String date = scanner.nextLine();

            dateBook.checkDate(date);
            FileManager.saveDateBook(dateBook);
        } else {
            System.out.println("Date not assigned");
        }
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", grade=" + grade + ", type=" + type + ", balance=" + balance + '}';
    }

    public String toStringJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
