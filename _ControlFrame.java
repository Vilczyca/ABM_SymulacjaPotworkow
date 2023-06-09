import javax.swing.*;
import java.awt.*;

public class _ControlFrame extends JFrame{
    JFrame mainframe;
    Simulation simulation;
    public _ControlFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));
        setSize(400, 800);

        // suwak
        int sliderStartValue = 10;
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, sliderStartValue);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // wartość suwaka
        JTextField sliderValueField = new JTextField(String.valueOf(sliderStartValue), JTextField.CENTER);
        sliderValueField.setHorizontalAlignment(JTextField.CENTER);
        sliderValueField.setMaximumSize(new Dimension(100,10));
        slider.addChangeListener(e -> {
            int sliderValue = slider.getValue();
            sliderValueField.setText(String.valueOf(sliderValue));
        });
        sliderValueField.setEditable(false);

        // guzik do rozpoczęcia symulacji
        JButton button = new JButton("START");
        button.addActionListener(e -> {
            System.out.println("Kliknięto START!");
            this.simulation = new Simulation(1000);
            this.simulation.createBoard(slider.getValue(), Main.SIZE_CANVAS);
            this.simulation.createMonsters(10); //TODO ustalanie ile potworków
            this.simulation.createFood(this.simulation.getBoard().getMap().size()/4); //TODO ustalanie ile jedzenia
            this.simulation.turn("run");
            this.mainframe = new _MainFrame(simulation, this);
            setVisible(false);
            this.mainframe.setVisible(true);
        });

        JPanel sliderPanel = new JPanel();
        slider.setPreferredSize(new Dimension(100,10));
        sliderPanel.setLayout(new GridLayout(3,1));
        sliderPanel.add(new JLabel("Rozmiar planszy:", JLabel.CENTER), BorderLayout.NORTH);
        sliderPanel.add(slider, BorderLayout.CENTER);
        sliderPanel.add(sliderValueField, BorderLayout.SOUTH);
        add(sliderPanel);
        add(button);

        /*JPanel p = new JPanel(new GridLayout(1,4));
        JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
        JSlider slider3 = new JSlider(JSlider.HORIZONTAL, 20, 40, 20);
        JSlider slider4 = new JSlider(JSlider.HORIZONTAL, 40, 60, 40);
        JSlider slider5 = new JSlider(JSlider.HORIZONTAL, 60, 80, 60);
        p.add(slider2);
        p.add(slider3);
        p.add(slider4);
        p.add(slider5);
        add(p);

        slider2.addChangeListener(e -> {
            slider.setMaximum(slider2.getValue());
        });*/
    }
}  