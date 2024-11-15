import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;

public class Unidades {
    Random random = new Random();
    private int[] elementos;
    private HashMap<Integer, Integer> combis;
    private int frente;
    private int fin;
    private int tamanio;
    private int capacidad;
     private int totalRonda = 0;

    public Unidades(int capacidad) {
        elementos = new int[capacidad];
        frente = 0;
        fin = -1;
        tamanio = 0;
        this.capacidad = capacidad;
        combis = new HashMap<>();
    }

    public boolean estaLlena() {
        return tamanio == capacidad;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public void encolar(int elemento) {
        if (estaLlena()) {
            System.out.println("La cola está llena");
            return;
        }
        fin = (fin + 1) % elementos.length;
        elementos[fin] = elemento;
        combis.put(elemento, 0);
        tamanio++;
    }
    public int desencolar() {
        if (estaVacia()) {
            System.out.println("Aún no hay combis trabajando");
            return -1;
        }
        int elemento = elementos[frente];
        frente = (frente + 1) % elementos.length;
        tamanio--;
        return elemento;
    }

    public void simulacion() {
        int numRondas = 5;
        for (int ronda = 1; ronda <= numRondas; ronda++) {
            System.out.println("Ronda " + ronda + ":");

            for (int i = 0; i < tamanio; i++) {
                int index = (frente + i) % elementos.length;
                int valorAleatorio = random.nextInt(10) + 1;
                totalRonda += valorAleatorio;
                int combiId = elementos[index];
                combis.put(combiId, combis.get(combiId) + valorAleatorio);
                System.out.println("Combi " + combiId + ", Valor Aleatorio = " + valorAleatorio);
            }
            System.out.println("Total acumulado de la ronda " + ronda + ": " + totalRonda);
            System.out.println("Total acumulado por combi:");
            for (Integer combi : combis.keySet()) {
                System.out.println("Combi " + combi + ": " + combis.get(combi));
            }


            System.out.print("Presione Enter para continuar a la siguiente ronda...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        System.out.println("Simulación finalizada.");
    }
    public void mostrarCola() {
        if (estaVacia()) {
            System.out.println("Aún no hay combis trabajando.");
            return;
        }
        System.out.print("Combis Trabajando (" + tamanio + "/" + capacidad + "): ");
        for (int i = 0; i < tamanio; i++) {
            int index = (frente + i) % elementos.length;
            System.out.print(elementos[index] + " ");
        }
        System.out.println();
    }

    public static void gestionarCola() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Combis dadas de alta: ");
        int capacidad = sc.nextInt();
        Unidades unidades = new Unidades(capacidad);

        int opcion;
        do {
            System.out.println("\nMenú Cola de Boletos:");
            System.out.println("1. Empezar recorrido de combi");
            System.out.println("2. Termino de recorrido de combi");
            System.out.println("3. Mostrar Combis en recorrido");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    if (unidades.estaLlena()) {
                        System.out.println("Ya están todas las combis en el recorrido, empezando simulación...");
                        unidades.simulacion();
                    } else {
                        System.out.print("No. de Combi: ");
                        int cliente = sc.nextInt();
                        unidades.encolar(cliente);
                        System.out.println("Combi " + cliente + " en recorrido.");
                    }
                    break;
                case 2:
                    int desencolado = unidades.desencolar();
                    if (desencolado != -1) {
                        System.out.println("La combi " + desencolado + " ha llegado a la base.");
                    }
                    break;
                case 3:
                    unidades.mostrarCola();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    public static void main(String[] args) {
        gestionarCola();
    }
}