package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UtenteDAOImpl;
import model.UtenteBean;

@WebServlet("/profilo")
public class ModificaServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("utente") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/profilo.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteBean utenteSessione = (UtenteBean) request.getSession().getAttribute("utente");
        
        if (utenteSessione == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String indirizzo = request.getParameter("indirizzo_spedizione");
        String nuovaPassword = request.getParameter("password");

        utenteSessione.setNome(nome);
        utenteSessione.setCognome(cognome);
        utenteSessione.setEmail(email);
        utenteSessione.setIndirizzoSpedizione(indirizzo);
        
        if (nuovaPassword != null && !nuovaPassword.trim().isEmpty()) {
            utenteSessione.setPassword(nuovaPassword);
        }

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl(ds);

        try {
            boolean successo = utenteDAO.aggiornaUtente(utenteSessione);
            if (successo) {
                request.getSession().setAttribute("utente", utenteSessione);
                request.setAttribute("successo", "Dati aggiornati con successo!");
            } else {
                request.setAttribute("errore", "Errore durante l'aggiornamento dei dati.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore di connessione al database.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/profilo.jsp");
        dispatcher.forward(request, response);
    }
}