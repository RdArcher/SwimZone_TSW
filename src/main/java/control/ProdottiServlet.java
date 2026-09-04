package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import dao.ProdottoDAOImpl;
import dao.ProdottoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoBean;

public class ProdottiServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProdottoDAO prodottoDAO = new ProdottoDAOImpl(ds);
		
		String id = request.getParameter("id");
		String order = request.getParameter("order");
		
		try {
			if(id!=null) {
				int idProdotto = Integer.parseInt(id);
				ProdottoBean prodotto = prodottoDAO.cercaProdotto(idProdotto);
				
				if(prodotto != null) {
					request.setAttribute("prodotto", prodotto);
					RequestDispatcher req = request.getRequestDispatcher("ettaglio_prodotto.jsp");
					req.forward(request, response);
				} else {
					response.sendError(HttpServletResponse.SC_NOT_FOUND, "Prodotto non trovato");
				}
			} else {
				Collection<ProdottoBean> prodotti = prodottoDAO.doRetrieveAll(order);
				request.setAttribute("prodotti", prodotti);
                RequestDispatcher dis = request.getRequestDispatcher("catalogo.jsp");
                dis.forward(request, response);
			}
		} catch(NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID prodotto non valido");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore di accesso al database");
        }
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);		
	}
}
