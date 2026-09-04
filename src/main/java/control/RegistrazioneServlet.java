package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.UtenteDAO;
import dao.UtenteDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UtenteBean;

public class RegistrazioneServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String indirizzo = request.getParameter("indirizzo_spedizione");
		
		if(nome==null || cognome==null || email==null || password==null || indirizzo==null) {
			request.setAttribute("errore", "Compila tutti i campi obbligatori");
			RequestDispatcher dis = request.getRequestDispatcher("registrazione.jsp");
			dis.forward(request, response);
			return;
		}
		
		UtenteBean nuovoUtente = new UtenteBean();
		nuovoUtente.setNome(nome);
		nuovoUtente.setCognome(cognome);
		nuovoUtente.setEmail(email);
		nuovoUtente.setPassword(password);
		nuovoUtente.setIndirizzoSpedizione(indirizzo);
		
		nuovoUtente.setRuolo("utente");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UtenteDAO utenteDAO = new UtenteDAOImpl(ds);
		
		try {
			utenteDAO.SalvaUtente(nuovoUtente);
			
			request.setAttribute("successo", "Registrazione completata!");
			RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
			dis.forward(request, response);
		} catch(SQLException e) {
			e.printStackTrace();
            request.setAttribute("errore", "Errore durante la registrazione. L'email potrebbe essere già in uso.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.jsp");
            dispatcher.forward(request, response);
		}
	}

}
