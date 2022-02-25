package model;

public class Produtos {
    private int ID;
    private String produto;
    private double preco;

    private String fornecedor;
    private int nFornecedor;
    private String tipo;
    private int nTipo;

    private int qtd;
    private String obs;


    public Produtos(int ID, String produto, double preco, int nTipo) {
        this.ID = ID;
        this.produto = produto;
        this.preco = preco;
        this.nTipo = nTipo;
    }

    public Produtos(int ID, String produto, int qtd) {
        this.ID = ID;
        this.produto = produto;
        this.qtd = qtd;
    }

    public Produtos(String produto, double preco, int nFornecedor, int nTipo) {
        this.produto = produto;
        this.preco = preco;
        this.nFornecedor = nFornecedor;
        this.nTipo = nTipo;
    }

    public Produtos(String produto, double preco) {
        this.produto = produto;
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
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
