<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UtenteBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="images/logo.png">
    <title>SwimZone</title>
    <link rel="stylesheet" href="styles/home.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/home">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/Prodotti">Prodotti</a></li>
                <li><a href="<%=request.getContextPath()%>/Carrello">Carrello</a></li>
                
                <% 
                    UtenteBean utente = (UtenteBean) session.getAttribute("utente");
                    if (utente != null) { 
                %>
                    <li><a href="<%=request.getContextPath()%>/Ordini">I miei ordini</a></li>
                    
                    <li><a href="<%=request.getContextPath()%>/profilo">Il mio Profilo</a></li>
                    
					<% if (utente != null && utente.getRuolo() == 2) { %>
   						 <li><a href="<%=request.getContextPath()%>/admin_ordini">Pannello Admin</a></li>
					<% } %>
                    
                    <li><a href="<%=request.getContextPath()%>/logout">Disconnetti</a></li>
                    
                <% } else { %>
                    <li><a href="<%=request.getContextPath()%>/LoginServlet">Accedi</a></li>
                    <li><a href="<%=request.getContextPath()%>/RegistrazioneServlet">Registrati</a></li>
                <% } %>
            </ul>
        </nav>
    </header>

    <main>
        <div class="m_section">
            <img src="images/piscina_desktop.png" alt="Sfondo Piscina" id="fotoHomeDesktop">
            <img src="images/piscina_mobile.jpg" alt="Sfondo Piscina Mobile" id="fotoHomeTelefono">
            
            <div class="m-testo">
                <h1>Domina l'acqua con stile</h1>
                <p>L'equipaggiamento perfetto per la tua passione, dalla piscina alle competizioni.</p>
                <br>
                <a href="<%=request.getContextPath()%>/Prodotti" class="btn-catalogo">Esplora il Catalogo</a>
            </div>
        </div>

        <section class="promo-slider-section">
            <div class="slider-wrapper">
                <div class="slider" id="promoSlider">
                    <a href="<%=request.getContextPath()%>/Prodotti?categoria=sconti" class="slide">
                        <img src="images/promo1.jpeg" alt="Spedizione gratuita">
                    </a>
                    <a href="<%=request.getContextPath()%>/Prodotti?categoria=accessori" class="slide">
                        <img src="images/promo2.jpeg" alt="Promo costume">
                    </a>
                    <a href="<%=request.getContextPath()%>/Prodotti" class="slide">
                        <img src="images/promo3.jpeg" alt="Nuova cobra">
                    </a>
                </div>
                
                <button class="slider-btn prev" onclick="moveSlide(-1)">&#10094;</button>
                <button class="slider-btn next" onclick="moveSlide(1)">&#10095;</button>
            </div>
        </section>

 
        <div class="info-section">
            <div class="info-card">
                <h3>Spedizione Veloce</h3>
                <p>Consegna in 24/48h in tutta Italia</p>
            </div>
            <div class="info-card">
                <h3>Qualità Premium</h3>
                <p>I migliori brand selezionati per te</p>
            </div>
            <div class="info-card">
                <h3>Pagamenti Sicuri</h3>
                <p>Transazioni protette al 100%</p>
            </div>
        </div>
    </main>

    <footer>
        &copy; 2026, SwimZone
    </footer>

    <script src="script/sconti.js"></script>
</body>
</html>