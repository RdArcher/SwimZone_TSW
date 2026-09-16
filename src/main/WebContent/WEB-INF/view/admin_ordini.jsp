<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection, model.OrdineBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Ordini</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/home.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/autenticazione.css">
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

    <main class="container">
        
            <h1>Pannello Gestione Ordini</h1>
            
            <a href="<%=request.getContextPath()%>/home"> Torna alla Home </a>

        <div class="filter-box">
            <form action="<%=request.getContextPath()%>/admin_ordini" method="GET" style="display: flex; align-items: center; gap: 12px; width: 100%;">
                <label for="dataInizio" style="font-weight: bold; color: #555;">Da:</label>
                <input type="date" id="dataInizio" name="dataInizio" value="<%= request.getParameter("dataInizio") != null ? request.getParameter("dataInizio") : "" %>">

                <label for="dataFine" style="font-weight: bold; color: #555;">A:</label>
                <input type="date" id="dataFine" name="dataFine" value="<%= request.getParameter("dataFine") != null ? request.getParameter("dataFine") : "" %>">

                <button type="submit" class="btn-filter">Filtra Date</button>
                <a href="<%=request.getContextPath()%>/admin_ordini" style="color: #007BFF; text-decoration: none; font-size: 0.95rem; margin-left: auto;">Mostra Tutti</a>
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
                    <th>Modifica Stato</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Collection<OrdineBean> ordini = (Collection<OrdineBean>) request.getAttribute("ordini");
                    if (ordini != null && !ordini.isEmpty()) {
                        for (OrdineBean o : ordini) {
                %>
                <tr>
                    <td>#<%= o.getIdOrdine() %></td>
                    <td><%= o.getData() %></td>
                    <td>
                        <%= (o.getUtente() != null && o.getUtente().getNome() != null) 
                            ? o.getUtente().getNome() + " " + o.getUtente().getCognome() 
                            : "Utente #" + (o.getUtente() != null ? o.getUtente().getIdUtente() : "-") %>
                    </td>
                    <td><%= (o.getUtente() != null && o.getUtente().getEmail() != null) ? o.getUtente().getEmail() : "-" %></td>
                    <td><%= (o.getUtente() != null && o.getUtente().getIndirizzoSpedizione() != null) ? o.getUtente().getIndirizzoSpedizione() : "-" %></td>
                    <td><strong>€ <%= String.format("%.2f", o.getTotale()) %></strong></td>
                    <td>
                        <span class="status-badge <%= o.getStato() ? "status-open" : "status-closed" %>">
                            <%= o.getStato() ? "In elaborazione" : "Evaso" %>
                        </span>
                    </td>
                    <td>
                        <form class="form-status" action="<%=request.getContextPath()%>/admin_ordini" method="POST">
                            <input type="hidden" name="id_ordine" value="<%= o.getIdOrdine() %>">
                            <select name="stato" class="select-status">
                                <option value="true" <%= o.getStato() ? "selected" : "" %>>In elaborazione</option>
                                <option value="false" <%= !o.getStato() ? "selected" : "" %>>Evaso</option>
                            </select>
                            <button type="submit" class="btn-save">Salva</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="8" style="text-align: center; color: #777; padding: 25px;">Nessun ordine trovato per i criteri selezionati.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </main>
</body>
</html>