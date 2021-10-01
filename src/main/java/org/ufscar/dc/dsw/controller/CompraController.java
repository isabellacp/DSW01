package org.ufscar.dc.dsw.controller;

import org.ufscar.dc.dsw.dao.ClienteDAO;
import org.ufscar.dc.dsw.dao.CompraDAO;
import org.ufscar.dc.dsw.dao.PacoteDAO;
import org.ufscar.dc.dsw.domain.Cliente;
import org.ufscar.dc.dsw.domain.Compra;
import org.ufscar.dc.dsw.domain.Pacote;
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


@WebServlet(urlPatterns = "/compra" +
        "/*")
public class CompraController extends HttpServlet {

    private PacoteDAO pacote_dao;
    private ClienteDAO cliente_dao;
    private CompraDAO compra_dao;

    @Override
    public void init() {
        pacote_dao = new PacoteDAO();
        cliente_dao = new ClienteDAO();
        compra_dao = new CompraDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuario == null || !Objects.equals(usuario.getRole(), "cliente")) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/efetuar":
                    efetuar(request, response);
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
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        List<Compra> listaPacotes = compra_dao.getbyCliente(usuario.getId());
        request.setAttribute("listaCompras", listaPacotes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/compra/lista.jsp");
        dispatcher.forward(request, response);
    }


    private void efetuar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int pacote_id = Integer.parseInt(request.getParameter("pacote_id"));
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Cliente cliente = cliente_dao.get(usuario.getId());
        Pacote pacote = pacote_dao.get(pacote_id);
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        Compra compra = new Compra(cliente, pacote, sqlDate, "");
        compra_dao.insert(compra);
        response.sendRedirect("lista");

    }
}