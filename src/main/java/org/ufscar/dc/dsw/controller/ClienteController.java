package org.ufscar.dc.dsw.controller;

import org.ufscar.dc.dsw.dao.ClienteDAO;
import org.ufscar.dc.dsw.domain.Cliente;
import org.ufscar.dc.dsw.domain.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;


@WebServlet(urlPatterns = "/cliente/*")
public class ClienteController extends HttpServlet {

    private ClienteDAO dao;

    @Override
    public void init() {
        dao = new ClienteDAO();
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
                case "/edicao":
                    edit_form(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    delete(request, response);
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
        List<Cliente> listaClientes = dao.getAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/lista.jsp");
        dispatcher.forward(request, response);
    }


    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void edit_form(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = dao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/formulario.jsp");
        request.setAttribute("cliente", cliente);
        dispatcher.forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = new Cliente(id);
        dao.delete(cliente);
        response.sendRedirect("lista");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));

        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");
        String nome = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        Date date = Date.valueOf(request.getParameter("nascimento"));

        Cliente cliente = new Cliente(id, email, senha, nome, cpf, telefone, sexo, date);
        dao.update(cliente);

        response.sendRedirect("lista");
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");
        String nome = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        Date date = Date.valueOf(request.getParameter("nascimento"));

        Cliente cliente = new Cliente(id, email, senha, nome, cpf, telefone, sexo, date);
        dao.insert(cliente);

        response.sendRedirect("lista");
    }
}