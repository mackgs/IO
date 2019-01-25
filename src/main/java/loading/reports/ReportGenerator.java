package loading.reports;

import models.Container;
import models.Ship;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class ReportGenerator extends ReportGeneratorInterface<Ship> {


    @Override
    public void generateRaport(Ship ship) {
        String fileName = "Reports/Ship_" + ship.getId() + ".txt";
        File report = new File(fileName);
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            PrintWriter writer = new PrintWriter(report, "UTF-8");
            writer.println("Ship id: " + ship.getId() + "\n");
            writer.println("Width: " + ship.getX());
            writer.println("Lenght: " + ship.getY());

            writer.println("\nSending time: " + timestamp);
            writer.println("Container on ship: " + ship.getContainers().size());
            writer.println("Free space on ship: " + ship.getFreeSpace());
            writer.println("Free space percentage:" + ship.getFreeSpacePercentage());

            writer.println("\n\nContainers on ship:");
            for (Container container : ship.getContainers()) {
                writer.println("-" + container.toString());
            }
            writer.println("\n\nSpace on Ship:");
            writer.println(ship.getSpaceString());

            writer.close();
            report.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
