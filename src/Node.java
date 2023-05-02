import java.util.Comparator;

public class Node implements Comparable<Node>{
    private int x;
    private int y;
    private Node dad;
    private char place;
    private int costNode;
    private int totalCost;


    private int f;
    private int g;
    private String direction;
    private int num;

    public void setF(int f) {
        this.f = f + this.g;
    }

    public void setG(int g) {
        this.g = g + this.costNode ;
    }

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getCost() {
        return this.costNode;
    }

    public String getDirection() {
        return direction;
    }
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.f, other.getF());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        Node other = (Node) obj;
        return this.x == other.x && this.y == other.y;
    }

    public Node(int x, int y, Node dad,String direction, int cost){
        this.x = x;
        this.y = y;
        this.dad = dad;
        this.costNode =cost;
        this.direction =direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Node getDad() {
        return dad;
    }

    public void setDad(Node dad) {
        this.dad = dad;
    }

    public boolean CheckXY(int x,int y){
        return (this.x == x && this.y==y);
    }

    public char getPlace() {
        return place;
    }

    public void setPlace(char place) {
        this.place = place;
    }


}

