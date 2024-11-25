import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
public class Main {
    private Circuito circuito;

    public Main() {
        circuito = new Circuito();
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Configuración de la ventana gráfica
        JFrame frame = new JFrame("Gestión y Control de Transporte de Teziutlan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        CircuitoPanel circuitoPanel = new CircuitoPanel(circuito.getCombis());
        frame.add(circuitoPanel);
        frame.setVisible(true);

        do {
            System.out.println("Gestion y Control de Transporte de Teziutlan:");
            System.out.println("1. Agregar Combi");
            System.out.println("2. Simular Ronda");
            System.out.println("3. Mostrar Combis");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID de la combi: ");
                    int id = sc.nextInt();
                    circuito.agregarCombi(id);
                    circuitoPanel.updateCombis(circuito.getCombis()); // Actualiza el panel gráfico
                    break;
                case 2:
                    circuito.simularRonda();
                    circuitoPanel.updateCombis(circuito.getCombis()); // Actualiza el panel gráfico
                    break;
                case 3:
                    circuito.mostrarCombis();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);

        sc.close();
    }
}
