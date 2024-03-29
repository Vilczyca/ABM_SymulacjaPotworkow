import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Panel symulacji, na którym wyświetlana jest symulacja
 */
public class _SimulationPanel extends JPanel {
    /**
     * Symulacja, która jest wyświetlana
     */
    Simulation simulation;
    /**
     * Płótno, na którym jest rysowana symulacja
     */
    _DrawingCanvas drawingCanvas;
    /**
     * Konstruktor
     * @param mf zawiera informacje o symulacji i sposobie jej wyświetlania
     */
    _SimulationPanel(_MainFrame mf) {
        this.simulation = mf.getSimulation();
        this.drawingCanvas = new _DrawingCanvas(this.simulation);

        setBackground(Color.blue);
        setPreferredSize(new Dimension(_MainFrame.SIMULATION_WIDTH, _MainFrame.SIMULATION_HEIGHT));
        setLayout(new BorderLayout());

        add(drawingCanvas);

        Runnable periodicFunction = () -> {
            drawingCanvas.updateGraphics();
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(periodicFunction, 0, 100, TimeUnit.MILLISECONDS);
    }
}
