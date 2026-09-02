package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.OrdineBean;

public interface OrdineDAO {
		
	public void salvaOrdine(OrdineBean ordine) throws SQLException;
	
	public OrdineBean cercaOrdine(int id_ordine) throws SQLException;
	
	public Collection<OrdineBean> ritornaOrdini(String order) throws SQLException;
	
	public boolean aggiornaStato(int id_ordine, boolean stato) throws SQLException;
}
