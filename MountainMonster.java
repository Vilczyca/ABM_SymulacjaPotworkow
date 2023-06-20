import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Potwór górski, który zdobywa więcej doświadczenia
 */
public class MountainMonster extends Monster {

    MountainMonster(int x, int y, int r) {
        super(x, y, r);
    }

    /**
     * Mnoznik EXP
     */
    @Override
    public void gainExp(int bonus) {
        this.EXP = this.EXP + 2 * bonus;
    }
    /**
     * Zwraca typ
     */
    @Override
    public String getType() {
        return "MountainMonster";
    }

    /**
     * Rysuje potworka
     */
    @Override
    public void draw(Graphics2D g) {
        int size = this.radious / 3 * 2;

        g.setColor(new Color(224, 255, 255));
        g.fillOval((int) (this.x * this.radious * 1.5 + Main.SIZE_CANVAS / 2) - size / 2, this.y * (int) (this.radious * Math.sqrt(3) / 2) + Main.SIZE_CANVAS / 2 + 36 - size / 2, size, size);
    }
}
