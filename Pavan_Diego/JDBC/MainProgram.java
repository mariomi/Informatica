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
                    System.out.println("5 - Contare il numero di alunni con età maggiore di un certo valore");
                    System.out.println("6 - Visualizzare l'età media");
                    System.out.println("7 - Visualizzare gli alunni più vecchi");
                    System.out.println("8 - Visualizzare gli alunni che hanno nel nome una sequenza data");
                    System.out.println("9 - Aumentare l'età di tutti gli alunni di un anno");
                    System.out.println("10 - Copiare nella tabella AlunniBack tutti i dati della tabella Alunni");
                    System.out.println("11 - Spostare nella tabella AlunniOld tutti gli alunni di una certa età");
        
                    System.out.println("99 - Esci");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

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
                            scanner.nextLine();
                            if (db.deleteElement(oldID) <= 0) {
                                System.out.println("Errore durante la modifica.");
                                break;
                            }
                            if (db.insertElement(name, surname, age) > 0) {
                                System.out.println("Elemento modificato con successo.");
                            } else {
                                System.out.println("Errore durante la modifica.");
                            }
                            break;

                        case 4:
                            System.out.println("Inserisci ID dell'alunno da eliminare:");
                            oldID = scanner.nextLine();
                            if (db.deleteElement(oldID) > 0) {
                                System.out.println("Elemento eliminato con successo.");
                            } else {
                                System.out.println("Errore durante l'eliminazione.");
                            }
                            break;
                        case 5:
                            System.out.println("Inserisci l'età minima:");
                            int ageThreshold = scanner.nextInt();
                            int count = db.countStudentsAboveGivenAge(ageThreshold);
                            System.out.println("Numero di alunni con età maggiore di " + ageThreshold + ": " + count);
                            break;

                        case 6:
                            double avgAge = db.averageAge();
                            System.out.println("Età media degli alunni: " + avgAge);
                            break;

                        case 7:
                            List<String> oldest = db.oldestStudents();
                            System.out.println("Alunni più vecchi:");
                            for (String student : oldest) {
                                System.out.println(student);
                            }
                            case 8:
                            System.out.println("Inserisci la sequenza da cercare nel nome:");
                            String sequence = scanner.nextLine();
                            List<String> matched = db.studentsWithNameSequence(sequence);
                            System.out.println("Alunni che corrispondono:");
                            for (String student : matched) {
                                System.out.println(student);
                            }
                            break;
        
                        case 9:
                            db.increaseAllStudentsAge();
                            System.out.println("Età di tutti gli alunni aumentata di un anno.");
                            break;
        
                        case 10:
                            int copied = db.copyToAlunniBack();
                            System.out.println(copied + " alunni copiati nella tabella AlunniBack.");
                            break;
        
                        case 11:
                            System.out.println("Inserisci l'età degli alunni da spostare in AlunniOld:");
                            int ageThreshold1 = scanner.nextInt();
                            int moved = db.moveToAlunniOld(ageThreshold1);
                            System.out.println(moved + " alunni spostati nella tabella AlunniOld.");
                            break;

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
