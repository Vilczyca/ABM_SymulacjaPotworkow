import java.awt.*;

public class Hexagon {
    private int x;
    private int y;
    private int radious = 0;
    private int height = 0;
    private String type = "ERROR";


    // KONSTRUKTORY
    Hexagon(int x, int y){
        this.x = x;
        this.y = y;
    }
    Hexagon(int x, int y, int r, String t){
        this.x = x;
        this.y = y;
        this.radious = r;
        this.type = t;

        this.height = (int)(r * Math.sqrt(3) / 2);
        int h = this.height;
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

    public void setType(String type) {
        this.type = type;
    }

    // METODY
    @Override // nadpisuje funkcję toString() aby wyświetlać współrzędne
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    //
    int[] setCoX(int size){
        int x_co[] = new int[]{ //współrzędne x punktów względem środka
                -this.radious / 2,
                this.radious / 2,
                this.radious,
                this.radious / 2,
                -this.radious / 2,
                -this.radious};
        //przesunięcie współrzędnych punktów -> przesunięcie względem środkowego sześciokąta + przesunięcie na środek Frame'a
        for (int i = 0; i < 6; i++) {
            x_co[i] += this.x*this.radious*1.5 + size/2;
        }
        return x_co;
    }
    int[] setCoY(int size){
        int y_co[] = new int[]{ //współrzędne y punktów względem środka
            this.height,
            this.height,
            0,
            -this.height,
            -this.height,
            0};
        //przesunięcie współrzędnych punktów -> przesunięcie względem środkowego sześciokąta + przesunięcie na środek Frame'a
        for (int i = 0; i < 6; i++) {
            y_co[i] += this.y*this.height + size/2 + 36; //36 - żeby nie ucinało z góry
        }
        return y_co;
    }

    //rysuje sześciokąt
    void draw(Graphics g, int size) {

        //ustalenie koloru na podstawie typu pola
        switch (this.type){
            case "basic":
                g.setColor(new Color(153, 243, 123));
                break;
            case "woda":
                g.setColor(new Color(128, 240, 255));
                break;
            case "las":
                g.setColor(new Color(249, 255, 128));
                break;
            case "gory":
                g.setColor(new Color(194, 201, 200));
        }
        g.fillPolygon(setCoX(size), setCoY(size), 6);
    }

    Polygon getPolygon(Graphics g, int size){
        return new Polygon(setCoX(size), setCoY(size), 6);
    }

    void drawContour(Graphics g, int size){
        g.setColor(new Color(255, 0, 0));
        g.drawPolygon(setCoX(size), setCoY(size), 6);
    }


}
