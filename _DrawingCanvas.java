import javax.swing.*;
import java.awt.*;

public class _DrawingCanvas extends JComponent {

    private Simulation simulation;
    public _DrawingCanvas(Simulation s){
        this.simulation = s;
    }

    // płótno, miejsce gdzie jest rysowana plansza
    protected void paintComponent(Graphics g){
        this.simulation.getBoard().draw(g);
    }

    protected void updateGraphics(){
        repaint();
    }
}
