import javax.swing.*;
import java.awt.*;

public class DrawingCanvas extends JComponent {
    private int size;
    Board map;
    public DrawingCanvas(int size, Board b){
        this.map = b;
        this.size = size;
    }

    // płótno, miejsce gdzie jest rysowana plansza
    protected void paintComponent(Graphics g){
        map.draw(g, size);
    }
}
