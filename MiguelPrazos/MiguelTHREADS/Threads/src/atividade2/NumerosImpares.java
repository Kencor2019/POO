package atividade2;

import java.util.Random;

public class NumerosImpares implements Runnable {

    @Override
    public void run() {
        int contador = 1;
        while (contador < 15) {
            try {
                Random rand = new Random();
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            
            if (contador % 2 != 0) {
                System.out.println("Impar: " + contador);
            }
            contador++;
        }
    }
}
