import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Potwór wodny, potrafiący ewoluować w formę zatapiającą pola
 */
public class LakeMonster extends Monster {

    LakeMonster(int x, int y, int r) {
        super(x, y, r);
    }

    /**
     * Boolean czy ewoulowany
     */
    boolean evolved = false;
    /**
     * czy zatopil juz pole
     */
    boolean sunk = false;

    /**
     * ewouluje od 50 expa
     */
    public void evolve() {
        if (this.EXP > 50) {
            evolved = true;
        }
    }

    /**
     * zlicza ze juz potwor zatopil jakies pole
     */
    public void sunk() {
        this.sunk = true;
    }


    @Override
    public String getType() {
        return "LakeMonster";
    }

    @Override
    public void draw(Graphics2D g) {
        int size = this.radious / 3 * 2;
        if (this.evolved) {
            g.setColor(new Color(0, 0, 169));
        } else {
            g.setColor(new Color(160, 32, 240));
        }
        g.fillOval((int) (this.x * this.radious * 1.5 + Main.SIZE_CANVAS / 2) - size / 2, this.y * (int) (this.radious * Math.sqrt(3) / 2) + Main.SIZE_CANVAS / 2 + 36 - size / 2, size, size);
    }

}
