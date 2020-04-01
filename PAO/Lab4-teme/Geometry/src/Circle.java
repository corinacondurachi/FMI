public class Circle extends Form {
    float radius;

    public Circle() {
        this.radius = 0;
    }

    public Circle(String color, float radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public float getArea() {
        return  radius * radius * (float)Math.PI;
    }

    @Override
    public String toString() {
        return  "Cerc: " +  super.toString();
    }

    public void printCircleDimensions() {
        System.out.println("radius=" + this.radius);
    }

    @Override
    public void printDimensions() {
        printCircleDimensions();
    }
}
