<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.CarrelloBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
<!-- Collegamento al file CSS globale -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css">
</head>
<body>
	<header>
		<a href="<%=request.getContextPath()%>/home">
			<!-- Logo -->
		</a>
		
		<nav>
			<ul>
				<li><a href="<%=request.getContextPath()%>/home">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/Prodotti">Prodotti</a></li>
				
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
		<h1>Il tuo carrello</h1>
		<br>
		
		<% 
			List<CarrelloBean> carrello = (List<CarrelloBean>) session.getAttribute("carrello");
			
			if (carrello == null || carrello.isEmpty()) { 
		%>
				<div class="msg_errore">
					<h2>Il tuo carrello è vuoto</h2>
					<p>Non hai ancora aggiunto nessun costume al tuo ordine.</p>
					<br>
					<a href="<%=request.getContextPath()%>/Prodotti" class="btn-back">Ritorna al Catalogo</a>
				</div>
		<% 
			} else {
				float totaleComplessivo = 0;
		%>
				<table class="tabella-carrello">
					<thead>
						<tr>
							<th>Prodotto</th>
							<th>Prezzo Unitario</th>
							<th>Quantità</th>
							<th>Subtotale</th>
							<th>Azioni</th>
						</tr>
					</thead>
					<tbody>
						<% 
							for (CarrelloBean item : carrello) { 
								float subtotale = item.getPrezzoTotale();
								totaleComplessivo += subtotale;
						%>
							<tr>
								<td><%= item.getProdotto().getNome() %></td>
								<td>&euro; <%= String.format("%.2f", item.getProdotto().getPrezzo()) %></td>
								<td><%= item.getQuantita() %></td>
								<td class="bold">&euro; <%= String.format("%.2f", subtotale) %></td>
								<td>
									<form action="<%=request.getContextPath()%>/Carrello" method="POST">
										<input type="hidden" name="azione" value="rimuovi">
										<input type="hidden" name="id" value="<%= item.getProdotto().getID_prodotto() %>">
										<button type="submit" class="btn-remove">Rimuovi</button>
									</form>
								</td>
							</tr>
						<% } %>
					</tbody>
				</table>

				<div class="riepilogo-carrello">
					<h3 class="totale-txt">Totale Ordine: &euro; <%= String.format("%.2f", totaleComplessivo) %></h3>
					
					<div class="azioni-bottoni">
						<form action="<%=request.getContextPath()%>/Carrello" method="POST">
							<input type="hidden" name="azione" value="svuota">
							<button type="submit" class="btn-svuota">Svuota Carrello</button>
						</form>
						<% if (session.getAttribute("utente") != null) { %>
							<a href="<%=request.getContextPath()%>/Ordine?azione=checkout" class="btn-checkout">Procedi al Checkout</a>
						<% } else { %>
							<a href="<%=request.getContextPath()%>/login" class="btn-checkout">Accedi per continuare</a>
						<% } %>
					</div>
				</div>
		<% } %>
	</main>

</body>
</html>