package workwiththreads;

public class Ex1 {
    public static void main(String[] args) {

        Thread t1 = new Thread();
        t1.start();

        MyThread myThread = new MyThread();
        myThread.start();

        // cu clasa anonima, nu prea este folosita metoda
        Thread t3 = new Thread("t3 thread") {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(this.getName() + " step " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t3.start();
    }
}
