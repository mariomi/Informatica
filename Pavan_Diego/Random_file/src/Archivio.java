import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Archivio {
    private static int lunghezza = 50;
    private static final String FILE_PATH = "anagrafico.dat";
    private static final String INDEX_PATH = "index.dat";

    public static void main(String[] args) throws IOException {

        boolean esci = false;
        Scanner scanner = new Scanner(System.in);

        Map<String, Long> indice = caricaIndice();

        while (!esci) {     
            System.out.println("\nMenu:");
            System.out.println("1) Inserire un utente");
            System.out.println("2) Ricerca di un utente");
            System.out.println("3) Visualizza tutto l'elenco");
            System.out.println("4) Cancellazione di un utente");
            System.out.println("5) Modifica indirizzo di un utente");
            System.out.println("6) Duplica archivio senza record cancellati");
            System.out.println("9) Esci dal programma");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    inserisciUtente(scanner);
                    break;
                case 2:
                    cercaUtente(scanner, indice);
                    break;
                case 3:
                    visualizzaElenco();
                    break;
                case 4:
                    cancellaUtente(scanner, indice);
                    break;
                case 5:
                    modificaIndirizzo(scanner, indice);
                    break;
                case 6:
                    if (new File(FILE_PATH).length() > 0) {

                        duplicaArchivioSenzaRecordCancellati();
                    } else {
                        System.out.println("Il file di archivio è vuoto.");
                    }
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

        cognome = completaConCaratteri(cognome, lunghezza);
        nome = completaConCaratteri(nome, lunghezza);
        indirizzo = completaConCaratteri(indirizzo, lunghezza);

        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "rw")) {
            file.seek(file.length());
            file.writeUTF(cognome);
            file.writeUTF(nome);
            file.writeUTF(indirizzo);

            // Aggiorna l'indice
            aggiornaIndice(cognome, file.getFilePointer());
            System.out.println("Utente inserito con successo.");
        }
    }

    private static void cercaUtente(Scanner scanner, Map<String, Long> indice) throws IOException {
        System.out.println("\nRicerca di un utente:");
        System.out.print("Inserisci il cognome da cercare: ");
        String cognomeCercato = scanner.nextLine();

        if (indice.containsKey(cognomeCercato)) {
            long posizione = indice.get(cognomeCercato);
            cercaUtentePerPosizione(posizione);
        } else {
            System.out.println("Utente non trovato.");
        }
    }

    private static void cercaUtentePerPosizione(long posizione) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "r")) {
            file.seek(posizione);

            String cognome = file.readUTF();
            String nome = file.readUTF();
            String indirizzo = file.readUTF();

            System.out.println("Utente trovato:");
            System.out.println("Cognome: " + cognome);
            System.out.println("Nome: " + nome);
            System.out.println("Indirizzo: " + indirizzo);
        }
    }

    private static void visualizzaElenco() throws IOException {
        System.out.println("\nElenco utenti:");

        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "r")) {
            while (file.getFilePointer() < file.length()) {
                String cognome = file.readUTF();
                String nome = file.readUTF();
                String indirizzo = file.readUTF();

                // Rimuovi '#' dagli indirizzi prima di stamparli
                indirizzo = indirizzo.replace("#", "").trim();
                nome = nome.replace("#", "").trim();
                cognome = cognome.replace("#", "").trim();

                System.out.println("Cognome: " + cognome);
                System.out.println("Nome: " + nome);
                System.out.println("Indirizzo: " + indirizzo);
                System.out.println("----------");
            }
        } catch (EOFException e) {
  
        }
    }

    private static void cancellaUtente(Scanner scanner, Map<String, Long> indice) throws IOException {
        System.out.println("\nCancellazione di un utente:");
        System.out.print("Inserisci il cognome dell'utente da cancellare: ");
        String cognomeCancellare = scanner.nextLine();

        if (indice.containsKey(cognomeCancellare)) {
            long posizione = indice.get(cognomeCancellare);

            try (RandomAccessFile inputFile = new RandomAccessFile(FILE_PATH, "rw");
                    RandomAccessFile tempFile = new RandomAccessFile("temp.dat", "rw")) {

                while (inputFile.getFilePointer() < inputFile.length()) {
                    long posizioneCorrente = inputFile.getFilePointer();
                    String cognome = inputFile.readUTF();
                    String nome = inputFile.readUTF();
                    String indirizzo = inputFile.readUTF();

                    if (posizioneCorrente != posizione) {
                        tempFile.writeUTF(cognome);
                        tempFile.writeUTF(nome);
                        tempFile.writeUTF(indirizzo);
                    }
                }

                File originalFile = new File(FILE_PATH);
                File tempFileRenamed = new File("temp.dat");
                originalFile.delete();
                tempFileRenamed.renameTo(originalFile);

                indice.remove(cognomeCancellare);
                System.out.println("Utente cancellato con successo.");
            }
        } else {
            System.out.println("Utente non trovato.");
        }
    }

    private static String completaConCaratteri(String input, int lunghezza) {
        if (input.length() < lunghezza) {
            StringBuilder builder = new StringBuilder(input);
            while (builder.length() < lunghezza) {
                builder.append('#');
            }
            return builder.toString();
        } else if (input.length() > lunghezza) {
            return input.substring(0, lunghezza);
        }
        return input;
    }

    private static void aggiornaIndice(String cognome, long posizione) throws IOException {
        try (RandomAccessFile indexFile = new RandomAccessFile(INDEX_PATH, "rw")) {
            indexFile.seek(indexFile.length());
            indexFile.writeUTF(cognome);
            indexFile.writeLong(posizione);
        }
    }

    private static void modificaIndirizzo(Scanner scanner, Map<String, Long> indice) throws IOException {
        System.out.println("\nModifica indirizzo di un utente:");
        System.out.print("Inserisci il cognome dell'utente da modificare: ");
        String cognomeModificare = scanner.nextLine();

        if (indice.containsKey(cognomeModificare)) {
            long posizione = indice.get(cognomeModificare);
            modificaIndirizzoPerPosizione(posizione, scanner);
        } else {
            System.out.println("Utente non trovato.");
        }
    }

    private static void modificaIndirizzoPerPosizione(long posizione, Scanner scanner) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "rw")) {
            file.seek(posizione);

            String cognome = file.readUTF();
            String nome = file.readUTF();
            String indirizzo = file.readUTF();

            System.out.println("Utente attuale:");
            System.out.println("Cognome: " + cognome);
            System.out.println("Nome: " + nome);
            System.out.println("Indirizzo attuale: " + indirizzo);

            System.out.print("Inserisci il nuovo indirizzo: ");
            String nuovoIndirizzo = scanner.nextLine();
            nuovoIndirizzo = completaConCaratteri(nuovoIndirizzo, 50);

            
            file.seek(posizione + cognome.length() + nome.length() + 6); 
                                                                         

            
            file.writeUTF(nuovoIndirizzo);

            System.out.println("Indirizzo modificato con successo.");
        }
    }

    private static void duplicaArchivioSenzaRecordCancellati() throws IOException {
        try (RandomAccessFile inputFile = new RandomAccessFile(FILE_PATH, "rw");
                RandomAccessFile outputFile = new RandomAccessFile("copia_anagrafico.dat", "rw")) {

            while (inputFile.getFilePointer() < inputFile.length()) {
                String cognome = inputFile.readUTF();
                String nome = inputFile.readUTF();
                String indirizzo = inputFile.readUTF();

                
                if (!cognome.startsWith("#")) {
                    outputFile.writeUTF(cognome);
                    outputFile.writeUTF(nome);
                    outputFile.writeUTF(indirizzo);
                }
            }

            System.out.println("Archivio duplicato senza record cancellati.");
        }
    }

    private static Map<String, Long> caricaIndice() throws IOException {
        Map<String, Long> indice = new HashMap<>();

        try (RandomAccessFile indexFile = new RandomAccessFile(INDEX_PATH, "r")) {
            while (indexFile.getFilePointer() < indexFile.length()) {
                String cognome = indexFile.readUTF();
                long posizione = indexFile.readLong();
                indice.put(cognome, posizione);
            }
        }

        return indice;
    }

}
