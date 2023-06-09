import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private Board board; //plansza
    private int speed; //prędkość symulacji
    private String state = "pause";
    ScheduledFuture<?> future;

    Simulation(int speed){
        this.speed = speed;
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

    //tworzy planszę
    public void createBoard(int N, int sizeCanvas){
        this.board = new Board(N);
        int countRadius = (5+(board.getN()-1)*3);
        int radius = sizeCanvas/countRadius;
        this.board.initializeBoard(radius);
    }

    //tworzy n potworków
    public void createMonsters(int n){
        for(int i = 0; i < n; i++) {
            this.board.initializeMonster();
        }
    }

    //tworzy n jedzenia
    public void createFood(int n){
        for(int i = 0; i < n; i++) {
            this.board.initializeFood();
        }
    }

    //pojedyncza tura
    public void turn(String state){
        this.state = state;
        Runnable periodicFunction = () -> {
            //poruszanie potworków
            for (Monster monster: board.getMonsters()) {
                //System.out.print("(" + monster.getX() + ", " + monster.getY()+") -> ");
                monster.move();
                //System.out.println("(" + monster.getX() + ", " + monster.getY()+")");
            }
        };
        if(this.state == "pause"){
            Main.future.cancel(true);
        } else {
            Main.future = Main.executor.scheduleAtFixedRate(periodicFunction, this.speed, this.speed, TimeUnit.MILLISECONDS);
        }
    }
}
