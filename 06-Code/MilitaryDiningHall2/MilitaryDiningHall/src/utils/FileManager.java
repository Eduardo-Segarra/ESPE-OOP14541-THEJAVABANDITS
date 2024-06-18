package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import ec.edu.espe.militarydininghall.model.Dishes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;


/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class FileManager {

    public static void save(Commensal newCommensal, String fileName) {
        fileName = fileName + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Commensal> commensals = new ArrayList<>();

        // Leer el archivo JSON existente
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            Type listType = new TypeToken<ArrayList<Commensal>>() {
            }.getType();
            commensals = gson.fromJson(bufferedReader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating a new one.");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Agregar el nuevo objeto a la lista
        commensals.add(newCommensal);

        // Guardar la lista actualizada de nuevo en el archivo JSON
        try (FileWriter fileWriter = new FileWriter(fileName); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            gson.toJson(commensals, bufferedWriter);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void updateAccount(Commensal updatedCommensal, String fileName, Commensal oldCommensalInformation) {
        fileName = fileName + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Commensal> commensals = new ArrayList<>();

        // Leer el archivo JSON existente
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            Type listType = new TypeToken<ArrayList<Commensal>>() {
            }.getType();
            commensals = gson.fromJson(bufferedReader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating a new one.");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Reemplazar la información antigua con la nueva
        boolean found = false;
        for (int i = 0; i < commensals.size(); i++) {
            if (commensals.get(i).getId() == oldCommensalInformation.getId()) {
                commensals.set(i, updatedCommensal);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Old commensal information not found, adding new commensal.");
            commensals.add(updatedCommensal);
        }

        // Guardar la lista actualizada de nuevo en el archivo JSON
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            gson.toJson(commensals, bufferedWriter);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static String findAccount(String fileName, int idSearch, String email, String password) {
        List<Commensal> foundAccount = new ArrayList<>();
        Gson gson = new Gson();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataInJSON = new StringBuilder();
            String line;

            while ((line = bufferReader.readLine()) != null) {
                dataInJSON.append(line);
            }

            Type accountListType = new TypeToken<ArrayList<Commensal>>() {
            }.getType();
            foundAccount = gson.fromJson(dataInJSON.toString(), accountListType);

            for (Commensal account : foundAccount) {
                if ((account.getId() == idSearch) || ((account.getEmail().equals(email)) && (account.getPassword().equals(password)))) {
                    return account.getId() + ":" + account.getName() + ":" + account.getEmail() + ":" + account.getPassword() + ":" + account.getGrade() + ":" + account.getType() + ":" + account.getBalance();
                }
            }

        } catch (IOException e) {
            System.err.println("Error at finding data of the file: " + e.getMessage());
        }
        return null;
    }
public static boolean findAccountById(String fileName, long idToCheck) {
        Gson gson = new Gson();
        List<Commensal> commensalList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataInJSON = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                dataInJSON.append(line);
            }

            Type accountListType = new TypeToken<ArrayList<Commensal>>() {}.getType();
            commensalList = gson.fromJson(dataInJSON.toString(), accountListType);

            for (Commensal commensal : commensalList) {
                if (commensal.getId() == idToCheck) {
                    return true; // El ID ya existe en los registros
                }
            }

        } catch (IOException e) {
            System.err.println("Error at finding data of the file: " + e.getMessage());
        }

        return false; // El ID no existe en los registros
    }

    public static void eraseAccount(String fileName, int idSearch) {
        fileName = fileName + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Commensal> accounts = new ArrayList<>();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataInJSON = new StringBuilder();
            String line;

            while ((line = bufferReader.readLine()) != null) {
                dataInJSON.append(line);
            }

            Type accountListType = new TypeToken<ArrayList<Commensal>>() {
            }.getType();
            accounts = gson.fromJson(dataInJSON.toString(), accountListType);

            // Filtrar las cuentas para excluir la que tiene el idSearch
            List<Commensal> updatedAccounts = new ArrayList<>();
            for (Commensal account : accounts) {
                if (account.getId() != idSearch) {
                    updatedAccounts.add(account);
                }
            }

            // Escribir las cuentas actualizadas de nuevo en el archivo
            try (FileWriter fileWriter = new FileWriter(fileName); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                gson.toJson(updatedAccounts, bufferedWriter);
            } catch (IOException e) {
                System.err.println("Error writing to the file: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error at erasing data from the file: " + e.getMessage());
        }
    }
    // Para el DateBook
    public static void saveDateBook(DateBook dateBook) {
        String fileName = "datebook.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<DateBook> listDateBook = new ArrayList<>();
            File file = new File(fileName);

            if (file.exists()) {
                listDateBook = mapper.readValue(file, new TypeReference<List<DateBook>>() {
                });

                boolean found = false;
                for (int i = 0; i < listDateBook.size(); i++) {
                    DateBook scannerDateBook = listDateBook.get(i);
                    if (scannerDateBook.getId() == dateBook.getId()) {
                        listDateBook.set(i, dateBook);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    listDateBook.add(dateBook);
                }
            } else {
                listDateBook.add(dateBook);
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(fileName), listDateBook);

            System.out.println("DateBook saved.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error to load DateBook.");
        }
    }

    public static DateBook loadDateBook(int id) {
        String fileName = "datebook.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(fileName);
            List<DateBook> listDateBook = new ArrayList<>();
            if (file.exists()) {
                listDateBook = mapper.readValue(file, new TypeReference<List<DateBook>>() {
                });
            }

            for (DateBook dateBook : listDateBook) {
                if (dateBook.getId() == id) {
                    return dateBook;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveDishes(Dishes dish) {
        String filename = "dishes.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Dishes> dishesList = new ArrayList<>();
            File file = new File(filename);

            if (file.exists()) {
                dishesList = mapper.readValue(file, new TypeReference<List<Dishes>>() {
                });
            }

            boolean found = false;
            for (int i = 0; i < dishesList.size(); i++) {
                Dishes existingDish = dishesList.get(i);
                if (existingDish.getMonth() == dish.getMonth()) {
                    dishesList.set(i, dish);
                    found = true;
                    break;
                }
            }

            if (!found) {
                dishesList.add(dish);
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(filename), dishesList);
            System.out.println("Saved.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error to load Dishes.");
        }
    }

    public static Dishes loadDishesByMonth(int month) {
        String filename = "dishes.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filename);
            List<Dishes> dishesList = new ArrayList<>();

            if (file.exists()) {
                dishesList = mapper.readValue(file, new TypeReference<List<Dishes>>() {
                });
            }

            for (Dishes dish : dishesList) {
                if (dish.getMonth() == month) {
                    return dish;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
