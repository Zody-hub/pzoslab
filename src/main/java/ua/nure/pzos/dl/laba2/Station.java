package ua.nure.pzos.dl.laba2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Station {
    private final String stationName;
    private final int maxBusOnStation;
    private final List<Bus> currentBusses;

    public Station(String stationName, int maxBusOnStation) {
        this.stationName = stationName;
        this.maxBusOnStation = maxBusOnStation;
        this.currentBusses = new ArrayList<>();
    }

    public String getStationName() {
        return stationName;
    }

    public boolean addBus(Bus bus) {
        if (currentBusses.size() == maxBusOnStation) {
            return false;
        } else {
            currentBusses.add(bus);
            return true;
        }
    }

    public int getCurrentBusesCount() {
        return currentBusses.size();
    }

    public int getMaxBusOnStation() {
        return maxBusOnStation;
    }

    public void removeBus(Bus bus) {
        currentBusses.remove(bus);
    }
}
