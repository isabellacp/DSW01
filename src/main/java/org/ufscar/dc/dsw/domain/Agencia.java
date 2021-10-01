package org.ufscar.dc.dsw.domain;

public class Agencia extends Usuario {
    private String cnpj;
    private String descricao;

    public Agencia(int id, String email, String senha, String nome, String cnpj, String descricao) {
        super(id, email, senha, nome, "agencia");
        this.cnpj = cnpj;
        this.descricao = descricao;

    }

    public Agencia(String email, String senha, String nome, String cnpj, String descricao) {
        super(email, senha, nome, "agencia");
        this.cnpj = cnpj;
        this.descricao = descricao;

    }

    public Agencia(int id) {
        super(id, "agencia");
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
