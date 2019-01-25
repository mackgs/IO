package loading;

import loading.sending.ShipSender;
import models.Container;
import models.Ship;
import singletons.StockRoom;

public abstract class ShipLoader {

    StockRoom stockRoom;
    ShipSender shipSender;


    public ShipLoader() {
        this.stockRoom = StockRoom.getStockRoom();
        shipSender = new ShipSender();
    }


    public abstract void processLoading();

    protected boolean load(Ship ship, Container container) {
        int[][] arrays = ship.getSpace();
        int[][] c1 = container.getSpace();

        outerloop:
        for (int i = 0; i <= ship.getY() - container.getX(); i++) {
            for (int j = 0; j <= ship.getX() - container.getY(); j++) {


                int result = 0; //sprawdzanie wolnego miejsca na dany konteer
                for (int m = 0; m < container.getX(); m++) {
                    for (int n = 0; n < container.getY(); n++) {
                        result = result + arrays[i + m][j + n];

                    }
                }

                //umieszczeni na statku danego kontenera jesli sie zmiensci
                if (result == 0) {  //0 oznacza ze w danym miejscu na statku  zmiesci sie kontener
                    for (int m = 0; m < container.getX(); m++) {
                        for (int n = 0; n < container.getY(); n++) {
                            arrays[i + m][j + n] = container.getId();
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
