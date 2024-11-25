package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Bancos.Agencia;
import Bancos.Banco;
import Bancos.Conta;

public class ContaDAO {
    private Connection conn;

    public ContaDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvar(Conta c) throws SQLException {
        String sql = "INSERT INTO Conta (numero, saldo, agencia_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, c.getNumero());
            stmt.setDouble(2, c.getSaldo());
            stmt.setInt(3, c.getAgencia().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                c.setNumero(rs.getInt(1));
            }
        }
    }

    public Conta buscar(int id) throws SQLException {
        String sql = "SELECT * FROM Conta WHERE numero = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Agencia ag = new Agencia(rs.getInt("agencia_id"), "Agência Exemplo", new Banco(1, "Banco Exemplo"));
                return new Conta(rs.getInt("numero"), rs.getDouble("saldo"), ag);
            }
        }
        return null;
    }

    public List<Conta> listar() throws SQLException {
        List<Conta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Conta";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Agencia ag = new Agencia(rs.getInt("agencia_id"), "Agência Exemplo", new Banco(1, "Banco Exemplo"));
                lista.add(new Conta(rs.getInt("numero"), rs.getDouble("saldo"), ag));
            }
        }
        return lista;
    }
}
