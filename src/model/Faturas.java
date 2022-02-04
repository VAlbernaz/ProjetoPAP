package model;

public class Faturas {
    private String dataHora;
    private int numPedido;
    private double valorPedido;

    public Faturas(String dataHora,int numPedido, double valorPedido) {
        this.dataHora = dataHora;
        this.numPedido = numPedido;
        this.valorPedido = valorPedido;
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
