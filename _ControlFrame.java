import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Okno panelu kontrolnego, którym można sterować całą symulacją.
 * <ul>
 *     <li>suwak rozmiaru planszy</li>
 *     <li>suwaki ustalania krajobrazu</li>
 *     <li>suwak zagęszczenia populacji</li>
 *     <li>guzik do rozpoczęcia symulacji (który wyświetla błędy jeśli dane są nieprawidłowe)</li>
 * </ul>
 */
public class _ControlFrame extends JFrame {
    JFrame mainframe;
    Simulation simulation;

    public _ControlFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
        setSize(400, 800);

        // suwak rozmiaru planszy
        int sliderStartValue = 10;
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, sliderStartValue);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // wartość suwaka rozmiaru planszy
        JTextField sliderValueField = new JTextField(String.valueOf(sliderStartValue), JTextField.CENTER);
        sliderValueField.setHorizontalAlignment(JTextField.CENTER);
        sliderValueField.setMaximumSize(new Dimension(100, 10));
        slider.addChangeListener(e -> {
            int sliderValue = slider.getValue();
            sliderValueField.setText(String.valueOf(sliderValue));
        });
        sliderValueField.setEditable(false);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setPreferredSize(new Dimension(100, 10));
        sliderPanel.setLayout(new GridLayout(3, 1));
        sliderPanel.add(new JLabel("Rozmiar planszy:", JLabel.CENTER), BorderLayout.NORTH);
        sliderPanel.add(slider, BorderLayout.CENTER);
        sliderPanel.add(sliderValueField, BorderLayout.SOUTH);
        add(sliderPanel);

        // suwaki krajobrazu
        int fieldStartValue = 25;
        JPanel fieldPanel = new JPanel(new GridLayout(4, 1));
        fieldPanel.add(new JLabel("Krajobraz [%]:", JLabel.CENTER));
        ArrayList<JSlider> fieldSliders = new ArrayList<>();
        String[] types = {"Góry:    ", "Pustynia:", "Woda:    "};
        for (int i = 0; i < 3; i++) {
            JPanel fPanel = new JPanel(new FlowLayout());

            JSpinner spinner = new JSpinner();

            JSlider fSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, fieldStartValue);
            fSlider.setMajorTickSpacing(25);
            fSlider.setMinorTickSpacing(5);
            fSlider.setPaintTicks(true);
            fSlider.setPaintLabels(true);

            fSlider.addChangeListener(e -> {
                int fSliderValue = fSlider.getValue();
                fSlider.setValue(fSliderValue);
                spinner.setValue(fSliderValue);
            });

            spinner.setModel(new SpinnerNumberModel(fieldStartValue, 0, 100, 1));
            spinner.setEditor(new JSpinner.NumberEditor(spinner, "0'%'"));
            spinner.addChangeListener(e -> {
                int spinnerValue = (int) spinner.getValue();
                fSlider.setValue(spinnerValue);
            });

            fieldSliders.add(fSlider);
            fPanel.add(new JLabel(types[i]));
            fPanel.add(fSlider);
            fPanel.add(spinner);
            fieldPanel.add(fPanel);
        }
        add(fieldPanel);

        // suwak populacji
        JPanel populationPanel = new JPanel(new GridLayout(4, 1));
        JPanel populationPanel2 = new JPanel(new GridBagLayout());
        JSpinner populationSpinner = new JSpinner();
        JSlider populationSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, fieldStartValue);

        populationPanel.add(new JLabel("Potworki [%]:", JLabel.CENTER));
        populationPanel2.add(new JLabel("Populacja:", JLabel.CENTER));

        populationSlider.setMajorTickSpacing(25);
        populationSlider.setMinorTickSpacing(5);
        populationSlider.setPaintTicks(true);
        populationSlider.setPaintLabels(true);

        populationSlider.addChangeListener(e -> {
            int fSliderValue = populationSlider.getValue();
            populationSlider.setValue(fSliderValue);
            populationSpinner.setValue(fSliderValue);
        });

        populationSpinner.setModel(new SpinnerNumberModel(fieldStartValue, 0, 100, 1));
        populationSpinner.setEditor(new JSpinner.NumberEditor(populationSpinner, "0'%'"));
        populationSpinner.addChangeListener(e -> {
            int spinnerValue = (int) populationSpinner.getValue();
            populationSlider.setValue(spinnerValue);
        });
        populationPanel2.add(populationSlider);
        populationPanel2.add(populationSpinner);
        populationPanel.add(populationPanel2);
        add(populationPanel, new GridBagConstraints());


         // guzik do rozpoczęcia symulacji
        JButton button = new JButton("START");
        button.addActionListener(e -> {
            int[] values = {0, 0, 0};
            values[0] = fieldSliders.get(0).getValue();
            values[1] = fieldSliders.get(1).getValue();
            values[2] = fieldSliders.get(2).getValue();
            if (Arrays.stream(values).sum() > 100) {
                button.setText("Suma nie może być większa od 100%!");
            } else {
                System.out.println("Kliknięto START!");
                this.simulation = new Simulation(1000);
                this.simulation.createBoard(slider.getValue(), Main.SIZE_CANVAS, values);
                this.simulation.createMonsters(populationSlider.getValue());
                this.simulation.setmonster(populationSlider.getValue());
                this.simulation.createFood(100 - populationSlider.getValue());
                this.simulation.turn("run");
                this.mainframe = new _MainFrame(simulation, this);
                setVisible(false);
                this.mainframe.setVisible(true);
                for (Monster monster : this.simulation.getBoard().getMonsters()) {
                    switch (monster.getType()) {
                        case "LakeMonster":
                            this.simulation.setLake(1);
                            break;
                        case "MountainMonster":
                            this.simulation.setMountain(1);
                            break;
                        case "Monster":
                            this.simulation.setNormal(1);
                            break;
                        case "DesertMonster":
                            this.simulation.setDesert(1);
                            break;
                    }
                }
            }

        });

        add(button);
    }
}  