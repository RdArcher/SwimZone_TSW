package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import dao.OrdineDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrdineBean;
import model.UtenteBean;

@WebServlet("/admin_ordini")
public class AdminOrdiniServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
        if (utente == null || utente.getRuolo() != 2) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        OrdineDAOImpl dao = new OrdineDAOImpl(ds);	

        String inizio = request.getParameter("dataInizio");
        String fine = request.getParameter("dataFine");

        try {
            List<OrdineBean> ordini;
            if (inizio != null && !inizio.trim().isEmpty() && fine != null && !fine.trim().isEmpty()) {
                ordini = (List<OrdineBean>) dao.doRetrieveByDateRange(Date.valueOf(inizio), Date.valueOf(fine));
            } else {
                ordini = (List<OrdineBean>) dao.doRetrieveAll();
            }
            request.setAttribute("ordini", ordini);
            request.getRequestDispatcher("/WEB-INF/view/admin_ordini.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore DB ordini");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
        if (utente == null || utente.getRuolo() != 2) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        int idOrdine = Integer.parseInt(request.getParameter("id_ordine"));
        boolean attivo = Boolean.parseBoolean(request.getParameter("stato"));

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        OrdineDAOImpl dao = new OrdineDAOImpl(ds);

        try {
            dao.aggiornaStato(idOrdine, attivo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/admin_ordini");
    }
}