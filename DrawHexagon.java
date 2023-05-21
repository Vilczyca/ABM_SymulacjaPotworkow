/*
import java.awt.*;

public class DrawHexagon extends Canvas {
    Hexagon hex;
    DrawHexagon(Hexagon hex){
        this.hex = hex;
    }

    public void paint(Graphics g) {
        int r = hex.getRadious(); //promień koła opisanego na sześciokącie
        int x[] = { //współrzędne x punktów
                -r/2,
                r/2,
                r,
                r/2,
                -r/2,
                -r};
        int y[] = { //współrzędne y punktów
                (int) (r * Math.sqrt(3) / 2d),
                (int) (r * Math.sqrt(3) / 2d),
                0,
                (int) (-r * Math.sqrt(3) / 2d),
                (int) (-r * Math.sqrt(3) / 2d),
                0};
        int numberOfApex = 6; //liczba wierzchołków

        //przesunięcie
        */
/*for (int i = 0; i < numberOfApex; i++) {
            x[i] += 200;
            y[i] += 200;
            System.out.println(x[i] + " " + y[i]);
        }*//*


        g.setColor(Color.black);

        for (int i = 0; i < numberOfApex - 1; i++) {
            g.drawLine(x[i], y[i], x[i + 1], y[i + 1]);
        }
        g.drawLine(x[0], y[0], x[numberOfApex - 1], y[numberOfApex - 1]);
        g.drawString(hex.getX() + ", " + hex.getY(), hex.getX()+175, hex.getY()+190);
    }
}
*/
