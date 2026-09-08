<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dettaglio Prodotto</title>
<!-- Collegamento al file CSS esterno -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/dettaglio.css">
</head>
<body>
	<header>
		<a href="<%=request.getContextPath()%>/home">
			<!-- Immagine logo -->
		</a>
		
		<nav>
			<ul>
				<li><a href="<%=request.getContextPath()%>/home">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/Prodotti">Prodotti</a></li>
				
				<%-- Controllo login in Java puro --%>
				<% if (session.getAttribute("utente") != null) { %>
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
				<!-- Immagine del prodotto -->
				
				<div class="dettagli">
					<h2 class="titolo"><%=prodotto.getNome()%></h2>
					<p class="descrizione"><%=prodotto.getDescrizione()%></p>
					<p class="prezzo"><%=prodotto.getPrezzo()%></p>
					<p class="disponibilita"><%=prodotto.getQuantita()%> pezzi</p>
					
					<form action="<%=request.getContextPath()%>/carrello" method="POST" class="form-carrello">
						<input type="hidden" name="azione" value="aggiungi">
                  	  	<input type="hidden" name="id" value="<%=prodotto.getID_Prodotto()%>">
                    	<input type="number" name="quantita" value="1" min="1" max="<%=prodotto.getQuantita()%>">
                    	<button type="submit" class="btn-add">Aggiungi al Carrello</button>
					</form>
					
					<a href="<%=request.getContextPath()%>/Prodotti" class="btn-back">Torna al catalogo</a>
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