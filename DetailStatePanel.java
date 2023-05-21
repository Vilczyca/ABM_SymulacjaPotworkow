import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DetailStatePanel extends JPanel {
    public DetailStatePanel(MainFrame mf) {
        setLayout(new GridLayout(5,1));

        setBackground(Color.gray);
        setPreferredSize(new Dimension(mf.WIDTH_CP,Main.SIZE_CANVAS));

        // tytuł
        JLabel title= new JLabel("SZCZEGÓŁY", JLabel.CENTER);
        title.setFont(new Font(title.getName(), Font.BOLD, 20));
        add(title);

        // Panel do danych pola
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(3,1));
        JLabel fieldTitle = new JLabel("Dane pola", JLabel.CENTER);
        JLabel fieldCoordinates = new JLabel("(x, y) = (-, -)" + this.getMousePosition(), JLabel.CENTER);
        mf.getMainPanel().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                fieldCoordinates.setText(e.getX() + "," + e.getY());
            }
        });
        JLabel fieldType = new JLabel("typ = -", JLabel.CENTER);
        fieldPanel.add(fieldTitle);
        fieldPanel.add(fieldCoordinates);
        fieldPanel.add(fieldType);
        add(fieldPanel);


        /*// guzik do zakończenia symulacji
        JButton endButton = new JButton("ZAKOŃCZ SYMULACJĘ");
        endButton.setPreferredSize(new Dimension(100,10));
        endButton.addActionListener(e -> {
            System.out.println("Kliknięto ZAKOŃCZ SYMULACJĘ!");
            mf.setVisible(false);
            mf.getControlFrame().setVisible(true);
        });
        add(endButton);*/
    }


}
