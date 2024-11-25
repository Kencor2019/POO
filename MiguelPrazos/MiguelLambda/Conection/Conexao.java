package Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int DB_PORT = 3306;
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "coltec";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "senhaSuperSegura";

    private static Connection conn = null;

    static {
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            System.err.println("Falha ao carregar o driver: " + ex.getMessage());
        }
    }

    private Conexao() {}

    public static Connection obtemConexao() throws SQLException {
        String url = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
        }
        return conn;
    }

    public static void fechaConexao() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}