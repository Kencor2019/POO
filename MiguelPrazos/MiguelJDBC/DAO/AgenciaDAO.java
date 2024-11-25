package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Bancos.Agencia;
import Bancos.Banco;

public class AgenciaDAO {
    private Connection conn;

    public AgenciaDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvar(Agencia a) throws SQLException {
        String sql = "INSERT INTO Agencia (nome, banco_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, a.getNome());
            stmt.setInt(2, a.getBanco().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            }
        }
    }

    public Agencia buscar(int id) throws SQLException {
        String sql = "SELECT * FROM Agencia WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Banco b = new Banco(rs.getInt("banco_id"), "Banco Exemplo");
                return new Agencia(rs.getInt("id"), rs.getString("nome"), b);
            }
        }
        return null;
    }

    public List<Agencia> listar() throws SQLException {
        List<Agencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Agencia";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Banco b = new Banco(rs.getInt("banco_id"), "Banco Exemplo");
                lista.add(new Agencia(rs.getInt("id"), rs.getString("nome"), b));
            }
        }
        return lista;
    }
}