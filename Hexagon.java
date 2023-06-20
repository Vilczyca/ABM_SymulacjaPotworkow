import java.awt.*;
import java.util.stream.Collectors;
import javax.print.attribute.standard.RequestingUserName;

/**
 * Sześciokąt, pole na którym poruszają się potwory
 */
public class Hexagon {
    /**
     * Wpółrzędna X
     */
    private int x;
    /**
     * Wpółrzędna Y
     */
    private int y;
    /**
     * Promień, na któym rozpiera się sześciokąt
     */
    private int radious = 0;
    /**
     * Wysokość ustalana na podstawie promienia
     */
    private int height = 0;
    /**
     * Boolean czy cokolwiek znajduje się na polu
     */
    boolean empty = true;
    /**
     * Boolean czy znajduje się na nim potwór
     */
    boolean takenbymonster = false;
    /**
     * Boolean czy znajduje się na nim jedzenie
     */
    boolean hasfood = false;
    /**
     * Typ pola
     */
    private String type = "ERROR";
    /**
     * Wpółrzędne x wierzchołków
     */
    private int x_co[] = new int[6];
    /**
     * Wpółrzędne y wierzchołków
     */
    private int y_co[] = new int[6];


    /**
     * Konstruktor
     * @param x współrzędna x
     * @param y współrzędna y
     * @param r promień
     * @param t typ
     */

    Hexagon(int x, int y, int r, String t) {
        this.x = x;
        this.y = y;
        this.radious = r;
        this.type = t;
        this.height = (int) (r * Math.sqrt(3) / 2);

        setCoX();
        setCoY();
    }

    //GETTERY

    /**
     * Getter promienia
     */
    public int getRadious() {
        return radious;
    }

    /**
     * Getter współrzędnej x
     */
    public int getX() {
        return x;
    }
    /**
     * Getter współrzędnej y
     */
    public int getY() {
        return y;
    }
    /**
     * Getter czy pole jest puste czy pełne
     */
    public boolean getempty() {
        return empty;
    }
    /**
     * Getter czy pole jest zajęte przez potwora
     */
    public boolean getTaken() {
        return takenbymonster;
    }
    /**
     * Getter czy na polu znajduje się jedzenie
     */
    public boolean getFood() {
        return hasfood;
    }
    /**
     * Getter typu pola
     */
    public String getType() {
        return type;
    }

    //SETTERY
    /**
     * Setter typu pola
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Setter czy pole jest puste czy pełne
     */
    public void setEmpty(boolean x) {
        empty = x;
    }
    /**
     * Setter czy na polu znajduje się jedzenie
     */
    public void setFood(boolean x) {
        hasfood = x;
    }
    /**
     * Setter czy pole jest zajęte przez potwora
     */
    public void setTaken(boolean x) {
        takenbymonster = x;
    }

    // METODY


    /**
     * Nadpisuje funkcję toString() aby wyświetlać współrzędne
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * przesunięcie współrzędnych punktów -> przesunięcie względem środkowego sześciokąta + przesunięcie na środek Frame'a
     */
    public int moveCoX() {
        return (int) (this.x * this.radious * 1.5 + Main.SIZE_CANVAS / 2);
    }

    /**
     * przesunięcie współrzędnych punktów -> przesunięcie względem środkowego sześciokąta + przesunięcie na środek Frame'a
     */
    public int moveCoY() {
        return (int) (this.y * this.height + Main.SIZE_CANVAS / 2 + 36); //36 - żeby nie ucinało z góry
    }

    /**
     * ustawia współrzędne x
     */
    private void setCoX() {
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

    /**
     * ustawia współrzędne y
     */
    private void setCoY() {
        //względem środka
        y_co[0] = this.height;
        y_co[1] = this.height;
        y_co[2] = 0;
        y_co[3] = -this.height;
        y_co[4] = -this.height;
        y_co[5] = 0;
        //przesunięcie
        for (int i = 0; i < 6; i++) {
            y_co[i] += moveCoY();
        }
    }

    /**
     * równanie linii
     */
    private float line(int x, int y, int x1, int y1, int x2, int y2) {
        float a = (y1 - y2) / (float) (x1 - x2);
        float b = y1 - a * x1;  //y = ax+b => b = y-ax
        return a * x + b - y;
    }
    /**
     * sprawdza czy hexagon ma w sobie dany punkt
     */
    public boolean containsPoint(int x, int y) {
        for (int i = 0; i < 6; i++) {
            float l1 = line(x, y, x_co[i % 6], y_co[i % 6], x_co[(i + 1) % 6], y_co[(i + 1) % 6]);
            float l2 = line(moveCoX() - 10, moveCoY() - 10, x_co[i % 6], y_co[i % 6], x_co[(i + 1) % 6], y_co[(i + 1) % 6]);
            if (l1 * l2 < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * rysuje sześciokąt, kolor zależy od typu pola
     * @param g grafika
     */
    public void draw(Graphics2D g) {
        //ustalenie koloru na podstawie typu pola
        switch (this.type) {
            case "basic" -> g.setColor(new Color(153, 243, 123));
            case "woda" -> g.setColor(new Color(128, 240, 255));
            case "pustynia" -> g.setColor(new Color(249, 255, 128));
            case "gory" -> g.setColor(new Color(194, 201, 200));
            case "zatopiony" -> g.setColor(new Color(0, 0, 139));
        }
        g.setStroke(new BasicStroke(2));
        g.drawPolyline(x_co, y_co, 6);
        g.fillPolygon(x_co, y_co, 6);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        g.setStroke(new BasicStroke(0));

        //g.drawString(this.x + ", " + this.y, moveCoX()-10, moveCoY());
    }

    //rysuje czerwoną obwódkę
    public void drawContour(Graphics2D g) {
        g.setColor(Color.red);
        g.drawPolyline(x_co, y_co, 6);
    }

}
