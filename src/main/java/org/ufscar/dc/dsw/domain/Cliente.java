package org.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Cliente extends Usuario {

    private String cpf;
    private String telefone;
    private String sexo;
    private Date date;

    public Cliente(int id) {
        super(id, "cliente");
    }

    public Cliente(int id, String email, String senha, String nome, String cpf, String telefone, String sexo, Date date) {
        super(id, email, senha, nome, "cliente");
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.date = date;
    }

    public Cliente(String email, String senha, String nome, String cpf, String telefone, String sexo, Date date) {
        super(email, senha, nome, "cliente");
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.date = date;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}