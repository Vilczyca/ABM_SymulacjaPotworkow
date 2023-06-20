import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Panel boczny, pozwala na sprawdzenie szczegółów symulacji.
 * <ul>
 *     <li>tytuł</li>
 *     <li>tabelę z danymi pola, na które się kliknęło</li>
 * </ul>
 */
public class _DetailStatePanel extends JPanel {
    private Simulation simulation;
    private int countMonsters;
    private JLabel monstersLabel;

    /**
     * Konstruktor
     * @param mf zawiera informacje o symulacji i sposobie jej wyświetlania
     */
    public _DetailStatePanel(_MainFrame mf) {
        this.countMonsters = mf.getSimulation().getBoard().getMonsters().size();
        this.monstersLabel = new JLabel("Ile potworów: " + this.countMonsters);
        this.simulation = mf.getSimulation();

        setLayout(new GridLayout(5, 1));
        setBackground(Color.gray);
        setPreferredSize(new Dimension(mf.WIDTH_CP, Main.SIZE_CANVAS));

        // tytuł
        JLabel title = new JLabel("SZCZEGÓŁY", JLabel.CENTER);
        title.setFont(new Font(title.getName(), Font.BOLD, 20));
        add(title);

        // ile potworków, generacji
        JPanel generalPanel = new JPanel();
        JLabel monstersLabel = new JLabel("Ile potworów: " + this.countMonsters);
        generalPanel.add(monstersLabel);
        add(generalPanel);

        // Panel do danych pola
        String data[][] = {{"Typ:", "-"},
                {"Jedzenie:", "-"},
                {"Potworek:", "-"},};
        String columnNames[] = {"Współrzędne:", "(-, -)"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setCellSelectionEnabled(false);

        mf.getSimulationPanel().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                for (Hexagon hex : mf.getSimulation().getBoard().getMap()) {
                    if (hex.containsPoint(e.getX(), e.getY())) {
                        System.out.println("Zaznaczono pole o współrzędnych (" + hex.getX() + "," + hex.getY() + ")");
                        model.setColumnIdentifiers(new String[]{"Współrzędne:", "(" + hex.getX() + "," + hex.getY() + ")"});
                        model.setValueAt(hex.getType(), 0, 1);
                        model.setValueAt(hex.getFood(), 1, 1);
                        mf.getSimulation().getBoard().getMonsters().stream()
                                .parallel()
                                .filter(monster -> hex.getX() == monster.getX() && hex.getY() == monster.getY())
                                .forEach(monster -> model.setValueAt(monster.getEXP(), 2, 1));
                        break;
                    } else {
                        model.setDataVector(data, columnNames);
                    }
                }
            }
        });

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(2, 1));
        JLabel fieldTitle = new JLabel("Dane pola", JLabel.CENTER);
        fieldPanel.add(fieldTitle);
        fieldPanel.add(new JScrollPane(table));
        add(fieldPanel);
    }
}
