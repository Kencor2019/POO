package atividade5;

import java.util.Stack;

public class Eater implements Runnable {
    Stack<Integer> fila;

    public Eater(Stack<Integer> fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumindo elemento da fila...");
                fila.pop();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}