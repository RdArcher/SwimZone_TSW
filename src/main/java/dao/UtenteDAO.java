package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.UtenteBean;

public interface UtenteDAO {

	public void SalvaUtente(UtenteBean utente) throws SQLException;
	
	public boolean EliminaUtente(int id_utente) throws SQLException;
	
	public UtenteBean CercaUtente(int id_utente) throws SQLException;
	
	public Collection<UtenteBean> Utente(String order) throws SQLException;
	
	public UtenteBean doRetrieveByEmailAndPassword(String email, String password) throws SQLException;
}
