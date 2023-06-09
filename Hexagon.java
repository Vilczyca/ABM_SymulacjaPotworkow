import java.awt.*;

public class Hexagon {
    private int x;
    private int y;
    private int radious = 0;
    private int height = 0;
    private String type = "ERROR";
    private int x_co[] = new int[6];
    private int y_co[] = new int[6];


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

        setCoX();
        setCoY();
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

    public String getType() {
        return type;
    }

    public int[] getX_co() {
        return x_co;
    }

    public int[] getY_co() {
        return y_co;
    }


    //SETTERY

    public void setType(String type) {
        this.type = type;
    }


    // METODY

    // nadpisuje funkcję toString() aby wyświetlać współrzędne
    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    //przesunięcie współrzędnych punktów -> przesunięcie względem środkowego sześciokąta + przesunięcie na środek Frame'a
    public int moveCoX(){
        return (int)(this.x*this.radious*1.5 + Main.SIZE_CANVAS/2);
    }

    //przesunięcie współrzędnych punktów -> przesunięcie względem środkowego sześciokąta + przesunięcie na środek Frame'a
    public int moveCoY(){
        return (int)(this.y*this.height + Main.SIZE_CANVAS/2 + 36); //36 - żeby nie ucinało z góry
    }

    //ustawia współrzędne x
    private void setCoX(){
        //względem środka
        x_co[0] = -this.radious / 2;
        x_co[1] = this.radious / 2;
        x_co[2] = this.radious;
        x_co[3] = this.radious / 2;
        x_co[4] = -this.radious / 2;
        x_co[5] = -this.radious;
        //przesunięcie
        for (int i = 0; i < 6; i++) {
            x_co[i] += moveCoX();
        }
    }

    //ustawia współrzędne y
    private void setCoY(){
        //względem środka
        y_co[0] = this.height;
        y_co[1] = this.height;
        y_co[2] = 0;
        y_co[3] = -this.height;
        y_co[4] = -this.height;
        y_co[5] = 0;
        //przesunięcie
        for (int i = 0; i < 6; i++) {
            y_co[i] +=  moveCoY();
        }
    }

    //równanie linii
    private float line(int x, int y,int x1, int y1, int x2, int y2) {
        float a = (y1-y2)/(float)(x1 - x2);
        float b = y1 - a*x1;  //y = ax+b => b = y-ax
        return a*x + b - y;
    }

    // sprawdza czy hexagon ma w sobie dany punkt
    public boolean containsPoint(int x, int y) {
        for (int i = 0; i < 6; i++){
            float l1 = line(x, y, x_co[i % 6], y_co[i % 6], x_co[(i+1) % 6], y_co[(i+1) % 6]);
            float l2 = line(moveCoX()-10, moveCoY()-10, x_co[i % 6], y_co[i % 6], x_co[(i+1) % 6], y_co[(i+1) % 6]);
            if(l1*l2 < 0){
                return false;
            }
        }
        return true;
    }

    //rysuje sześciokąt
    public void draw(Graphics g) {
        //ustalenie koloru na podstawie typu pola
        switch (this.type) {
            case "basic" -> g.setColor(new Color(153, 243, 123));
            case "woda" -> g.setColor(new Color(128, 240, 255));
            case "las" -> g.setColor(new Color(249, 255, 128));
            case "gory" -> g.setColor(new Color(194, 201, 200));
        }
        g.fillPolygon(x_co, y_co, 6);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        g.drawString(this.x + ", " + this.y, moveCoX()-10, moveCoY());
    }

    //rysuje czerwoną obwódkę
    public void drawContour(Graphics g) {
        g.setColor(Color.red);
        g.drawPolyline(x_co, y_co, 6);
    }
}
