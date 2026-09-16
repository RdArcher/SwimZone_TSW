<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrati - SwimZone</title>
    <link rel="stylesheet" href="styles/home.css">
    <link rel="stylesheet" href="styles/autenticazione.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/home">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/Prodotti">Prodotti</a></li>
            </ul>
        </nav>
    </header>

    <main class="container">
        <div class="form-container">
            <h1>Crea account</h1>
            <!--  
            <% String errore = (String) request.getAttribute("errore");
               if (errore != null) { %>
                <div class="m_errore" style="margin-bottom: 15px;">
                    <%= errore %>
                </div>
            <% } %>
-->
    <form id="form" action="<%=request.getContextPath()%>/RegistrazioneServlet" method="POST" onsubmit="return validate(event)" novalidate>
    
	    <div class="form-group">
	        <label for="nome">Nome</label>
	        <input type="text" id="nome" name="nome" required>
	        <span id="errorNome"></span>
	    </div>
	
	    <div class="form-group">
	        <label for="cognome">Cognome</label>
	        <input type="text" id="cognome" name="cognome" required>
	        <span id="errorCognome"></span>
	    </div>
	
	    <div class="form-group">
	        <label for="email">Email</label>
	        <input type="email" id="email" name="email" required>
	        <span id="errorMail"></span>
	    </div>
	    
	    <div class="form-group">
	        <label for="password">Password</label>
	        <input type="password" id="password" name="password" required>
	        <span id="errorPass"></span>
	    </div>
	    
	    <div class="form-group">
	        <label for="indirizzo_spedizione">Indirizzo di spedizione</label>
	        <input type="text" id="indirizzo_spedizione" name="indirizzo_spedizione" required>
	        <span id="errorIndirizzo"></span>
	    </div>
	    
	    <button type="submit" class="btn-submit">Registrati</button>
</form>
            
            <p class="link-auth">Hai già un account? <a href="<%=request.getContextPath()%>/LoginServlet">Accedi qui</a></p>
        </div>
    </main>
    
    <script src="script/validazioneRegistrazione.js"></script>
</body>
</html>