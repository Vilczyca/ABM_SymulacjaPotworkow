import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _SimulationPanel extends JPanel {
    Simulation simulation;
    _DrawingCanvas drawingCanvas;
    _SimulationPanel(_MainFrame mf){
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
