package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Bancos.Cliente;
import Bancos.Conta;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvar(Cliente c) throws SQLException {
        String sql = "INSERT INTO Cliente (nome) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, c.getNome());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                c.setId(rs.getInt(1));
            }
        }
    }

    public Cliente buscar(int id) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(rs.getInt("id"), rs.getString("nome"));
            }
        }
        return null;
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("id"), rs.getString("nome")));
            }
        }
        return lista;
    }

    public void associar(Cliente c, Conta co) throws SQLException {
        String sql = "INSERT INTO Conta_Cliente (conta_numero, cliente_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, co.getNumero());
            stmt.setInt(2, c.getId());
            stmt.executeUpdate();
        }
    }

    public void desassociar(Cliente c, Conta co) throws SQLException {
        String sql = "DELETE FROM Conta_Cliente WHERE conta_numero = ? AND cliente_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, co.getNumero());
            stmt.setInt(2, c.getId());
            stmt.executeUpdate();
        }
    }
}