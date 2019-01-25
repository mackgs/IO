package singletons;

import models.Container;
import models.Ship;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FileStore {

    private static FileStore fileStore;
    private final File file;
    volatile private StockRoom stockRoom;
    private BufferedReader bufferedReader;

    private FileStore(File file) {
        this.file = file;
        stockRoom = StockRoom.getStockRoom();
        initFileReader();
    }

    static public FileStore getFileStore() {

        return fileStore;
    }

    static public void setFileStore(File file) {
        fileStore = new FileStore(file);
    }


    private void initFileReader() {
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public String getNextLine() {
        String line = "";
        try {
            String result = bufferedReader.readLine();
            if (result != null && !result.isEmpty()) {

                line = convertLineToObject(result);
            }
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private String convertLineToObject(String result) {
        char c = result.charAt(0);
        switch (c) {
            case 'S': {
                String substring = result.substring(2, result.length() - 1);
                String[] split = substring.split(",");

                Ship ship = new Ship(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
                stockRoom.addShip(ship);
                return "New Ship";
            }
            case 'C': {
                Container container = getContainer(result);
                stockRoom.addContainer(container);
                return "New containers: 1";

            }
            case '[': {

                String substring = result.substring(1, result.length() - 1);

                String[] split = substring.split(";");
                List<Container> containers = new ArrayList<>();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                for (String s : split) {
                    Container container = getContainerWithTimestamp(s, timestamp);
                    containers.add(container);
                    stockRoom.addContainer(container);
                }
                return "New containers: " + containers.size();

            }
            default:
                return "";
        }
    }

    private Container getContainer(String result) {
        String substring = result.substring(2, result.length() - 1);
        String[] split = substring.split(",");
        return new Container(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
    }

    private Container getContainerWithTimestamp(String result, Timestamp timestamp) {
        String substring = result.substring(2, result.length() - 1);
        String[] split = substring.split(",");
        return new Container(Integer.valueOf(split[0]), Integer.valueOf(split[1]), timestamp);
    }

}
