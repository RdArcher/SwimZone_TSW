<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="model.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalogo - SwimZone</title>
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
                <div class="card-prodotto" style="border: 1px solid #ccc; padding: 15px; width: 250px; text-align: center;">
                    <a href="<%=request.getContextPath()%>/Prodotti?id=<%= p.getID_prodotto() %>" style="text-decoration: none; color: inherit;">
                        <h3><%= p.getNome() %></h3>
                    </a>
                    
                    <p class="prezzo">&euro; <%= String.format("%.2f", p.getPrezzo()) %></p>
                    
                    <form action="<%=request.getContextPath()%>/Carrello" method="POST">
                        <input type="hidden" name="azione" value="aggiungi">
                        <input type="hidden" name="id" value="<%= p.getID_prodotto() %>">
                        
                        <label for="quantita_<%= p.getID_prodotto() %>">Qtà:</label>
                        <input type="number" id="quantita_<%= p.getID_prodotto() %>" name="quantita" value="1" min="1" max="10" style="width: 50px;">
                        <br><br>
                        <button type="submit" class="btn-aggiungi">Aggiungi al Carrello</button>
                    </form>
                </div>
            <% 
                    }
                } else {
            %>
                <p>Nessun prodotto disponibile al momento.</p>
            <% } %>
        </div>
    </main>
</body>
</html>