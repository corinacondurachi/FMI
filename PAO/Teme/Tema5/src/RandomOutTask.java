import java.util.Random;

public class RandomOutTask implements Task {
    private int number;

    public RandomOutTask() {
        Random random = new Random();
        this.number = random.nextInt()%100;
    }

    @Override
    public void task() {
        System.out.println(number);
    }
}
