package control;

import java.io.IOException;
import java.util.List;
import javax.sql.DataSource;

import dao.ProdottoDAOImpl;
import dao.OrdineDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.UtenteBean;
import model.CarrelloBean;
import model.OrdineBean;
import model.DettaglioOrdineBean;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UtenteBean utente = (UtenteBean) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        List<CarrelloBean> carrello = (List<CarrelloBean>) session.getAttribute("carrello");

        if (carrello == null || carrello.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/Prodotti");
            return;
        }

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDAOImpl prodottoDAO = new ProdottoDAOImpl(ds);
        OrdineDAOImpl ordineDAO = new OrdineDAOImpl(ds);

        try {
            OrdineBean nuovoOrdine = new OrdineBean();
            nuovoOrdine.setUtente(utente);
            nuovoOrdine.setData(new java.sql.Date(System.currentTimeMillis()));
            nuovoOrdine.setStato(true);

            float totaleFinale = 0;

            for (CarrelloBean item : carrello) {
                totaleFinale += item.getPrezzoTotale();

                DettaglioOrdineBean dettaglio = new DettaglioOrdineBean();
                dettaglio.setIdProdotto(item.getProdotto().getID_prodotto());
                dettaglio.setPrezzoAcquisto(item.getProdotto().getPrezzo());
                dettaglio.setQuantita(item.getQuantita());

                nuovoOrdine.addProdotto(dettaglio);

                prodottoDAO.aggiornaQuantita(item.getProdotto().getID_prodotto(), item.getQuantita());
            }

            nuovoOrdine.setTotale(totaleFinale);

            ordineDAO.salvaOrdine(nuovoOrdine);

            session.removeAttribute("carrello");
            response.sendRedirect(request.getContextPath() + "/Prodotti");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/Carrello");
        }
    }
}