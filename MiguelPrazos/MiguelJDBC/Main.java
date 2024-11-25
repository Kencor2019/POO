import java.sql.*;
import java.util.List;
import DAO.*;
import Bancos.*;
import Conection.Conexao;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = Conexao.obtemConexao()) {

            Banco banco = new Banco(0, "Banco Exemplo");
            Agencia agencia = new Agencia(0, "Agência Centro", banco);

            BancoDAO bancoDAO = new BancoDAO(conn);
            AgenciaDAO agenciaDAO = new AgenciaDAO(conn);
            bancoDAO.salvar(banco);
            agenciaDAO.salvar(agencia);

            Cliente cliente = new Cliente(0, "João Silva");

            ClienteDAO clienteDAO = new ClienteDAO(conn);
            clienteDAO.salvar(cliente);

            Conta conta = new Conta(0, 5000.00, agencia);

            ContaDAO contaDAO = new ContaDAO(conn);
            contaDAO.salvar(conta);

            clienteDAO.associar(cliente, conta);

            System.out.println("Contas associadas ao cliente " + cliente.getNome() + ":");
            List<Conta> contas = contaDAO.listar();
            for (Conta c : contas) {
                System.out.println("Conta nº: " + c.getNumero() + ", Saldo: " + c.getSaldo());
            }

            clienteDAO.desassociar(cliente, conta);

            System.out.println("\nApós desassociação:");
            contas = contaDAO.listar();
            for (Conta c : contas) {
                System.out.println("Conta nº: " + c.getNumero() + ", Saldo: " + c.getSaldo());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou manipular o banco de dados: " + e.getMessage());
        }
    }
}
