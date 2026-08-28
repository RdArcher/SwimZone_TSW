package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.UtenteBean;

public interface UtenteDAO {

	public void doSave(UtenteBean utente) throws SQLException;
	
	public boolean doUpdageImage(UtenteBean utente) throws SQLException;
	
	public boolean doDelete(int code) throws SQLException;
	
	public UtenteBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException;
}
