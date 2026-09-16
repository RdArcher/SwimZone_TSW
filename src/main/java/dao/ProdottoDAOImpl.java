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

public class ProdottoDAOImpl implements ProdottoDAO {
	
	private DataSource ds;
	
	public ProdottoDAOImpl(DataSource ds) {
		this.ds = ds;
	}
	
	public void salvaProdotto(ProdottoBean prodotto) throws SQLException {
		String insertSQL = "INSERT INTO Prodotto (nome, id_categoria, descrizione, colore, prezzo, taglia, quantita, attivo, image_path, mime_type) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(insertSQL)) {
			
			statement.setString(1, prodotto.getNome());
			statement.setInt(2, prodotto.getID_categoria());
			statement.setString(3, prodotto.getDescrizione());
			statement.setString(4, prodotto.getColore());
			statement.setFloat(5, prodotto.getPrezzo());
			statement.setString(6, prodotto.getTaglia());
			statement.setInt(7, prodotto.getQuantita());
			statement.setBoolean(8, prodotto.getStato());
			statement.setString(9, prodotto.getPath());
			statement.setString(10, prodotto.getMimeType());
			
			statement.executeUpdate();
		}
	}
	
	public boolean eliminaProdotto(int id_prodotto) throws SQLException {
		String deleteSQL = "UPDATE Prodotto SET attivo = false WHERE id_prodotto = ?";
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
			
			statement.setInt(1, id_prodotto);
			int r = statement.executeUpdate();
			return r > 0;
		}
	}
	
	public ProdottoBean cercaProdotto(int id_prodotto) throws SQLException {
		String selectSQL = "SELECT * FROM Prodotto WHERE id_prodotto = ?";
		ProdottoBean bean = null;
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(selectSQL)) {
			
			statement.setInt(1, id_prodotto);
			
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					bean = new ProdottoBean();
					bean.setID_prdotto(rs.getInt("id_prodotto"));
					bean.setID_categoria(rs.getInt("id_categoria"));
					bean.setNome(rs.getString("nome"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setColore(rs.getString("colore"));
					bean.setPrezzo(rs.getFloat("prezzo"));
					bean.setTaglia(rs.getString("taglia"));
					bean.setQuantita(rs.getInt("quantita"));
					bean.setStato(rs.getBoolean("attivo"));
					bean.setPath(rs.getString("image_path"));
					bean.setMimeType(rs.getString("mime_type"));
				}
			}
		}
		return bean;
	}
	
	public Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException {
		String selectSQL = "SELECT * FROM Prodotto WHERE attivo = 1";
		List<ProdottoBean> prodotti = new LinkedList<>();
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(selectSQL)) {
			
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					ProdottoBean bean = new ProdottoBean();
					bean.setID_prdotto(rs.getInt("id_prodotto"));
					bean.setID_categoria(rs.getInt("id_categoria"));
					bean.setNome(rs.getString("nome"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setColore(rs.getString("colore"));
					bean.setPrezzo(rs.getFloat("prezzo"));
					bean.setTaglia(rs.getString("taglia"));
					bean.setQuantita(rs.getInt("quantita"));
					bean.setStato(rs.getBoolean("attivo"));
					bean.setPath(rs.getString("image_path"));
					bean.setMimeType(rs.getString("mime_type"));
					
					prodotti.add(bean);
				}
			}
			return prodotti;
		}
	}
	
	public boolean AggiornaProdotto(ProdottoBean prodotto) throws SQLException {
		String updateSQL = "UPDATE Prodotto SET nome = ?, descrizione = ?, prezzo = ?, quantita = ? WHERE id_prodotto = ?";
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(updateSQL)) {
			
			statement.setString(1, prodotto.getNome());
			statement.setString(2, prodotto.getDescrizione());
			statement.setFloat(3, prodotto.getPrezzo());
			statement.setInt(4, prodotto.getQuantita());
			statement.setInt(5, prodotto.getID_prodotto());
			
			int r = statement.executeUpdate();
			return r > 0;
		}
	}
	
	public boolean AttivaProdotto(int id_prodotto) throws SQLException {
		String updateSQL = "UPDATE Prodotto SET attivo = true WHERE id_prodotto = ?";
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(updateSQL)) {
			
			statement.setInt(1, id_prodotto);
			int r = statement.executeUpdate();
			return r > 0;
		}
	}
	
	public void doUpdateImage(int id_prodotto, String image_path, String mimeType) throws SQLException {
		String updateSQL = "UPDATE Prodotto SET image_path = ?, mime_type = ? WHERE id_prodotto = ?";
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
			
			preparedStatement.setString(1, image_path);
			preparedStatement.setString(2, mimeType);
			preparedStatement.setInt(3, id_prodotto);
			
			preparedStatement.executeUpdate();
		}
	}
	
	public Collection<ProdottoBean> doRetrieveByCategoria(int id_categoria) throws SQLException {
		String selectSQL = "SELECT * FROM Prodotto WHERE id_categoria = ? AND attivo = 1";
		List<ProdottoBean> prodotti = new LinkedList<>();
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(selectSQL)) {
			
			statement.setInt(1, id_categoria);
			
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					ProdottoBean bean = new ProdottoBean();
					bean.setID_prdotto(rs.getInt("id_prodotto"));
					bean.setID_categoria(rs.getInt("id_categoria"));
					bean.setNome(rs.getString("nome"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setColore(rs.getString("colore"));
					bean.setPrezzo(rs.getFloat("prezzo"));
					bean.setTaglia(rs.getString("taglia"));
					bean.setQuantita(rs.getInt("quantita"));
					bean.setStato(rs.getBoolean("attivo"));
					bean.setPath(rs.getString("image_path"));
					bean.setMimeType(rs.getString("mime_type"));
					
					prodotti.add(bean);
				}
			}
			return prodotti;
		}
	}
}