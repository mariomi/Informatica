import java.sql.*;

public class Insert {

    static final String DB_URL = "jdbc:mysql://localhost/Alunni";
    static final String USER = "root";				 	// Credentiali Database 
    static final String PASS = "";				

    public static void main(String[] args) {
       System.out.println("INIZIO PROGRAMMA");

       Connection conn = null;
       Statement stmt = null;
       try {
          
            Class.forName("com.mysql.jdbc.Driver");			// Carica in memoria il driver JDBC 		
            conn = DriverManager.getConnection(DB_URL, USER, PASS);	// Apre una connessione
   
            stmt = conn.createStatement();				   
            String sql = "INSERT INTO anagrafico (nome, cognome, Eta)  VALUES ( 'Sara', 'Verdicchio', 12)";
            int result = stmt.executeUpdate(sql);				// Esegue una insert
 
            if (result > 0) System.out.println("inserito con successo"); 
            else            System.out.println("Non inserito "); 
    						
            stmt.close();						  
            conn.close();
       } 
       catch (Exception e) {
            e.printStackTrace();            					// Gestione errori 
       }

    }// main
}
