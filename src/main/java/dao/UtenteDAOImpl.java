package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import model.UtenteBean;

public class UtenteDAOImpl implements UtenteDAO{
	private DataSource ds = null;
	
	public UtenteDAOImpl(DataSource ds) {
		this.ds=ds;
	}
	
	@Override
	public synchronized void SalvaUtente(UtenteBean utente) throws SQLException{
		String insertSQL = "INSERT INTO Utente ( nome, cognome, email, password, ruolo, indirizzo_spedizione) VALUES(?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(insertSQL)){
			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getCognome());
			statement.setString(3, utente.getEmail());
			statement.setString(4, utente.getPassword());
			statement.setString(5, utente.getRuolo());
			statement.setString(6, utente.getIndirizzoSpedizione());
			statement.executeUpdate();
		}
	
	}
	
	@Override
	public synchronized boolean EliminaUtente(int id_utente) throws SQLException{
		String deleteSQL = "DELETE FROM Utente WHERE id_utente= ?";
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(deleteSQL)){
			statement.setInt(1, id_utente);
			int result = statement.executeUpdate();
			return result != 0;
		}
	}

    @Override
    public synchronized UtenteBean CercaUtente(int id_utente) throws SQLException {
    	UtenteBean utente = new UtenteBean();
        String selectSQL = "SELECT * FROM Utente WHERE id_utente = ?";
        try (Connection connection = ds.getConnection();
        		PreparedStatement statement = connection.prepareStatement(selectSQL)) {
        	statement.setInt(1, id_utente);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                	utente.setIdUtente(result.getInt("id_utente"));
                	utente.setNome(result.getString("nome"));
                	utente.setCognome(result.getString("cognome"));
                	utente.setEmail(result.getString("email"));
                	utente.setPassword(result.getString("password"));
                	utente.setRuolo(result.getString("ruolo"));
                	utente.setIndirizzoSpedizione(result.getString("indirizzo_spedizione"));
                }
            }
        }
        return utente;
    }
    
    @Override
    public synchronized Collection<UtenteBean> Utente(String order) throws SQLException{
    	List<UtenteBean> utenti = new LinkedList<>();
    	String selectSQL = "SELECT * FROM Utente";
    	if(order.equals("nome") || order.equals("cognome") || order.equals("email")) {
    		selectSQL+=" ORDER BY "+order;
    	}
    	
    	try(Connection connection = ds.getConnection();
    			PreparedStatement statement = connection.prepareStatement(selectSQL);
    					ResultSet result = statement.executeQuery()){
    		
    		while(result.next()) {
    			UtenteBean utente = new UtenteBean();
    			
    			utente.setIdUtente(result.getInt("id_utente"));
            	utente.setNome(result.getString("nome"));
            	utente.setCognome(result.getString("cognome"));
            	utente.setEmail(result.getString("email"));
            	utente.setPassword(result.getString("password"));
            	utente.setRuolo(result.getString("ruolo"));
            	utente.setIndirizzoSpedizione(result.getString("indirizzo_spedizione"));
            	
            	utenti.add(utente);
    		}
    	}
    	
    	return utenti;
    	
    }
    
    public UtenteBean doRetrieveByEmailAndPassword(String email, String password) throws SQLException {
        UtenteBean utente = null;
        String selectSQL = "SELECT * FROM Utente WHERE email = ? AND password = ?";
        
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    utente = new UtenteBean();
                    utente.setIdUtente(rs.getInt("id_utente"));
                    utente.setNome(rs.getString("nome"));
                    utente.setCognome(rs.getString("cognome"));
                    utente.setEmail(rs.getString("email"));
                    utente.setPassword(rs.getString("password"));
                    utente.setRuolo(rs.getString("ruolo"));
                    utente.setIndirizzoSpedizione(rs.getString("indirizzo_spedizione"));
                }
            }
        }
        return utente;
    }
}
