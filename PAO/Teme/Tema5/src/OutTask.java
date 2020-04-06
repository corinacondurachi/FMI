public class OutTask implements Task {

    private String message;

    public OutTask() {
        this.message = "This is a message";
    }

    @Override
    public void task() {
        System.out.println(message);
    }
}
