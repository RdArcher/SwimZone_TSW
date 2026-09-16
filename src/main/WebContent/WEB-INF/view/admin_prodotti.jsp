<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ProdottoBean" %>
<%@ page import="java.util.Collection" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin prodotti</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/admin_prodotti" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="action" value="add">
    
    <input type="text" name="nome" placeholder="Nome Prodotto" required><br>
    <input type="number" name="id_categoria" placeholder="ID Categoria (es. 1)" required><br>
    <input type="text" name="descrizione" placeholder="Descrizione" required><br>
    <input type="text" name="colore" placeholder="Colore" required><br>
    <input type="text" name="taglia" placeholder="Taglia (es. M, Unica)" required><br>
    <input type="number" step="0.01" name="prezzo" placeholder="Prezzo" required><br>
    <input type="number" name="quantita" placeholder="Quantità" required><br>
    
    <label>Immagine:</label>
    <input type="file" name="immagine" accept="image/*" required><br><br>
    
    <button type="submit">Aggiungi Prodotto</button>
</form>
	
	<hr>
	
	<table>
	    <tr><th>Nome</th><th>Prezzo</th><th>Azione</th></tr>
	    <% for(ProdottoBean p : (Collection<ProdottoBean>) request.getAttribute("prodotti")) { %>
	    <tr>
	        <td><%= p.getNome() %></td>
	        <td>€ <%= p.getPrezzo() %></td>
	        <td>
	            <form action="<%=request.getContextPath()%>/admin_prodotti" method="POST">
	                <input type="hidden" name="action" value="delete">
	                <input type="hidden" name="id_prodotto" value="<%= p.getID_prodotto() %>">
	                <button type="submit" style="background: red; color: white;">Rimuovi</button>
	            </form>
	        </td>
	    </tr>
	    <% } %>
	</table>
</body>
</html>