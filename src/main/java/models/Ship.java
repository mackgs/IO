package models;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Ship {

    private static int count = 1;

    private int id;

    private int y;

    private int x;

    private List<Container> containers;

    private Timestamp timestamp;

    private int[][] space;


    public Ship(int y, int x) {
        this.id = count++;
        this.y = y;
        this.x = x;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        containers = new LinkedList<>();
        this.space = new int[y][x];
    }

    public int getId() {
        return id;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }


    public int[][] getSpace() {
        return space;
    }

    public void addContainer(Container container) {
        containers.add(container);
    }

    public String getSpaceString() {
        StringBuilder stringBuilder = new StringBuilder();
        int[][] space = getSpace();
        for (int[] x : space) {
//            StringBuilder iinerStringBuilder = new StringBuilder();
//            iinerStringBuilder.append("[");
//            for(int i:x){
//                iinerStringBuilder.append(i).append(",");
//            }
//            iinerStringBuilder.append("]");
//
//            stringBuilder.append(iinerStringBuilder.toString()).append("\n");
            stringBuilder.append(Arrays.toString(x)).append("\n");
        }
        return stringBuilder.toString();

    }

    public int getFreeSpace() {
        int result = 0;
        int[][] space = getSpace();
        for (int i = 0; i < getY(); i++) {
            for (int j = 0; j < getX(); j++) {
                if (space[i][j] == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public float getFreeSpacePercentage() {
        float space = getX() * getY();
        float freeSpace = getFreeSpace();
        float freeSpacePercetnage = (freeSpace / space)*100;
        return freeSpacePercetnage;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", y=" + y +
                ", x=" + x +
                ", timestamp=" + timestamp +
                '}';
    }


    public List<Container> getContainers() {
        return containers;
    }
}
