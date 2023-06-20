import javax.swing.*;
import java.awt.*;

/**
 * Okno symulacji wraz z panelami pomocniczymi, zawiera najważniejsze informacje.
 * <ul>
 *     <li>panel szczegółów</li>
 *     <li>panel symulacji</li>
 *     <li>panel sterowania symulacją</li>
 * </ul>
 */
public class _MainFrame extends JFrame {
    /**
     * Odstęp między symulacją a panelami bocznymi
     */
    static int BORDER = 5;
    /**
     * Szerokość panelu symulacji
     */
    static int SIMULATION_WIDTH = Main.SIZE_CANVAS + 4 * BORDER;
    /**
     * Wysokość panelu symulacji
     */
    static int SIMULATION_HEIGHT = (int) (2 * Main.SIZE_CANVAS / Math.sqrt(3));
    /**
     * Szerokość paneli bocznych
     */
    static int WIDTH_CP = 200;
    private Simulation simulation;
    private _ControlFrame controlFrame;

    //panele
    private JPanel simulationPanel;
    //panel ustawień
    private JPanel simulationStatePanel;
    //panel szczegółów
    private _DetailStatePanel detailStatePanel;
    /**
     * Konstruktor
     * @param simulation symulacja, która będzie wyświetlana
     * @param cf okno panelu kontrolnego, na podstawie której generuje się symulację
     */
    _MainFrame(Simulation simulation, _ControlFrame cf) {
        this.simulation = simulation;
        this.controlFrame = cf;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ramka
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SIMULATION_WIDTH + 2 * WIDTH_CP, SIMULATION_HEIGHT);
        setLocation(400, 0);
        setLayout(new BorderLayout(BORDER, BORDER));
        setResizable(false);

        //panele
        this.simulationPanel = new _SimulationPanel(this);
        //panel ustawień
        this.simulationStatePanel = new _GeneralStatePanel(this);
        //panel szczegółów
        this.detailStatePanel = new _DetailStatePanel(this);

        add(simulationPanel, BorderLayout.CENTER);
        add(simulationStatePanel, BorderLayout.EAST);
        add(detailStatePanel, BorderLayout.WEST);
    }

    /**
     * Getter ControlFrame
     */
    public _ControlFrame getControlFrame() {
        return controlFrame;
    }

    /**
     * Getter Simulation
     */
    public Simulation getSimulation() {
        return simulation;
    }

    /**
     * Getter SimulationPanel
     */
    public JPanel getSimulationPanel() {
        return simulationPanel;
    }

}
