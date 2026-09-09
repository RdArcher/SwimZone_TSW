package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.ProdottoDAO;
import dao.ProdottoDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CarrelloBean;
import model.ProdottoBean;

@WebServlet("/Carrello")
public class CarrelloServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		
		List<CarrelloBean> carrello = (List<CarrelloBean>) session.getAttribute("carrello");
		
		if(carrello==null) {
			carrello=new ArrayList<>();
			session.setAttribute("carrello", carrello);
		}
		
		String azione = request.getParameter("azione");
		
		if(azione!=null) {
			try {
				if(azione.equals("aggiungi")) {
					int idProdotto=Integer.parseInt(request.getParameter("id"));
					int quantita=Integer.parseInt(request.getParameter("quantita"));
					
					boolean flag = false;
					
					for(CarrelloBean item : carrello) {
						if(item.getProdotto().getID_prodotto() == idProdotto) {
							item.setQuantita(item.getQuantita()+quantita);
							flag=true;
							break;
						}
					}
					
					if(flag==false) {
						DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");	
						ProdottoDAO prodottoDAO = new ProdottoDAOImpl(ds);
						ProdottoBean prodotto = prodottoDAO.cercaProdotto(idProdotto);
						
						if(prodotto!=null)
							carrello.add(new CarrelloBean(prodotto, quantita));
					}
				} else if (azione.equals("rimuovi")) {
					int idProdotto = Integer.parseInt(request.getParameter("id"));
					carrello.removeIf(item -> item.getProdotto().getID_prodotto() == idProdotto);
					
				} else if (azione.equals("svuota")) {
					carrello.clear();
				}
			} catch(NumberFormatException e) {
				System.out.println("Errore nei parametri");
			} catch(SQLException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			
			if(azione.equals("mostraCarrello")) {
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/carrello.jsp");
				dis.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/Carrello?azione=mostraCarrello");
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}