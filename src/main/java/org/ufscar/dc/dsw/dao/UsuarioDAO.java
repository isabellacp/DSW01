package org.ufscar.dc.dsw.dao;


import org.ufscar.dc.dsw.domain.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends GenericDAO {

    public Usuario insert(Usuario usuario) {

        String sql = "Insert into usuario(email, senha, nome, role) VALUES (?, ?, ?, ?)";
        String[] generatedColumns = {"ID"};
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, generatedColumns);
            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getRole());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                usuario.setId((int) rs.getLong(1));
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public List<Usuario> getAll() {

        List<Usuario> Usuarios = new ArrayList<>();

        String sql = "SELECT * from Usuario u";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String role = resultSet.getString("role");

                Usuario usuario = new Usuario(id, email, senha, nome, role);
                Usuarios.add(usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Usuarios;
    }

    public void delete(Usuario usuario) {
        String sql = "DELETE FROM Usuario where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }


    public Usuario get(int id) {
        Usuario usuario = null;

        String sql = "SELECT * from Usuario WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                usuario = new Usuario(id, email, senha, nome, role);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE USUARIO SET EMAIL = ?, SENHA=?, NOME=?, ROLE=? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getRole());
            statement.setInt(5, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario getbyEmail(String email) {
        Usuario usuario = null;

        String sql = "SELECT * from Usuario WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String role = resultSet.getString("role");
                int id = resultSet.getInt("id");
                usuario = new Usuario(id, email, senha, nome, role);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

}