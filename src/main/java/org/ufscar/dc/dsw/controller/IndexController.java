package org.ufscar.dc.dsw.controller;

import org.ufscar.dc.dsw.dao.UsuarioDAO;
import org.ufscar.dc.dsw.domain.Usuario;
import org.ufscar.dc.dsw.util.Erro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Index", urlPatterns = {"/index.jsp", "/logout.jsp"})
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erro erros = new Erro();
        if (request.getParameter("bOK") != null) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            if (email == null || email.isEmpty()) {
                erros.add("Login não informado!");
            }
            if (senha == null || senha.isEmpty()) {
                erros.add("Senha não informada!");

            }

            if (erros.isExisteErros()) {
                InvalidateAndSendErrors(request, response, erros);
                return;
            }

            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.getbyEmail(email);

            if (usuario == null) {
                erros.add("Usuário não encontrado!");
                InvalidateAndSendErrors(request, response, erros);
                return;
            }

            if (!usuario.getSenha().equalsIgnoreCase(senha)) {
                erros.add("Senha incorreta!");
                InvalidateAndSendErrors(request, response, erros);
                return;
            }

            request.getSession().setAttribute("usuarioLogado", usuario);
            response.sendRedirect("logado/painel.jsp");
            return;

        }
        request.getSession().setAttribute("usuarioLogado", null);
        request.getSession().invalidate();

        String URL = "/login.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }

    private void InvalidateAndSendErrors(HttpServletRequest request, HttpServletResponse response, Erro erros) throws ServletException, IOException {
        request.getSession().setAttribute("usuarioLogado", null);

        request.getSession().invalidate();
        request.setAttribute("mensagens", erros);
        String URL = "/login.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}