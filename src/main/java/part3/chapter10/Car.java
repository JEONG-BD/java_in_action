package part3.chapter10;

public class Car {
    private Color color;
    private String brand;

    public Car(Color color, String brand) {
        this.color = color;
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public Color getColor() {
        return color;
    }
}
