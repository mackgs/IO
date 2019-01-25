package models;


import java.sql.Timestamp;

public class Container {

    private static int count = 1;


    private int id;

    private int x;

    private int y;

    private Timestamp timestamp;

    private int[][] space;


    public Container(int x, int y) {
        this.id = count++;
        this.x = x;
        this.y = y;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.space = new int[x][y];
    }
    public Container(int x, int y,Timestamp timestamp) {
        this.id = count++;
        this.x = x;
        this.y = y;
        this.timestamp = timestamp;
        this.space = new int[x][y];
    }

    public int getId() {
        return this.id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int[][] getSpace() {
        return space;
    }

    public int getSize() {
        return x * y;
    }


    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", timestamp=" + timestamp +
                '}';
    }

    public Container reverseContainer(){
        int x=this.getX();
        int y=this.getY();
        this.setX(y);
        this.setY(x);
        return this;

    }
}
