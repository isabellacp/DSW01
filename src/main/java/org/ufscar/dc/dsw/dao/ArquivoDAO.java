package org.ufscar.dc.dsw.dao;

import org.ufscar.dc.dsw.domain.Arquivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArquivoDAO extends GenericDAO {
    PacoteDAO pacoteDAO;

    public ArquivoDAO() {
        this.pacoteDAO = new PacoteDAO();
    }

    public void insert(Arquivo arquivo) {
        String sql = "Insert INTO ARQUIVO(pacote_id, name, path,tipo) VALUES (?,?,?,?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, arquivo.getPacote().getId());
            statement.setString(2, arquivo.getNome());
            statement.setString(3, arquivo.getPath());
            statement.setString(4, arquivo.getTipo());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Arquivo arquivo) {
        String sql = "DELETE FROM Arquivo where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, arquivo.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public Arquivo get(int id) {
        Arquivo arquivo = null;

        String sql = "SELECT * from ARQUIVO WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int pacote_id = resultSet.getInt("pacote_id");
                String path = resultSet.getString("path");
                String nome = resultSet.getString("name");
                String tipo = resultSet.getString("tipo");
                arquivo = new Arquivo(nome, path, id, pacoteDAO.get(pacote_id), tipo);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arquivo;
    }
}
