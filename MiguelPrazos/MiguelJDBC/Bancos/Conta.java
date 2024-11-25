package Bancos;

import java.util.List;

public class Conta {
    private int numero;
    private double saldo;
    private Agencia agencia;
    private List<Cliente> clientes;

    public Conta(int numero, double saldo, Agencia agencia) {
        this.numero = numero;
        this.saldo = saldo;
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}