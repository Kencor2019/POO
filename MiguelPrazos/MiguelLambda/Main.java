import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Bancos.Agencia;
import Bancos.Banco;
import Bancos.Cliente;
import Bancos.Conta;
import Conection.Conexao;
import DAO.AgenciaDAO;
import DAO.BancoDAO;
import DAO.ClienteDAO;
import DAO.ContaDAO;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = Conexao.obtemConexao()) {

            Banco banco = new Banco(0, "Banco Exemplo");
            Agencia agencia = new Agencia(0, "Agência Centro", banco);
            Cliente cliente = new Cliente(0, "João Silva");
            Conta conta = new Conta(0, 5000.00, agencia);

            BancoDAO bancoDAO = new BancoDAO(conn);
            AgenciaDAO agenciaDAO = new AgenciaDAO(conn);
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            ContaDAO contaDAO = new ContaDAO(conn);

            bancoDAO.salvar(banco);
            agenciaDAO.salvar(agencia);
            clienteDAO.salvar(cliente);
            contaDAO.salvar(conta);
            clienteDAO.associar(cliente, conta);

            List<Banco> bancos = bancoDAO.listar();
            List<Agencia> agencias = agenciaDAO.listar();
            List<Conta> contas = contaDAO.listar();
            List<Cliente> clientes = clienteDAO.listar();

            Banco bancoEncontrado = MetodosFacilitadores.buscarBancoPorId(bancos, 1).orElse(null);
            System.out.println("Banco Encontrado: " + bancoEncontrado.getNome());

            List<Agencia> agenciasDoBanco = MetodosFacilitadores.filtrarAgenciasPorBanco(agencias, 1);
            System.out.println("Agências do Banco: " + agenciasDoBanco.size());

            List<Conta> contasDaAgencia = MetodosFacilitadores.filtrarContasPorAgencia(contas, 1);
            System.out.println("Contas da Agência: " + contasDaAgencia.size());

            List<Cliente> clientesDaConta = MetodosFacilitadores.filtrarClientesPorConta(clientes, contas, conta.getNumero());
            System.out.println("Clientes da Conta: " + clientesDaConta.size());

            List<Conta> contasComSaldoMaiorQue2000 = MetodosFacilitadores.filtrarContasPorSaldo(contas, 2000);
            System.out.println("Contas com saldo maior que 2000: " + contasComSaldoMaiorQue2000.size());

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou manipular o banco de dados: " + e.getMessage());
        }
    }
}
