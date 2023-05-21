import javax.swing.*;
import java.awt.*;
class Main {
    static int SIZE_CANVAS = 700;
    public static void main(String args[]) {

        Simulation simulation = new Simulation();

        ControlFrame cp = new ControlFrame(simulation);
        cp.setVisible(true);

        /*

        // ramka
        JFrame frame = new JFrame("Plansza");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SIZE_CANVAS,SIZE_CANVAS+120);

        // pasek menu
        JMenuBar mb = new JMenuBar();
        JMenu settingsM = new JMenu("Ustawienia");
        JMenu saveM = new JMenu("Zapisz...");
        JMenu pause = new JMenu("Pauza");
        mb.add(settingsM);
        mb.add(saveM);
        mb.add(Box.createHorizontalGlue());
        mb.add(pause);
        JMenuItem saveM_txt = new JMenuItem("...dane do pliku .txt");
        JMenuItem saveM_png = new JMenuItem("...wykres");
        saveM.add(saveM_txt);
        saveM.add(saveM_png);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        */


        // płótno
        /*DrawingCanvas m = new DrawingCanvas(SIZE_CANVAS, map);
        frame.add(m);

        frame.setVisible(true);

        ControlPanel frame1=new ControlPanel();
        frame1.pack();
        frame1.setVisible(true);*/
    }
}