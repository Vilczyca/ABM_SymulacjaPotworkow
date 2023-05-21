import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int N = 1;
    List<Hexagon> board = new ArrayList<>();

    Board(){

    }

    Board(int N) {
        this.N = N;
    }

    public int getN() {
        return N;
    }

    //generuje planszę o kształcie sześciokąta
    void initialize(int radius){
        int columnY = -N;
        String types[] = {"basic","basic","basic", "woda", "las", "gory"};
        Random rand = new Random();
        for(int x = -N; x < N+1; x++){ //x
            for(int y = columnY; y <= Math.abs(columnY); y+=2){ //y
                this.board.add(new Hexagon(x, y, radius, types[rand.nextInt(types.length)]));
            }
            if(x < 0){
                columnY--;
            } else {
                columnY++;
            }
        }
        //System.out.println(board);
        System.out.println("Inicjalizacja tablicy dla N = " + this.N + " (" + this.board.size() + " pól)");
    }

    //rysuje mapę
    void draw(Graphics g, int size){
        for(Hexagon hex: board) {
            hex.draw(g, size);
        }
    }

}
