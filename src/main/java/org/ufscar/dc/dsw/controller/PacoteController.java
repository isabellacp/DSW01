package org.ufscar.dc.dsw.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.ufscar.dc.dsw.dao.AgenciaDAO;
import org.ufscar.dc.dsw.dao.ArquivoDAO;
import org.ufscar.dc.dsw.dao.PacoteDAO;
import org.ufscar.dc.dsw.domain.Agencia;
import org.ufscar.dc.dsw.domain.Arquivo;
import org.ufscar.dc.dsw.domain.Pacote;
import org.ufscar.dc.dsw.domain.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

import static org.ufscar.dc.dsw.controller.Constants.*;


@WebServlet(urlPatterns = "/pacote/*")
public class PacoteController extends HttpServlet {

    private PacoteDAO dao;
    private ArquivoDAO arquivo_dao;

    @Override
    public void init() {
        dao = new PacoteDAO();
        arquivo_dao = new ArquivoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }  else if (!(action.equals("/lista-vigente") || action.equals("/listar")) && (usuario != null && !Objects.equals(usuario.getRole(), "agencia"))) {
            response.sendRedirect(request.getContextPath());
            return;
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
                case "/lista-vigente":
                    listaVigentes(request, response);
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
        List<Pacote> listaPacotes = dao.getAll();
        request.setAttribute("listaPacotes", listaPacotes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacote/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void listaVigentes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pacote> listaPacotes = dao.getAllVigentes();
        request.setAttribute("listaPacotes", listaPacotes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacote/lista.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Integer, String> getAgencias() {
        Map<Integer, String> agencias = new HashMap<>();
        for (Agencia editora : new AgenciaDAO().getAll()) {
            agencias.put(editora.getId(), editora.getNome());
        }
        return agencias;
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacote/formulario.jsp");
        request.setAttribute("agencias", getAgencias());
        dispatcher.forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Pacote pacote = new Pacote(id);
        dao.delete(pacote);
        response.sendRedirect("lista");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        System.out.println("3333");
        Map<String, String> values = new HashMap<>();
        List<Arquivo> files = new ArrayList<Arquivo>();

        if (ServletFileUpload.isMultipartContent(request)) {
            System.out.println("aglgo");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                System.out.println("mkdir");
                System.out.println(uploadDir.getAbsolutePath());

                uploadDir.mkdir();
            }

            try {
                List<FileItem> formItems = upload.parseRequest(request);

                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            String fileName = new File(item.getName()).getName();
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                            request.getSession().setAttribute("message", "File " + fileName + " has uploaded successfully!");
                            files.add(new Arquivo(fileName, filePath, item.getContentType()));
                        } else {
                            values.put(item.getFieldName(), item.getString());
                            System.out.println(item.getFieldName() + "- " + item.getString());
                        }
                    }
                }
            } catch (Exception ex) {
                request.getSession().setAttribute("message", "There was an error: " + ex.getMessage());
            }
            int id = Integer.parseInt(values.get("id"));

            int agencia_id = Integer.parseInt(values.get("agencia"));
            String destino = values.get("destino");
            Date partida = Date.valueOf(values.get("partida"));
            int duracao = Integer.parseInt(values.get("duracao"));
            float valor = Float.parseFloat(values.get("valor"));
            Agencia agencia = new Agencia(agencia_id);

            Pacote pacote = new Pacote(id, destino, partida, duracao, valor, agencia);
            dao.update(pacote);
            for (Arquivo arquivo : files) {
                arquivo.setPacote(pacote);
                arquivo_dao.insert(arquivo);
            }
            response.sendRedirect("lista");
        }
    }

    private void edit_form(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Pacote pacote = dao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacote/formulario.jsp");
        request.setAttribute("agencias", getAgencias());
        request.setAttribute("pacote", pacote);
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        System.out.println("3333");
        Map<String, String> values = new HashMap<>();
        List<Arquivo> files = new ArrayList<Arquivo>();

        if (ServletFileUpload.isMultipartContent(request)) {
            System.out.println("aglgo");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                System.out.println("mkdir");
                System.out.println(uploadDir.getAbsolutePath());

                uploadDir.mkdir();
            }

            try {
                List<FileItem> formItems = upload.parseRequest(request);

                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            String fileName = new File(item.getName()).getName();
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                            request.getSession().setAttribute("message", "File " + fileName + " has uploaded successfully!");
                            files.add(new Arquivo(fileName, filePath, item.getContentType()));
                        } else {
                            values.put(item.getFieldName(), item.getString());
                            System.out.println(item.getFieldName() + "- " + item.getString());
                        }
                    }
                }
            } catch (Exception ex) {
                request.getSession().setAttribute("message", "There was an error: " + ex.getMessage());
            }
            int agencia_id = Integer.parseInt(values.get("agencia"));
            String destino = values.get("destino");
            Date partida = Date.valueOf(values.get("partida"));
            int duracao = Integer.parseInt(values.get("duracao"));
            float valor = Float.parseFloat(values.get("valor"));
            Agencia agencia = new Agencia(agencia_id);

            Pacote pacote = new Pacote(destino, partida, duracao, valor, agencia);
            Pacote pacote_com_id = dao.insert(pacote);
            for (Arquivo arquivo : files) {
                arquivo.setPacote(pacote_com_id);
                arquivo_dao.insert(arquivo);
            }
            response.sendRedirect("lista");
        }
    }
}