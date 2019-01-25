package loading.sending;

import loading.reports.ReportGenerator;
import models.Ship;
import singletons.StockRoom;

public class ShipSender extends Sender<Ship> {


    public ShipSender() {
        this.stockRoom = StockRoom.getStockRoom();
        this.reportGenerator = new ReportGenerator();
    }

    @Override
    public void send(Ship ship) {
        reportGenerator.generateRaport(ship);
        textArea.appendText("Ship with id " + ship.getId() + " was sent\n");
        removeFromStock(ship);


    }

    @Override
    public void removeFromStock(Ship ship) {
        stockRoom.removeShip(ship);
    }


}
