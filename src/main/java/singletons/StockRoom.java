package singletons;

import models.Container;
import models.Ship;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


public class StockRoom {

    static StockRoom stockRoom;
    private List<Container> containers;
    private List<Ship> ships;

    private StockRoom() {
        ships = new ArrayList<>();
        containers = new ArrayList<>();
    }

    public static StockRoom getStockRoom() {
        if (stockRoom == null) {
            stockRoom = new StockRoom();
        }
        return stockRoom;
    }

    public void addContainer(Container container) {
        containers.add(container);
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public List<Container> getContainers() {
        return containers;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public int getShipsCount() {
        return ships.size();
    }

    public int getContainerCount() {
        return containers.size();
    }

    public List<Container> getOldestContainers() {
        Optional<Timestamp> minTimeStampOptional = getContainers().stream().map(Container::getTimestamp).min(Comparator.naturalOrder());
        if (minTimeStampOptional.isPresent()) {
            Timestamp minTimeStamp = minTimeStampOptional.get();
            return getContainers().stream().filter(container -> container.getTimestamp().equals(minTimeStamp)).sorted((o1, o2) -> o2.getSize() - o1.getSize()).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public void removeContainer(Container containerToRemove) {
        getContainers().remove(containerToRemove);
    }

    public Optional<Ship> getOldest() {

        return ships.stream().min(Comparator.comparing(Ship::getTimestamp));
    }

    public void removeShip(Ship ship) {
        getShips().remove(ship);
    }
}
