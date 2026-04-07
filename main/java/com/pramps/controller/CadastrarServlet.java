package com.pramps.controller;

import com.pramps.model.Pessoa;
import com.pramps.view.ViewUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/CadastrarServlet")
public class CadastrarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/cadastro.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");

        if (nome == null || nome.isBlank()) {
            showRegisterError(response, "Informe o nome completo.");
            return;
        }
        if (login == null || login.isBlank() || !isValidEmail(login)) {
            showRegisterError(response, "Informe um e-mail válido.");
            return;
        }
        if (senha == null || senha.length() < 6) {
            showRegisterError(response, "A senha deve ter pelo menos 6 caracteres.");
            return;
        }
        if (telefone == null || telefone.isBlank() || !isValidPhone(telefone)) {
            showRegisterError(response, "Informe um telefone válido no formato (xx)xxxxx-xxxx.");
            return;
        }

        List<Pessoa> pessoas = getPessoas(request);
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getLogin().equalsIgnoreCase(login)) {
                showRegisterError(response, "Esse e-mail já está cadastrado.");
                return;
            }
        }

        pessoas.add(new Pessoa(nome, login.toLowerCase(), senha, telefone));
        response.sendRedirect(request.getContextPath() + "/LoginServlet");
    }

    @SuppressWarnings("unchecked")
    private List<Pessoa> getPessoas(HttpServletRequest request) {
        return (List<Pessoa>) getServletContext().getAttribute("pessoas");
    }

    private void showRegisterError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ViewUtils.writeHeader(out, "Cadastro", "styles.css");
            out.println("<painel>");
            out.println("<mensagem>" + message + "</mensagem>");
            out.println("<a class=\"botao\" href=\"cadastro.html\">Voltar ao cadastro</a>");
            out.println("</painel>");
            ViewUtils.writeFooter(out);
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$");
    }
}
