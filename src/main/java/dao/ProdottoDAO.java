package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.ProdottoBean;

public interface ProdottoDAO {
	
	public void salvaProdotto(ProdottoBean prodotto) throws SQLException;
	
	public boolean eliminaProdotto(int id_prodotto) throws SQLException;
	
	public ProdottoBean cercaProdotto(int id_prodotto) throws SQLException;
	
	public Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException;
	
	public boolean AggiornaProdotto(ProdottoBean prodotto) throws SQLException;
	
	public boolean AttivaProdotto(int id_prodotto) throws SQLException;
	
	public void doUpdateImage(int id_prodotto, String path, String mimeType) throws SQLException;
	
}
