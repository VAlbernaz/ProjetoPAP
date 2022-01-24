package model;

public class Pedidos {
    private String produto;
    private double valor;
    private int qtd;
    private String obs;

    private int idTipo;

    public Pedidos(String produto, double valor, int qtd, String obs, int idTipo) {
        this.produto = produto;
        this.valor = valor;
        this.qtd = qtd;
        this.obs = obs;
        this.idTipo = idTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
