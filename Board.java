import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    //generuje planszę o podanych
    void initialize(int radius){
        int columnY = -N;
        for(int x = -N; x < N+1; x++){ //x
            for(int y = columnY; y <= Math.abs(columnY); y+=2){ //y
                //System.out.println(x + "%=" + y +": " + (x%2==y%2));
                board.add(new Hexagon(x, y, radius));
            }
            if(x < 0){
                columnY--;
            } else {
                columnY++;
            }
        }
        System.out.println(board);
        System.out.println(board.size());
    }

    void draw(Graphics g, int size){
        for(Hexagon hex: board) {
            hex.draw(g, size);
        }
    }

}
