package atividade5;

import java.util.Stack;

public class Productor implements Runnable {
    Stack<Integer> fila;

    public Productor(Stack<Integer> fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Adicionando elemento à fila...");
                fila.add(1);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}