import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

class Combi {
    private int id;
    private int paradaIndex;

    public Combi(int id) {
        this.id = id;
        this.paradaIndex = 0; // Inicialmente en la parada 0
    }

    public int getId() {
        return id;
    }

    public int getParadaIndex() {
        return paradaIndex;
    }

    public void setParadaIndex(int paradaIndex) {
        this.paradaIndex = paradaIndex;
    }

    @Override
    public String toString() {
        return "Combi " + id + " está en: " + PARADAS[paradaIndex];
    }

    public static final String[] PARADAS = {
            "Base Teziutlán",
            "La palma",
            "Plan de Edén",
            "Bodega ahorrará",
            "Ahuateno",
            "San diego",
            "IES",
            "La estación",
            "Las tres cruces",
            "La misma",
            "Amila",
            "Base de Tecnológico"
    };
}

class Circuito {
    private HashMap<Integer, Combi> combis; // Mapa de combis
    private Queue<Integer> cola; // Cola para el procesamiento de combis
    private Random random;

    public Circuito() {
        this.combis = new HashMap<>();
        this.cola = new LinkedList<>();
        this.random = new Random();
    }

    public void agregarCombi(int id) {
        Combi combi = new Combi(id);
        combis.put(id, combi); // Agregar la combi al HashMap
        cola.offer(id); // Agregar la ID de la combi a la cola
    }

    public void simularRonda() {
        int size = cola.size();
        for (int i = 0; i < size; i++) {
            Integer idCombi = cola.poll();
            Combi combi = combis.get(idCombi);

            if (combi != null) {
                int valorAleatorio = random.nextInt(3) + 1;
                int nuevaParada = combi.getParadaIndex() + valorAleatorio;

                // Si la combi llega a la última parada, se elimina del HashMap y de la cola
                if (nuevaParada < Combi.PARADAS.length) {
                    combi.setParadaIndex(nuevaParada);
                    cola.offer(idCombi);
                } else {
                    System.out.println("Combi " + combi.getId() + " ha llegado a su destino final y ha sido desencolada.");
                    combis.remove(idCombi);
                }
            }
        }
    }

    public void mostrarCombis() {
        for (Combi combi : combis.values()) {
            System.out.println(combi);
        }
    }

    public HashMap<Integer, Combi> getCombis() {
        return combis;
    }
}