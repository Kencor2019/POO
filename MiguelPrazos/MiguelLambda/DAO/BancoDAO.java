package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Bancos.Banco;

public class BancoDAO {
    private Connection conn;

    public BancoDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvar(Banco b) throws SQLException {
        String sql = "INSERT INTO Banco (nome) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, b.getNome());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                b.setId(rs.getInt(1));
            }
        }
    }

    public Banco buscar(int id) throws SQLException {
        String sql = "SELECT * FROM Banco WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Banco(rs.getInt("id"), rs.getString("nome"));
            }
        }
        return null;
    }

    public List<Banco> listar() throws SQLException {
        List<Banco> lista = new ArrayList<>();
        String sql = "SELECT * FROM Banco";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                lista.add(new Banco(rs.getInt("id"), rs.getString("nome")));
            }
        }
        return lista;
    }
}