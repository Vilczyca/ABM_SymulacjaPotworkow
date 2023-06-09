import java.util.concurrent.*;

class Main {
    static int SIZE_CANVAS = 700;
    static ScheduledFuture<?> future;
    static ScheduledExecutorService executor;
    public static void main(String args[]) {
        executor= Executors.newScheduledThreadPool(1);

        _ControlFrame cp = new _ControlFrame();
        cp.setVisible(true);
    }
}