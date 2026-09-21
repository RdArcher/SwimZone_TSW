<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="model.OrdineBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/png" href="images/logo.png">
    <title>I miei ordini - SwimZone</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/ordini.css">
     <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/home.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/home">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/Prodotti">Prodotti</a></li>
                <li><a href="<%=request.getContextPath()%>/Carrello">Carrello</a></li>
                <li><a href="<%=request.getContextPath()%>/LoginServlet">Disconnetti</a></li>
            </ul>
        </nav>
    </header>

    <main class="container">
        <h1>Storico Ordini</h1>
        <br>

        <% 
            Collection<OrdineBean> ordini = (Collection<OrdineBean>) request.getAttribute("ordini");
            if (ordini == null || ordini.isEmpty()) { 
        %>
            <div class="msg_errore">
                <p>Non hai ancora effettuato nessun ordine.</p>
                <br>
                <a href="<%=request.getContextPath()%>/Prodotti" class="btn-back">Inizia lo shopping</a>
            </div>
        <% } else { %>
            <table class="tabella-carrello">
                <thead>
                    <tr>
                        <th>ID Ordine</th>
                        <th>Data</th>
                        <th>Totale</th>
                        <th>Indirizzo Spedizione</th>
                        <th>Stato</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (OrdineBean o : ordini) { %>
                        <tr>
                            <td>#<%= o.getIdOrdine() %></td>
                            <td><%= o.getData() %></td>
                            <td>&euro; <%= String.format("%.2f", o.getTotale()) %></td>
                            <td><%= o.getUtente() != null ? o.getUtente().getIndirizzoSpedizione() : "N/D" %></td>
                            <td><%= o.getStato() ? "In corso" : "Processato" %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>
    </main>
</body>
</html>