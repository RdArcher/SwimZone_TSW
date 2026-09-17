<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ProdottoBean" %>
<%@ page import="java.util.Collection" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Prodotti</title>
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
        
        <h1>Gestione Catalogo Prodotti</h1>
        <a href="<%=request.getContextPath()%>/home" class="btn-admin btn-grigio" style="margin-bottom: 20px;">Torna alla Home</a>

        <div class="admin-sezione">
            <h2>Aggiungi Prodotto</h2>
            <form action="<%=request.getContextPath()%>/admin_prodotti" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="action" value="add">
                
                <div class="admin-form-group">
                    <label>Nome Prodotto</label>
                    <input type="text" name="nome" required>
                </div>
                
                <div class="admin-form-group">
                    <label>ID Categoria</label>
                    <input type="number" name="id_categoria" required>
                </div>
                
                <div class="admin-form-group">
                    <label>Descrizione</label>
                    <textarea name="descrizione" rows="3" required></textarea>
                </div>
                
                <div class="admin-form-group">
                    <label>Colore</label>
                    <input type="text" name="colore" required>
                </div>
                
                <div class="admin-form-group">
                    <label>Taglia</label>
                    <input type="text" name="taglia" required>
                </div>
                
                <div class="admin-form-group">
                    <label>Prezzo</label>
                    <input type="number" step="0.01" name="prezzo" required>
                </div>
                
                <div class="admin-form-group">
                    <label>Quantità</label>
                    <input type="number" name="quantita" required>
                </div>
                
                <div class="admin-form-group">
                    <label>Immagine</label>
                    <input type="file" name="immagine" accept="image/*" required>
                </div>
                
                <button type="submit" class="btn-admin btn-verde">Aggiungi Prodotto</button>
            </form>
        </div>
        
        <h2>Lista Prodotti</h2>
        <div class="admin-tabella-container">
            <table class="admin-tabella">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Foto</th>
                        <th>Nome</th>
                        <th>Prezzo</th>
                        <th>Qtà</th>
                        <th>Azione</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                       Collection<ProdottoBean> prodotti = (Collection<ProdottoBean>) request.getAttribute("prodotti");
                       if(prodotti != null && !prodotti.isEmpty()) {
                           for(ProdottoBean p : prodotti) { 
                    %>
                    <tr>
                        <td><%= p.getID_prodotto() %></td>
                        <td>
                            <img src="<%=request.getContextPath()%>/<%= p.getPath() %>" alt="img" class="admin-thumb">
                        </td>
                        <td><%= p.getNome() %></td>
                        <td>€ <%= p.getPrezzo() %></td>
                        <td><%= p.getQuantita() %></td>
                        <td>
                            <form action="<%=request.getContextPath()%>/admin_prodotti" method="POST" style="margin:0;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id_prodotto" value="<%= p.getID_prodotto() %>">
                                <button type="submit" class="btn-admin btn-rosso">Rimuovi</button>
                            </form>
                        </td>
                    </tr>
                    <%     } 
                       } else { %>
                    <tr>
                        <td colspan="6" style="text-align: center;">Nessun prodotto trovato.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>