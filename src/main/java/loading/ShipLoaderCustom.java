package loading;

import models.Container;
import models.Ship;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ShipLoaderCustom extends ShipLoader {
    @Override
    public void processLoading() {

        List<Container> containers = stockRoom.getOldestContainers();
        Optional<Ship> shipOptional = stockRoom.getOldest();
        if (shipOptional.isPresent()) {
            Ship ship = shipOptional.get();
            for (Container container : containers) {
                boolean loadResult = load(ship, container);
                if (loadResult) {
                    ship.addContainer(container);
                    stockRoom.removeContainer(container);
                } else if (load(ship, container.reverseContainer())) {
                    ship.addContainer(container);
                    stockRoom.removeContainer(container);


                } else {
                    container.reverseContainer();
                    break;
                }

            }
            containers.sort(Comparator.comparingInt(Container::getSize));

            for (Container container : containers) {
                boolean load = load(ship, container);
                if (load) {
                    ship.addContainer(container);
                    stockRoom.removeContainer(container);
                } else if (load(ship, container.reverseContainer())) {
                    ship.addContainer(container);
                    stockRoom.removeContainer(container);


                } else {
                    container.reverseContainer();
                    shipSender.send(ship);
                }

            }
        }
    }
}
