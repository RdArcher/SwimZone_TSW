<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="model.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalogo - SwimZone</title>
    <!-- Non dimenticare il CSS per la barra di navigazione! -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/home.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/home">Home</a></li>
                <% if (session.getAttribute("utente") != null) { %>
                    <li><a href="<%=request.getContextPath()%>/Ordini">I miei ordini</a></li>
                    <li><a href="<%=request.getContextPath()%>/Carrello">Carrello</a></li>
                    <li><a href="<%=request.getContextPath()%>/LoginServlet">Disconnetti</a></li>
                <% } else { %>
                    <li><a href="<%=request.getContextPath()%>/LoginServlet">Accedi</a></li>
                <% } %>
            </ul>
            
            <ul>
			    <li><a href="<%=request.getContextPath()%>/Prodotti">Tutti i prodotti</a></li>
			    <li><a href="<%=request.getContextPath()%>/Prodotti?categoria=1">Costumi Donna</a></li>
			    <li><a href="<%=request.getContextPath()%>/Prodotti?categoria=2">Costumi Uomo</a></li>
			    <li><a href="<%=request.getContextPath()%>/Prodotti?categoria=3">Occhialini</a></li>
		   </ul>
        </nav>
    </header>

    <main class="container">
        <h1>Il nostro Catalogo</h1>
        
        <div class="griglia-prodotti" style="display: flex; gap: 20px; flex-wrap: wrap;">
            <% 
                Collection<ProdottoBean> prodotti = (Collection<ProdottoBean>) request.getAttribute("prodotti");
                if (prodotti != null && !prodotti.isEmpty()) {
                    for (ProdottoBean p : prodotti) {
            %>
                <div class="card-prodotto" style="background: #f9f9f9; border: 1px solid #ddd; border-radius: 4px; padding: 15px; width: 250px; text-align: center;">
                    
                    <a href="<%=request.getContextPath()%>/dettaglio?id=<%= p.getID_prodotto() %>" style="text-decoration: none; color: #333;">
                        
                        <img src="<%=request.getContextPath()%>/<%= p.getPath() %>" alt="<%= p.getNome() %>" style="width: 100%; height: 200px; object-fit: contain; margin-bottom: 10px; background: white;">
                        
                        <h3 style="margin: 10px 0 5px 0;"><%= p.getNome() %></h3>
                    </a>
                    
                    <p class="prezzo" style="color: #28a745; font-size: 1.2rem; font-weight: bold; margin: 10px 0;">
                        &euro; <%= String.format("%.2f", p.getPrezzo()) %>
                    </p>
                    
                    <form action="<%=request.getContextPath()%>/Carrello" method="POST" style="margin-top: 15px;">
                        <input type="hidden" name="azione" value="aggiungi">
                        <input type="hidden" name="id" value="<%= p.getID_prodotto() %>">
                        
                        <label for="quantita_<%= p.getID_prodotto() %>" style="font-weight: bold;">Qtà:</label>
                        <input type="number" id="quantita_<%= p.getID_prodotto() %>" name="quantita" value="1" min="1" max="<%= p.getQuantita() %>" style="width: 60px; padding: 5px; border: 1px solid #ccc; border-radius: 4px;">
                        <br><br>
                        
                        <% if(p.getQuantita() > 0) { %>
                            <button type="submit" class="btn-save" style="width: 100%;">Aggiungi al Carrello</button>
                        <% } else { %>
                            <button type="button" disabled style="width: 100%; padding: 10px; background: #6c757d; color: white; border: none; border-radius: 4px; font-weight: bold;">Esaurito</button>
                        <% } %>
                    </form>
                </div>
            <% 
                    }
                } else {
            %>
                <p style="width: 100%; text-align: center; color: #666; font-style: italic;">Nessun prodotto disponibile al momento.</p>
            <% } %>
        </div>
    </main>
</body>
</html>