package org.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Pacote {
    private int id;
    private String destino;
    private Date partida;
    private int duracao;
    private float valor;
    private Agencia agencia;

    public Pacote(int id, String destino, Date partida, int duracao, float valor, Agencia agencia) {
        this.id = id;
        this.destino = destino;
        this.partida = partida;
        this.duracao = duracao;
        this.valor = valor;
        this.agencia = agencia;
    }

    public Pacote(String destino, Date partida, int duracao, float valor, Agencia agencia) {
        this.destino = destino;
        this.partida = partida;
        this.duracao = duracao;
        this.valor = valor;
        this.agencia = agencia;
    }

    public Pacote(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getPartida() {
        return partida;
    }

    public void setPartida(Date partida) {
        this.partida = partida;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
}
