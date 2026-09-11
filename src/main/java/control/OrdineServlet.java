package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.OrdineDAO;
import dao.OrdineDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.CarrelloBean;
import model.DettaglioOrdineBean;
import model.OrdineBean;
import model.UtenteBean;

@WebServlet("/Ordine")
public class OrdineServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String azione = request.getParameter("azione");
        HttpSession session = request.getSession();
        UtenteBean utente = (UtenteBean) session.getAttribute("utente");
        List<CarrelloBean> carrello = (List<CarrelloBean>) session.getAttribute("carrello");

        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        if (azione.equals("checkout")) {
            if (carrello == null || carrello.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/Carrello");
                return;
            }

            try {
                OrdineBean ordine = new OrdineBean();
                ordine.setUtente(utente);
                ordine.setData(new Date(System.currentTimeMillis()));
                ordine.setStato(true);
                
                float totale = 0;
                List<DettaglioOrdineBean> prodottiOrdine = new ArrayList<>();
                
                for (CarrelloBean item : carrello) {
                    totale += item.getPrezzoTotale();
                    
                    DettaglioOrdineBean dettaglio = new DettaglioOrdineBean();
                    dettaglio.setIdProdotto(item.getProdotto().getID_prodotto());
                    dettaglio.setPrezzoAcquisto(item.getProdotto().getPrezzo());
                    dettaglio.setQuantita(item.getQuantita());
                    
                    prodottiOrdine.add(dettaglio);
                }
                
                ordine.setTotale(totale);
                ordine.setProdotti(prodottiOrdine);

                DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
                OrdineDAO ordineDAO = new OrdineDAOImpl(ds);
                
                ordineDAO.salvaOrdine(ordine);
                session.removeAttribute("carrello");
                response.sendRedirect(request.getContextPath() + "/Ordini"); 
                
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il checkout");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}