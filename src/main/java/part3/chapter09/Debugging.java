package part3.chapter09;


import java.util.Arrays;
import java.util.List;

public class Debugging {

    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);
        //points.stream().map(p -> p.getX()).forEach(System.out::println);

    }

    public static class Point {

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY(){
            return y;
        }


        public void setX(int x) {
            this.x = x;
        }

        public Point moveRightBy(int x){
            return new Point(this.x + x, this.y);
        }
    }
}

