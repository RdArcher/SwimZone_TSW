package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrdineBean implements Serializable {
    
    private int idOrdine; 
    private float totale;;
    private boolean stato;
    private Date data;
    private UtenteBean utente;
    private List<DettaglioOrdineBean> prodotti;

    public OrdineBean() {
    	this.prodotti = new ArrayList<>();
    }
    
    public void setUtente(UtenteBean utente) {
    	this.utente=utente;
    }
    
    public UtenteBean getUtente() {
    	return utente;
    }

    public int getIdOrdine() {
    	return idOrdine; 
    	}
    
    public void setIdOrdine(int idOrdine) { 
    	this.idOrdine = idOrdine; 
    	}

    public float getTotale() { 
    	return totale; 
    	}
    
    public void setTotale(float prezzoAcquisto) { 
    	this.totale = prezzoAcquisto; }
    
    public boolean getStato() { 
        return stato; 
    }
    
    public void setStato(boolean stato) { 
        this.stato = stato; 
    }
    
    public Date getData() {
    	return data;
    }
    
    public void setData(Date data) {
    	this.data=data;
    }
    
    public List<DettaglioOrdineBean> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<DettaglioOrdineBean> prodotti) {
        this.prodotti = prodotti;
    }

    public void addProdotto(DettaglioOrdineBean prodotto) {
        this.prodotti.add(prodotto);
    }
}