package atividade3;

public class Controlar {

    boolean turnoPar = true;

    synchronized void imprimePar(int numero) {
        try {
            while (!turnoPar) {
                wait();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        
        if (numero % 2 == 0) {
            System.out.println("Par: " + numero);
            turnoPar = false;
            notify();
        }
    }

    synchronized void imprimeImpar(int numero) {
        try {
            while (turnoPar) {
                wait();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        
        if (numero % 2 != 0) {
            System.out.println("Impar: " + numero);
            turnoPar = true;
            notify();
        }
    }
}