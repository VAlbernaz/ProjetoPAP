package model;

public class tipos {
    private int ID;
    private String tipo;

    public tipos(int ID, String tipo) {
        this.ID = ID;
        this.tipo = tipo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
