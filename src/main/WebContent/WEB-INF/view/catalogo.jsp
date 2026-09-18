<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="model.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalogo - SwimZone</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/catalogo.css">
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
        
        <div class="griglia-prodotti">
            <% 
                Collection<ProdottoBean> prodotti = (Collection<ProdottoBean>) request.getAttribute("prodotti");
                if (prodotti != null && !prodotti.isEmpty()) {
                    for (ProdottoBean p : prodotti) {
            %>
                <div class="card-prodotto">
                    
                    <a href="<%=request.getContextPath()%>/dettaglio?id=<%= p.getID_prodotto() %>">
                        <img src="<%=request.getContextPath()%>/<%= p.getPath() %>" alt="<%= p.getNome() %>">
                        <h3><%= p.getNome() %></h3>
                    </a>
                    
                    <p class="prezzo">
                        &euro; <%= String.format("%.2f", p.getPrezzo()) %>
                    </p>
                    
                    <form action="<%=request.getContextPath()%>/Carrello" method="POST">
                        <input type="hidden" name="azione" value="aggiungi">
                        <input type="hidden" name="id" value="<%= p.getID_prodotto() %>">
                        
                        <label for="quantita_<%= p.getID_prodotto() %>">Qtà:</label>
                        <input type="number" id="quantita_<%= p.getID_prodotto() %>" name="quantita" value="1" min="1" max="<%= p.getQuantita() %>">
                        <br>
                        
                        <% if(p.getQuantita() > 0) { %>
                            <button type="submit" class="btn-save">Aggiungi al Carrello</button>
                        <% } else { %>
                            <button type="button" class="btn-esaurito" disabled>Esaurito</button>
                        <% } %>
                    </form>
                </div>
            <% 
                    }
                } else {
            %>
                <p class="nessun-prodotto">Nessun prodotto disponibile al momento.</p>
            <% } %>
        </div>
    </main>
</body>
</html>