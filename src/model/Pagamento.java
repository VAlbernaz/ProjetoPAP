package model;

public class Pagamento {
    private int idPedido;
    private String contribuinte;
    private int idForma;

    public Pagamento(int idPedido, String contribuinte, int idForma) {
        this.idPedido = idPedido;
        this.contribuinte = contribuinte;
        this.idForma = idForma;
    }

    public String getContribuinte() {
        return contribuinte;
    }

    public void setContribuinte(String contribuinte) {
        this.contribuinte = contribuinte;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }



    public int getIdForma() {
        return idForma;
    }

    public void setIdForma(int idForma) {
        this.idForma = idForma;
    }
}
