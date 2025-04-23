package part1.chapter02;

public class Apple {
    private int weight = 0; 
    private Color color;
    
    public Apple(int weight, Color color){
        this.weight = weight;
        this.color = color;
    }

    public Color getColor(){
        return this.color; 
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return "Apple [weight=" + weight + ", color=" + color + "]";
    }
}

