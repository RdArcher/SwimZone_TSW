package model;

import java.io.Serializable;

public class ProdottoBean implements Serializable{
	
	private int id_prodotto;
	private String nome;
	private float prezzo;
	private String descrizione;
	private String taglia;
	private String colore;
	private int quantita;
	private boolean attivo;
	
	private String path;
	private String mimeType;
	
	public ProdottoBean() {}
	
	public int getID_Prodotto() {
        return id_prodotto;
    }

    public void setID_prdotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    
    public boolean getStato() {
    	return attivo;
    }
    
    public boolean setStato(boolean attivo) {
    	return this.attivo=attivo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
	
}
