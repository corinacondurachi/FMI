public class Stack {
    private int capacity;
    private int index = 0;
    private Task[] stack;

    public Stack() {
        this.capacity = 10;
        this.stack = new Task[capacity];
    }


    public Stack(int capacity, int index, Task[] stack) {
        this.capacity = capacity;
        this.index = index;
        this.stack = stack;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public boolean isFull() {
        return index >= capacity;
    }

    public boolean push(Task t) {
        if (isFull()) {
            return false;
        }
        stack[index++] = t;
        return true;
    }

    public Task pop() {
        if (isEmpty()) {
            return null;
        }
        index--;
        return stack[index];
    }

    public void peak() {
        if (!isEmpty()) {
            stack[index - 1].task();
        }
    }

}
