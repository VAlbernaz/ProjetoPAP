package model;

public class pedidos {
    private String produto;
    private double valor;
    private int qtd;
    private String obs;

    public pedidos(String produto, double valor, int qtd, String obs) {
        this.produto = produto;
        this.valor = valor;
        this.qtd = qtd;
        this.obs = obs;
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
