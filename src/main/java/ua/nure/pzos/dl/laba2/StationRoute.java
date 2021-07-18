package ua.nure.pzos.dl.laba2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class StationRoute {
    private final List<Station> stations;
    private final List<Bus> buses;

    StationRoute(List<Station> stations, List<Bus> buses) {
        this.buses = buses;
        this.stations = stations;
    }

    @SneakyThrows
    public void startMovement() {
        buses.forEach(Bus::startMovement);
        boolean allBusesReachedDestination = false;
        int busesReached = 0;
        while (!allBusesReachedDestination) {
            for (Bus bus : buses) {
                if (!bus.isDestinationReached()) {
                    int nextStationIndex;
                    if (stations.indexOf(bus.getCurrentStation()) == stations.size() - 1) {
                        nextStationIndex = 0;
                    } else {
                        nextStationIndex = stations.indexOf(bus.getCurrentStation()) + 1;
                    }
                    bus.setNextStation(stations.get(nextStationIndex));
                    if (bus.isDestinationReached()) busesReached++;
                }
            }
            if (busesReached == buses.size()) allBusesReachedDestination = true;
        }
    }

    public void finishMovement() {
        for (Bus bus : buses) {
            try {
                bus.leave();
            } catch (InterruptedException e) {
                log.warn("interruption on waiting bus {}", bus, e);
            }
        }
    }

    public static void main(String[] args) {
        log.info("Movement started");
        List<Station> stations = List.of(new Station("A", 3), new Station("B", 3), new Station("C", 1), new Station("D", 4));
        List<Bus> buses = List.of(new Bus("Ikarus", stations.get(1), stations.get(3)), new Bus("Tsoi", stations.get(1), stations.get(3)), new Bus("Victor", stations.get(0), stations.get(3)));
        StationRoute stationRoute = new StationRoute(stations, buses);
        stationRoute.startMovement();
        stationRoute.finishMovement();
        log.info("Movement ended");
    }
}
