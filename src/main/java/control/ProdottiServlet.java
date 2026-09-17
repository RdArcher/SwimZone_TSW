package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import dao.ProdottoDAOImpl;
import dao.ProdottoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoBean;

@WebServlet("/Prodotti")
public class ProdottiServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDAOImpl prodottoDAO = new ProdottoDAOImpl(ds);
        Collection<ProdottoBean> listaProdotti;
        
        String idCategoriaStr = request.getParameter("categoria");
        
        try {
            if (idCategoriaStr != null && !idCategoriaStr.trim().isEmpty()) {
                int idCategoria = Integer.parseInt(idCategoriaStr);
                listaProdotti = prodottoDAO.doRetrieveByCategoria(idCategoria);
            } else {
                listaProdotti = prodottoDAO.doRetrieveAll("");
            }
            
            request.setAttribute("prodotti", listaProdotti);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/catalogo.jsp");
            dispatcher.forward(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore Database");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/Prodotti");
        }
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);		
	}
}