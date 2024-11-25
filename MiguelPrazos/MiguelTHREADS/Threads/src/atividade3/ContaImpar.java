package atividade3;

public class ContaImpar implements Runnable {

    Controlar Controlar;

    public ContaImpar(Controlar Controlar) {
        this.Controlar = Controlar;
    }

    @Override
    public void run() {
        for (int i = 1; i < 20; i += 2) {
            Controlar.imprimeImpar(i);
        }
    }
}

