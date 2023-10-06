import java.io.*;
import java.util.Scanner;

public class Archivio {

    private static final String FILE_PATH = "..anagrafico.dat";

    public static void main(String[] args) throws IOException {
        boolean esci = false;
        Scanner scanner = new Scanner(System.in);

        while (!esci) {
            System.out.println("\nMenu:");
            System.out.println("1) Inserire un utente");
            System.out.println("2) Ricerca di un utente");
            System.out.println("3) Visualizza tutto l'elenco");
            System.out.println("4) Cancellazione di un utente");
            System.out.println("9) Esci dal programma");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();  // Consuma il newline rimanente dopo nextInt()

            switch (scelta) {
                case 1:
                    inserisciUtente(scanner);
                    break;
                case 2:
                    cercaUtente(scanner);
                    break;
                case 3:
                    visualizzaElenco();
                    break;
                case 4:
                    cancellaUtente(scanner);
                    break;
                case 9:
                    esci = true;
                    System.out.println("Programma terminato.");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        }
    }

    private static void inserisciUtente(Scanner scanner) throws IOException {
        System.out.println("\nInserisci un utente:");
        System.out.print("Cognome: ");
        String cognome = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Indirizzo: ");
        String indirizzo = scanner.nextLine();

        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "rw")) {
            file.seek(file.length());
            writeString(file, cognome);
            writeString(file, nome);
            writeString(file, indirizzo);
            System.out.println("Utente inserito con successo.");
        }
    }

    private static void cercaUtente(Scanner scanner) throws IOException {
        System.out.println("\nRicerca di un utente:");
        System.out.print("Inserisci il cognome da cercare: ");
        String cognomeCercato = scanner.nextLine();

        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "r")) {
            boolean trovato = false;
            while (file.getFilePointer() < file.length()) {
                String cognome = readString(file);
                String nome = readString(file);
                String indirizzo = readString(file);

                if (cognome.equalsIgnoreCase(cognomeCercato)) {
                    System.out.println("Utente trovato:");
                    System.out.println("Cognome: " + cognome);
                    System.out.println("Nome: " + nome);
                    System.out.println("Indirizzo: " + indirizzo);
                    trovato = true;
                }
            }

            if (!trovato) {
                System.out.println("Utente non trovato.");
            }
        }
    }

    private static void visualizzaElenco() throws IOException {
        System.out.println("\nElenco utenti:");

        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "r")) {
            while (file.getFilePointer() < file.length()) {
                String cognome = readString(file);
                String nome = readString(file);
                String indirizzo = readString(file);

                System.out.println("Cognome: " + cognome);
                System.out.println("Nome: " + nome);
                System.out.println("Indirizzo: " + indirizzo);
                System.out.println("----------");
            }
        }
    }

    private static void cancellaUtente(Scanner scanner) throws IOException {
        System.out.println("\nCancellazione di un utente:");
        System.out.print("Inserisci il cognome dell'utente da cancellare: ");
        String cognomeCancellare = scanner.nextLine();

        try (RandomAccessFile inputFile = new RandomAccessFile(FILE_PATH, "rw");
             RandomAccessFile tempFile = new RandomAccessFile("temp.dat", "rw")) {

            while (inputFile.getFilePointer() < inputFile.length()) {
                String cognome = readString(inputFile);
                String nome = readString(inputFile);
                String indirizzo = readString(inputFile);

                if (!cognome.equalsIgnoreCase(cognomeCancellare)) {
                    writeString(tempFile, cognome);
                    writeString(tempFile, nome);
                    writeString(tempFile, indirizzo);
                }
            }

            // Rinomina il file temporaneo per sostituire il file originale
            File originalFile = new File(FILE_PATH);
            File tempFileRenamed = new File("temp.dat");
            tempFileRenamed.renameTo(originalFile);

            System.out.println("Utente cancellato con successo.");
        }
    }

    private static void writeString(RandomAccessFile file, String str) throws IOException {
        byte[] bytes = str.getBytes();
        file.writeInt(bytes.length);
        file.write(bytes);
    }

    private static String readString(RandomAccessFile file) throws IOException {
        int length = file.readInt();
        byte[] bytes = new byte[length];
        file.readFully(bytes);
        return new String(bytes);
    }
}
