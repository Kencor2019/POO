package atividade6;

import java.util.Stack;

public class Eater6 implements Runnable {
    Stack<Integer> fila;

    public Eater6(Stack<Integer> fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumidor: " + Thread.currentThread() + " consumindo...");
                fila.pop();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}