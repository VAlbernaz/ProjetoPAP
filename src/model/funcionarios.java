package model;

import java.util.Date;

public class funcionarios {
    private int ID;
    private  String nome;

    private String primNome;
    private String ultiNome;
    private Date dataNascimento;
    private String sexo;
    private int numFunc;
    private String atividade;

    private String atividadeEdit;

    public funcionarios(String primNome, String ultiNome, Date dataNascimento, String sexo, int numFunc) {
        this.primNome = primNome;
        this.ultiNome = ultiNome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.numFunc = numFunc;
    }

    public funcionarios(int ID, String atividadeEdit) {
        this.ID = ID;
        this.atividadeEdit = atividadeEdit;
    }

    public funcionarios(int ID, String nome, String atividade, int numFunc) {
        this.ID = ID;
        this.nome = nome;
        this.atividade = atividade;
        this.numFunc = numFunc;
    }

    public String getPrimNome() {
        return primNome;
    }

    public void setPrimNome(String primNome) {
        this.primNome = primNome;
    }

    public String getUltiNome() {
        return ultiNome;
    }

    public void setUltiNome(String ultiNome) {
        this.ultiNome = ultiNome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getAtividadeEdit() {
        return atividadeEdit;
    }

    public void setAtividadeEdit(String atividadeEdit) {
        this.atividadeEdit = atividadeEdit;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public int getNumFunc() {
        return numFunc;
    }

    public void setNumFunc(int numFunc) {
        this.numFunc = numFunc;
    }

}
