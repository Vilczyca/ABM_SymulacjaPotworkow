import java.util.concurrent.*;

class Main {
    public static int SIZE_CANVAS = 700;
    public static ScheduledFuture<?> future;
    public static ScheduledExecutorService executor;

    /**
     * Główna funkcja, której pola statyczne można traktować jak stałe dla całej symulacji
     * @param args argumenty
     */
    public static void main(String[] args) {
        executor = Executors.newScheduledThreadPool(1);

        _ControlFrame cp = new _ControlFrame();
        cp.setVisible(true);
    }
}