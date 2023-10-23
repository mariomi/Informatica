// MainProgram.java

import java.util.Scanner;
import java.util.List;

public class MainProgram {
    public static void main(String[] args) {
        try (DBManager db = new DBManager()) {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println("Menu:");
                    System.out.println("1 - Inserisci un elemento");
                    System.out.println("2 - Visualizza l'elenco");
                    System.out.println("3 - Modifica un elemento");
                    System.out.println("4 - Cancella un elemento");
                    // ... Other options go here ...
                    System.out.println("99 - Esci");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over

                    switch (choice) {
                        case 1:
                            System.out.println("Inserisci Nome:");
                            String name = scanner.nextLine();
                            System.out.println("Inserisci Cognome:");
                            String surname = scanner.nextLine();
                            System.out.println("Inserisci età:");
                            int age = scanner.nextInt();
                            if (db.insertElement(name, surname, age) > 0) {
                                System.out.println("Elemento inserito con successo.");
                            } else {
                                System.out.println("Errore durante l'inserimento.");
                            }
                            break;

                        case 2:
                            List<String> students = db.viewAll();
                            for (String student : students) {
                                System.out.println(student);
                            }
                            break;

                            case 3:
                            System.out.println("Inserisci ID dell'alunno da modificare:");
                            String oldID = scanner.nextLine();
                            System.out.println("Inserisci Nome:");
                            name = scanner.nextLine();
                            System.out.println("Inserisci Cognome:");
                            surname = scanner.nextLine();
                            System.out.println("Inserisci età:");
                            age = scanner.nextInt();
                            scanner.nextLine();  // consume the newline
                            // First, delete the old record
                            if (db.deleteElement(oldID) <= 0) {
                                System.out.println("Errore durante la modifica.");
                                break;
                            }
                            // Now, insert the new record with the generated ID
                            if (db.insertElement(name, surname, age) > 0) {
                                System.out.println("Elemento modificato con successo.");
                            } else {
                                System.out.println("Errore durante la modifica.");
                            }
                            break;
                        
                        case 4:
                            System.out.println("Inserisci ID dell'alunno da eliminare:");
                            oldID  = scanner.nextLine();
                            if (db.deleteElement(oldID) > 0) {
                                System.out.println("Elemento eliminato con successo.");
                            } else {
                                System.out.println("Errore durante l'eliminazione.");
                            }
                            break;
                        // ... Other cases to handle the other functionalities ...

                        case 99:
                            System.out.println("Arrivederci!");
                            return;

                        default:
                            System.out.println("Scelta non valida.");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
