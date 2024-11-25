import atividade3.Controlar;
import atividade4.Increment;
import atividade3.ContaImpar;
import atividade3.ContaPar;
import atividade5.Eater;
import atividade5.Productor;
import atividade6.Eater6;
import atividade6.Productor6;
import atividade1.PrimeiraThread;
import atividade2.NumerosImpares;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {}

    public static class Exercicios {

        public static void Tarefa1(){
            Thread tarefa0 = new Thread(new PrimeiraThread());
            tarefa0.start();
        }

        public static void Tarefa2(){
            Thread tarefa0 = new Thread(new NumerosImpares());
            Thread tarefa1 = new Thread(new NumerosImpares());

            tarefa0.start();
            tarefa1.start();
        }

        public static void Tarefa3(){
            Controlar controlador = new Controlar();

            Thread tarefa0 = new Thread(new ContaImpar(controlador));
            Thread tarefa1 = new Thread(new ContaPar(controlador));

            tarefa0.start();
            tarefa1.start();
        }

        public static void Tarefa4(){
            Increment incrementoGlobal = new Increment(0);

            Thread tarefa0 = new Thread(incrementoGlobal);
            Thread tarefa1 = new Thread(incrementoGlobal);
            Thread tarefa2 = new Thread(incrementoGlobal);
            Thread tarefa3 = new Thread(incrementoGlobal);
            Thread tarefa4 = new Thread(incrementoGlobal);

            tarefa0.start();
            tarefa1.start();
            tarefa2.start();
            tarefa3.start();
            tarefa4.start();

            try {
                tarefa0.join();
                tarefa1.join();
                tarefa2.join();
                tarefa3.join();
                tarefa4.join();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(incrementoGlobal.obterValor());
        }

        public static void Tarefa5(){
            Stack<Integer> pilhaDeItens = new Stack<>();

            Thread produtor = new Thread(new Productor(pilhaDeItens));
            Thread comedor = new Thread(new Eater(pilhaDeItens));

            produtor.start();
            comedor.start();
        }

        public static void Tarefa6(){
            Stack<Integer> pilhaDeItens = new Stack<>();

            for (int i = 0; i < 2; i++){
                Thread produtor = new Thread(new Productor6(pilhaDeItens));
                produtor.start();
            }

            for (int i = 0; i < 1; i++) {
                Thread comedor = new Thread(new Eater6(pilhaDeItens));
                comedor.start();
            }

        }
    }
}
