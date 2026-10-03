abstract class Shape {
    private static int counter = 1;
    private final String shapeId;

    Shape() {
        shapeId = "SH-" + counter++;
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        scale(factor, factor);
    }

    public abstract void scale(double xFactor, double yFactor);

    public String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        radius *= (xFactor + yFactor) / 2.0;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side *= (xFactor + yFactor) / 2.0;
    }
}

public class Main11 {

    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {

        CircleShape c = new CircleShape(5.0);
        System.out.println(c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);

        System.out.println(c.getShapeId());
        System.out.println(sq.getShapeId());

        // Shape s = new Shape(); // Compilation error
    }
}