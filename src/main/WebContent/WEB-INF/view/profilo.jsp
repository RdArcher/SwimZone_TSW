<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UtenteBean" %>
<% 
    UtenteBean utente = (UtenteBean) session.getAttribute("utente"); 
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="images/logo.png">
    <title>Il mio Profilo</title>
    <link rel="stylesheet" href="styles/home.css">
    <link rel="stylesheet" href="styles/autenticazione.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/home">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/Prodotti">Prodotti</a></li>
                <li><a href="<%=request.getContextPath()%>/Carrello">Carrello</a></li>
                <li><a href="<%=request.getContextPath()%>/Ordini">I miei ordini</a></li>
                <li><a href="<%=request.getContextPath()%>/logout">Disconnetti</a></li>
            </ul>
        </nav>
    </header>

    <main class="container">
        <div class="form-container">
            <h1>Modifica Profilo</h1>  
            <% String errore = (String) request.getAttribute("errore");
               String successo = (String) request.getAttribute("successo");
               if (errore != null) { %>
                <div class="m_errore" style="margin-bottom: 15px;">
                    <%= errore %>
                </div>
            <% } else if (successo != null) { %>
                <div style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; text-align: center; margin-bottom: 15px;">
                    <%= successo %>
                </div>
            <% } %>

            <form action="<%=request.getContextPath()%>/profilo" method="POST">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" value="<%= utente.getNome() %>" required>
                </div>

                <div class="form-group">
                    <label for="cognome">Cognome</label>
                    <input type="text" id="cognome" name="cognome" value="<%= utente.getCognome() %>" required>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="<%= utente.getEmail() %>" required>
                </div>
                
                <div class="form-group">
      				<label for="indirizzo_spedizione">Indirizzo di spedizione</label>
      				<input type="text" id="indirizzo_spedizione" name="indirizzo_spedizione" value="<%= utente.getIndirizzoSpedizione() %>" required>
    			</div>

                <div class="form-group">
                    <label for="password">Nuova Password</label>
                    <input type="password" id="password" name="password" placeholder="Lascia vuoto per non modificare">
                </div>
                
                <button type="submit" class="btn-submit">Salva Modifiche</button>
            </form>
        </div>
    </main>
</body>
</html>