public abstract class Form {
    String color;

    public float getArea(){
        return 0;
    }

    @Override
    public String toString() {
        return  "color='" + color + "' area=" + getArea();
    }

    public Form() {
    }

    public Form(String color) {
        this.color = color;
    }

    public abstract void printDimensions();
}
