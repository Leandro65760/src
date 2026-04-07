package com.pramps.controller;

import com.pramps.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet("/ExcluirServlet")
public class ExcluirServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String produtoId = request.getParameter("produtoId");
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Map<String, CartItem> carrinho = (Map<String, CartItem>) session.getAttribute("carrinho");
        if (carrinho != null) {
            carrinho.remove(produtoId);
        }
        response.sendRedirect(request.getContextPath() + "/CarrinhoServlet");
    }
}
