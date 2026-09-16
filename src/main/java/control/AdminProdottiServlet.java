package control;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.ProdottoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ProdottoBean;
import model.UtenteBean;

@WebServlet("/admin_prodotti")
@MultipartConfig // Fondamentale perché caricheremo immagini
public class AdminProdottiServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
    	if (utente == null || utente.getRuolo() != 2) { 
    	    response.sendRedirect(request.getContextPath() + "/LoginServlet");
    	    return;
    	}
        
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDAOImpl dao = new ProdottoDAOImpl(ds);
        
        try {
            request.setAttribute("prodotti", dao.doRetrieveAll(""));
            request.getRequestDispatcher("/WEB-INF/view/admin_prodotti.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
    	if (utente == null || utente.getRuolo() != 2) { 
    	    response.sendRedirect(request.getContextPath() + "/LoginServlet");
    	    return;
    	}
        
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDAOImpl dao = new ProdottoDAOImpl(ds);
        String action = request.getParameter("action");
        
        try {
            if ("add".equals(action)) {
                ProdottoBean p = new ProdottoBean();
                p.setNome(request.getParameter("nome"));
                p.setID_categoria(Integer.parseInt(request.getParameter("id_categoria")));
                p.setColore(request.getParameter("colore"));
                p.setTaglia(request.getParameter("taglia"));
                p.setDescrizione(request.getParameter("descrizione"));
                p.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
                p.setQuantita(Integer.parseInt(request.getParameter("quantita")));
                
                Part filePart = request.getPart("immagine");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                
                p.setPath("images/" + fileName);
                p.setMimeType(filePart.getContentType());
                 p.setStato(true);
                dao.salvaProdotto(p);
                
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id_prodotto"));
               
                dao.eliminaProdotto(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/admin_prodotti");
    }
}