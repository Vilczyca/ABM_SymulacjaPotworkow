import java.awt.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Panel boczny, pozwala na sterowanie symulacją.
 * <ul>
 *     <li>tytuł</li>
 *     <li>suwak prędkość symulacji</li>
 *     <li>guzik do zapauzowania/odpauzowania symulacji</li>
 *     <li>guzik do zakończenia symulacji</li>
 * </ul>
 */
public class _GeneralStatePanel extends JPanel {
    Simulation simulation;

    /**
     * Konstruktor
     * @param mf zawiera informacje o symulacji i sposobie jej wyświetlania
     */
    public _GeneralStatePanel(_MainFrame mf) {
        this.simulation = mf.getSimulation();
        setLayout(new GridLayout(5, 1));

        setBackground(Color.gray);
        setPreferredSize(new Dimension(mf.WIDTH_CP, Main.SIZE_CANVAS));

        // tytuł
        JLabel title = new JLabel("STAN SYMULACJI", JLabel.CENTER);
        title.setFont(new Font(title.getName(), Font.BOLD, 20));
        add(title);

        // suwak prędkość symulacji
        int speedSliderStartValue = 1000;
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 100, 1000, speedSliderStartValue);
        speedSlider.setMinorTickSpacing(100);
        speedSlider.setMajorTickSpacing(900);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setSnapToTicks(true);

        // wartość suwaka prędkości symulacji
        JTextField sliderValueField = new JTextField(String.valueOf(speedSliderStartValue), JTextField.CENTER);
        sliderValueField.setHorizontalAlignment(JTextField.CENTER);
        sliderValueField.setMaximumSize(new Dimension(100, 10));
        speedSlider.setEnabled(false);
        speedSlider.addChangeListener(e -> {
            int sliderValue = speedSlider.getValue();
            this.simulation.setSpeed(sliderValue);
            this.simulation.turn("pause");
            sliderValueField.setText(String.valueOf(sliderValue));
        });
        sliderValueField.setEditable(false);

        //panel do kontroli prędkości symulacji
        JPanel speedSliderPanel = new JPanel();
        speedSlider.setPreferredSize(new Dimension(100, 10));
        speedSliderPanel.setLayout(new GridLayout(3, 1));
        speedSliderPanel.add(new JLabel("Prędkość symulacji:", JLabel.CENTER), BorderLayout.NORTH);
        speedSliderPanel.add(speedSlider, BorderLayout.CENTER);
        speedSliderPanel.add(sliderValueField, BorderLayout.SOUTH);
        add(speedSliderPanel);

        // guzik do zapauzowania/odpauzowania symulacji
        JButton pauseButton = new JButton("PAUZA ⏸");
        pauseButton.setPreferredSize(new Dimension(100, 10));
        pauseButton.addActionListener(e -> {
            if (pauseButton.getText() == "PAUZA ⏸") {
                pauseButton.setText("PAUZA ⏵");
                System.out.println("Kliknięto PAUZA!");
                this.simulation.turn("pause");
                speedSlider.setEnabled(true);

            } else {
                pauseButton.setText("PAUZA ⏸");
                System.out.println("Kliknięto WZNÓW!");
                this.simulation.turn("run");
                speedSlider.setEnabled(false);

            }
        });
        add(pauseButton);

        // guzik do zakończenia symulacji
        JButton endButton = new JButton("ZAKOŃCZ SYMULACJĘ");
        endButton.setPreferredSize(new Dimension(100, 10));
        endButton.addActionListener(e -> {
            System.out.println("Kliknięto ZAKOŃCZ SYMULACJĘ!");

        // pętla zliczająca stan koncowy potworow
            for (Monster monster : this.simulation.getBoard().getMonsters()) {
                {
                    switch (monster.getType()) {
                        case "LakeMonster":
                            this.simulation.setFinishLake(1);
                            break;
                        case "MountainMonster":
                            this.simulation.setFinishMountain(1);
                            break;
                        case "Monster":
                            this.simulation.setFinishNormal(1);
                            break;
                        case "DesertMonster":
                            this.simulation.setFinishDesert(1);
                            break;
                    }
                }
            }
            String fileName = "simulation_result.txt";
            String line1 = "\n Symulacje zakonczono na generacji " + simulation.generations;
            String line2 = "Wartosci poczatkowe: \n Zaczynajace potwory normalne - " + simulation.getNormal() + " \n Wodne - " + simulation.getLake() + " \n Gorskie - " + simulation.getMountain() + " \n Pustynne - " + simulation.getDesert();
            String line3 = "Wartosci koncowe: \n Konczace potwory normalne - " + simulation.finishnormal + " \n Wodne - " + simulation.finishlake + " \n Gorskie - " + simulation.finishmountain + " \n Pustynne - " + simulation.finishdesert;
            try {
                FileWriter writer = new FileWriter(fileName, true);
                writer.write(line1 + "\n" + line2 + "\n" + line3);
                writer.close();
            } catch (IOException ex) {
                System.out.println("Error pliku");
            }

            this.simulation.turn("pause");
            mf.setVisible(false);
            mf.getControlFrame().setVisible(true);
        });
        add(endButton);
    }
}