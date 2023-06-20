import java.awt.*;
/**
 * Potwór podstawowy, służący za bazę dla potworków specjalistycznych
 */
public class Monster {
    /**
     * Współrzędna x
     */
    int x;
    /**
     * Współrzędna y
     */
    int y;
    /**
     * Promień, na jego podstawie jest ustalany rozmiar potwora
     */
    int radious;

    /**
     * Konstruktor
     * @param x współrzędna x
     * @param y współrzędna y
     * @param r promień
     */
    Monster(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.radious = r;
    }

    /**
     * EXP, jedyna statystyka
     */
    int EXP = 1;

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
     * zdobywa EXP
     * @param n ilość punktów EXP
     */
    public void gainExp(int n) {
        this.EXP += n;
    }
    /**
     * Getter EXP
     */
    public int getEXP() {
        return EXP;
    }
    /**
     * Getter typu, dla podstawowych "Monster"
     */
    public String getType() {
        return "Monster";
    }

    /**
     * Porusza potworka, zmieniając jego współrzędne
     */

    public void move(int option) {
        if (option == 1) {
            this.north();
        } else if (option == 2) {
            this.south();
        } else if (option == 3) {
            this.northeast();
        } else if (option == 4) {
            this.southeast();
        } else if (option == 5) {
            this.southwest();
        } else {
            this.northwest();
        }
    }

    public void north() {
        this.y += 2;
    }

    public void south() {
        this.y -= 2;
    }

    public void northeast() {
        this.x += 1;
        this.y += 1;
    }

    public void northwest() {
        this.x -= 1;
        this.y -= 1;
    }

    public void southeast() {
        this.x -= 1;
        this.y += 1;
    }

    public void southwest() {
        this.x += 1;
        this.y -= 1;
    }

    public int returnX(int option) {
        if (option == 1) {
            return this.returnnorthx();
        } else if (option == 2) {
            return this.returnsouthx();
        } else if (option == 3) {
            return this.returnnortheastx();
        } else if (option == 4) {
            return this.returnsoutheastx();
        } else if (option == 5) {
            return this.returnsouthwestx();
        } else {
            return this.returnnorthwestx();
        }
    }

    public int returnY(int option) {
        if (option == 1) {
            return this.returnnorthy();
        } else if (option == 2) {
            return this.returnsouthy();
        } else if (option == 3) {
            return this.returnnortheasty();
        } else if (option == 4) {
            return this.returnsoutheasty();
        } else if (option == 5) {
            return this.returnsouthwesty();
        } else {
            return this.returnnorthwesty();
        }
    }


    public int returnnorthx() {
        return this.x;
    }

    public int returnnorthy() {
        return this.y + 2;
    }

    public int returnsouthx() {
        return this.x;
    }

    public int returnsouthy() {
        return this.y - 2;
    }

    public int returnnortheastx() {
        return this.x + 1;
    }

    public int returnnortheasty() {
        return this.y + 1;
    }

    public int returnnorthwestx() {
        return this.x - 1;
    }

    public int returnnorthwesty() {
        return this.y - 1;
    }

    public int returnsoutheastx() {
        return this.x - 1;
    }

    public int returnsoutheasty() {
        return this.y + 1;
    }

    public int returnsouthwestx() {
        return this.x + 1;
    }

    public int returnsouthwesty() {
        return this.y - 1;
    }

    public void draw(Graphics2D g) {
        int size = this.radious / 3 * 2;
        g.setColor(Color.red);
        g.fillOval((int) (this.x * this.radious * 1.5 + Main.SIZE_CANVAS / 2) - size / 2, this.y * (int) (this.radious * Math.sqrt(3) / 2) + Main.SIZE_CANVAS / 2 + 36 - size / 2, size, size);
    }


}