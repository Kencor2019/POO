package Bancos;

public class Agencia {
    private int id;
    private String nome;
    private Banco banco;

    public Agencia(int id, String nome, Banco banco) {
        this.id = id;
        this.nome = nome;
        this.banco = banco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}
