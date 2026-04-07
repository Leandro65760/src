package com.pramps.controller;

import com.pramps.model.CartItem;
import com.pramps.view.ViewUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/CarrinhoServlet")
public class CarrinhoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Map<String, CartItem> carrinho = (Map<String, CartItem>) session.getAttribute("carrinho");

        try (PrintWriter out = response.getWriter()) {
            ViewUtils.writeHeader(out, "Carrinho", request.getContextPath() + "/styles.css");
            out.println("<painel>");
            out.println("<h2>Meu Carrinho</h2>");

            if (carrinho == null || carrinho.isEmpty()) {
                out.println("<mensagem>Seu carrinho está vazio. Selecione produtos no painel direito.</mensagem>");
            } else {
                double total = 0.0;
                out.println("<ul class=\"lista-itens\">");
                for (CartItem item : carrinho.values()) {
                    double itemTotal = item.getTotalPrice();
                    total += itemTotal;
                    out.println("<li>");
                    out.println("<strong>" + item.getProduto().getNome() + "</strong><br>");
                    out.println("Preço unitário: R$ " + String.format("%.2f", item.getProduto().getPreco()) + "<br>");
                    out.println("Quantidade: " + item.getQuantidade() + "<br>");
                    out.println("Total do item: R$ " + String.format("%.2f", itemTotal) + "<br>");
                    out.println("<form method=\"post\" action=\"ExcluirServlet\">\n");
                    out.println("<input type=\"hidden\" name=\"produtoId\" value=\"" + item.getProduto().getId() + "\">\n");
                    out.println("<button class=\"botao\" type=\"submit\">Excluir item</button>");
                    out.println("</form>");
                    out.println("</li>");
                }
                out.println("</ul>");
                out.println("<div class=\"footer-total\">Total geral: R$ " + String.format("%.2f", total) + "</div>");
            }

            out.println("</painel>");
            ViewUtils.writeFooter(out);
        }
    }
}
