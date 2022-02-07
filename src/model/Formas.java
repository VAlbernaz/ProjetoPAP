package model;

public class Formas {
    private int idForma;
    private String forma;

    public Formas(int idForma, String forma) {
        this.idForma = idForma;
        this.forma = forma;
    }

    public int getIdForma() {
        return idForma;
    }

    public void setIdForma(int idForma) {
        this.idForma = idForma;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
}
