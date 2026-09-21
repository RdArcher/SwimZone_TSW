<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UtenteBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="images/logo.png">
<title>Dettaglio Prodotto</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/dettaglio.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/home.css">
</head>
<body>
	<header>
		<a href="<%=request.getContextPath()%>/home">
		</a>
		
		<nav>
			<ul>
				<li><a href="<%=request.getContextPath()%>/home">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/Prodotti">Prodotti</a></li>
				
				<% 
					UtenteBean utente = (UtenteBean) session.getAttribute("utente");
					if (utente != null) { 
				%>
					<% if (utente.getRuolo() == 2) { %>
						<li><a href="<%=request.getContextPath()%>/admin_prodotti">Admin Prodotti</a></li>
						<li><a href="<%=request.getContextPath()%>/admin_ordini">Admin Ordini</a></li>
					<% } %>
					<li><a href="<%=request.getContextPath()%>/ordini">I miei ordini</a></li>
					<li><a href="<%=request.getContextPath()%>/logout">Disconnetti</a></li>
				<% } else { %>
					<li><a href="<%=request.getContextPath()%>/login">Accedi</a></li>
				<% } %>
			</ul>
		</nav>
	</header>
	
	<main class="container">
		<% 
			model.ProdottoBean prodotto = (model.ProdottoBean) request.getAttribute("prodotto");
			if (prodotto != null) { 
		%>
				<div class="prodotto-wrapper">
					<div class="immagine-container">
						<img src="<%=request.getContextPath()%>/<%=prodotto.getPath()%>" alt="<%=prodotto.getNome()%>" class="prodotto-img">
					</div>
					
					<div class="dettagli">
						<h2 class="titolo"><%=prodotto.getNome()%></h2>
						<p class="descrizione"><%=prodotto.getDescrizione()%></p>
						<p class="prezzo">€ <%=String.format("%.2f", prodotto.getPrezzo())%></p>
						
						<p class="attributo"><strong>Colore:</strong> <%=prodotto.getColore()%></p>
						<p class="attributo"><strong>Taglia:</strong> <%=prodotto.getTaglia()%></p>
						
						<% if (prodotto.getQuantita() > 0) { %>
							<p class="disponibilita">Disponibilità: <%=prodotto.getQuantita()%> pezzi</p>
							
							<form action="<%=request.getContextPath()%>/Carrello" method="POST" class="form-carrello">
								<input type="hidden" name="azione" value="aggiungi">
								<input type="hidden" name="id" value="<%=prodotto.getID_prodotto()%>">
								<input type="number" name="quantita" value="1" min="1" max="<%=prodotto.getQuantita()%>">
								<button type="submit" class="btn-add">Aggiungi al Carrello</button>
							</form>
						<% } else { %>
							<p class="esaurito">Prodotto esaurito</p>
						<% } %>
						
						<a href="<%=request.getContextPath()%>/Prodotti" class="btn-back">Torna al catalogo</a>
					</div>
				</div>
		<% } else { %>
				<div class="msg_errore">
					<h2>Prodotto non trovato</h2>
					<p>Il prodotto che stai cercando non esiste o è stato rimosso</p>
					<a href="<%=request.getContextPath()%>/Prodotti" class="btn-back">Torna al catalogo</a>
				</div>
		<% } %>
	</main>
</body>
</html>