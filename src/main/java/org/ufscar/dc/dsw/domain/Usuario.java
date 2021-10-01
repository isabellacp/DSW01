package org.ufscar.dc.dsw.domain;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private String nome;
    private String role;

    public Usuario(int id, String email, String senha, String nome, String role) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.role = role;
    }

    public Usuario(String email, String senha, String nome, String role) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.role = role;
    }

    public Usuario(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}