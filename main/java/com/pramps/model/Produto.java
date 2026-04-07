package com.pramps.model;

public class Produto {
    private final String id;
    private final String nome;
    private final String descricao;
    private final double preco;
    private final String imagem;
    private final String departamento;

    public Produto(String id, String nome, String descricao, double preco, String imagem, String departamento) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
        this.departamento = departamento;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public String getImagem() {
        return imagem;
    }

    public String getDepartamento() {
        return departamento;
    }
}
