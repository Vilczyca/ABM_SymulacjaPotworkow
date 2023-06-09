import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int N = 1;
    private List<Hexagon> map = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();
    private List<Food> food = new ArrayList<>();


    Board(){

    }

    Board(int N) {
        this.N = N;
    }

    // GETTERY

    public int getN() {
        return N;
    }

    public List<Hexagon> getMap() {
        return map;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Food> getFood() {
        return food;
    }

    //METODY

    //generuje planszę o kształcie sześciokąta
    void initializeBoard(int radius){
        int columnY = -N;
        String types[] = {"basic","basic","basic", "woda", "las", "gory"};
        Random rand = new Random();
        for(int x = -N; x < N+1; x++){ //x
            for(int y = columnY; y <= Math.abs(columnY); y+=2){ //y
                this.map.add(new Hexagon(x, y, radius, types[rand.nextInt(types.length)]));
            }
            if(x < 0){
                columnY--;
            } else {
                columnY++;
            }
        }
        //System.out.println(board);
        System.out.println("! Inicjalizacja tablicy dla N = " + this.N + " (" + this.map.size() + " pól)");
    }

    //generuje potworki
    void initializeMonster(){
        Random rand = new Random();
        Hexagon choosenHex = this.map.get(rand.nextInt(this.map.size())); //losowanie hexagonu
        this.monsters.add(new Monster(choosenHex.getX(), choosenHex.getY(), choosenHex.getRadious()));

        System.out.println("-> Stworzono potworka na polu (" + choosenHex.getX() + ", " + choosenHex.getY()+")");
    }

    //generuje jedzenie - na basic może być tylko basic, na specjalistycznych może być specjalistyczne lub basic
    void initializeFood(){
        Random rand = new Random();
        Hexagon choosenHex = this.map.get(rand.nextInt(this.map.size())); //losowanie hexagonu
        String choosenType = rand.nextInt(2)==0 ? "basic" : choosenHex.getType(); //losowanie typu
        this.food.add(new Food(choosenHex.getX(), choosenHex.getY(), choosenHex.getRadious(), choosenType));

        System.out.println("-> Stworzono jedzonko na polu (" + choosenHex.getX() + ", " + choosenHex.getY()+")");
    }

    //rysuje elementy mapy
    void draw(Graphics g){
        //rysuje mapę
        for(Hexagon hex: map) {
            hex.draw(g);
        }
        //rysuje jedzenie
        for(Food foo: food) {
            foo.draw(g);
        }
        //rysuje potworki
        for(Monster monster: monsters) {
            monster.draw(g);
        }

    }
}
