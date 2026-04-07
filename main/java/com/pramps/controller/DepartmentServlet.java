package com.pramps.controller;

import com.pramps.model.Pessoa;
import com.pramps.view.ViewUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Pessoa pessoa = (Pessoa) session.getAttribute("pessoa");

        try (PrintWriter out = response.getWriter()) {
            ViewUtils.writeHeader(out, "Departamentos", request.getContextPath() + "/styles.css");
            out.println("<painel>");
            out.println("<usuario>" + (pessoa != null ? "Usuário: " + pessoa.getNome() : "Nenhum usuário conectado. Faça login.") + "</usuario>");
            out.println("<departamento><h2>Departamentos</h2>");
            out.println("<div><a href=\"produtos_eletronicos.html\" target=\"mainFrame\">Eletrônicos</a></div>");
            out.println("<div><a href=\"produtos_roupas.html\" target=\"mainFrame\">Roupas</a></div>");
            out.println("<div><a href=\"produtos_livros.html\" target=\"mainFrame\">Livros</a></div>");
            out.println("</departamento>");
            out.println("<departamento><h3>Opções</h3>");
            out.println("<div><a class=\"botao\" href=\"LoginServlet\" target=\"leftFrame\">Login / Cadastro</a></div>");
            out.println("<div style=\"margin-top: 10px;\"><a class=\"botao\" href=\"CarrinhoServlet\" target=\"mainFrame\">Ver Carrinho</a></div>");
            out.println("</departamento>");
            out.println("</painel>");
            ViewUtils.writeFooter(out);
        }
    }
}
