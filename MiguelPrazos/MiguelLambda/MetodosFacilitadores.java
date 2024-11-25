import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Bancos.Agencia;
import Bancos.Banco;
import Bancos.Cliente;
import Bancos.Conta;

public class MetodosFacilitadores {

    public static Optional<Banco> buscarBancoPorId(List<Banco> bancos, int id) {
        return bancos.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
    }

    public static List<Agencia> filtrarAgenciasPorBanco(List<Agencia> agencias, int bancoId) {
        return agencias.stream()
                .filter(a -> a.getBanco().getId() == bancoId)
                .collect(Collectors.toList());
    }

    public static List<Conta> filtrarContasPorAgencia(List<Conta> contas, int agenciaId) {
        return contas.stream()
                .filter(c -> c.getAgencia().getId() == agenciaId)
                .collect(Collectors.toList());
    }

    public static List<Cliente> filtrarClientesPorConta(List<Cliente> clientes, List<Conta> contas, int contaNumero) {
        return clientes.stream()
                .filter(c -> contas.stream().anyMatch(co -> co.getClientes().contains(c) && co.getNumero() == contaNumero))
                .collect(Collectors.toList());
    }

    public static List<Conta> filtrarContasPorSaldo(List<Conta> contas, double saldoMinimo) {
        return contas.stream()
                .filter(c -> c.getSaldo() >= saldoMinimo)
                .collect(Collectors.toList());
    }
}
