import javax.swing.*;
import java.awt.*;
/**
 * Płótno, na którym rysowana jest plansza.
 */
public class _DrawingCanvas extends JComponent {

    private Simulation simulation;

    /**
     * Konstruktor
     * @param s przekazana symulacja
     */
    public _DrawingCanvas(Simulation s) {
        this.simulation = s;
    }
    /**
     * Rysuje planszę
     */
    protected void paintComponent(Graphics g) {
        this.simulation.getBoard().draw((Graphics2D) g);
    }
    /**
     * Aktualizuje płótno
     */
    protected void updateGraphics() {
        repaint();
    }
}
