package com.pramps.model;

public class Pessoa {
    private final String nome;
    private final String login;
    private final String senha;
    private final String telefone;

    public Pessoa(String nome, String login, String senha, String telefone) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }
}
