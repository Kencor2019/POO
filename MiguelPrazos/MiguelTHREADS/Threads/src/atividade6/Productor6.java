
package atividade6;

import java.util.Stack;

public class Productor6 implements Runnable {

    Stack<Integer> fila;

    public Productor6(Stack<Integer> fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Produtor: " + Thread.currentThread() + " adicionando...");
                fila.add(1);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
