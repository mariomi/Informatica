import java.sql.*;

public class Select {
    static final String DB_URL = "jdbc:mysql://localhost/Alunni";
    static final String USER = "root";				 	//  Database credentials
    static final String PASS = "";				

    public static void main(String[] args) {
       System.out.println("INIZIO PROGRAMMA");

        Connection conn = null;						// usato per stabilire una connessione
        Statement stmt = null;                                          // usato per eseguire query
        try {
          
            Class.forName("com.mysql.jdbc.Driver");			// Carica in memoria il driver JDBC 
            System.out.println("Connecting to database...");		
            conn = DriverManager.getConnection(DB_URL, USER, PASS);	
        
            			

            String sql = "SELECT id, Nome, Cognome, Eta FROM anagrafico WHERE id=1000";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.isBeforeFirst() && rs.isAfterLast())    System.out.println("è vuoto!!");	// test per vedere se è vuoto		
            else 
            while (rs.next()) {						// Estrae dati da risultato di una query
                int id = rs.getInt("id");
                String nome = rs.getString("Nome");
                String cognome = rs.getString("Cognome");
                int eta = rs.getInt("Eta");

                System.out.print("ID: " + id);
                System.out.print(", Age: " + eta);
                System.out.print(", Nome: " + nome);
                System.out.println(", Cognome: " + cognome);
            }
          
            rs.close();							  
            stmt.close();
            conn.close();
        } 
  
        catch (Exception e) {
            e.printStackTrace();            				// Gestione degli errori
        }  

    }// main
}
