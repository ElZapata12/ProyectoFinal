import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
class CircuitoPanel extends JPanel {
    private HashMap<Integer, Combi> combis;

    private Color[] colores = {
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
            Color.ORANGE, Color.CYAN, Color.MAGENTA, Color.PINK,
            Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK, Color.WHITE
    };

    public CircuitoPanel(HashMap<Integer, Combi> combis) {
        this.combis = combis;
    }

    public void updateCombis(HashMap<Integer, Combi> combis) {
        this.combis = combis;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircuit(g);
        drawCombis(g);
    }

    private void drawCircuit(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(50, 50, 500, 500);
    }

    private void drawCombis(Graphics g) {
        // Dibuja las combis
        for (Combi combi : combis.values()) {
            int paradaIndex = combi.getParadaIndex();
            int x = 50 + (paradaIndex % 10) * 30; // Ejemplo de posición X
            int y = 50 + (paradaIndex / 10) * 30; // Ejemplo de posición Y

            // Asignar color
            Color color = colores[combi.getId() % colores.length];
            g.setColor(color);
            g.fillOval(x, y, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString("Combi " + combi.getId(), x, y - 5);
        }
    }
}