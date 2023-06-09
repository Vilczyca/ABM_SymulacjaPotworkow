import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class _DetailStatePanel extends JPanel {
    public _DetailStatePanel(_MainFrame mf) {
        setLayout(new GridLayout(5,1));

        setBackground(Color.gray);
        setPreferredSize(new Dimension(mf.WIDTH_CP,Main.SIZE_CANVAS));

        // tytuł
        JLabel title= new JLabel("SZCZEGÓŁY", JLabel.CENTER);
        title.setFont(new Font(title.getName(), Font.BOLD, 20));
        add(title);

        // Panel do danych pola
        String data[][]={ {"Typ:","-"},
                {"Jedzenie:","-"},
                {"Potworek:","-"},};
        String columnNames[]={"Współrzędne:","(-, -)"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setCellSelectionEnabled(false);

        mf.getMainPanel().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                for(Hexagon hex: mf.getSimulation().getBoard().getMap()){
                    if(hex.containsPoint(e.getX(), e.getY())){
                        System.out.println("Zaznaczono pole o współrzędnych (" + hex.getX() + "," + hex.getY() + ")");
                        model.setColumnIdentifiers(new String[]{"Współrzędne:", "(" + hex.getX() + "," + hex.getY() + ")"});
                        model.setValueAt(hex.getType(), 0, 1);
                        mf.getSimulation().getBoard().getFood().stream()
                                .parallel()
                                .filter(food -> hex.getX() == food.getX() && hex.getY() == food.getY())
                                .forEach(food -> model.setValueAt(food.getType(), 1, 1));
                        mf.getSimulation().getBoard().getMonsters().stream()
                                .parallel()
                                .filter(monster -> hex.getX() == monster.getX() && hex.getY() == monster.getY())
                                .forEach(monster -> model.setValueAt("+", 2, 1));
                        break;
                    } else {
                        model.setDataVector(data, columnNames);
                    }
                }
            }
        });

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(2,1));
        JLabel fieldTitle = new JLabel("Dane pola", JLabel.CENTER);
        fieldPanel.add(fieldTitle);
        fieldPanel.add(new JScrollPane(table));
        add(fieldPanel);
    }
}
