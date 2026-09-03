package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
	
	public OrdineBean cercaOrdineID(int id_ordine) throws SQLException{
		OrdineBean ordine = null;
		String selectOrdine = "SELECT * FROM Ordine WHERE id_ordine = ?";
		String selectDettagli = "SELECT * FROM Comprende WHERE id_ordine = ?";
		
		try (Connection connection = ds.getConnection();
				PreparedStatement psOrdine = connection.prepareStatement(selectOrdine);
				PreparedStatement psDettagli = connection.prepareStatement(selectDettagli)){
			
			psOrdine.setInt(1,  id_ordine);
			try(ResultSet rs = psOrdine.executeQuery()){
				if(rs.next()) {
					ordine = new OrdineBean();
					UtenteBean utente = new UtenteBean();
                    ordine.setIdOrdine(rs.getInt("id_ordine"));
                    utente.setIdUtente(rs.getInt("id_utente"));
                    ordine.setData(rs.getDate("data"));
                    ordine.setTotale(rs.getFloat("totale"));
                    ordine.setStato(rs.getBoolean("stato"));
                    utente.setIndirizzoSpedizione(rs.getString("indirizzo_spedizione"));
                    
                    ordine.setUtente(utente);
				}
			}
			if (ordine != null) {
				psDettagli.setInt(1, id_ordine);
				try (ResultSet rsDettagli = psDettagli.executeQuery()) {
					while (rsDettagli.next()) {
						DettaglioOrdineBean dettaglio = new DettaglioOrdineBean();
						dettaglio.setIdOrdine(rsDettagli.getInt("id_ordine"));
						dettaglio.setIdProdotto(rsDettagli.getInt("id_prodotto"));
						dettaglio.setPrezzoAcquisto(rsDettagli.getFloat("prezzo_acquisto"));
						dettaglio.setQuantita(rsDettagli.getInt("quantita"));
						ordine.addProdotto(dettaglio);
					}
				}
			}
		}
		return ordine;
	}
	
	public Collection<OrdineBean> cercaOrdineUtente(int id_utente) throws SQLException{
		List<OrdineBean> ordini = new LinkedList<>();
		String selectSQL = "SELECT * FROM Ordine WHERE id_utente = ? ORDER BY data DESC";
		
		try (Connection connection = ds.getConnection();
	             PreparedStatement ps = connection.prepareStatement(selectSQL)) {

	            ps.setInt(1, id_utente);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    OrdineBean ordine = new OrdineBean();
	                    UtenteBean utente = new UtenteBean();
	                    ordine.setIdOrdine(rs.getInt("id_ordine"));
	                    utente.setIdUtente(rs.getInt("id_utente"));
	                    ordine.setData(rs.getDate("data"));
	                    ordine.setTotale(rs.getFloat("totale"));
	                    ordine.setStato(rs.getBoolean("stato"));
	                    utente.setIndirizzoSpedizione(rs.getString("indirizzo_spedizione"));
	                    ordine.setUtente(utente);
	                    ordini.add(ordine);
	                }
	            }
	        }
		return ordini;
	}
	
	public boolean aggiornaStato(int id_ordine, boolean stato) throws SQLException{
		String updateSQL = "UPDATE Ordine SET stato = ? WHERE id_ordine = ?";
		        
		        try (Connection connection = ds.getConnection();
		             PreparedStatement ps = connection.prepareStatement(updateSQL)) {
		            
		            ps.setBoolean(1, stato);
		            ps.setInt(2, id_ordine);
		            int result = ps.executeUpdate();
		            return result > 0;
		        }
    }

}
