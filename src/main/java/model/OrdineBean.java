package model;

import java.io.Serializable;

public class OrdineBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idOrdine;
    private int idProdotto; 
    private float prezzoAcquisto;
    private int quantita;
    private boolean stato;

    public OrdineBean() {}

    public int getIdOrdine() {
    	return idOrdine; 
    	}
    
    public void setIdOrdine(int idOrdine) { 
    	this.idOrdine = idOrdine; 
    	}

    public int getIdProdotto() { 
    	return idProdotto; 
    	}
    
    public void setIdProdotto(int idProdotto) {
    	this.idProdotto = idProdotto; 
    	}

    public float getPrezzoAcquisto() { 
    	return prezzoAcquisto; 
    	}
    
    public void setPrezzoAcquisto(float prezzoAcquisto) { 
    	this.prezzoAcquisto = prezzoAcquisto; }

    public int getQuantita() { 
    	return quantita; 
    	}
    
    public void setQuantita(int quantita) { 
    	this.quantita = quantita; 
    	}
    
    public boolean isStato() { 
        return stato; 
    }
    
    public void setStato(boolean stato) { 
        this.stato = stato; 
    }
}