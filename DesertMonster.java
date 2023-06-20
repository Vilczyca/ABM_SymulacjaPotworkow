import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Potwór pustynny, potrafiący się rozmnażać
 */
public class DesertMonster extends Monster {

    /**
     * Konstruktor dziedziczący z klasy Monster
     * @param x współrzędna x
     * @param y espółrzędna y
     * @param r promień pola, na którym potworek się pojawi (pozwala ustalić jego wielkość podczas rysowania)
     */
    DesertMonster(int x, int y, int r) {
        super(x, y, r);
    }

    /**
     * Boolean czy potwór jest w ciąży
     */
    boolean pregnant = false;
    /**
     * Liczba zrodzonych potomków
     */
    int offspring = 0;

    /**
     * Rozmnazanie
     */
    public void breed() {
        if (this.EXP > 50) {
            offspring++;
            this.EXP = 20;
            this.setPregnant(true);
        }
    }

    /**
     * Getter czy sie rozmnozyl
     */
    public boolean getPregnant() {
        return pregnant;
    }

    /**
     * Setter czy sie rozmnozyl
     */
    public void setPregnant(boolean k) {
        pregnant = k;
    }

    /**
     * Getter typu
     */
    @Override
    public String getType() {
        return "DesertMonster";
    }

    /**
     * Rysuje potwora
     */
    @Override
    public void draw(Graphics2D g) {
        int size = this.radious / 3 * 2;

        g.setColor(new Color(225, 225, 20));
        g.fillOval((int) (this.x * this.radious * 1.5 + Main.SIZE_CANVAS / 2) - size / 2, this.y * (int) (this.radious * Math.sqrt(3) / 2) + Main.SIZE_CANVAS / 2 + 36 - size / 2, size, size);
    }
}
