public class Simulation {
    Board map;
    Simulation(){}

    public void createBoard(int N, int sizeCanvas){
        this.map = new Board(N); //plansza
        int countRadius = (5+(map.getN()-1)*3);
        int radius = sizeCanvas/countRadius;
        map.initialize(radius);
    }
}
