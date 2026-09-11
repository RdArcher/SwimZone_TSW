package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import dao.OrdineDAO;
import dao.OrdineDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrdineBean;
import model.UtenteBean;

@WebServlet("/Ordini")
public class ListaOrdiniServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UtenteBean utente = (UtenteBean) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        OrdineDAO ordineDAO = new OrdineDAOImpl(ds);

        try {
            Collection<OrdineBean> ordini = ordineDAO.cercaOrdineUtente(utente.getIdUtente());
            request.setAttribute("ordini", ordini);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/ordini.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel caricamento degli ordini");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}