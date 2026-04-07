package com.pramps.controller;

import com.pramps.model.CartItem;
import com.pramps.model.Produto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/ComprarServlet")
public class ComprarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String produtoId = request.getParameter("produtoId");
        int quantidade = 1;
        try {
            quantidade = Integer.parseInt(request.getParameter("quantidade"));
        } catch (NumberFormatException ignored) {
        }
        quantidade = Math.max(1, quantidade);

        Produto produto = findProductById(request, produtoId);
        if (produto != null) {
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            Map<String, CartItem> carrinho = (Map<String, CartItem>) session.getAttribute("carrinho");
            if (carrinho == null) {
                carrinho = new LinkedHashMap<>();
                session.setAttribute("carrinho", carrinho);
            }
            CartItem item = carrinho.get(produtoId);
            if (item == null) {
                carrinho.put(produtoId, new CartItem(produto, quantidade));
            } else {
                item.setQuantidade(item.getQuantidade() + quantidade);
            }
        }

        response.sendRedirect(request.getContextPath() + "/CarrinhoServlet");
    }

    @SuppressWarnings("unchecked")
    private Produto findProductById(HttpServletRequest request, String produtoId) {
        if (produtoId == null) {
            return null;
        }
        Map<String, Produto> productCatalog = (Map<String, Produto>) getServletContext().getAttribute("productCatalog");
        return productCatalog == null ? null : productCatalog.get(produtoId);
    }
}
