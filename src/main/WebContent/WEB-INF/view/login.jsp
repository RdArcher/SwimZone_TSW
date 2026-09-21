<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/png" href="images/logo.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accedi - SwimZone</title>
    <link rel="stylesheet" href="styles/home.css">
    <link rel="stylesheet" href="styles/autenticazione.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="<%= request.getContextPath()%>/home">Home</a></li>
                <li><a href="<%= request.getContextPath()%>/Prodotti">Prodotti</a></li>
            </ul>
        </nav>
    </header>
	
    <main class="container">
        <div class="form-container">
            <h1>Accedi al tuo account</h1>
            
            <% String errore = (String) request.getAttribute("errore");
               if (errore != null) { %>
                <div class="m_errore" style="margin-bottom:15px;">
                    <%= errore %>
                </div>
            <% } %>
		
            <form id="formLogin" action="<%= request.getContextPath()%>/LoginServlet" method="POST" onsubmit="return validate()" novalidate>
                <% String from = request.getParameter("from"); 
                   if (from != null) { %>
                    <input type="hidden" name="from" value="<%= from %>">
                <% } %>
                
                <div class="form-group">
				    <label for="email">Email</label>
				    <input type="email" id="email" name="email" required onchange="validateFormElem(this, document.getElementById('errorMail'), emailErrorMessage)">
				    <span id="errorMail"></span>
				</div>
				
				<div class="form-group">
				    <label for="password">Password</label>
				    <input type="password" id="password" name="password" required onchange="validateFormElem(this, document.getElementById('errorPass'), passwordErrorMessage)">
				    <span id="errorPass"></span>
				</div>
                
                <button type="submit" class="btn-submit">Accedi</button>
            </form>

            <p class="link-auth">Non hai un account? <a href="<%=request.getContextPath()%>/RegistrazioneServlet">Registrati qui</a></p>
        </div>
    </main>
    
    <script src="script/validazioneLogin.js"></script>
</body>
</html>