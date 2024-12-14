package AtDe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    private boolean running = true;

    public static void main(String[] args) {
        GamePlay gamePlay = new GamePlay();
        gamePlay.main();
    }

    public void start() {
        // Tạo một ExecutorService để quản lý luồng
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Luồng chính (Game Loop)
        executor.execute(() -> gameLoop(1));
        executor.execute(() -> gameLoop(2));
        executor.execute(() -> gameLoop(3));

        executor.shutdown();
    }

    private void gameLoop(int index) {
        while (running) {
            // Xử lý logic game
            System.out.println("Game logic running..." + index);
            if (index == 2) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000); // Giả lập 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
