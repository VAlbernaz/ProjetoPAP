package model;

public class Faturas {
    private String dataHora;
    private int numPedido;
    private double valorPedido;

    private String produto;
    private String qtd;
    private String preco;
    private String func;

    public Faturas(String produto, String qtd, String preco, String func) {
        this.produto = produto;
        this.qtd = qtd;
        this.preco = preco;
        this.func = func;
    }

    public Faturas(String dataHora, int numPedido, double valorPedido) {
        this.dataHora = dataHora;
        this.numPedido = numPedido;
        this.valorPedido = valorPedido;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public double getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(double valorPedido) {
        this.valorPedido = valorPedido;
    }
}
