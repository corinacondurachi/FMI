package usevolatile;

public class Ex1 {
    private static volatile int outer; //0 by default

    public static void main(String[] args) throws InterruptedException {

        Thread rt = new Thread(new ReaderTask(), "reader");
        rt.start();
        Thread wt = new Thread(new WriterTask(), "writer");
        wt.start();

        rt.join();
        wt.join();
    }

    static class ReaderTask implements Runnable {

        @Override
        public void run() {
            int local = outer;
            while (local < 3) {
                if (local != outer) {
                    local = outer;
                    System.out.println("local value from " + Thread.currentThread().getName() + " is " + local);
                }
            }
        }
    }

    static class WriterTask implements Runnable {

        @Override
        public void run() {
            int local = outer;
            while (outer < 3) {
                System.out.println("local value from " + Thread.currentThread().getName() + " is " + local);
                outer = ++local;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
