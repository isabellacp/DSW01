package org.ufscar.dc.dsw.dao;

import org.ufscar.dc.dsw.domain.Agencia;
import org.ufscar.dc.dsw.domain.Pacote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacoteDAO extends GenericDAO {

    public Pacote insert(Pacote pacote) {

        String sql = "Insert into PACOTE(AGENCIA_ID, destino, partida, duracao, valor) VALUES (?, ?, ?, ?, ?)";
        String[] generatedColumns = {"ID"};

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, generatedColumns);
            statement.setInt(1, pacote.getAgencia().getId());
            statement.setString(2, pacote.getDestino());
            statement.setDate(3, pacote.getPartida());
            statement.setInt(4, pacote.getDuracao());
            statement.setFloat(5, pacote.getValor());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                pacote.setId((int) rs.getLong(1));
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacote;
    }

    public List<Pacote> getAll() {

        List<Pacote> listaPacotes = new ArrayList<>();

        String sql = "SELECT * from PACOTE u";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String destino = resultSet.getString("destino");
                Date partida = resultSet.getDate("partida");
                int duracao = resultSet.getInt("duracao");
                float valor = resultSet.getFloat("valor");
                Agencia agencia = new AgenciaDAO().get(resultSet.getInt("agencia_id"));
                Pacote pacote = new Pacote(id, destino, partida, duracao, valor, agencia);
                listaPacotes.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacotes;
    }

    public List<Pacote> getAllVigentes() {

        List<Pacote> listaPacotes = new ArrayList<>();

        String sql = "SELECT * from PACOTE u WHERE PARTIDA > current date";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String destino = resultSet.getString("destino");
                Date partida = resultSet.getDate("partida");
                int duracao = resultSet.getInt("duracao");
                float valor = resultSet.getFloat("valor");
                Agencia agencia = new AgenciaDAO().get(resultSet.getInt("agencia_id"));
                Pacote pacote = new Pacote(id, destino, partida, duracao, valor, agencia);
                listaPacotes.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacotes;
    }

    public void delete(Pacote pacote) {
        String sql = "DELETE FROM Pacote where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, pacote.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Pacote pacote) {
        String sql = "UPDATE Pacote SET AGENCIA_ID = ?, destino = ?, partida = ?, duracao = ?, valor = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, pacote.getAgencia().getId());
            statement.setString(2, pacote.getDestino());
            statement.setDate(3, pacote.getPartida());
            statement.setInt(4, pacote.getDuracao());
            statement.setFloat(5, pacote.getValor());
            statement.setInt(6, pacote.getId());


            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pacote get(int id) {
        Pacote pacote = null;

        String sql = "SELECT * from Pacote WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String destino = resultSet.getString("destino");
                Date partida = resultSet.getDate("partida");
                int duracao = resultSet.getInt("duracao");
                float valor = resultSet.getFloat("valor");
                Agencia agencia = new AgenciaDAO().get(resultSet.getInt("agencia_id"));
                pacote = new Pacote(id, destino, partida, duracao, valor, agencia);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacote;
    }

    public Pacote getbyAgencia(int agencia_id) {
        Pacote pacote = null;

        String sql = "SELECT * from Pacote WHERE AGENCIA_ID = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, agencia_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String destino = resultSet.getString("destino");
                Date partida = resultSet.getDate("partida");
                int duracao = resultSet.getInt("duracao");
                float valor = resultSet.getFloat("valor");
                Agencia agencia = new AgenciaDAO().get(agencia_id);
                pacote = new Pacote(id, destino, partida, duracao, valor, agencia);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacote;
    }
}