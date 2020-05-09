package workwiththreads;

public class Ex3 {
    public static void main(String[] args) throws InterruptedException {

        for (Thread.State state : Thread.State.values()) {
            System.out.println(state);
        }

        Thread t1 = new Thread(new Processor(), "processor 1");
        t1.start();
        Thread t2 = new Thread(new Processor(), "processor 2");
        t2.start();

        Thread.sleep(10 * 1000);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
    }
}
