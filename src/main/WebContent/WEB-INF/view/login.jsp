<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accedi - SwimZone</title>
</head>
<body>
	<header>
		<nav>
			<li><a href="<%= request.getContextPath()%>/home">Home</a></li>
			<li><a href="<%= request.getContextPath()%>/Prodotti">Prodotti</a></li>
		</nav>
	</header>
	
	<main class="container">
		<div class="form-container">
			<h1>Accedi al tuo accout</h1>
			<% String errore = (String) request.getAttribute("errore");
				if(errore!=null){%>
					<div class="m_errore" style="color:red; font-weight:bold; margin-bottom:15px;">
					<%= errore %>
		</div>
		<% } %>
		
		<form action="<%= request.getContextPath()%>/LoginServlet" method="POST" class="form_aut">
		<% String from = request.getParameter("from"); 
       if (from != null) { %>
        <input type="hidden" name="from" value="<%= from %>">
    <% } %>
			<div class="gruppo-form">
				<label for="email">Email</label>
				<input type="email" id="email" name="email" required>
			</div>
			
			<div class="gruppo-form">
				<label for="password">Password</label>
				<input type="password" if="password" name="password">
			</div>
			
			<button type="submit" class="invia">Accedi</button>
		</form>

		<p class="link">Non hai un account= <a href="<%=request.getContextPath()%>/RegistrazioneServlet">Registrati qui</a></p>
	</main>
</body>
</html>