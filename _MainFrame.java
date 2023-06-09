import javax.swing.*;
import java.awt.*;

public class _MainFrame extends JFrame {
    static int BORDER = 5;
    static int SIMULATION_WIDTH = Main.SIZE_CANVAS+4*BORDER;
    static int SIMULATION_HEIGHT = (int)(2*Main.SIZE_CANVAS/Math.sqrt(3));
    static int WIDTH_CP = 200;
    private Simulation simulation;
    private _ControlFrame controlFrame;

    //panele
    private JPanel mainPanel;
    //panel ustawień
    private JPanel simulationStatePanel;
    //panel szczegółów
    private JPanel detailStatePanel;

    _MainFrame(Simulation simulation, _ControlFrame cf){
        this.simulation = simulation;
        this.controlFrame = cf;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ramka
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SIMULATION_WIDTH + 2*WIDTH_CP, SIMULATION_HEIGHT);
        setLocation(400,0);
        setLayout(new BorderLayout(BORDER,BORDER));
        setResizable(false);

        //panele
        this.mainPanel = new _SimulationPanel(this);
        //panel ustawień
        this.simulationStatePanel = new _GeneralStatePanel(this, this.simulation);
        //panel szczegółów
        this.detailStatePanel = new _DetailStatePanel(this);

        add(mainPanel,BorderLayout.CENTER);
        add(simulationStatePanel,BorderLayout.EAST);
        add(detailStatePanel,BorderLayout.WEST);
    }

    public _ControlFrame getControlFrame() {
        return controlFrame;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
