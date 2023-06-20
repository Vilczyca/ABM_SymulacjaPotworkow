import java.util.ArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Random;
/**
 * Symulacja, zawiera informacje między innymi o jej stanie, planszy i prędkości
 */
public class Simulation {
    /**
     * Plansza
     */
    private Board board;
    /**
     * Predkosc symulacji
     */
    private int speed;
    /**
     * Licznik generacji
     */
    public int generations = 0;
    /**
     * Stan, głównie do ustalania czy symulacja jest zapauzowana czy nie
     */
    private String state = "pause";
    /**
     * Procent potworków
     */
    public int monsteprcnt;
    /**
     * Licznik młodych potworków
     */
    private int baby;
    /**
     * zlicza poczatkowe ilosci potworkow
     */
    public int desert = 0;
    /**
     * zlicza poczatkowe ilosci potworkow
     */
    public int normal = 0;
    /**
     * zlicza poczatkowe ilosci potworkow
     */
    public int mountain = 0;
    /**
     * zlicza poczatkowe ilosci potworkow
     */
    public int lake = 0;
    /**
     * zliczają aktualna ilosc potworkow na planszy, mozliwe do wyswietlenia
     */
    public int finishdesert = 0;
    /**
     * zliczają aktualna ilosc potworkow na planszy, mozliwe do wyswietlenia
     */
    public int finishnormal = 0;
    /**
     * zliczają aktualna ilosc potworkow na planszy, mozliwe do wyswietlenia
     */
    public int finishmountain = 0;
    /**
     * zliczają aktualna ilosc potworkow na planszy, mozliwe do wyswietlenia
     */
    public int finishlake = 0;

    Simulation(int speed) {
        this.speed = speed;
    }

    /**
     * settery na inty zliczające początkowe i końcowe potwory
     *
     * @param c
     */
    public void setFinishDesert(int c) {
        this.finishdesert += c;
    }

    public void setFinishNormal(int c) {
        this.finishnormal += c;
    }

    public void setFinishMountain(int c) {
        this.finishmountain += c;
    }

    public void setFinishLake(int c) {
        this.finishlake += c;
    }

    public void setNormal(int k) {
        this.normal += k;
    }

    public void setMountain(int k) {
        this.mountain += k;
    }

    public void setLake(int k) {
        this.lake += k;
    }

    public void setDesert(int k) {
        this.desert += k;
    }

    public void setmonster(int n) {
        monsteprcnt = n;
    }

