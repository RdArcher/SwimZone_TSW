package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import model.DettaglioOrdineBean;
import model.OrdineBean;
import model.UtenteBean;

public class OrdineDAOImpl implements OrdineDAO{

	private DataSource ds;
	
	public OrdineDAOImpl(DataSource ds) {
		this.ds=ds;	
	}
	
	public void salvaOrdine(OrdineBean ordine) throws SQLException{
		String insOrdine = "INSERT INTO Ordine (id_utente, data, totale, stato, indirizzo_spedizione) VALUES (?, ?, ?, ?, ?)";
		String insComprende = "INSERT INTO Comprende (id_ordine, id_prodotto, prezzo_acquisto, quantita) VALUES (?, ?, ?, ?)";
		
		Connection connection = null;
	    PreparedStatement psOrdine = null;
	    PreparedStatement psComprende = null;
	    ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			
			psOrdine = connection.prepareStatement(insOrdine, Statement.RETURN_GENERATED_KEYS);
			
			psOrdine.setInt(1, ordine.getUtente().getIdUtente());
			psOrdine.setDate(2, ordine.getData());
			psOrdine.setFloat(3, ordine.getTotale());
			psOrdine.setBoolean(4, ordine.getStato());
			psOrdine.setString(5, ordine.getUtente().getIndirizzoSpedizione());
			psOrdine.executeUpdate();
			
			rs = psOrdine.getGeneratedKeys();		
			int id=0;
			
			if(rs.next()) {
				id = rs.getInt(1);
				ordine.setIdOrdine(id);
			}
			
			psComprende = connection.prepareStatement(insComprende);
            for (DettaglioOrdineBean dettaglio : ordine.getProdotti()) {
                psComprende.setInt(1, id);
                psComprende.setInt(2, dettaglio.getIdProdotto());
                psComprende.setFloat(3, dettaglio.getPrezzoAcquisto());
                psComprende.setInt(4, dettaglio.getQuantita());
                psComprende.executeUpdate();
            }
            
            connection.commit();
		} catch(SQLException e) {
			if(connection != null)
				connection.rollback();
			throw e;
		} finally {
			if (rs != null) rs.close();
            if (psOrdine != null) psOrdine.close();
            if (psComprende != null) psComprende.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
		}
	}
}
