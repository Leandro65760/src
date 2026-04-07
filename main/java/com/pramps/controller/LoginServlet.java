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
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        if (login == null || login.isBlank() || !isValidEmail(login)) {
            showLoginError(response, "Informe um e-mail válido.");
            return;
        }
        if (senha == null || senha.length() < 6) {
            showLoginError(response, "A senha deve ter pelo menos 6 caracteres.");
            return;
        }

        List<Pessoa> pessoas = getPessoas(request);
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getLogin().equalsIgnoreCase(login) && pessoa.getSenha().equals(senha)) {
                HttpSession session = request.getSession();
                session.setAttribute("pessoa", pessoa);
                response.sendRedirect(request.getContextPath() + "/DepartmentServlet");
                return;
            }
        }

        showLoginError(response, "Login ou senha incorretos.");
    }

    @SuppressWarnings("unchecked")
    private List<Pessoa> getPessoas(HttpServletRequest request) {
        return (List<Pessoa>) getServletContext().getAttribute("pessoas");
    }

    private void showLoginError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ViewUtils.writeHeader(out, "Login", "styles.css");
            out.println("<painel>");
            out.println("<mensagem>" + message + "</mensagem>");
            out.println("<a class=\"botao\" href=\"login.html\">Voltar ao login</a>");
            out.println("</painel>");
            ViewUtils.writeFooter(out);
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }
}
