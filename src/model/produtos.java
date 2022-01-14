package model;

public class produtos {
    private int ID;
    private String produto;
    private double preco;

    private String fornecedor;
    private int nFornecedor;
    private String tipo;
    private int nTipo;


    public produtos(String produto, double preco, int nFornecedor, int nTipo) {
        this.produto = produto;
        this.preco = preco;
        this.nFornecedor = nFornecedor;
        this.nTipo = nTipo;
    }

    public produtos(String produto, double preco) {
        this.produto = produto;
        this.preco = preco;
    }

    public int getnFornecedor() {
        return nFornecedor;
    }

    public void setnFornecedor(int nFornecedor) {
        this.nFornecedor = nFornecedor;
    }

    public int getnTipo() {
        return nTipo;
    }

    public void setnTipo(int nTipo) {
        this.nTipo = nTipo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