    public Board getBoard() {
        return board;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getState() {
        return state;
    }

    /**
     * gettery intów zliczających ilość początkową i momentalną potworów
     */
    public int getDesert() {
        return desert;
    }

    public int getNormal() {
        return normal;
    }

    public int getMountain() {
        return mountain;
    }

    public int getLake() {
        return lake;
    }

    /**
     * tworzy plansze
     * @param N rozmiar
     * @param sizeCanvas wielkość płótna
     * @param percentage procenty potworków
     */
    public void createBoard(int N, int sizeCanvas, int[] percentage) {
        this.board = new Board(N);
        int countRadius = (5 + (board.getN() - 1) * 3);
        int radius = sizeCanvas / countRadius;
        this.board.initializeBoard(radius, percentage);
    }

    /**
     * tworzy n potworków
     *
     * @param percentage
     */
    public void createMonsters(int percentage) {
        int n = (int) (percentage / (float) 100 * this.board.getMap().size());
        //System.out.println(n);
        for (int i = 0; i < n; i++) {
            this.board.initializeMonster();
        }
    }

    /**
     * tworzy n jedzenia
     *
     * @param percentage
     */
    public void createFood(int percentage) {
        int n = (int) (percentage / (float) 100 * this.board.getMap().size());
        for (int i = 0; i < n; i++) {
            this.board.initializeFood();
        }
    }

    /**
     * zwraca hexa o zadanych parametrach, albo nullla jak nie istnieje
     */
    public Hexagon findHex(int x, int y) {
        List<Hexagon> matchingHexagons = this.board.getMap().stream()
                .filter(hex -> hex.getX() == x && hex.getY() == y)
                .collect(Collectors.toList());
        if (!matchingHexagons.isEmpty()) {
            return matchingHexagons.get(0);
        }
        return null;
    }

    /**
     * zwraca jedzenie o zadanych parametrach, albo nulla jak nie istnieje
     *
     * @param x
     * @param y
     * @return
     */
    public Food findFood(int x, int y) {
        return this.board.getFood().stream()
                .filter(food -> food.getX() == x && food.getY() == y)
                .findFirst()
                .orElse(null);
    }

    /**
     * zwraca potwora o zaanych parametrach, a nulla jak nie istnieje
     *
     * @param x
     * @param y
     * @return
     */
    public Monster findMonster(int x, int y) {
        return this.board.getMonsters().stream()
                .filter(monster -> monster.getX() == x && monster.getY() == y)
                .findFirst()
                .orElse(null);
    }

    /**
     * usuwa jedzenie z boarda
     *
     * @param x
     * @param y
     */
    public void removeFoodFromBoard(int x, int y) {
        board.removeFood(x, y);
    }

    /**
     * usuwa zadanego potwora z boarda
     *
     * @param x
     * @param y
     * @param exp
     */
    public void removeMonsterFromBoard(int x, int y, int exp) {

        board.removeMonster(x, y, exp);
    }

    /**
     * pojedyncza tura
     *
     * @param state
     */
    public void turn(String state) {
        this.state = state;
        Runnable periodicFunction = () -> {
            Random random = new Random();
            int randomNumber;
            ArrayList<Monster> removedMonsters = new ArrayList<>();
            ArrayList<Food> eatenFood = new ArrayList<>();

            /**
             * poruszaniee potworkow
             */
            for (Monster monster : board.getMonsters()) {
                randomNumber = random.nextInt(6) + 1;
                Hexagon targetHex = findHex(monster.returnX(randomNumber), monster.returnY(randomNumber)); //hex na ktorego potwor chce wejsc
                Hexagon originHex = findHex(monster.getX(), monster.getY()); //hex na ktorym stal potwor na poczatku tury
                Food fud = findFood(monster.returnX(randomNumber), monster.returnY(randomNumber)); //jedzenie ktore potwor moze chciec zjesc
                Monster oponent = findMonster(monster.returnX(randomNumber), monster.returnY(randomNumber)); //potencjalny przciwnik potwora
                if (findHex(monster.returnX(randomNumber), monster.returnY(randomNumber)) == null) {
                    monster.gainExp(3);
                    continue;
                }
                switch (monster.getType()) {
                    case "LakeMonster":
                        LakeMonster lakeMonster = (LakeMonster) monster;
                        lakeMonster.evolve();
                        /**
                         * downCast to LakeMonster
                         */
                        if (lakeMonster.evolved && !lakeMonster.sunk) {
                            /**
                             * zatapianie
                             */
                            originHex.setType("zatopiony");
                            lakeMonster.sunk();
                            /**
                             * licznik, kazdy ewoulowany monster zatapia max jedno pole (opcjonalne)
                             */
                        }
                        if (targetHex.getType().equals("zatopiony")) {
                            /**
                             * może wejśc na zatopione pola
                             */
                            lakeMonster.move(randomNumber);
                            targetHex.setEmpty(false);
                            targetHex.setTaken(true);
                            originHex.setEmpty(true);
                            originHex.setTaken(false);
                        }
                        break;
                    case "DesertMonster":
                        DesertMonster desertMonster = (DesertMonster) monster;
                        desertMonster.breed();
                        if (desertMonster.getPregnant()) {
                            /**
                             * jeśli się rozmnożył to:
                             */
                            this.baby++;
                            desertMonster.setPregnant(false);
                        }
                        break;
                }
                if (findHex(monster.returnX(randomNumber), monster.returnY(randomNumber)) == null) {
                    monster.gainExp(3);
                    continue;
                }
                if (targetHex.getType().equals("zatopiony")) {
                    /**
                     * blokada zwykłych potworów od wchodzeina na zatopione pola
                     */
                    monster.gainExp(3);
                    continue;
                }
                if (targetHex.getempty()) {
                    /**
                     * Ruszanie tylko po polach (DZIAŁA)
                     */
                    monster.move(randomNumber);
                    targetHex.setEmpty(false);
                    targetHex.setTaken(true);
                    originHex.setEmpty(true);
                    originHex.setTaken(false);
                } else if (targetHex.getFood()) {
                    /**
                     * jedzenie (DZIAŁA)
                     */
                    monster.move(randomNumber);
                    targetHex.setFood(false);
                    originHex.setEmpty(true);
                    originHex.setTaken(false);
                    if (fud == null) {
                        continue;
                    }
                    monster.gainExp(fud.getExp());
                    eatenFood.add(fud);
                } else if (targetHex.getTaken()) {
                    /**
                     * WALKA DZIAŁA
                     */
                    if (oponent == null) {
                        monster.gainExp(4);
                    } else if (monster.EXP >= oponent.EXP) {
                        originHex.setEmpty(true);
                        originHex.setTaken(false);
                        monster.gainExp(oponent.EXP);
                        removedMonsters.add(oponent);
                        monster.move(randomNumber);
                    } else {
                        originHex.setEmpty(true);
                        originHex.setTaken(false);
                        oponent.gainExp(monster.EXP);
                        removedMonsters.add(monster);
                    }
                }
            }
            for (Monster deafated : removedMonsters) {
                removeMonsterFromBoard(deafated.getX(), deafated.getY(), deafated.getEXP());
            }
            for (Food eaten : eatenFood) {
                removeFoodFromBoard(eaten.getX(), eaten.getY());
            }
            for (int i = 0; i < this.baby; ) {
                /**
                 * pętla rozmnażająca
                 */
                board.initializeDesert();
                this.baby--;
            }
            this.generations++;
        };
        if (this.state == "pause") {
            Main.future.cancel(true);
        } else {
            Main.future = Main.executor.scheduleAtFixedRate(periodicFunction, this.speed, this.speed, TimeUnit.MILLISECONDS);
        }
    }
}
