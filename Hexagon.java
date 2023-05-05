import java.awt.*;

public class Hexagon {
    private int x;
    private int y;
    private int radious = 80;

    // KONSTRUKTORY
    Hexagon(int x, int y){
        this.x = x;
        this.y = y;
    }
    Hexagon(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.radious = r;
    }

    //GETTERY
    public int getRadious() {
        return radious;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //SETTERY


    // METODY
    @Override // nadpisuje funkcję toString() aby wyświetlać współrzędne
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    //rysuje sześciokąt
    void draw(Graphics g, int size) {
        int r = this.radious; //promień koła opisanego na sześciokącie
        int h = (int)(r * Math.sqrt(3) / 2);
        int x[] = { //współrzędne x punktów względem środka
                -r/2,
                r/2,
                r,
                r/2,
                -r/2,
                -r};
        int y[] = { //współrzędne y punktów względem środka
                h,
                h,
                0,
                -h,
                -h,
                0};
        int numberOfApex = 6; //liczba wierzchołków

        //przesunięcie współrzędnych punktów -> przesunięcie względem środkowego sześciokąta + przesunięcie na środek Frame'a
        for (int i = 0; i < numberOfApex; i++) {
            x[i] += this.x*r*1.5 + size/2;
            y[i] += this.y*h + size/2 + 36; //36 - żeby nie ucinało z góry
        }

        g.setColor(Color.black);

        for (int i = 0; i < numberOfApex - 1; i++) {
            g.drawLine(x[i], y[i], x[i + 1], y[i + 1]);
        }
        g.drawLine(x[0], y[0], x[numberOfApex - 1], y[numberOfApex - 1]);
        //g.drawString(this.x + ", " + this.y, (int)(this.x*r*1.5) + size/2-10, this.y*h + size/2);
    }
}
