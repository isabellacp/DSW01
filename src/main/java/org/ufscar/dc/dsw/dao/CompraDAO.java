package org.ufscar.dc.dsw.dao;

import org.ufscar.dc.dsw.domain.Cliente;
import org.ufscar.dc.dsw.domain.Compra;
import org.ufscar.dc.dsw.domain.Pacote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO extends GenericDAO {

    public void insert(Compra compra) {

        String sql = "Insert into Compra(cliente_id, pacote_id, data, link) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, compra.getCliente().getId());
            statement.setInt(2, compra.getPacote().getId());
            statement.setDate(3, compra.getData());
            statement.setString(4, compra.getLink());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Compra> getAll() {

        List<Compra> listaCompras = new ArrayList<>();

        String sql = "SELECT * from Compra u";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int cliente_id = resultSet.getInt("cliente_id");
                int pacote_id = resultSet.getInt("pacote_id");
                Date data = resultSet.getDate("data");
                String link = resultSet.getString("link");
                Cliente cliente = new ClienteDAO().get(cliente_id);
                Pacote pacote = new PacoteDAO().get(pacote_id);
                Compra compra = new Compra(id, cliente, pacote, data, link);
                listaCompras.add(compra);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCompras;
    }

    public void delete(Compra compra) {
        String sql = "DELETE FROM Compra where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, compra.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Compra compra) {
        String sql = "UPDATE Compra SET CLIENTE_ID = ?, pacote_id = ?, data = ?, link = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setInt(1, compra.getCliente().getId());
            statement.setInt(2, compra.getPacote().getId());
            statement.setDate(3, compra.getData());
            statement.setString(4, compra.getLink());
            statement.setInt(4, compra.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Compra get(int id) {
        Compra compra = null;

        String sql = "SELECT * from Compra WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int cliente_id = resultSet.getInt("cliente_id");
                int pacote_id = resultSet.getInt("pacote_id");
                Date data = resultSet.getDate("data");
                String link = resultSet.getString("link");
                Cliente cliente = new ClienteDAO().get(cliente_id);
                Pacote pacote = new PacoteDAO().get(pacote_id);

                compra = new Compra(id, cliente, pacote, data, link);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return compra;
    }

    public List<Compra> getbyCliente(int id) {
        List<Compra> listaCompras = new ArrayList<>();

        String sql = "SELECT * from Compra WHERE CLIENTE_ID = ?";
        Cliente cliente = new ClienteDAO().get(id);
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int pacote_id = resultSet.getInt("pacote_id");
                Date data = resultSet.getDate("data");
                String link = resultSet.getString("link");
                Pacote pacote = new PacoteDAO().get(pacote_id);
                Compra compra = new Compra(id, cliente, pacote, data, link);
                listaCompras.add(compra);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCompras;
    }
}