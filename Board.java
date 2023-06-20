import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Plansza, zawiera informacje o polach, potworach i jedzeniu.
 */
public class Board {
    /**
     * Rozmiar "promienia" planszy licząc od pola środkowego
     */
    private int N = 1;
    /**
     * Lista pól
     */
    private List<Hexagon> map = new ArrayList<>();
    /**
     * Lista wszystkich potworów
     */
    private List<Monster> monsters = new ArrayList<>();
    /**
     * Lista jedzenia
     */
    private List<Food> food = new ArrayList<>();

    Board(int N) {
        this.N = N;
    }

    /**
     * Getter N
     */
    public int getN() {
        return N;
    }
    /**
     * Getter listy pól
     */
    public List<Hexagon> getMap() {
        return map;
    }
    /**
     * Getter listy potworów
     */
    public List<Monster> getMonsters() {
        return monsters;
    }
    /**
     * Getter listy jedzenia
     */
    public List<Food> getFood() {
        return food;
    }

    /**
     * Usuwa zadane jedzenie z boarda
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public void removeFood(int x, int y) {
        List<Food> foodList = this.getFood();
        foodList.removeIf(foo -> foo.getX() == x && foo.getY() == y);
    }

    /**
     * Usuwa zadanego monstera z boarda
     * @param x współrzędna x
     * @param y współrzędna y
     * @param exp poziom doświadczenia
     */
    public void removeMonster(int x, int y, int exp) {
        List<Monster> monsterList = this.getMonsters();
        monsterList.removeIf(monster -> monster.getX() == x && monster.getY() == y && monster.getEXP() == exp);
    }

    /**
     * Generuje planszę o kształcie sześciokąta
     * @param radius promień okręgu na którym rozpiera się sześciokąt
     * @param percentage tablica zawierająca procent pól
     */
    void initializeBoard(int radius, int[] percentage) {
        int columnY = -N;

        String[] types = {"gory", "pustynia", "woda"};
        String[] percentageTypes = new String[100];
        int n = 0;
        for (int t = 0; t < 3; t++) {
            for (int i = 0; i < percentage[t]; i++) {
                percentageTypes[n] = types[t];
                n++;
            }
        }
        for (int i = n; i < 100; i++) {
            percentageTypes[i] = "basic";
        }
        System.out.println(n);

        Random rand = new Random();
        for (int x = -N; x < N + 1; x++) { //x
            for (int y = columnY; y <= Math.abs(columnY); y += 2) { //y
                this.map.add(new Hexagon(x, y, radius, percentageTypes[rand.nextInt(percentageTypes.length)]));
            }
            if (x < 0) {
                columnY--;
            } else {
                columnY++;
            }
        }
        System.out.println("! Inicjalizacja tablicy dla N = " + this.N + " (" + this.map.size() + " pól)");
    }

    /**
     * Generuje potworka na losowym polu
     */
    void initializeMonster() {
        Random rand = new Random();
        Hexagon choosenHex = this.map.get(rand.nextInt(this.map.size())); //losowanie hexagonu
        while (!choosenHex.getempty()) {
            choosenHex = this.map.get(rand.nextInt(this.map.size())); //losowanie hexagonu
        }
        switch (choosenHex.getType()) {
            case "woda" ->
                    this.monsters.add(new LakeMonster(choosenHex.getX(), choosenHex.getY(), choosenHex.getRadious()));
            case "pustynia" ->
                    this.monsters.add(new DesertMonster(choosenHex.getX(), choosenHex.getY(), choosenHex.getRadious()));
            case "gory" ->
                    this.monsters.add(new MountainMonster(choosenHex.getX(), choosenHex.getY(), choosenHex.getRadious()));
            default -> this.monsters.add(new Monster(choosenHex.getX(), choosenHex.getY(), choosenHex.getRadious()));
        }
        this.monsters.add(new Monster(choosenHex.getX(), choosenHex.getY(), choosenHex.getRadious()));
        choosenHex.setEmpty(false);
        choosenHex.setTaken(true);
        System.out.println("-> Stworzono potworka na polu (" + choosenHex.getX() + ", " + choosenHex.getY() + ")");
    }

    /**
     * Generuje potworka pustynngo (rozmnażanie)
     */
    void initializeDesert() {
        Random rand = new Random();
        Hexagon choosenHex = this.map.get(rand.nextInt(this.map.size())); //losowanie hexagonu
        while (!choosenHex.getempty()) {
            choosenHex = this.map.get(rand.nextInt(this.map.size())); //losowanie hexagonu
        }
        this.monsters.add(new DesertMonster(choosenHex.getX(), choosenHex.getY(), choosenHex.getRadious()));

        this.monsters.add(new Monster(choosenHex.getX(), choosenHex.getY(), choosenHex.getRadious()));
        choosenHex.setEmpty(false);
        choosenHex.setTaken(true);
        System.out.println("-> Stworzono potworka na polu (" + choosenHex.getX() + ", " + choosenHex.getY() + ")");
    }


    /**
     * Generuje jedzenie - na basic może być tylko basic, na specjalistycznych może być specjalistyczne lub basic
     */
    void initializeFood() {
        Random rand = new Random();
        Hexagon choosenHex = this.map.get(rand.nextInt(this.map.size())); //losowanie hexagonu
        while (!choosenHex.getempty()) {
            choosenHex = this.map.get(rand.nextInt(this.map.size()));
        }
        choosenHex.setFood(true);
        this.food.add(new Food(choosenHex.getX(), choosenHex.getY(), choosenHex.getRadious(), choosenHex.getType()));
        choosenHex.setEmpty(false);
        choosenHex.setFood(true);
        System.out.println("-> Stworzono jedzonko na polu (" + choosenHex.getX() + ", " + choosenHex.getY() + ")");
    }

    /**
     * Rysuje elementy planszy - pola, potworki, jedzenie
     * @param g grafika
     */
    void draw(Graphics2D g) {
        // rysuje mapę
        for (Hexagon hex : map) {
            hex.draw(g);
        }
        // rysuje jedzenie
        for (Food foo : food) {
            foo.draw(g);
        }
        // rysuje potworki
        for (Monster monster : monsters) {
            monster.draw(g);
        }

    }
}
