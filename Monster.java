import java.awt.*;
import java.util.Random;
import javax.swing.*;
public class Monster {
    private int x;
    private int y;
    private int radious;
    Monster(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.radious = r;
    }
    int HP,ATK,DEF,SPEED,REACH,EXP; //statystyki

    // GETTERY

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //METODY

    public void START() {

    }

    public void move() {
        int min = 1;
        int max = 6;
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
        switch (random_int) {
            case 1 -> this.north();
            case 2 -> this.south();
            case 3 -> this.northeast();
            case 4 -> this.southeast();
            case 5 -> this.southwest();
            default -> this.northwest();
        }
    }

    public void north(){
        this.y += 2;
    }
    public void south() {
        this.y -= 2;
    }
    public void northeast() {
        this.x+=1;
        this.y+=1;
    }
    public void northwest() {
        this.x-=1;
        this.y-=1;
    }
    public void southeast() {
        this.x-=1;
        this.y+=1;
    }
    public void southwest() {
        this.x+=1;
        this.y-=1;
    }

    public void draw(Graphics g) {
        int size = this.radious/2;
        g.setColor(Color.red); //TODO ustalanie kolorku
        g.fillOval((int)(this.x*this.radious*1.5 + Main.SIZE_CANVAS/2) - size/2, this.y*(int)(this.radious * Math.sqrt(3) / 2) + Main.SIZE_CANVAS/2 + 36 - size/2, size, size);
    }


}