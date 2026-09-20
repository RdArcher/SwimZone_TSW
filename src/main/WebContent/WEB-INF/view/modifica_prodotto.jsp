<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifica Prodotto - Admin</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/home.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/home">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/Prodotti">Catalogo</a></li>
                <li><a href="<%=request.getContextPath()%>/admin_prodotti">Admin Prodotti</a></li>
                <li><a href="<%=request.getContextPath()%>/admin_ordini">Admin Ordini</a></li>
            </ul>
        </nav>
    </header>

    <main class="container">
        <h1>Modifica Prodotto</h1>
        <a href="<%=request.getContextPath()%>/admin_prodotti" class="btn-admin btn-grigio" style="margin-bottom: 20px;">Torna alla lista prodotti</a>

        <div class="admin-sezione">
            <% ProdottoBean p = (ProdottoBean) request.getAttribute("prodotto"); %>
            
            <form action="<%=request.getContextPath()%>/admin_prodotti" method="POST">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id_prodotto" value="<%= p.getID_prodotto() %>">
                
                <div class="admin-form-group">
                    <label>Nome Prodotto</label>
                    <input type="text" name="nome" value="<%= p.getNome() %>" required>
                </div>
                
                <div class="admin-form-group">
                    <label>Prezzo (&euro;)</label>
                    <input type="number" step="0.01" name="prezzo" value="<%= String.format(java.util.Locale.US, "%.2f", p.getPrezzo()) %>" required>
                </div>
                
                <div class="admin-form-group">
                    <label>Quantità in magazzino</label>
                    <input type="number" name="quantita" value="<%= p.getQuantita() %>" required>
                </div>
                
                <div class="admin-form-group">
                    <label>ID Categoria (1: Donna, 2: Uomo, 3: Occhialini)</label>
                    <input type="number" name="id_categoria" value="<%= p.getID_categoria() %>" required>
                </div>

                <div class="admin-form-group">
                    <label>Colore</label>
                    <input type="text" name="colore" value="<%= p.getColore()%>">
                </div>

                <div class="admin-form-group">
                    <label>Taglia</label>
                    <input type="text" name="taglia" value="<%= p.getTaglia()%>">
                </div>

                <div class="admin-form-group">
                    <label>Descrizione</label>
                    <textarea name="descrizione" rows="4"><%= p.getDescrizione()%></textarea>
                </div>
                
                <div style="margin-top: 20px; display: flex; gap: 10px;">
                    <button type="submit" class="btn-admin btn-verde">Salva Modifiche</button>
                    <a href="<%=request.getContextPath()%>/admin_prodotti" class="btn-admin btn-grigio" style="text-decoration: none;">Annulla</a>
                </div>
            </form>
        </div>
    </main>
</body>
</html>