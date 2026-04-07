package com.pramps.controller;

import com.pramps.model.Produto;
import com.pramps.model.Pessoa;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<String, List<Produto>> departments = new LinkedHashMap<>();
        List<Produto> eletronicos = List.of(
                new Produto("E001", "Fone Bluetooth", "Fone de ouvido bluetooth com cancelamento de ruído", 249.90, "imagem/fone.svg", "Eletrônicos"),
                new Produto("E002", "Smartphone", "Smartphone com 128 GB e câmera tripla", 1999.90, "imagem/celular.svg", "Eletrônicos"),
                new Produto("E003", "Notebook", "Notebook leve com processador rápido", 4299.00, "imagem/notebook.svg", "Eletrônicos"),
                new Produto("E004", "Smartwatch", "Relógio inteligente com monitor cardíaco", 549.90, "imagem/relogio.svg", "Eletrônicos"),
                new Produto("E005", "Tablet", "Tablet de 10 polegadas com caneta", 1299.90, "imagem/tablet.svg", "Eletrônicos"),
                new Produto("E006", "Caixa de som", "Caixa de som bluetooth portátil", 189.90, "imagem/caixa.svg", "Eletrônicos")
        );
        List<Produto> roupas = List.of(
                new Produto("R001", "Camisa Polo", "Camisa polo em algodão premium", 89.90, "imagem/camisa.svg", "Roupas"),
                new Produto("R002", "Calça Jeans", "Calça jeans com corte moderno", 149.90, "imagem/calca.svg", "Roupas"),
                new Produto("R003", "Jaqueta", "Jaqueta leve e confortável", 219.90, "imagem/jaqueta.svg", "Roupas"),
                new Produto("R004", "Vestido", "Vestido casual com estampa exclusiva", 129.90, "imagem/vestido.svg", "Roupas"),
                new Produto("R005", "Tênis", "Tênis esportivo de alta performance", 179.90, "imagem/tenis.svg", "Roupas"),
                new Produto("R006", "Boné", "Boné estiloso com ajuste traseiro", 49.90, "imagem/bone.svg", "Roupas")
        );
        List<Produto> livros = List.of(
                new Produto("L001", "Java Avançado", "Livro com técnicas avançadas em Java", 99.90, "imagem/livro.svg", "Livros"),
                new Produto("L002", "CSS Moderno", "Guia completo de estilo e design web", 79.90, "imagem/livro.svg", "Livros"),
                new Produto("L003", "História do Brasil", "Narrativas dos principais fatos históricos", 64.90, "imagem/livro.svg", "Livros"),
                new Produto("L004", "Fotografia", "Manual de fotografia digital e composição", 89.90, "imagem/livro.svg", "Livros"),
                new Produto("L005", "Receitas Veganas", "Receitas saudáveis e práticas para o dia a dia", 59.90, "imagem/livro.svg", "Livros"),
                new Produto("L006", "Autoajuda", "Dicas para aumentar foco e produtividade", 69.90, "imagem/livro.svg", "Livros")
        );

        departments.put("Eletrônicos", eletronicos);
        departments.put("Roupas", roupas);
        departments.put("Livros", livros);

        Map<String, Produto> productCatalog = new LinkedHashMap<>();
        eletronicos.forEach(product -> productCatalog.put(product.getId(), product));
        roupas.forEach(product -> productCatalog.put(product.getId(), product));
        livros.forEach(product -> productCatalog.put(product.getId(), product));

        sce.getServletContext().setAttribute("departments", departments);
        sce.getServletContext().setAttribute("productCatalog", productCatalog);
        sce.getServletContext().setAttribute("pessoas", new ArrayList<Pessoa>());
    }
}
