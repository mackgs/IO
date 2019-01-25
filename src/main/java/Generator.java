import models.Container;
import models.Ship;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    public static void main(String[] args) {
        Generator generator = new Generator();
        try {
            File randomFileData = generator.randomFileData(5);
            randomFileData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File addShipToDataFile(File dataFile, Ship ship) {
        try {
            PrintWriter writer = new PrintWriter(dataFile, "UTF-8");
            writer.println(shipConverter(ship));
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return dataFile;
    }

    public File addContainerToDataFile(File dataFile, Container container) {
        try {
            PrintWriter writer = new PrintWriter(dataFile, "UTF-8");
            writer.println(containerConverter(container));
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return dataFile;
    }

    public File addContainerListToDataFile(File dataFile, List<Container> containerList) {
        try {
            PrintWriter writer = new PrintWriter(dataFile, "UTF-8");
            writer.println(containterListToString(containerList));
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return dataFile;
    }


    public File randomFileData(int numberOfships) throws FileNotFoundException, UnsupportedEncodingException {
        File dataFile = new File("generatedRandomData.txt");
        PrintWriter writer = new PrintWriter(dataFile, "UTF-8");
        for (int i = 0; i < numberOfships; i++) {
            writer.println(getRandomShipData());
            writer.println(getRandomCoinatainerListData());
            writer.println(getRandomCoinatainerListData());

        }
        writer.close();

        return dataFile;
    }


    public String shipConverter(Ship ship) {
        return "S" + "[" + ship.getX() + "," + ship.getY() + "]";
    }

    public String containerConverter(Container container) {
        return "C" + "[" + container.getX() + "," + container.getY() + "]";
    }

    public String containterListToString(List<Container> containerList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        String prefix = "";

        for (Container container : containerList) {
            stringBuilder.append(prefix);
            prefix = ";";

            stringBuilder.append(containerConverter(container));
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private String getRandomShipData() {
        Random random = new Random();
        int x = random.nextInt(100 + 1 - 50) + 50;
        int y = random.nextInt(100 + 1 - 50) + 50;
        Ship ship = new Ship(y, x);
        System.out.print(shipConverter(ship));
        return shipConverter(ship);

    }

    private String getRandomConatainerData() {
        Random random = new Random();
        int x = random.nextInt(50 + 1 - 1) + 1;
        int y = random.nextInt(50 + 1 - 1) + 1;
        Container container = new Container(y, x);
        System.out.print(containerConverter(container));
        return containerConverter(container);
    }

    private String getRandomCoinatainerListData() {
        Random random = new Random();
        List<Container> containerList = new ArrayList<>();
        for (int i = 0; i < random.nextInt(100 + 1 - 50) + 50; i++) {
            int x = random.nextInt(50 + 1 - 1) + 1;
            int y = random.nextInt(50 + 1 - 1) + 1;
            Container container = new Container(y, x);
            containerList.add(container);

        }
        return containterListToString(containerList);

    }
}
