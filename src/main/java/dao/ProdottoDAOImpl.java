package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import model.ProdottoBean;

public class ProdottoDAOImpl implements ProdottoDAO{
	
	private DataSource ds;
	
	public ProdottoDAOImpl(DataSource ds) {
		this.ds=ds;
	}
	
	public void salvaProdotto(ProdottoBean prodotto) throws SQLException{
		
		String insertSQL = "INSERT INTO Prodotto (nome, descrizione, prezzo, quantita, path, mime_path) VALUES (?,?,?,?,?,?)";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(insertSQL)){
			
			statement.setString(1, prodotto.getNome());
			statement.setString(2, prodotto.getDescrizione());
			statement.setFloat(3, prodotto.getPrezzo());
			statement.setInt(4, prodotto.getQuantita());
			statement.setString(5, prodotto.getPath());
			statement.setString(6, prodotto.getMimeType());
			
			statement.executeUpdate();
		}
	}
	
	public boolean eliminaProdotto(int id_prodotto) throws SQLException{
		String deleteSQL = "UPDATE Prodotto SET attivo = false WHERE id_prodotto = ?";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(deleteSQL)){
			
			statement.setInt(1, id_prodotto);
			int r = statement.executeUpdate();
			return r>0;
		}
	}
	
	public ProdottoBean cercaProdotto(int id_prodotto) throws SQLException{
		String selectSQL = "SELECT FROM Prodotto WHERE id_prodotto = ?";
		ProdottoBean bean = null;
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL)){
			
			statement.setInt(1, id_prodotto);
			
			try(ResultSet rs= statement.executeQuery()){
				if(rs.next()) {
					bean = new ProdottoBean();
					bean.setID_prdotto(rs.getInt("id_prodotto"));
					bean.setNome(rs.getString("nome"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setPrezzo(rs.getFloat("prezzo"));
					bean.setQuantita(rs.getInt("quantita"));
					bean.setPath(rs.getString("path"));
					bean.setMimeType(rs.getString("mime_type"));
				}
			}
		}
		return bean;
	}
	
	public Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException{
		String selectSQL = "SELECT * FROM Prodotti";
		List<ProdottoBean> prodotti = new LinkedList<>();
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL)){
			
			try(ResultSet rs= statement.executeQuery()){
				if(rs.next()) {
					ProdottoBean bean = new ProdottoBean();
					bean.setID_prdotto(rs.getInt("id_prodotto"));
					bean.setNome(rs.getString("nome"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setPrezzo(rs.getFloat("prezzo"));
					bean.setQuantita(rs.getInt("quantita"));
					bean.setPath(rs.getString("path"));
					bean.setMimeType(rs.getString("mime_type"));
					
					prodotti.add(bean);
				}
			}
			
			return prodotti;
		}
	}
	
	public boolean AggiornaProdotto(ProdottoBean prodotto) throws SQLException{
		String updateSQL = "UPDATE Prodotto SET name = ?, descrizione = ?, prezzo =?, quantita = ? WHERE code =?";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(updateSQL)){
			
			statement.setString(1, prodotto.getNome());
			statement.setString(2, prodotto.getDescrizione());
			statement.setFloat(3, prodotto.getPrezzo());
			statement.setInt(4, prodotto.getQuantita());
			statement.setString(5, prodotto.getPath());
			statement.setString(6, prodotto.getMimeType());
			
			int r = statement.executeUpdate();
			return r>0;
		}
	}
	
	public boolean AttivaProdotto(int id_prodotto) throws SQLException{
			String updateSQL = "UPDATE Prodotto SET attivo = true WHERE id_prodotto = ?";
		
			try(Connection connection = ds.getConnection();
					PreparedStatement statement = connection.prepareStatement(updateSQL)){
			
				statement.setInt(1, id_prodotto);
				int r = statement.executeUpdate();
				return r>0;
		}
	}
	
	public void doUpdateImage(int id_prodotto, String path, String mimeType) throws SQLException{
			String updateSQL = "UPDATE Prodotto SET path = ?, mime_type = ? WHERE id_prodotto = ?";
	        
	        try (Connection connection = ds.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
	            
	            preparedStatement.setString(1, path);
	            preparedStatement.setString(2, mimeType);
	            preparedStatement.setInt(3, id_prodotto);
	            
	            preparedStatement.executeUpdate();
        }
	}
}
