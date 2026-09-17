package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;

import dao.ProdottoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoBean;

@WebServlet("/dettaglio")
public class ProdottoDettaglioServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int idProdotto = Integer.parseInt(idParam);
                
                DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
                ProdottoDAOImpl dao = new ProdottoDAOImpl(ds);
                
                ProdottoBean prodotto = dao.cercaProdotto(idProdotto);
                
                if (prodotto != null) {
                    request.setAttribute("prodotto", prodotto);
                    request.getRequestDispatcher("/WEB-INF/view/dettaglio_prodotto.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/Prodotti");
    }
}