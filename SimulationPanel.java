import javax.swing.*;
import javax.swing.plaf.multi.MultiSliderUI;
import java.awt.*;

public class SimulationPanel extends JPanel {
    SimulationPanel(MainFrame mf){
        setBackground(Color.blue);
        setPreferredSize(new Dimension(MainFrame.SIMULATION_WIDTH,MainFrame.SIMULATION_HEIGHT));
        setLayout(new BorderLayout());

        DrawingCanvas m = new DrawingCanvas(Main.SIZE_CANVAS, mf.getSimulation().map);
        add(m);

    }
}
