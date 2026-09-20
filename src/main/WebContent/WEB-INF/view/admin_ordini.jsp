<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection, model.OrdineBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pannello Admin - Ordini</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/home.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/autenticazione.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/home">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/Prodotti">Catalogo</a></li>
                <li><a href="<%=request.getContextPath()%>/admin_prodotti">Admin Prodotti</a></li>
                <li><a href="<%=request.getContextPath()%>/admin_ordini" style="font-weight:bold;">Admin Ordini</a></li>
            </ul>
        </nav>
    </header>

    <main class="container admin-container">
        
        <div class="admin-header">
            <h1>Gestione Ordini Clienti</h1>
            <a href="<%=request.getContextPath()%>/home" class="btn btn-back">&larr; Torna alla Home</a>
        </div>

        <div class="filter-box">
            <form action="<%=request.getContextPath()%>/admin_ordini" method="GET">
                <label for="dataInizio">Da:</label>
                <input type="date" id="dataInizio" name="dataInizio" class="admin-input" style="width:auto;" value="<%= request.getParameter("dataInizio") != null ? request.getParameter("dataInizio") : "" %>">

                <label for="dataFine">A:</label>
                <input type="date" id="dataFine" name="dataFine" class="admin-input" style="width:auto;" value="<%= request.getParameter("dataFine") != null ? request.getParameter("dataFine") : "" %>">

                <button type="submit" class="btn btn-primary">Filtra Date</button>
                <a href="<%=request.getContextPath()%>/admin_ordini" style="color: #007BFF; text-decoration: none; margin-left: auto;">Mostra Tutti</a>
            </form>
        </div>

        <table class="admin-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Data</th>
                    <th>Cliente</th>
                    <th>Email</th>
                    <th>Indirizzo Consegna</th>
                    <th>Totale</th>
                    <th>Stato Corrente</th>
                    <th>Modifica</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Collection<OrdineBean> ordini = (Collection<OrdineBean>) request.getAttribute("ordini");
                    if (ordini != null && !ordini.isEmpty()) {
                        for (OrdineBean o : ordini) {
                %>
                <tr>
                    <td><strong>#<%= o.getIdOrdine() %></strong></td>
                    <td><%= o.getData() %></td>
                    <td>
                        <%= (o.getUtente() != null && o.getUtente().getNome() != null) 
                            ? o.getUtente().getNome() + " " + o.getUtente().getCognome() 
                            : "Utente #" + (o.getUtente() != null ? o.getUtente().getIdUtente() : "-") %>
                    </td>
                    <td><%= (o.getUtente() != null && o.getUtente().getEmail() != null) ? o.getUtente().getEmail() : "-" %></td>
                    <td><%= (o.getUtente() != null && o.getUtente().getIndirizzoSpedizione() != null) ? o.getUtente().getIndirizzoSpedizione() : "-" %></td>
                    <td style="color:#28a745; font-weight:bold;">€ <%= String.format("%.2f", o.getTotale()) %></td>
                    <td>
                        
                            <%= o.getStato() ? "In elaborazione" : "Processato" %>
                        
                    </td>
                    <td>
                        <form class="form-status" action="<%=request.getContextPath()%>/admin_ordini" method="POST">
                            <input type="hidden" name="id_ordine" value="<%= o.getIdOrdine() %>">
                            <select name="stato" class="admin-input" style="width: auto; padding: 6px;">
                                <option value="true" <%= o.getStato() ? "selected" : "" %>>In elaborazione</option>
                                <option value="false" <%= !o.getStato() ? "selected" : "" %>>Processato</option>
                            </select>
                            <button type="submit">Salva</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="8" style="text-align: center; color: #777; padding: 30px;">Nessun ordine trovato per i criteri selezionati.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </main>
</body>
</html>