public class Lucky {
    static int x = 0;
    static int count = 0;

    static class LuckyThread extends Thread {
        private static final Object lock = new Object();

        @Override
        public void run() {
            while (true) {
                int x2;
                synchronized (lock) {
                    if (x > 999_999) break;
                    x2 = x++;
                }
                if ((x2 % 10) + (x2 / 10) % 10 + (x2 / 100) % 10 == (x2 / 1000)
                        % 10 + (x2 / 10_000) % 10 + (x2 / 100_000) % 10) {
                    System.out.println(x2);
                    synchronized (lock) {
                        count++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}
