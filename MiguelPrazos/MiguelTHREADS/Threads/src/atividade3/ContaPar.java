package atividade3;

public class ContaPar implements Runnable {

    Controlar Controlar;

    public ContaPar(Controlar Controlar) {
        this.Controlar = Controlar;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i += 2) {
            Controlar.imprimePar(i);
        }
    }
}