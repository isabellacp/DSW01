package org.ufscar.dc.dsw.domain;

public class Arquivo {
    private String nome;
    private String path;
    private int id;
    private Pacote pacote;
    private String tipo;

    public Arquivo(int id) {
        this.id = id;
    }

    public Arquivo(String nome, String path, String tipo) {
        this.nome = nome;
        this.path = path;
        this.tipo = tipo;

    }

    public Arquivo(String nome, String path, int id, Pacote pacote, String tipo) {
        this.nome = nome;
        this.path = path;
        this.id = id;
        this.pacote = pacote;
        this.tipo = tipo;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
