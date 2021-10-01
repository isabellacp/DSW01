package org.ufscar.dc.dsw.dao;

import org.ufscar.dc.dsw.domain.Agencia;
import org.ufscar.dc.dsw.domain.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDAO extends GenericDAO {
    UsuarioDAO usuarioDAO;

    public AgenciaDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void insert(Agencia agencia) {
        Usuario usuario = usuarioDAO.insert(agencia);
        String sql = "Insert INTO AGENCIA(id, cnpj, descricao) VALUES (?, ?,?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, usuario.getId());
            statement.setString(2, agencia.getCnpj());
            statement.setString(3, agencia.getDescricao());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Agencia> getAll() {

        List<Agencia> listaAgencias = new ArrayList<>();

        String sql = "SELECT * from AGENCIA NATURAL JOIN USUARIO";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                Agencia agencia = new Agencia(id, email, senha, nome, cnpj, descricao);
                listaAgencias.add(agencia);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgencias;
    }

    public void delete(Agencia agencia) {
        String sql = "DELETE FROM Agencia where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, agencia.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Agencia agencia) {
        usuarioDAO.update(agencia);
        String sql = "UPDATE Agencia SET descricao = ?, cnpj = ?  WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agencia.getDescricao());
            statement.setString(2, agencia.getCnpj());
            statement.setInt(3, agencia.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Agencia get(int id) {
        Agencia agencia = null;

        String sql = "SELECT * from AGENCIA NATURAL JOIN USUARIO WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                agencia = new Agencia(id, email, senha, nome, cnpj, descricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return agencia;
    }

    public Agencia getbyEmail(String email) {
        Agencia agencia = null;

        String sql = "SELECT * from AGENCIA NATURAL JOIN USUARIO WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");

                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String descricao = resultSet.getString("descricao");

                agencia = new Agencia(id, email, senha, nome, cnpj, descricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return agencia;
    }
}
