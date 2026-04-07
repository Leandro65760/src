package com.pramps.model;

public class CartItem {
    private final Produto produto;
    private int quantidade;

    public CartItem(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = Math.max(1, quantidade);
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = Math.max(1, quantidade);
    }

    public double getTotalPrice() {
        return produto.getPreco() * quantidade;
    }
}
