package model;

public class Pedidos {
    private  int id;
    private String produto;
    private double valor;
    private int qtd;
    private String obs;

    private int idTipo;

    private int numPedido;
    private int idFunc;
    private int idProduto;
    private int nMesa;

    public Pedidos(String produto, double valor, int qtd) {
        this.produto = produto;
        this.valor = valor;
        this.qtd = qtd;
    }

    public Pedidos(int idProduto, String produto, double valor, int qtd, String obs) {
        this.produto = produto;
        this.valor = valor;
        this.qtd = qtd;
        this.obs = obs;
        this.idProduto = idProduto;
    }

    public Pedidos(int id, int idProduto, int qtd, String obs, double valor) {
        this.qtd = qtd;
        this.obs = obs;
        this.idProduto = idProduto;
        this.id = id;
        this.valor=valor;
    }

    public Pedidos(int id, double valor, int qtd, String obs, int idTipo) {
        this.id = id;
        this.valor = valor;
        this.qtd = qtd;
        this.obs = obs;
        this.idTipo = idTipo;
    }

    public int getnumPedido() {
        return numPedido;
    }

    public void setnumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getnMesa() {
        return nMesa;
    }

    public void setnMesa(int nMesa) {
        this.nMesa = nMesa;
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
