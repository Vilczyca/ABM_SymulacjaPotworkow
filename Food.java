import java.awt.*;

public class Food { //TODO klasa Food
    private int x;
    private int y;
    private int radious;
    private String type;

    Food(int x, int y, int r, String t) {
        this.x = x;
        this.y = y;
        this.radious = r;
        this.type = t;
    }

    //GETTERY

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    // rysuje jedzenie
    public void draw(Graphics g) {
        int size = this.radious/3;
        switch (this.type) {
            case "basic" -> g.setColor(new Color(210, 100, 100));
            case "woda" -> g.setColor(new Color(93, 170, 183));
            case "las" -> g.setColor(new Color(173, 178, 90));
            case "gory" -> g.setColor(new Color(140, 148, 148));
        }
        g.fillOval((int)(this.x*this.radious*1.5 + Main.SIZE_CANVAS/2) - size/2, this.y*(int)(this.radious * Math.sqrt(3) / 2) + Main.SIZE_CANVAS/2 + 36 - size/2, size, size);
    }
}
