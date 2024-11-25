package atividade4;

public class Increment implements Runnable {

    private int valor;

    public Increment(int valor) {
        this.valor = valor;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 100000; i++) {
            valor++;
        }
        String nomeThread = Thread.currentThread().getName();
        System.out.println("Thread " + nomeThread + " terminou de incrementar.");
    }

    public int obterValor() {
        return valor;
    }
}
