import java.awt.*;

/**
 * Jedzenie, które potwory mogą zjadać by zdobyć EXP
 */
public class Food {
    /**
     * Współrzędna x
     */
    private int x;
    /**
     * Współrzędna y
     */
    private int y;
    /**
     * Zdobywany poziom doświadczenia
     */
    private int exp = 10;
    /**
     * Promień, pozwala ustalić wielkość rysowanego jedzenia
     */
    private int radious;
    /**
     * Typ, decyduje gdzie jedzenie będzie generowane i w jakim kolorze
     */
    private String type;

    /**
     * Konstruktor
     * @param x współrzędna x
     * @param y współrzędna y
     * @param r promień
     * @param t typ
     */
    Food(int x, int y, int r, String t) {
        this.x = x;
        this.y = y;
        this.radious = r;
        this.type = t;
    }

    /**
     * Getter współrzędnej X
     */
    public int getX() {
        return x;
    }
    /**
     * Getter współrzędnej Y
     */
    public int getY() {
        return y;
    }
    /**
     * Getter typu
     */
    public String getType() {
        return type;
    }
    /**
     * Getter EXP
     */
    public int getExp() {
        return exp;
    }

    /**
     * Rysuje jedzenie
     * @param g grafika
     */
    public void draw(Graphics2D g) {
        int size = this.radious / 3;
        switch (this.type) {
            case "basic" -> g.setColor(new Color(210, 100, 100));
            case "woda" -> g.setColor(new Color(93, 170, 183));
            case "pustynia" -> g.setColor(new Color(173, 178, 90));
            case "gory" -> g.setColor(new Color(140, 148, 148));
        }
        g.fillOval((int) (this.x * this.radious * 1.5 + Main.SIZE_CANVAS / 2) - size / 2, this.y * (int) (this.radious * Math.sqrt(3) / 2) + Main.SIZE_CANVAS / 2 + 36 - size / 2, size, size);
    }
}
