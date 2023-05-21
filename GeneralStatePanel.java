import java.awt.*;
import javax.swing.*;

public class GeneralStatePanel extends JPanel {
    public GeneralStatePanel(MainFrame mf) {
        setLayout(new GridLayout(5,1));

        setBackground(Color.gray);
        setPreferredSize(new Dimension(mf.WIDTH_CP, Main.SIZE_CANVAS));

        // tytuł
        JLabel title= new JLabel("STAN SYMULACJI", JLabel.CENTER);
        title.setFont(new Font(title.getName(), Font.BOLD, 20));
        add(title);

       // guzik do zakończenia symulacji
        JButton endButton = new JButton("ZAKOŃCZ SYMULACJĘ");
        endButton.setPreferredSize(new Dimension(100,10));
        endButton.addActionListener(e -> {
            System.out.println("Kliknięto ZAKOŃCZ SYMULACJĘ!");
            mf.setVisible(false);
            mf.getControlFrame().setVisible(true);
        });
        add(endButton);
    }


}
