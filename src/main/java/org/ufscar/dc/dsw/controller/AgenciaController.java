package org.ufscar.dc.dsw.controller;

import org.ufscar.dc.dsw.dao.AgenciaDAO;
import org.ufscar.dc.dsw.domain.Agencia;
import org.ufscar.dc.dsw.domain.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


@WebServlet(urlPatterns = "/agencia/*")
public class AgenciaController extends HttpServlet {

    private AgenciaDAO dao;

    @Override
    public void init() {
        dao = new AgenciaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuario == null || !Objects.equals(usuario.getRole(), "admin")) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    delete(request, response);
                    break;
                case "/edicao":
                    edit_form(request, response);
                    break;
                case "/atualizacao":
                    update(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Agencia> listaAgencias = dao.getAll();
        request.setAttribute("listaAgencias", listaAgencias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/lista.jsp");
        dispatcher.forward(request, response);
    }


    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Agencia agencia = new Agencia(id);
        dao.delete(agencia);
        response.sendRedirect("lista");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String cnpj = request.getParameter("cnpj");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String descricao = request.getParameter("descricao");
        String nome = request.getParameter("nome");

        Agencia agencia = new Agencia(id, email, senha, nome, cnpj, descricao);
        dao.update(agencia);

        response.sendRedirect("lista");
    }

    private void edit_form(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Agencia agencia = dao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/formulario.jsp");
        request.setAttribute("agencia", agencia);
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cnpj = request.getParameter("cnpj");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String descricao = request.getParameter("descricao");
        String nome = request.getParameter("nome");

        Agencia agencia = new Agencia(email, senha, nome, cnpj, descricao);
        dao.insert(agencia);

        response.sendRedirect("lista");
    }
}