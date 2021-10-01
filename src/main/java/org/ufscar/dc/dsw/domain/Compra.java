package org.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Compra {
    private int id;
    private Cliente cliente;
    private Pacote pacote;
    private Date data;
    private String link;

    public Compra(Cliente cliente, Pacote pacote, Date data, String link) {
        this.cliente = cliente;
        this.pacote = pacote;
        this.data = data;
        this.link = link;
    }

    public Compra(int id, Cliente cliente, Pacote pacote, Date data, String link) {
        this.id = id;
        this.cliente = cliente;
        this.pacote = pacote;
        this.data = data;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
